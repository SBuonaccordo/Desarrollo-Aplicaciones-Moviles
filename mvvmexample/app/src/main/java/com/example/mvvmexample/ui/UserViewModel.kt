package com.example.mvvmexample.ui

import androidx.lifecycle.ViewModel
import com.example.mvvmexample.data.UserRepository
import com.example.mvvmexample.domain.models.User
import com.example.mvvmexample.domain.usecases.GetAgeUseCase
import com.example.mvvmexample.domain.usecases.GetUserUseCase

class UserViewModel : ViewModel() {

    private val repository = UserRepository()
    private val getUserUseCase = GetUserUseCase(repository)
    private val getAgeUseCase = GetAgeUseCase(repository)

    fun getUser() : User = getUserUseCase.execute()
    fun  getAge() : Int = getAgeUseCase.execute()
}