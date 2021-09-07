package ru.iandreyshev.realestate.data

import ru.iandreyshev.realestate.domain.Result
import kotlin.random.Random

class ApartmentsGateway {

    fun getApartments() = when (Random.nextInt(0, 5) != 0) {
        true -> Result.Success(ApartmentsProvider.apartments)
        else -> Result.Error(Random.nextInt())
    }

}