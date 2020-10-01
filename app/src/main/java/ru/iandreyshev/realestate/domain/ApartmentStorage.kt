package ru.iandreyshev.realestate.domain

object ApartmentStorage {

    val apartments = listOf(
        // https://www.airbnb.com/rooms/36461288?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378608_Q2YJeCeWpK9gVUwh
        Apartment(
            id = ApartmentId("0"),
            name = "Beautiful apartment in the city center",
            details = "Красивая новая однокомнатная квартира в центре города.\n" +
                    "Для наших гостей большая двуспальная кровать и два раскладных кресла икеа.\n" +
                    "Бесплатная парковка и интернет, чай, кофе, сахар, соль, кофемашина.\n" +
                    "Есть кондиционер и необходимые бытовые приборы, индивидуальные полотенца, фен, одноразовые шампуни и гели, ватные диски и палочки.\n" +
                    "Поквартирное отопление.",
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
                position = Position(
                    lat = 56.642666,
                    lng = 47.898776,
                ),
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
                name = "Алина Орел",
                isSuper = true,
                photo = "https://sun9-23.userapi.com/c858332/v858332391/14bd5f/dKaLNSahToI.jpg"
            ),
            rating = Rating(
                average = 4.75,
                ratesCount = 214
            ),
            address = Address(
                position = Position(
                    lat = 56.638124,
                    lng = 47.898347,
                ),
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 2_200,
            photo = "https://a0.muscache.com/im/pictures/37c7e6bb-d31b-4190-bcdb-f9fb001ed494.jpg?im_w=1200",
            isLiked = false,
            details = "Однокомнатная квартира с современным дизайнерским ремонтом в стиле Loft. В 6-7 минутах ходьбы от центра города и набережной Брюгге. Всегда свежее постельное бельё, кoмплeкт пoлoтeнец, халаты, тапочки. Интepактивнoе ТV, xoлодильник, стиральная мaшинкa, утюг, гладильная доска,фен,микроволновка печь, пoсудa. Тёплый пoл, Wi-Fi, домофон.\n" +
                    "Заезд после 14-00, выезд до 12-00. Для вашего удобства предусмотрена бесконтактная передача ключей, что значительно экономит время и конфиденциальность пребывания."
        ),
        // https://www.airbnb.com/rooms/41878071?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378632_rRGzdne7d3iMmkM3
        Apartment(
            id = ApartmentId("2"),
            name = "Сталинка На Орая",
            owner = Owner(
                name = "Kathy Bates",
                isSuper = false,
                photo = "https://www.goldenglobes.com/sites/default/files/articles/cover_images/15-kathy_bates20191120_35.jpg"
            ),
            rating = Rating(
                average = 3.4,
                ratesCount = 624
            ),
            address = Address(
                position = Position(
                    lat = 56.632890,
                    lng = 47.904446,
                ),
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 800,
            photo = "https://a0.muscache.com/im/pictures/98c42a9a-6f2a-401b-b9e2-6d59f3ca4d63.jpg?im_w=1200",
            isLiked = false,
            details = "Центр, все поблизости"
        ),
        // https://www.airbnb.com/rooms/42987709?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378636_my%2FuOLN%2BF9E24nU9
        Apartment(
            id = ApartmentId("3"),
            name = "Modern studio in the center",
            owner = Owner(
                name = "Ivan Andreyshev",
                isSuper = true,
                photo = "https://sun9-22.userapi.com/c847217/v847217354/284a6/nuwroo9jPLA.jpg"
            ),
            rating = Rating(
                average = 4.1,
                ratesCount = 905
            ),
            address = Address(
                position = Position(
                    lat = 56.633315,
                    lng = 47.899554,
                ),
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 2_499,
            photo = "https://a0.muscache.com/im/pictures/8dae35ed-4599-4ffe-a052-346c3e50bd07.jpg?im_w=1440",
            isLiked = false,
            details = "Апартаменты \"Bounty\" расположены в центре Йошкар-Олы, на одной из самых оживленных улиц, (ул. Первомайская). В числе удобств круглосуточное заселение, бесплатная парковка. В апартаментах сервируется английский завтрак, с вкуснейшим кофе на выбор.\n" +
                    "Светлые апартаменты оформлены в современном стиле, в теплых тонах. В распоряжении гостей телевизор, собственная ванная комната, стиральная машина, утюг, фен, гладильная доска, сушилка. Собственная кухня со встроенной техникой, лоджия. Бесплатный Wi-Fi."
        ),
        // https://www.airbnb.com/rooms/44084494?check_in=2020-10-02&check_out=2020-10-03&source_impression_id=p3_1601378640_A2gp%2BCj3cXNfDMZi
        Apartment(
            id = ApartmentId(""),
            name = "Светлая красивая квартира вблизи вокзала",
            owner = Owner(
                name = "Stanislav Sodov",
                isSuper = true,
                photo = "https://sun9-16.userapi.com/c852028/v852028016/17792/dyqkD2nsins.jpg"
            ),
            rating = Rating(
                average = 4.8,
                ratesCount = 1539
            ),
            address = Address(
                position = Position(
                    lat = 56.633905,
                    lng = 47.911398,
                ),
                description = "Yoshkar-Ola, Respublika Mariy El, Russia"
            ),
            cost = 1_999,
            photo = "https://a0.muscache.com/im/pictures/c358d7d5-cd41-4cbf-8abb-046186787af6.jpg?im_w=1440",
            isLiked = true,
            details = "Уютная 1-к квартира в районе Вокзала/центра. Рядом Водный Дворец, Ледовый Дворец, множество остановок, торговых центров, магазинов."
        )
    )

    operator fun get(id: ApartmentId) =
        apartments.first { it.id == id }

}
