package io.untungs.nutrisport.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RoleDto(
    val isAdmin: Boolean = false,
)
