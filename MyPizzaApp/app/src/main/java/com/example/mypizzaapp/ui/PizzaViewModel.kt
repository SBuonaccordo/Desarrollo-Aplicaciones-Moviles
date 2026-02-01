package com.example.mypizzaapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mypizzaapp.data.repository.PizzaRepositoryImpl
import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.usecases.GetPizzaOfTheDayUseCase

class PizzaViewModel: ViewModel() {

    private val getPizzaUseCase = GetPizzaOfTheDayUseCase(PizzaRepositoryImpl())

    var pizzaState by mutableStateOf(getPizzaUseCase.execute())
        private set

    fun refreshPizza() {
        pizzaState = getPizzaUseCase.execute()
    }
}