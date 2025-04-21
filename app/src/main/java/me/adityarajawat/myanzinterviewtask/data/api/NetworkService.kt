package me.adityarajawat.myanzinterviewtask.data.api

import me.adityarajawat.myanzinterviewtask.data.model.User
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {

    @GET("users")
    suspend fun getUsers(): List<User>
}