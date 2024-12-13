import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.Timestamp
import kotlinx.coroutines.tasks.await

data class Transaction(
    val transactionId: String,
    val userId: String,
    val totalAmount: Double,
    val timestamp: Timestamp = Timestamp.now(),
    val status: String = "pending"
)

data class TransactionDetail(
    val productId: String,
    val productName: String,
    val quantity: Int,
    val price: Double
)

class TransactionManager {
    private val firestore = FirebaseFirestore.getInstance()

    // Create a new transaction
    suspend fun createTransaction(
        userId: String,
        totalAmount: Double,
        transactionDetails: List<TransactionDetail>
    ): String? {
        return try {
            // Generate a new transaction ID
            val transactionId = firestore.collection("transactions").document().id

            // Create the transaction object
            val transaction = Transaction(
                transactionId = transactionId,
                userId = userId,
                totalAmount = totalAmount
            )

            // Save the transaction document
            firestore.collection("transactions").document(transactionId).set(transaction).await()

            // Save transaction details in a sub-collection
            val transactionDetailsRef = firestore.collection("transactions")
                .document(transactionId)
                .collection("transactionDetails")

            for (detail in transactionDetails) {
                transactionDetailsRef.add(detail).await()
            }

            // Return the transaction ID
            transactionId
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
