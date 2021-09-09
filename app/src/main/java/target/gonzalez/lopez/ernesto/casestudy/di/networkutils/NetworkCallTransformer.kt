package target.gonzalez.lopez.ernesto.casestudy.di.networkutils

import okhttp3.Request
import okhttp3.ResponseBody
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse
import java.io.IOException
import java.lang.Exception
import java.lang.UnsupportedOperationException

class NetworkCallTransformer<S:Any, E:Any>(
    private val delegate: Call<S>,
    private val errorConverter: Converter<ResponseBody, E>
): Call<NetworkResponse<S, E>> {
    override fun clone(): Call<NetworkResponse<S, E>> = NetworkCallTransformer(
        delegate.clone(),
        errorConverter
    )

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("Execution unsupported")
    }

    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object: Callback<S> {
            override fun onResponse(call: Call<S>, response: Response<S>) {
                val body = response.body()
                val code =  response.code()
                val error = response.errorBody()
                if(response.isSuccessful) {
                    if (body != null) {
                        callback.onResponse(
                            this@NetworkCallTransformer,
                            Response.success(NetworkResponse.Success(body))
                        )
                    } else {
                        callback.onResponse(
                            this@NetworkCallTransformer,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                } else {
                    val errorBody = when {
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex: Exception) {
                            null
                        }

                    }
                    if(errorBody != null) {
                        callback.onResponse(
                            this@NetworkCallTransformer,
                            Response.success(NetworkResponse.ApiError(errorBody, code)))
                    } else {
                        callback.onResponse(
                            this@NetworkCallTransformer,
                            Response.success(NetworkResponse.UnknownError(null)))
                    }
                }

            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                val networkResponse = when (t) {
                    is IOException -> NetworkResponse.NetworkError(t)
                    else -> NetworkResponse.UnknownError(t)
                }
                callback.onResponse(
                    this@NetworkCallTransformer,
                    Response.success(networkResponse)
                )
            }

        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun cancel() = delegate.cancel()

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}