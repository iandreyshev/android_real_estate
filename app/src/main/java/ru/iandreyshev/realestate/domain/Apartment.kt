package ru.iandreyshev.realestate.domain

data class Apartment(
    val id: ApartmentId,
    val name: String,
    val description: String,
    val rating: Rating,
    val isChecked: Boolean,
    val address: Address
)
