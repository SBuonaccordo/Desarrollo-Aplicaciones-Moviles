package com.example.mypizzaapp.ui

import androidx.lifecycle.ViewModel
import com.example.mypizzaapp.data.repository.PizzaRepositoryImpl
import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.usecases.GetPizzaUseCase

class PizzaViewModel: ViewModel() {
    private val getPizzaUseCase = GetPizzaUseCase( repository = PizzaRepositoryImpl())

    fun getPizzaOfTheDay(): Pizza = getPizzaUseCase.execute()
}