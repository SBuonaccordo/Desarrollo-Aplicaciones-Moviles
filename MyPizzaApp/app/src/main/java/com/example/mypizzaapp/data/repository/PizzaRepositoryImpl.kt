package com.example.mypizzaapp.data.repository

import com.example.mypizzaapp.R
import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.repository.PizzaRepository

class PizzaRepositoryImpl: PizzaRepository {

    private val pizza = listOf(
        Pizza("Pepperoni", 180.0, R.drawable.pepperoni),
        Pizza( "Hawaiana", 160.0, R.drawable.hawaiana),
        Pizza( "Mexicana", 190.0, R.drawable.mexicana)
    )

    override fun getPizzaOfTheDay(): Pizza {
        return pizza.random()
    }
}