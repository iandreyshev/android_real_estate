package ru.iandreyshev.realestate.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.iandreyshev.realestate.domain.Apartment

class ApartmentViewModel(
    private val apartment: Apartment
) : ViewModel() {

    val isLiked = MutableLiveData(false)

}