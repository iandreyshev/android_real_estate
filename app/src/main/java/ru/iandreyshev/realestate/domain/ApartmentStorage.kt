package ru.iandreyshev.realestate.domain

object ApartmentStorage {

    val apartments = listOf(
        // https://www.airbnb.com/rooms/36461288?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378608_Q2YJeCeWpK9gVUwh
        Apartment(
            id = ApartmentId("0"),
            name = "Beautiful apartment in the city center",
            owner = Owner(
                name = "Jack London",
                isSuper = false,
                photo = "https://i3.mybook.io/p/x256/author_photos/ab/a2/aba28aea-c467-423d-bff1-a34361591038.jpg?v2"
            ),
            rating = Rating(
                average = 5.0,
                ratesCount = 33
            ),
            address = Address(
                lat = 56.636572,
                lng = 47.902638,
                description = "Yoshkar-Ola, Mari El Republic, Russia",
            ),
            cost = 2_369,
            photo = "https://a0.muscache.com/im/pictures/9f9a17ca-0ce9-4bb1-8549-d4d372079ca0.jpg?im_w=1200",
            isLiked = true
        ),
        // https://www.airbnb.com/rooms/42056150?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378627_vPna6uA%2Bby0m%2BUgG
        Apartment(
            id = ApartmentId("1"),
            name = "Квартира Лофт",
            owner = Owner(
                name = "Rich Bitch",
                isSuper = true,
                photo = ""
            ),
            rating = Rating(
                average = 4.75,
                ratesCount = 214
            ),
            address = Address(
                lat = 56.641575,
                lng = 47.870500,
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 2_200,
            photo = "https://a0.muscache.com/im/pictures/37c7e6bb-d31b-4190-bcdb-f9fb001ed494.jpg?im_w=1200",
            isLiked = false
        ),
        // https://www.airbnb.com/rooms/41878071?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378632_rRGzdne7d3iMmkM3
        Apartment(
            id = ApartmentId("2"),
            name = "Сталинка На Орая",
            owner = Owner(
                name = "Kathy Bates",
                isSuper = false,
                photo = ""
            ),
            rating = Rating(
                average = 3.4,
                ratesCount = 624
            ),
            address = Address(
                lat = 56.6327589,
                lng = 47.9085313,
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 800,
            photo = "https://a0.muscache.com/im/pictures/98c42a9a-6f2a-401b-b9e2-6d59f3ca4d63.jpg?im_w=1200",
            isLiked = false
        ),
        // https://www.airbnb.com/rooms/42987709?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378636_my%2FuOLN%2BF9E24nU9
        Apartment(
            id = ApartmentId("3"),
            name = "Modern studio in the center",
            owner = Owner(
                name = "Ivan Andreyshev",
                isSuper = true,
                photo = ""
            ),
            rating = Rating(
                average = 4.1,
                ratesCount = 905
            ),
            address = Address(
                lat = 56.642259,
                lng = 47.883697,
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 2_499,
            photo = "https://a0.muscache.com/im/pictures/8dae35ed-4599-4ffe-a052-346c3e50bd07.jpg?im_w=1440",
            isLiked = false
        ),
        // https://www.airbnb.com/rooms/44084494?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378640_A2gp%2BCj3cXNfDMZi
        Apartment(
            id = ApartmentId(""),
            name = "Светлая красивая квартира вблизи вокзала",
            owner = Owner(
                name = "Stanislav Sodov",
                isSuper = true,
                photo = ""
            ),
            rating = Rating(
                average = 4.8,
                ratesCount = 1539
            ),
            address = Address(
                lat = 56.636666,
                lng = 47.896807,
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 1_999,
            photo = "https://a0.muscache.com/im/pictures/c358d7d5-cd41-4cbf-8abb-046186787af6.jpg?im_w=1440",
            isLiked = true
        )
    )

    operator fun get(id: ApartmentId) =
        apartments.first { it.id == id }

}
