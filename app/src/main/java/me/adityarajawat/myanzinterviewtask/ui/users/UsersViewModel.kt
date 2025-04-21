package me.adityarajawat.myanzinterviewtask.ui.users

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import me.adityarajawat.myanzinterviewtask.data.model.User
import me.adityarajawat.myanzinterviewtask.data.repository.UsersRepository
import me.adityarajawat.myanzinterviewtask.ui.base.UiState
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(private val usersRepository: UsersRepository) : ViewModel(){

    private val _uiState = MutableStateFlow<UiState<List<User>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<User>>> = _uiState

    // Shared user for detail screen
    private val _selectedUser = MutableStateFlow<User?>(null)
    val selectedUser: StateFlow<User?> = _selectedUser

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            usersRepository.getUsers()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    // Set the user for detail screen
    fun selectUser(user: User) {
        _selectedUser.value = user
    }

    // Optionally, clear selected user
    fun clearSelectedUser() {
        _selectedUser.value = null
    }

}