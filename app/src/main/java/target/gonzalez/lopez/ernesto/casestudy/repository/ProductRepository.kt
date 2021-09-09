package target.gonzalez.lopez.ernesto.casestudy.repository

import target.gonzalez.lopez.ernesto.casestudy.model.Product
import target.gonzalez.lopez.ernesto.casestudy.model.Deals
import target.gonzalez.lopez.ernesto.casestudy.model.ProductNotFoundError
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse

interface ProductRepository {
    suspend fun getDeals(): Deals
    suspend fun productDetail(id: Int): NetworkResponse<Product, ProductNotFoundError>
}