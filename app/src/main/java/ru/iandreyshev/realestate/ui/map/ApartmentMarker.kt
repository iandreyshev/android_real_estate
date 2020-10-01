package ru.iandreyshev.realestate.ui.map

import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.domain.Position

data class ApartmentMarker(
    val id: ApartmentId,
    val isActive: Boolean,
    val cost: Int,
    val position: Position
)
