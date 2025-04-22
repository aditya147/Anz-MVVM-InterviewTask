package me.adityarajawat.myanzinterviewtask


import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import me.adityarajawat.myanzinterviewtask.data.model.User
import me.adityarajawat.myanzinterviewtask.data.repository.UsersRepository
import me.adityarajawat.myanzinterviewtask.ui.base.UiState
import me.adityarajawat.myanzinterviewtask.ui.users.UsersViewModel
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UsersViewModelTest {

    private lateinit var usersRepository: UsersRepository
    private lateinit var viewModel: UsersViewModel
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val jeramy = User(
        id = 3,
        name = "Jeramy Schuster",
        company = "Hermann Inc",
        userName = "Nickolas.Marvin",
        email = "Elisa_Adams-Bayer49@yahoo.com",
        address = "76692 Bogan Shoals",
        zip = "23831",
        state = "Louisiana",
        country = "Libyan Arab Jamahiriya",
        phone = "1-486-909-4614 x315",
        photo = "https://json-server.dev/ai-profiles/76.png"
    )

    private val dummyUsers = listOf(jeramy)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        usersRepository = mockk()
    }

    @Test
    fun uiStateEmitsSuccessWhenRepositoryReturnsData () = testScope.runTest {
        coEvery { usersRepository.getUsers() } returns flow {
            emit(dummyUsers)
        }

        viewModel = UsersViewModel(usersRepository)

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())
            advanceUntilIdle()
            assertEquals(UiState.Success(dummyUsers), awaitItem())
            cancel()
        }
    }

    @Test
    fun uiStateEmitsErrorWhenRepositoryThrows() = testScope.runTest {
        coEvery { usersRepository.getUsers() } returns flow {
            throw RuntimeException("Network error")
        }

        viewModel = UsersViewModel(usersRepository)

        viewModel.uiState.test {
            assertEquals(UiState.Loading, awaitItem())
            advanceUntilIdle()
            val errorState = awaitItem()
            assert(errorState is UiState.Error)
            cancel()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}