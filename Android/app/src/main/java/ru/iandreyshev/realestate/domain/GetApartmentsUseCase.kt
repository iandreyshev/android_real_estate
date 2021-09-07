package ru.iandreyshev.realestate.domain

import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import ru.iandreyshev.realestate.CoroutineContexts
import ru.iandreyshev.realestate.data.ApartmentsGateway
import timber.log.Timber
import kotlin.random.Random

class GetApartmentsUseCase(
    private val gateway: ApartmentsGateway,
    private val contexts: CoroutineContexts
) {

    suspend operator fun invoke(): List<Apartment> {
        return withContext(contexts.io) {
            val delayInSec = Random.nextInt(0, 4)

            delay(timeMillis = delayInSec * 1000L)

            Timber.d("Fetch data from server. Delay is: $delayInSec seconds")

            when (val result = gateway.getApartments()) {
                is Result.Success -> {
                    result.apartments
                }
                is Result.Error -> {
                    Timber.d("Some error happened")
                    emptyList()
                }
            }
        }
    }

}