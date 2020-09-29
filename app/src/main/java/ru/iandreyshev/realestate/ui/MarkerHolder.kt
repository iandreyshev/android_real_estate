package ru.iandreyshev.realestate.ui

import com.google.android.gms.maps.model.Marker

data class MarkerHolder(
    val appMarker: ApartmentMarker,
    val mapMarker: Marker
)