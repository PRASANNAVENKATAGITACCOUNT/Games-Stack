package com.example.games_app.Screens.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.games_app.R
import com.example.games_app.ui.theme.FORGET_PASSWORD_COLOR
import com.example.games_app.ui.theme.LOGINBUTTONCOLOR
import com.example.games_app.ui.theme.SIGNUPCOLOR
import com.example.games_app.ui.theme.GameAppTheme



@Composable
fun LoginScreen(loginClickListener:  (String, String)->Unit) {

    var usernameOrEmail by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var rememberMe by remember {
        mutableStateOf(false)
    }



    Column(
        Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.Cyan),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            LoginInputFields(
                email = usernameOrEmail, onEmailChange = {usernameOrEmail=it},
                password =password, onPasswordChange = {password=it}
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                    .padding(top=10.dp, start = 8.dp, end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween)
            {
                Row (
                    Modifier
                        .fillMaxWidth(0.5f)
                        .fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ){
                    Checkbox(
                        checked = rememberMe,
                        onCheckedChange = { rememberMe=it },
                    )
                    Text(
                        text = "Remember Me",
                        fontSize = 18.sp
                        )
                }
                Row(
                    Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "ForgotPassword ?",
                        Modifier.wrapContentWidth(),
                        style = TextStyle(
                            color= FORGET_PASSWORD_COLOR
                        ),
                        fontSize = 18.sp
                    )
                }

            }
            CreateAccountAndLoginSection(
                onClickLogin = {loginClickListener(usernameOrEmail,password)},
                onClickCreateAccount = {}
            )
            Column(
                Modifier.fillMaxWidth().wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalDivider(thickness = 1.dp,
                    color = Color.Black, modifier = Modifier.padding(10.dp))
                Text(text = "OR")
                SignUpOptions(iconRes = R.drawable.google_icon, signInText = "Signing In With Google") {

                }

            }
            
        }
       
    }
}


@Composable
fun SignUpOptions(@DrawableRes iconRes:Int, signInText:String,onClickSignUp:()->Unit) {
    Button(
        onClick = {onClickSignUp},
        colors = ButtonDefaults.buttonColors(
            containerColor=Color.Black,
            contentColor = Color.White
        ),
        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
        shape = RoundedCornerShape(8.dp))
    {
        Row (
            Modifier
                .fillMaxWidth()
                .height(45.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = iconRes),
                contentDescription = signInText,
                Modifier
                    .width(30.dp)
                    .size(30.dp),
                tint = Color.Unspecified
            )
            Text(
                text = signInText,
                fontSize = 18.sp
            )
        }
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginInputFields(email:String, onEmailChange: (String)->Unit,
                     password:String, onPasswordChange:(String)->Unit ) {

    var eyeIcon by remember {
        mutableStateOf(0)
    }

    val eyeResource = when(eyeIcon) {
        0 -> R.drawable.passowrd_eye_open
        else -> R.drawable.password_eye_close
    }

    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f)
            .padding(start = 8.dp, end = 8.dp)
    ) {
        Text(
            text = "Log In\n\nHere",
            fontSize = 45.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start= 10.dp)
        )
        TextField(
            value = email,
            onValueChange =onEmailChange,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(2.dp),

            label = { Text(text = "Email")},
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )

        )

        TextField(value = password, onValueChange = onPasswordChange,
            Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(2.dp),
            label = { Text(text = "Password") },
            visualTransformation = if (eyeIcon == 0 )  VisualTransformation.None else PasswordVisualTransformation() ,
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 20.sp
            ),
            trailingIcon = {
                IconButton(onClick = { eyeIcon = if(eyeIcon==0) 1  else 0  }) {
                    Icon(
                        painter = painterResource(id = eyeResource),
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}


@Composable
fun CreateAccountAndLoginSection(onClickLogin:()->Unit,onClickCreateAccount:()->Unit) {
    Spacer(modifier = Modifier.height(30.dp))
    Column(
        Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 5.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ElevatedButton(
            onClick = onClickLogin,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(55.dp)
                .padding(2.dp),
            colors = ButtonColors(LOGINBUTTONCOLOR, Color.White, Color.Blue, Color.Blue)
            ) {
            Text(text = "Login")
        }
        ElevatedButton(
            onClick = onClickCreateAccount ,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(55.dp)
                .padding(2.dp),
            colors = ButtonColors(SIGNUPCOLOR, Color.White, Color.Blue, Color.Blue)
            ) {
            Text(text = "Create Account")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    GameAppTheme {
        LoginScreen(){email,password->

        }
    }
}