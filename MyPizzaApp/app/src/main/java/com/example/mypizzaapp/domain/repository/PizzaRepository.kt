package com.example.mypizzaapp.domain.repository

import com.example.mypizzaapp.domain.models.Pizza

interface PizzaRepository {
    fun getPizzaOfTheDay(): Pizza
}