package com.sample.moviedb.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Search(){

    Box(){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)){
            CollapsableLazyColumn(
                sections = listOf(
                    com.sample.moviedb.Other.CollapsableSection(
                        title = "Fruits A",
                        rows = listOf("Apple", "Apricots", "Avocado")
                    ),
                    com.sample.moviedb.Other.CollapsableSection(
                        title = "Fruits B",
                        rows = listOf("Banana", "Blackberries", "Blueberries")
                    ),
                    com.sample.moviedb.Other.CollapsableSection(
                        title = "Fruits C",
                        rows = listOf("Cherimoya", "Cantaloupe", "Cherries", "Clementine")
                    ),
                ),
            )
        }
    }
}

@Composable
fun CollapsableLazyColumn(
    sections: List<com.sample.moviedb.Other.CollapsableSection>,
    modifier: Modifier = Modifier
) {
    val collapsedState = remember(sections) { sections.map { true }.toMutableStateList() }
    LazyColumn(modifier) {
        sections.forEachIndexed { i, dataItem ->
            val collapsed = collapsedState[i]
            item(key = "header_$i") {
                Card(modifier= Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, bottom = 10.dp, end = 10.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().background(Color.White).clickable {
                            collapsedState[i] = !collapsed
                        }
                    ) {
                        Icon(
                            Icons.Default.run {
                                if (collapsed)
                                    KeyboardArrowDown
                                else
                                    KeyboardArrowUp
                            },
                            contentDescription = "",
                            tint = Color.LightGray,
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(
                            dataItem.title,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(vertical = 10.dp)
                                .weight(1f)
                        )
                    }
                }

                Divider(modifier=Modifier.height(10.dp))
            }
            if (!collapsed) {
                items(dataItem.rows.size) { row ->
                    Card(modifier= Modifier
                        .fillMaxWidth().background(com.sample.moviedb.ui.theme.Purple80)
                        .padding(start = 10.dp,top = 10.dp, end = 10.dp, bottom = 10.dp)) {
                        Row(modifier=Modifier.fillMaxWidth().background(Color.White)) {
                            Spacer(modifier = Modifier.size(MaterialIconDimension.dp))
                            Text(
                                dataItem.rows.get(row),
                                modifier = Modifier
                                    .padding(vertical = 10.dp)
                            )
                        }
                    }

                    Divider(modifier=Modifier.height(5.dp))
                }
            }
        }
    }
}

const val MaterialIconDimension = 24f

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewSearch(){
   // Search()
}






