package me.adityarajawat.myanzinterviewtask

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import me.adityarajawat.myanzinterviewtask.data.model.User
import me.adityarajawat.myanzinterviewtask.ui.base.UiState
import me.adityarajawat.myanzinterviewtask.ui.users.UserScreen
import org.junit.Rule
import org.junit.Test

class UserScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun loading_whenUiStateIsLoading_isShown() {
        composeTestRule.setContent {
            UserScreen(
                uiState = UiState.Loading,
                onUserClick = {}
            )
        }

        composeTestRule
            .onNodeWithContentDescription(composeTestRule.activity.resources.getString(R.string.loading))
            .assertExists()
    }

    @Test
    fun articles_whenUiStateIsSuccess_isShown() {
        composeTestRule.setContent {
            UserScreen(
                uiState = UiState.Success(testUsers),
                onUserClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(
                testUsers[0].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()

        composeTestRule.onNode(hasScrollToNodeAction())
            .performScrollToNode(
                hasText(
                    testUsers[5].name,
                    substring = true
                )
            )

        composeTestRule
            .onNodeWithText(
                testUsers[5].name,
                substring = true
            )
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun error_whenUiStateIsError_isShown() {
        val errorMessage = "Error Message For You"

        composeTestRule.setContent {
            UserScreen(
                uiState = UiState.Error(errorMessage),
                onUserClick = {}
            )
        }

        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

}



private val testUsers = listOf(
    User(
        id = 1,
        name = "Raheem Runolfsson",
        company = "Huels - Murphy",
        userName = "Gail_Bosco",
        email = "Wellington_Powlowski9@yahoo.com",
        address = "5985 Old State Road",
        zip = "01788",
        state = "South Carolina",
        country = "Tonga",
        phone = "(904) 664-2616 x6991",
        photo = "https://json-server.dev/ai-profiles/67.png"
    ),
    User(
        id = 2,
        name = "Melyna Faker",
        company = "Wuckert Group",
        userName = "Bret84",
        email = "Faye_DAmore@hotmail.com",
        address = "59853 Edgar Route",
        zip = "23839-6443",
        state = "Louisiana",
        country = "Bahrain",
        phone = "(279) 811-8744",
        photo = "https://json-server.dev/ai-profiles/65.png"
    ),
    User(
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
    ),
    User(
        id = 4,
        name = "Abigale Wyman",
        company = "Weber, Kiehn and Swift",
        userName = "Mittie17",
        email = "Kaela82@yahoo.com",
        address = "19729 Euna Way",
        zip = "65633-7080",
        state = "Maine",
        country = "Kyrgyz Republic",
        phone = "237.708.6703 x8285",
        photo = "https://json-server.dev/ai-profiles/45.png"
    )
)

