package target.gonzalez.lopez.ernesto.casestudy.api

import retrofit2.http.GET
import retrofit2.http.Path
import target.gonzalez.lopez.ernesto.casestudy.model.Product
import target.gonzalez.lopez.ernesto.casestudy.model.Deals
import target.gonzalez.lopez.ernesto.casestudy.model.ProductNotFoundError
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse

interface ProductApiService {
    @GET("/deals")
    suspend fun getDeals(): Deals
    @GET("/deals/{id}")
    suspend fun dealDetail(@Path("id") id:Int): NetworkResponse<Product, ProductNotFoundError>
}