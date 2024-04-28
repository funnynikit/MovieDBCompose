package com.sample.moviedb.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.PrivacyTip
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Settings(){

    val context = LocalContext.current
    var checked = remember {
        mutableStateOf(false)
    }

    var seekBarPosition = remember { mutableStateOf(0.0f) }
    var checkBoxState = remember {
        mutableStateOf(false)
    }

    val radioOptions = remember {
        mutableStateOf("Java")
    }

    Box(){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 70.dp)
            , horizontalAlignment = Alignment.CenterHorizontally) {
            SettingsRow(Icons.Default.AccountBalance,"Account","Security nofifications,change number"){
                Toast.makeText(context,"Account",Toast.LENGTH_SHORT).show()
            }
            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
            SettingsRow(Icons.Default.PrivacyTip,"Privacy","Block Contacts,Disappearing Messages"){

            }
            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))
            SettingsRow(Icons.Default.VerifiedUser,"Avatar","Create,edit,profile photo"){

            }
            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            mySwitchRow(checked,"Read receipts")

            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            MySeekBar(seekBarPosition)

            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            MyCheckBoxComponent(checkBoxState)

            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

            MyRadioSelection(radioOptions)

            Divider(modifier = Modifier.padding(top = 8.dp, bottom = 8.dp))

        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSettings(){
    Settings()
}

@Composable
fun MyCheckBoxComponent(checkBoxState : MutableState<Boolean>){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = "Married")
        Checkbox(checked = checkBoxState.value, onCheckedChange = { checkBoxState.value = it} )
    }
}

@Composable
fun MyRadioSelection(radioState : MutableState<String>){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp)) {
        Text(text = "Choose favourite Language : ${radioState.value.toString()}")
        Row(modifier = Modifier
            .fillMaxWidth(),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Java")
                RadioButton(selected = radioState.value == "Java", onClick = { radioState.value = "Java" })
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Kotlin")
                RadioButton(selected = radioState.value == "Kotlin", onClick = { radioState.value = "Kotlin" })
            }
        }
    }

}

@Composable
fun MySeekBar(seekBarPosition: MutableState<Float>) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Column {
            Row {
                Text(text = "Volume")
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = seekBarPosition.value.toString())
            }

            Slider(value = seekBarPosition.value, onValueChange = { seekBarPosition.value = it  })
        }
    }
}


@Composable
fun mySwitchRow(state: MutableState<Boolean>, title: String){

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp),horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        Text(text = title)
       // Spacer(modifier = Modifier.width(150.dp))
        Switch(checked = state.value, onCheckedChange = { state.value = it})
    }

}

@Composable
fun SettingsRow(icon : ImageVector, title : String, subTitle: String,onClick : () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(start = 20.dp, top = 8.dp, bottom = 8.dp, end = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(imageVector = icon, contentDescription = "")
        Spacer(modifier = Modifier.width(30.dp))
        Column {
            Text(text = title, fontSize = 16.sp)
            Text(text = subTitle, fontSize = 14.sp, maxLines = 1)
        }
    }

}