package com.example.mamfoods

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.Firebase
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.security.MessageDigest
import java.util.UUID

class AuthenticationManager (val context: Context){
    private val auth =  Firebase.auth

    fun createAccountWithEmail(email:String , password:String): Flow<AuthResponse> = callbackFlow {

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(AuthResponse.Success)
                } else {
                    trySend(AuthResponse.Error(task.exception?.message ?: "Error creating account"))
                }
            }
        awaitClose()
    }

    fun loginWithEmail(email:String , password:String): Flow<AuthResponse> = callbackFlow {

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    trySend(AuthResponse.Success)
                } else {
                    trySend(AuthResponse.Error(task.exception?.message ?: "Error logging in"))
                }
            }
        awaitClose()
    }

    fun loginInWithGoogle(): Flow<AuthResponse> = callbackFlow {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(true)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setNonce(createNonce())
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()


        try {
            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(
                context = context,
                request =  request
            )
            val credential = result.credential
            if(credential is CustomCredential){
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        val firebaseCredential = GoogleAuthProvider
                            .getCredential(
                                googleIdTokenCredential.idToken,
                                null
                            )
                        auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener{
                                if (it.isSuccessful){
                                    trySend(AuthResponse.Success)
                                }else{
                                    trySend(
                                        AuthResponse.Error(message = it.exception?.message?:"")
                                    )
                                }
                            }
                    }catch (e:Exception){
                        Log.e(TAG, "signInWithGoogle: Error", e)
                        trySend(AuthResponse.Error("Error signing in with Google"))
                    }
                }
            }


        }catch (e:Exception){
            Log.e(TAG, "signInWithGoogle: Error", e)
            trySend(AuthResponse.Error("Error signing in with Google"))
        }
        awaitClose()
    }

    fun signInWithGoogle(): Flow<AuthResponse> = callbackFlow {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .setNonce(createNonce())
            .setAutoSelectEnabled(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()


        try {
            val credentialManager = CredentialManager.create(context)
            val result = credentialManager.getCredential(
                context = context,
                request =  request
            )
            val credential = result.credential
            if(credential is CustomCredential){
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL){
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)

                        val firebaseCredential = GoogleAuthProvider
                            .getCredential(
                                googleIdTokenCredential.idToken,
                                null
                            )
                        auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener{
                                if (it.isSuccessful){
                                    val user = auth.currentUser
                                    val userName = user?.displayName ?: "Unknown"
                                    val userEmail = user?.email ?: "Unknown"
                                    val userPhotoUrl = user?.photoUrl?.toString() ?: ""

                                    // Menyimpan data pengguna ke Firestore
                                    saveUserDataToFirestore(userName, userEmail, userPhotoUrl)

                                    trySend(AuthResponse.Success)
                                }else{
                                    trySend(
                                        AuthResponse.Error(message = it.exception?.message?:"")
                                    )
                                }
                            }
                    }catch (e:Exception){
                        Log.e(TAG, "signInWithGoogle: Error", e)
                        trySend(AuthResponse.Error("Error signing in with Google"))
                    }
                }
            }


        }catch (e:Exception){
            Log.e(TAG, "signInWithGoogle: Error", e)
            trySend(AuthResponse.Error("Error signing in with Google"))
        }catch (e: GetCredentialCancellationException) {
            Log.e(TAG, "signInWithGoogle: User canceled the login process", e)
            trySend(AuthResponse.Error("Login dibatalkan"))
        }

        awaitClose()
    }


    fun saveUserDataToFirestore(name: String, email: String, photoUrl: String) {
        val userId = auth.currentUser?.uid
        val userData = hashMapOf(
            "name" to name,
            "email" to email,
            "photoUrl" to photoUrl
        )

        // Menyimpan data pengguna ke Firestore
        val firestore = FirebaseFirestore.getInstance()
        userId?.let {
            firestore.collection("users").document(it)
                .set(userData)
                .addOnSuccessListener {
                    Log.d(TAG, "User data saved to Firestore")
                }
                .addOnFailureListener { e ->
                    Log.e(TAG, "Error saving user data to Firestore", e)
                }
        }
    }
    fun createNonce():String{
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold(""){
                str, it -> str + "%02x".format(it)
        }
    }
}

