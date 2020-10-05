package ru.iandreyshev.realestate.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.iandreyshev.realestate.domain.Apartment

class ApartmentViewModel(
    apartment: Apartment
) : ViewModel() {

    val apartment = MutableLiveData(apartment)

    fun onToggleLike() {
        val currentValue = apartment.value ?: return
        apartment.value = currentValue.copy(isLiked = !currentValue.isLiked)
    }

}
