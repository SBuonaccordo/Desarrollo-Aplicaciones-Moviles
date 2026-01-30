package com.example.mvvmexample.domain.usecases

import com.example.mvvmexample.data.UserRepository

class GetAgeUseCase (private val repository: UserRepository) {
    fun execute(): Int = repository.getUserAge()
}