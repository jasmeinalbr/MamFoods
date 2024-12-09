import com.example.mamfoods.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response

abstract class BaseRepository {
    protected fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Flow<NetworkResult<T & Any>> = flow {
        emit(NetworkResult.Loading())
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    emit(NetworkResult.Success(it))
                } ?: emit(NetworkResult.Error("Response body is null"))
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                emit(NetworkResult.Error(errorBody))
            }
        } catch (e: Exception) {
            emit(NetworkResult.Error(e.localizedMessage ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)
}