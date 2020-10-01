package ru.iandreyshev.realestate.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.iandreyshev.realestate.domain.*
import ru.iandreyshev.realestate.extension.Event
import ru.iandreyshev.realestate.ui.map.ApartmentMarker

class MapViewModel : ViewModel() {

    val apartments = ApartmentStorage.apartments

    val apartmentMarkers = MutableLiveData<List<ApartmentMarker>>()
    val userMarker = MutableLiveData<Position>()

    val showTargetEvent = MutableLiveData<Event<Position>>()
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
                    position = it.address.position
                )
            }
            UserPositionProvider.getUserPosition()
                .onEach { userMarker.value = it }
                .launchIn(viewModelScope)
            mIsMarkersInitialized = true
        }

        onSelectApartmentAt(mTargetPosition)
    }

    fun onSelectApartmentAt(position: Int) {
        mTargetPosition = position

        val apartment = apartments.getOrNull(position) ?: return
        showTargetEvent.value = Event(apartment.address.position)

        apartmentMarkers.value = apartmentMarkers.value?.map {
            it.copy(isActive = apartment.id == it.id)
        }
    }

    fun onOpenApartmentAt(position: Int) {
        val apartment = apartments.getOrNull(position) ?: return
        openApartment.value = Event(apartment.id)
    }

}
