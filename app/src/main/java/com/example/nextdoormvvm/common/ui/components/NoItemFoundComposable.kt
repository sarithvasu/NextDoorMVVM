package com.example.nextdoormvvm.common.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.nextdoormvvm.R

@Composable
fun NoItemFound() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_mood_bad),
            contentDescription = null,
            modifier = Modifier.size(96.dp)
        )
        Text(
            text = stringResource(id = R.string.no_item_found),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}
