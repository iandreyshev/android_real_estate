package ru.iandreyshev.realestate.domain

data class Apartment(
    val id: ApartmentId,
    val name: String,
    val owner: Owner,
    val rating: Rating,
    val address: Address,
    val cost: Int,
    val photo: String,
    val isLiked: Boolean
)
