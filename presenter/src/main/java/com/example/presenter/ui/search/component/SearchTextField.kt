package com.example.presenter.ui.search.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.presenter.R

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var inputString by remember { mutableStateOf("") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        OutlinedTextField(value = inputString, onValueChange = {
            inputString = it
        }, modifier = Modifier.padding(10.dp))
        Button(onClick = {
            if (inputString.isNotEmpty()) {
                onSearch(inputString)
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_search_24),
                tint = Color.White,
                contentDescription = ""
            )
        }
    }
}