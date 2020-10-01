package ru.iandreyshev.realestate.ui.map

import ru.iandreyshev.realestate.domain.Address
import ru.iandreyshev.realestate.domain.ApartmentId

data class ApartmentMarker(
    val id: ApartmentId,
    val isActive: Boolean,
    val cost: Int,
    val address: Address
)
