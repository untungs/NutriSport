package io.untungs.nutrisport.core.domain.model

enum class Country(
    val countryName: String,
    val dialCode: Int,
    val code: String
) {
    INDONESIA("Indonesia", 62, "ID"),
    INDIA("India", 91, "IN"),
    USA("United States", 1, "US");

    companion object {
        fun fromDialCode(dialCode: Int): Country {
            return entries.find { it.dialCode == dialCode } ?: INDONESIA
        }
    }
}
