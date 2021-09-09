package target.gonzalez.lopez.ernesto.casestudy.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Deals
    (
    @Json(name = "products")
    var products: List<Product>
)