package com.example.mypizzaapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mypizzaapp.data.repository.PizzaRepositoryImpl
import com.example.mypizzaapp.domain.models.Pizza
import com.example.mypizzaapp.domain.usecases.GetAllPizzasUseCase
import com.example.mypizzaapp.domain.usecases.GetPizzaOfTheDayUseCase

class PizzaViewModel: ViewModel() {

    private val getPizzaUseCase = GetPizzaOfTheDayUseCase(PizzaRepositoryImpl())
    private val getAllPizzasUseCase = GetAllPizzasUseCase(PizzaRepositoryImpl())

    var pizzaState by mutableStateOf(getPizzaUseCase.execute())
        private set
    var pizzaList by mutableStateOf(getAllPizzasUseCase.execute())
        private set

    var carItems by mutableStateOf<List<Pizza>>(emptyList())
        private set

    fun refreshPizza() {
        pizzaState = getPizzaUseCase.execute()
    }

    fun refreshPizzaList(){
        pizzaList = getAllPizzasUseCase.execute()
    }

    fun finPizzasByName(name: String?): Pizza? {
        if (name.isNullOrBlank()) return null
        return pizzaList.firstOrNull{ it.type == name}
    }

    fun addToCart(pizza: Pizza){
        cartItems = cartItems + pizza
    }

    fun clearcart(){
        cartItems = empyList()
    }

}