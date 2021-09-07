package ru.iandreyshev.realestate.domain

sealed class Result {
    class Success(val apartments: List<Apartment>) : Result()
    class Error(val code: Int) : Result()
}