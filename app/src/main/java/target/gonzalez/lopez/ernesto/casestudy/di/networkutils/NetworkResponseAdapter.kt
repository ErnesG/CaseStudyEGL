package target.gonzalez.lopez.ernesto.casestudy.di.networkutils

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse
import java.lang.reflect.Type

class NetworkResponseAdapter<S:Any, E:Any>(
    private val succssType: Type,
    private val errorBodyConverter: Converter<ResponseBody, E>
): CallAdapter<S, Call<NetworkResponse<S, E>>> {
    override fun responseType(): Type = succssType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return NetworkCallTransformer(call, errorBodyConverter)
    }
}