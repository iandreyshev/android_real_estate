package ru.iandreyshev.realestate.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.iandreyshev.realestate.domain.Address
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.domain.ApartmentStorage
import ru.iandreyshev.realestate.extension.Event

class MapViewModel : ViewModel() {

    val apartments = ApartmentStorage.apartments

    val showTargetEvent = MutableLiveData<Event<Address>>()
    val openApartment = MutableLiveData<Event<ApartmentId>>()

    private var mTargetPosition: Int = 0

    fun onMapReady() {
        onSelectApartmentAt(mTargetPosition)
    }

    fun onSelectApartmentAt(position: Int) {
        mTargetPosition = position

        val apartment = apartments.getOrNull(position) ?: return
        showTargetEvent.value = Event(apartment.address)
    }

    fun onOpenApartmentAt(position: Int) {
        val apartment = apartments.getOrNull(position) ?: return
        openApartment.value = Event(apartment.id)
    }

}
