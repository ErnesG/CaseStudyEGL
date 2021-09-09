package target.gonzalez.lopez.ernesto.casestudy.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Price(
    @Json(name = "amount_in_cents")
    var amountInCents: Int?,
    @Json(name = "currency_symbol")
    var currencySymbol: String?,
    @Json(name = "display_string")
    var displayString: String?
)
