package ru.iandreyshev.realestate.domain

object ApartmentStorage {

    val apartments = listOf(
        Apartment(
            id = ApartmentId("0"),
            name = "iSpring",
            description = "IT-компания",
            rating = Rating(
                average = 4.75,
                ratesCount = 1337
            ),
            isChecked = true,
            address = Address(
                lat = 56.6298581,
                lng = 47.8936355,
                description = "Вознесенская ул., 110, Йошкар-Ола, Респ. Марий Эл, 424000"
            )
        ),
        Apartment(
            id = ApartmentId("1"),
            name = "Travelline",
            description = "IT-компания",
            rating = Rating(
                average = 3.75,
                ratesCount = 1337
            ),
            isChecked = true,
            address = Address(
                lat = 56.6370134,
                lng = 47.8750415,
                description = "Ленинский пр., 56А, Йошкар-Ола, Респ. Марий Эл, 424003"
            )
        ),
        Apartment(
            id = ApartmentId("2"),
            name = "Omega-R",
            description = "IT-компания",
            rating = Rating(
                average = 4.2,
                ratesCount = 1337
            ),
            isChecked = true,
            address = Address(
                lat = 56.6327589,
                lng = 47.9085313,
                description = "Воскресенский просп., 17, Йошкар-Ола, Респ. Марий Эл, 424033"
            )
        )
    )

    operator fun get(id: ApartmentId) =
        apartments.first { it.id == id }

}
