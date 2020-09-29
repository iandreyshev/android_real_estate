package ru.iandreyshev.realestate.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.iandreyshev.realestate.domain.Address
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.domain.ApartmentStorage
import ru.iandreyshev.realestate.extension.Event
import ru.iandreyshev.realestate.ui.ApartmentMarker

class MapViewModel : ViewModel() {

    val apartments = ApartmentStorage.apartments

    val apartmentMarkers = MutableLiveData<List<ApartmentMarker>>()

    val showTargetEvent = MutableLiveData<Event<Address>>()
    val openApartment = MutableLiveData<Event<ApartmentId>>()

    private var mTargetPosition: Int = 0
    private var mIsMarkersInitialized = false

    fun onMapReady() {
        if (!mIsMarkersInitialized) {
            apartmentMarkers.value = apartments.map {
                ApartmentMarker(
                    id = it.id,
                    isActive = false,
                    cost = it.cost,
                    address = it.address
                )
            }
            mIsMarkersInitialized = true
        }

        onSelectApartmentAt(mTargetPosition)
    }

    fun onSelectApartmentAt(position: Int) {
        mTargetPosition = position

        val apartment = apartments.getOrNull(position) ?: return
        showTargetEvent.value = Event(apartment.address)

        apartmentMarkers.value = apartmentMarkers.value?.map {
            it.copy(isActive = apartment.id == it.id)
        }
    }

    fun onOpenApartmentAt(position: Int) {
        val apartment = apartments.getOrNull(position) ?: return
        openApartment.value = Event(apartment.id)
    }

}
