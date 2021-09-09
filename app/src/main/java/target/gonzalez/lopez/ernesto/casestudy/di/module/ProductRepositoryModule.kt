package target.gonzalez.lopez.ernesto.casestudy.di.module

import android.annotation.SuppressLint
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import target.gonzalez.lopez.ernesto.casestudy.api.ProductApiService
import target.gonzalez.lopez.ernesto.casestudy.repository.ProductRepository
import target.gonzalez.lopez.ernesto.casestudy.repository.ProductRepositoryImpl
import javax.inject.Singleton

@Module
abstract class ProductRepositoryModule {
    @Binds
    abstract fun bindProductRepository(productRepositoryImpl: ProductRepositoryImpl): ProductRepository
    @SuppressLint("ModuleCompanionObjects")
    @Module
    companion object{
        @JvmStatic
        @Singleton
        @Provides
        fun provideProductApiService(retrofit: Retrofit): ProductApiService {
            return retrofit.create(ProductApiService::class.java)
        }
    }
}