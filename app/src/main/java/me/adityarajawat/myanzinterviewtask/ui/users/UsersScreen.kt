package me.adityarajawat.myanzinterviewtask.ui.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.adityarajawat.myanzinterviewtask.data.model.User
import me.adityarajawat.myanzinterviewtask.ui.base.ShowError
import me.adityarajawat.myanzinterviewtask.ui.base.ShowLoading
import me.adityarajawat.myanzinterviewtask.ui.base.UiState
import me.adityarajawat.myanzinterviewtask.utils.AppConstant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserListRoute(viewModel : UsersViewModel , onUserClick : (User) -> Unit) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        ), title = { Text(text = AppConstant.APP_NAME) })
    }, content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            UserScreen(uiState , onUserClick = onUserClick)
        }
    })

}


@Composable
fun UserScreen(uiState: UiState<List<User>>, onUserClick: (User) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            UserList(uiState.data , onUserClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(uiState.message)
        }
    }
}

@Composable
fun UserList(users: List<User>, onUserClick: (User) -> Unit) {
    LazyColumn {
        items(users, key = {user -> user.id }) { user ->
            User(user = user , onUserClick)
        }
    }
}

@Composable
fun User(user: User , onUserClick: (User) -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onUserClick(user)
        }.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "${user.id}.",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(end = 8.dp)
        )
        TitleText(user.name)
    }

}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}