package target.gonzalez.lopez.ernesto.casestudy.viewmodel

import android.accounts.NetworkErrorException
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import target.gonzalez.lopez.ernesto.casestudy.model.Resource
import target.gonzalez.lopez.ernesto.casestudy.model.remotewrapper.NetworkResponse
import target.gonzalez.lopez.ernesto.casestudy.repository.ProductRepository
import javax.inject.Inject

class DealsViewModel
@Inject
constructor(private val productRepository: ProductRepository): ViewModel(){
    fun getDeals() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = productRepository.getDeals()))

        } catch (exception: NetworkErrorException) {
            emit(Resource.error(data = null, msg = "Network Error"))
        }
    }

    fun getProductDetail(productId:Int) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            when(val productDetailResponse = productRepository.productDetail(productId)) {
                is NetworkResponse.Success -> {
                    emit(Resource.success(productDetailResponse.body))
                }
                is NetworkResponse.ApiError -> {
                    if(productDetailResponse.code == 404){
                        emit(Resource.error(msg = "API-ERR", data = productDetailResponse.body))
                    } else {
                        emit(Resource.error("API->Generic", data = null))
                    }

                }
                is NetworkResponse.NetworkError,
                is NetworkResponse.UnknownError -> {
                    emit(Resource.error(msg = "Something went wrong", data = null))
                }
            }

        } catch (exception: NetworkErrorException) {
            emit(Resource.error(msg = "Unable to fetch user", data = null))
        }
    }
}