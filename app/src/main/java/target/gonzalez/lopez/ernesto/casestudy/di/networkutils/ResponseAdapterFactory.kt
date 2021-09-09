package target.gonzalez.lopez.ernesto.casestudy.di.networkutils

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ResponseAdapterFactory: CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        // for coroutines, the response type is wrapped in Call
        if (Call::class.java != getRawType(returnType)) {
            return null
        }
        check(returnType is ParameterizedType) {
            "return type must be parameterized  as Call<NetworkResponse>"
        }
        // gather the response type inside Call type
        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkResponse::class.java) {
            return null
        }
        // the response type for the search product api, should be parametrized
        check(responseType is ParameterizedType) {
            "This response must be parametrized"
        }
        val succesBodyType = getParameterUpperBound(0, responseType)
        val errorBodyType = getParameterUpperBound(1, responseType)
        val errorConverter = retrofit.nextResponseBodyConverter<Any>(
            null,
            errorBodyType,
            annotations
        )
        return NetworkResponseAdapter<Any, Any>(succesBodyType, errorConverter)
    }
}