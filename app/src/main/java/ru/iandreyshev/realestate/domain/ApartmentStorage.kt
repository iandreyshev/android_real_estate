package ru.iandreyshev.realestate.domain

object ApartmentStorage {

    private val mApartments = listOf<Apartment>()

    operator fun get(id: ApartmentId) =
        mApartments.first { it.id == id }

}
