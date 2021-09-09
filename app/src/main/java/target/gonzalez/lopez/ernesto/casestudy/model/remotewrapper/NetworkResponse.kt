package target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper

import java.io.IOException

sealed class NetworkResponse<out T: Any, out S:Any> {
    data class Success<T:Any>(val body:T):NetworkResponse<T,Nothing>()
    data class ApiError<S:Any>(val body:S, val code:Int): NetworkResponse<Nothing, S>()
    data class NetworkError(val error: IOException): NetworkResponse<Nothing,Nothing>()
    data class UnknownError(val error:Throwable?): NetworkResponse<Nothing,Nothing>()
}
