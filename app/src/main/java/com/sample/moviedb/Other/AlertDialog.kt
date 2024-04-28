package com.sample.moviedb.Other

import android.content.Context
import android.util.Log
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState

@Composable
fun AlertDialogComponent(state: MutableState<Boolean>, type: String, onclick: OnClickHandler) {


    if (state.value){

        AlertDialog(onDismissRequest = {
            state.value = false
        }, confirmButton = { TextButton(
            onClick = { state.value = false
                if (type == "MainActivity"){
                    Log.d("Check","My Click")
                    onclick.onClick()
                }
            }) {
            Text(text = "Confirm")

        } }, title = {  Text(
            text = "Logout"
        )  }, text = {  Text(text = "are You sure want to Logout ?")  },dismissButton = {
            // in below line we are displaying
            // our text button
            TextButton(
                // adding on click listener for this button
                onClick = {
                    state.value = false

                }
            ) {
                // adding text to our button.
                Text("Dismiss")
            }
        })
    }
}