package io.untungs.nutrisport.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val title: String,
    val description: String,
    val thumbnail: String,
    val category: String,
    val flavors: List<String>? = null,
    val weight: Int? = null,
    val price: Double,
    val isNew: Boolean = false,
    val isPopular: Boolean = false,
    val isDiscounted: Boolean = false
)
