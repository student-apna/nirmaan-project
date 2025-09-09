package org.example.project.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import org.example.project.presentation.screens.authentication.login.LoginScreen
import org.example.project.presentation.screens.authentication.signup.SignupScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    navcontroller: NavHostController = rememberNavController()
){
    NavHost(navcontroller, startDestination = loginScreen){
     composable<loginScreen> {
         AnimatedContent(
             targetState = loginScreen,
             transitionSpec = {
                 slideInHorizontally { it } + fadeIn() togetherWith
                         slideOutHorizontally { -it } + fadeOut()
             }
         ) {
             LoginScreen(navcontroller)
         }}
     composable<signupScreen> { SignupScreen(navcontroller) }
    }
}

@Serializable
object loginScreen
@Serializable
object signupScreen