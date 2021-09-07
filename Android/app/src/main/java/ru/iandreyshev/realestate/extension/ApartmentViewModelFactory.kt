package ru.iandreyshev.realestate.extension

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.iandreyshev.realestate.domain.ApartmentId
import ru.iandreyshev.realestate.data.ApartmentsProvider
import ru.iandreyshev.realestate.presentation.ApartmentViewModel

class ApartmentViewModelFactory(
    private val id: ApartmentId
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ApartmentViewModel(ApartmentsProvider[id]) as T
    }

}