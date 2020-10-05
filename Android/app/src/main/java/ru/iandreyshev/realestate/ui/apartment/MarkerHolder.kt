package ru.iandreyshev.realestate.ui.apartment

import com.google.android.gms.maps.model.Marker
import ru.iandreyshev.realestate.ui.map.ApartmentMarker

data class MarkerHolder(
    val appMarker: ApartmentMarker,
    val mapMarker: Marker
)