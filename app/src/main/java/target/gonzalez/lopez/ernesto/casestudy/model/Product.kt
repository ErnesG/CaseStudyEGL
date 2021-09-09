package target.gonzalez.lopez.ernesto.casestudy.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Product(
    @Json(name = "id")
    var id: Int?,
    @Json(name = "title")
    var title: String?,
    @Json(name = "aisle")
    var aisle: String?,
    @Json(name = "description")
    var description: String?,
    @Json(name = "image_url")
    var imageUrl: String?  = "",
    @Json(name = "regular_price")
    var regularPrice: Price?,
    @Json(name = "sale_price")
    var salePrice: Price? = null
)
