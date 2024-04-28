package com.sample.moviedb.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sample.moviedb.R

@Composable
fun Profile(){

    var passwordValue = remember {
        mutableStateOf("India12345")
    }

    var mobileValue = remember {
        mutableStateOf("8218131661")
    }

    var addressValue = remember {
        mutableStateOf("Noida")
    }


    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Box(){
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally) {

            Box(){
                Image(
                    painter = painterResource(id = R.drawable.top_bcakground),
                    contentDescription = null
                )

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(painter = painterResource(id = R.drawable.profile), contentDescription = "", modifier = Modifier
                        .width(120.dp)
                        .height(120.dp))

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Nikit Kumar",color = Color.White,fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "kumar.nikit1@gmail.com", color = Color.White,fontSize = 14.sp)
                }
            }
        }

        Card(modifier = Modifier
            .fillMaxSize()
            .padding(top = 310.dp, start = 24.dp, end = 24.dp)
            .background(
                Color.White
            )) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(Color.White), horizontalArrangement = Arrangement.SpaceBetween) {

                Card(modifier = Modifier.padding(10.dp),colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )){
                    Column(modifier = Modifier
                        .width(100.dp)
                        .padding(10.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally)
                    {
                        Image(painter = painterResource(id = R.drawable.notification), contentDescription = "", modifier = Modifier
                            .width(60.dp)
                            .height(60.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Notification",color = Color.Black, fontSize = 12.sp)
                    }
                }

                Card(modifier = Modifier.padding(10.dp),colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )){
                    Column(modifier = Modifier
                        .width(100.dp)
                        .padding(10.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.payment), contentDescription = "", modifier = Modifier
                            .width(60.dp)
                            .height(60.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Payment",color = Color.Black, fontSize = 12.sp)
                    }
                }

                Card(modifier = Modifier.padding(10.dp),colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray
                )) {
                    Column(modifier = Modifier
                        .width(100.dp)
                        .padding(10.dp),verticalArrangement = Arrangement.Center,horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(id = R.drawable.settings), contentDescription = "", modifier = Modifier
                            .width(60.dp)
                            .height(60.dp))
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Settings", color = Color.Black, fontSize = 12.sp)
                    }
                }

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(Color.White)) {

                // Password
                OutlinedTextField(value = passwordValue.value, modifier = Modifier
                   .fillMaxWidth(),onValueChange = {passwordValue.value =  it}, placeholder = { Text(
                   text = "Enter Password...",
               ) },keyboardOptions = KeyboardOptions(
                   capitalization = KeyboardCapitalization.None,
                   autoCorrect = true,
                   keyboardType = KeyboardType.Text,
               ), singleLine = true)

                Spacer(modifier = Modifier.height(10.dp))

                // Mobile
                OutlinedTextField(value = mobileValue.value, modifier = Modifier
                    .fillMaxWidth(),onValueChange = {mobileValue.value =  it}, placeholder = { Text(
                    text = "Enter Mobile...",
                ) },keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ), singleLine = true)

                Spacer(modifier = Modifier.height(10.dp))


                // Address
                OutlinedTextField(value = addressValue.value, modifier = Modifier
                    .fillMaxWidth(),onValueChange = {addressValue.value =  it}, placeholder = { Text(
                    text = "Enter Address...",
                ) },keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.None,
                    autoCorrect = true,
                    keyboardType = KeyboardType.Text,
                ), singleLine = true)

                Spacer(modifier = Modifier.height(10.dp))

                // Button
                Button(onClick = {
                    val updatedPassword = passwordValue.value
                    val updatedMobile = mobileValue.value
                    val updatedAddress = addressValue.value

                    Log.d("Check","Updated Mobile : $updatedMobile")
                    Toast.makeText(context,"Update Called",Toast.LENGTH_SHORT).show()

                }, modifier = Modifier
                    .fillMaxWidth()) {
                    Text(text = "UPDATE", fontSize = 14.sp, color = Color.White)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewProfile(){
    Profile()
}