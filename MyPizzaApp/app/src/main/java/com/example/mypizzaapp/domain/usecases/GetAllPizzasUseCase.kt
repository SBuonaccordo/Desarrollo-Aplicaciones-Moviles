package com.example.mypizzaapp.domain.usecases

import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.repository.PizzaRepository

class GetAllPizzasUseCase(private val repository: PizzaRepository) {
    fun execute(): List<Pizza> = repository.getAllPizzas()
}