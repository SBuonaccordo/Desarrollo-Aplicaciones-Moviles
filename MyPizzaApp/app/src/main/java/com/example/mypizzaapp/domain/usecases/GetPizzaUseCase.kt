package com.example.mypizzaapp.domain.usecases

import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.repository.PizzaRepository

class GetPizzaUseCase(private val repository: PizzaRepository) {
    fun execute(): Pizza {
        val pizza = repository.getPizzaOfTheDay()
        val discountedPrice = pizza.price * 0.9
        return pizza.copy(price = discountedPrice)
    }
}