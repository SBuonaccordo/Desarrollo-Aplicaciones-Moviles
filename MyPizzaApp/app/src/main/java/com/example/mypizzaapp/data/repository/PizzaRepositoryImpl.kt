package com.example.mypizzaapp.data.repository

import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.repository.PizzaRepository

class PizzaRepositoryImpl: PizzaRepository {
    override fun getPizzaOfTheDay(): Pizza {
        print("Obteniendo Pizza desde el servidor...")
        return Pizza(type = "Pepperoni", size = "Grande", price = 180.0)
    }
}