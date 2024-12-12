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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.security.MessageDigest
import java.util.UUID

/*
User Data
- name: String
- email: String
- password: String
- alamat : String null able

 */

class AuthenticationManager(val context: Context) {


    private val auth = FirebaseAuth.getInstance()
    private val database = Firebase.database
    private val userRef = database.getReference("users") // Referensi untuk node users


    fun createAccountWithEmail(email: String, password: String,name: String): Flow<AuthResponse> = callbackFlow {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser // Mendapatkan user yang baru terdaftar
                    val uid = user?.uid // Mendapatkan UID pengguna


                    // Membuat data pengguna di Realtime Database
                    if (uid != null) {
                        // Membuat data pengguna baru di node "users" dengan UID sebagai key
                        val userData = mapOf(
                            "email" to email,
                            "uid" to uid,
                            "name" to name,
                            "address" to null,
                            "phone" to null
                        )

                        userRef.child(uid).setValue(userData)
                            .addOnCompleteListener { dbTask ->
                                if (dbTask.isSuccessful) {
                                    trySend(AuthResponse.Success)
                                } else {
                                    trySend(AuthResponse.Error(dbTask.exception?.message ?: "Error saving user data to database"))
                                }
                            }
                    } else {
                        trySend(AuthResponse.Error("User UID is null"))
                    }
                } else {
                    trySend(AuthResponse.Error(task.exception?.message ?: "Error creating account"))
                }
            }
        awaitClose()
    }
    // Method untuk login dengan email dan password
    fun loginWithEmail(email: String, password: String): Flow<AuthResponse> = callbackFlow {
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

    // Method untuk login dengan Google
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
            val result = credentialManager.getCredential(context = context, request = request)
            val credential = result.credential

            if (credential is CustomCredential) {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                        val firebaseCredential = GoogleAuthProvider.getCredential(googleIdTokenCredential.idToken, null)
                        auth.signInWithCredential(firebaseCredential)
                            .addOnCompleteListener { it ->
                                if (it.isSuccessful) {
                                    val user = auth.currentUser
                                    val userName = user?.displayName ?: "Unknown"
                                    val userEmail = user?.email ?: "Unknown"
                                    val userPhotoUrl = user?.photoUrl?.toString() ?: ""

                                    // Menyimpan data pengguna ke Firestore
                                    saveUserDataToFirestore(userName, userEmail, userPhotoUrl)
                                    trySend(AuthResponse.Success)
                                } else {
                                    trySend(AuthResponse.Error(message = it.exception?.message ?: "Error during Firebase sign-in"))
                                }
                            }
                    } catch (e: Exception) {
                        Log.e(TAG, "signInWithGoogle: Error processing Google credentials", e)
                        trySend(AuthResponse.Error("Error processing Google credentials"))
                    }
                } else {
                    trySend(AuthResponse.Error("Invalid credential type"))
                }
            } else {
                trySend(AuthResponse.Error("Invalid credential"))
            }
        } catch (e: GetCredentialCancellationException) {
            Log.e(TAG, "signInWithGoogle: User canceled the login process", e)
            trySend(AuthResponse.Error("Login dibatalkan"))
        } catch (e: Exception) {
            Log.e(TAG, "signInWithGoogle: General error", e)
            trySend(AuthResponse.Error("Error signing in with Google"))
        }

        awaitClose()
    }

    // Method untuk menyimpan data pengguna ke Firestore
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

    // Method untuk membuat nonce
    fun createNonce(): String {
        val rawNonce = UUID.randomUUID().toString()
        val bytes = rawNonce.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }



    // Method untuk logout
    fun logout() {
        auth.signOut()
    }

    // Method untuk mengambil data pengguna yang sedang login
    fun getCurrentUser(): FirebaseUser? {
        // Mengambil data pengguna yang sedang login
        val data = auth.currentUser
        return data
    }

    // auth admin
    fun loginAdmin(email: String, password: String): Flow<AuthResponse> = callbackFlow {
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

    // create account admin lokasi name name restorant email password
    fun createAccountAdmin(email: String, password: String, location: String, restorant: String): Flow<AuthResponse> = callbackFlow {
        Log.d(TAG, "createAccountAdmin: $email, $password, $location, $restorant")

        val authTask = auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createAccountAdmin: Account created successfully")
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        val userData = hashMapOf(
                            "email" to email,
                            "location" to location,
                            "restorant" to restorant
                        )
                        val firestore = FirebaseFirestore.getInstance()
                        firestore.collection("admin").document(userId)
                            .set(userData)
                            .addOnSuccessListener {
                                Log.d(TAG, "User data saved to Firestore")
                                trySend(AuthResponse.Success).isSuccess
                            }
                            .addOnFailureListener { e ->
                                Log.e(TAG, "Error saving user data to Firestore", e)
                                trySend(AuthResponse.Error("Error saving user data: ${e.message}")).isSuccess
                            }
                    } else {
                        trySend(AuthResponse.Error("User ID is null")).isSuccess
                    }
                } else {
                    Log.e(TAG, "createAccountAdmin: Error creating account", task.exception)
                    trySend(AuthResponse.Error(task.exception?.message ?: "Error creating account")).isSuccess
                }
            }

        awaitClose {
            authTask.result?.let {
                Log.d(TAG, "CallbackFlow closed.")
            }
        }
    }

}
