package ru.iandreyshev.realestate.extension

import com.google.android.gms.maps.model.LatLng
import ru.iandreyshev.realestate.domain.Position

fun Position.latLng() = LatLng(lat, lng)
