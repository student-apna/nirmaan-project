package org.example.project.presentation.screens.authentication.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil3.compose.AsyncImage
import io.ktor.client.call.body
import io.ktor.client.request.accept
import io.ktor.client.request.get
import io.ktor.http.ContentType
import org.example.project.data.httpClient
import org.example.project.presentation.signupScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoginScreen(navcontroller: NavHostController) {
    val backgroundColor = Color(0xFFFFE9C8)
    val loginBoxColor = Color(0xFFFFE9C8)
    val textFieldColor = Color(0xFFFFDCC8)
    val buttonGradient = Brush.linearGradient(listOf(Color(0xFFE6A6FF), Color(0xFFF7C8FF)))

    var emailText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Centers the card on any screen
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize().background(Color(0xFFFFE9C8)), contentAlignment = Alignment.Center) {
            val cardWidth: Dp = when {
                maxWidth < 400.dp -> maxWidth * 0.9f
                maxWidth < 800.dp -> 400.dp
                else -> 500.dp
            }

            Card(
                modifier = Modifier.width(cardWidth),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = loginBoxColor),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                border = BorderStroke(1.dp, Color(0xFFFFDCC8))
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val meraData = remember { mutableStateOf("not received data") }

                    LaunchedEffect(Unit){
                        meraData.value = httpClient.get("https://jsonplaceholder.typicode.com/todos/1"){
                            accept(ContentType.Application.Json)
                        }.body()
                    }


                    Text(meraData.value)
                    // --- Header ---
                    Text(
                        text = "Hello Again!",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Welcome back to our app",
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(32.dp))

                    // --- Login Title ---
                    Text(
                        text = "Login",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // --- Email & Password Fields ---
                    CustomTextField(
                        label = "Email Id",
                        value = emailText,
                        onValueChange = { emailText = it },
                        textFieldColor = textFieldColor,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    CustomTextField(
                        label = "Password",
                        value = passwordText,
                        onValueChange = { passwordText = it },
                        textFieldColor = textFieldColor,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        isPassword = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Forgot Password?",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.align(Alignment.End)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Login Button ---
                    Button(
                        onClick = { /* Handle login */ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(buttonGradient, RoundedCornerShape(10.dp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        contentPadding = PaddingValues()
                    ) {
                        Text(
                            text = "Login",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Don't have an account? Sign Up",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        modifier = Modifier.clickable
                            {
                               navcontroller.navigate(signupScreen)
                            }

                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // --- Social Login Section ---
                    Text(
                        text = "or continue with",
                        fontSize = 12.sp,
                        color = Color.Black.copy(alpha = 0.5f)
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        SocialLoginButton(
                            onClick = { /* Google login */ },
                            imageUrl = "data:image/png;base64,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",
                            contentDescription = "Google"
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        SocialLoginButton(
                            onClick = { /* Facebook login */ },
                            imageUrl = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBxITEhITERIWFRAVFRUWFxYRFhUYFRAYFREaFxcSFhMYHCghGR4lGxYVITEiJSkrLi4uFx8zODMtNygtLi0BCgoKDQ0ODw8PDysZFRkrKysrKy0rKys3KystKy0tLSsrKystKys3KystKysrKystKysrKysrKysrKy0rKystK//AABEIAOAA4AMBIgACEQEDEQH/xAAcAAEAAgMBAQEAAAAAAAAAAAAABwgBBQYEAgP/xABJEAABAwICBwQECQkGBwAAAAABAAIDBBEFIQYHEjFBUXETImGBQnKRoQgUIzJic4KisSQzNVJTsrPBwkOSk6PD4RUlVGN0g4T/xAAWAQEBAQAAAAAAAAAAAAAAAAAAAQL/xAAXEQEBAQEAAAAAAAAAAAAAAAAAAREx/9oADAMBAAIRAxEAPwCcUREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBERAREQEREBF8yPABJIAGZJyA81yuK6yMLp7h9ZG5w3thvK4HlaMHNB1iKLKzXnQN/NwVEnjsxsHvdf3LUS6/Bfu4cSObqkA+wRH8VcE1IoVj1+Z97DiBzbUgn2GIfittR686B352Coj8bMePc6/uTBKiLk8J1j4XUWDKyNrjkGzXiJO61pAM7rqo3ggEEEHcRmD0Kg+kREBERAREQEREBERAREQEREBERARYJUfaw9Z8FBtQw2mrbfNv3IMsjKRx47Az6b0HaYvjEFLGZamVsUY9J5tc8gN5PgFEGlOu83LMOhFv21QDn4thH4uPkopx7HKisl7WqldJJwvk2MfqsaMmjotctSJraY5pDV1jtqqqJJc7hrz3G+rGLNHsWrCIqgiIgIiIoVs8D0hq6M3paiSLO5aw9x3rRm7T7FrEREz6La8DcMxGEW/bU43eLoT+LT5KX8IxiCqjEtNK2WM+kw3seRHA+BzVOVssBx2oo5RLSyujfle2bZAPRew5OHVTF1cJFHurvWhBX7MM9oa3g2/cn8YieP0Tn1Ug3WVZREQEREBERAREQEREBYKyo01w6fGij+K0zvyyVty4HOmjOW165ztysTyuGs1r6zzAX0dC75cZSzg/mOcbPp8z6Pid0FON7km5OZJzJJNySeJWEW8QReqmw6eQF0UMsjRkXRxveAeRLR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",
                            contentDescription = "Facebook"
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    textFieldColor: Color,
    keyboardOptions: KeyboardOptions,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = textFieldColor,
            unfocusedContainerColor = textFieldColor,
            disabledContainerColor = textFieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = keyboardOptions,
        visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Composable
fun SocialLoginButton(
    onClick: () -> Unit,
    imageUrl: String,
    contentDescription: String
) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(60.dp)
            .background(Color.White, CircleShape)
            .clip(CircleShape)
            .border(1.dp, Color.Gray, CircleShape)
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
@Preview
fun LoginScreenPreview(navcontroller: NavHostController = rememberNavController()) {
    LoginScreen(navcontroller)
}
