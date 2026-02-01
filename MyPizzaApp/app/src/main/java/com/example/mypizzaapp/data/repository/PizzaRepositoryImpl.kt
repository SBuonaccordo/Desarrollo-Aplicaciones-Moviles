package com.example.mypizzaapp.data.repository

import com.example.mypizzaapp.R
import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.repository.PizzaRepository

class PizzaRepositoryImpl: PizzaRepository {

    private val pizzas = listOf(
        Pizza("Pepperoni", 180.0, R.drawable.pepperoni),
        Pizza( "Hawaiana", 160.0, R.drawable.hawaiana),
        Pizza( "Mexicana", 190.0, R.drawable.mexicana),
        Pizza("Pepperoni", 180.0, R.drawable.pepperoni),
        Pizza( "Hawaiana", 160.0, R.drawable.hawaiana),
        Pizza("Pepperoni", 180.0, R.drawable.pepperoni),
        Pizza( "Hawaiana", 160.0, R.drawable.hawaiana),
        Pizza("Pepperoni", 180.0, R.drawable.pepperoni),
        Pizza( "Hawaiana", 160.0, R.drawable.hawaiana)
    )

    override fun getPizzaOfTheDay(): Pizza {
        return pizzas.random()
    }

    override fun getAllPizzas(): List<Pizza> {
        return pizzas
    }
}