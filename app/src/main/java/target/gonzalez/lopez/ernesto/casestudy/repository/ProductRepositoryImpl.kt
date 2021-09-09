package target.gonzalez.lopez.ernesto.casestudy.repository

import target.gonzalez.lopez.ernesto.casestudy.api.ProductApiService
import target.gonzalez.lopez.ernesto.casestudy.model.Product
import target.gonzalez.lopez.ernesto.casestudy.model.Deals
import target.gonzalez.lopez.ernesto.casestudy.model.ProductNotFoundError
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse
import javax.inject.Inject

class ProductRepositoryImpl
@Inject
constructor(private val productApiService: ProductApiService) : ProductRepository {
    override suspend fun getDeals(): Deals = productApiService.getDeals()

    override suspend fun productDetail(id: Int): NetworkResponse<Product, ProductNotFoundError> =
        productApiService.dealDetail(id)
}