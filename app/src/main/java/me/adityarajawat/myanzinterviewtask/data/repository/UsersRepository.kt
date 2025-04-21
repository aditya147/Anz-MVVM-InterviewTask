package me.adityarajawat.myanzinterviewtask.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import me.adityarajawat.myanzinterviewtask.data.api.NetworkService
import me.adityarajawat.myanzinterviewtask.data.model.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UsersRepository @Inject constructor(private val networkService: NetworkService) {

    fun getUsers(): Flow<List<User>> {
        return flow {
            emit(networkService.getUsers())
        }
    }
}