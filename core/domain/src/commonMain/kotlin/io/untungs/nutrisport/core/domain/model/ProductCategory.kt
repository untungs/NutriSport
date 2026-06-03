package io.untungs.nutrisport.core.domain.model

enum class ProductCategory(
    val title: String,
    val isConsumable: Boolean,
    val color: Long
) {
    Protein("Protein", true, 0xFFFFC738),
    Creatine("Creatine", true, 0xFF38B3FF),
    PreWorkout("Pre-Workout", true, 0xFF19D109),
    Gainers("Gainers", true, 0xFF8E5EFF),
    Accessories("Accessories", false, 0xFFFF5E60);
}
