package io.untungs.nutrisport.core.domain.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Customer(
    val id: String,
    val displayName: String,
    val email: String,
    val city: String? = null,
    val postalCode: Int? = null,
    val address: String? = null,
    val phoneNumber: PhoneNumber? = null,
    val cart: List<CartItem> = emptyList(),
    @Transient
    val isAdmin: Boolean = false
)

@Serializable
data class PhoneNumber(
    val dialCode: Int,
    val number: String
)
