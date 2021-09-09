package target.gonzalez.lopez.ernesto.casestudy.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductNotFoundError(
    @Json(name = "message")
    var message: String,
    @Json(name = "code")
    var code:String
)