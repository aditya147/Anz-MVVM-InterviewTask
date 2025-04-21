package me.adityarajawat.myanzinterviewtask.ui.base

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.adityarajawat.myanzinterviewtask.ui.users.UserDetailScreen
import me.adityarajawat.myanzinterviewtask.ui.users.UserListRoute
import me.adityarajawat.myanzinterviewtask.ui.users.UsersViewModel

sealed class Route(val name: String) {
    object UserList : Route("UserList")
    object UserDetail : Route("UserDetail")
}

@Composable
fun UserNavHost() {

    val navController = rememberNavController()
    val viewModel: UsersViewModel = hiltViewModel()


    NavHost(
        navController = navController,
        startDestination = Route.UserList.name
    ) {
        composable(route = Route.UserList.name) {
            UserListRoute(viewModel = viewModel,
                onUserClick = { user ->
                    viewModel.selectUser(user)
                    navController.navigate(Route.UserDetail.name)
                }
            )

        }
        composable(route = Route.UserDetail.name) {
            val user = viewModel.selectedUser.collectAsState().value
            if (user != null) {
                UserDetailScreen(user)
            } else {
                Text("User data not available.")
            }
        }

    }
}