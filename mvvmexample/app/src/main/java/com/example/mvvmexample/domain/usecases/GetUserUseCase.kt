package com.example.mvvmexample.domain.usecases

import com.example.mvvmexample.data.UserRepository
import com.example.mvvmexample.domain.models.User

class GetUserUseCase (private val repository: UserRepository) {
    fun execute(): User = User( name = repository.getUserName(), age = repository.getUserAge())
}