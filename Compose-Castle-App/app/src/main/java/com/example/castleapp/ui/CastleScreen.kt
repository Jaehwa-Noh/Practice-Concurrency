package com.example.castleapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.castleapp.ui.theme.CastleAppTheme

@Composable
fun CastleScreen(
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            "Great Castle",
            style = MaterialTheme.typography.displaySmall
        )
        CastleButtonAndWarriorLocation(
            onCallClick = { },
            onPauseClick = { },
            onReturnClick = { }
        )
    }
}

@Composable
fun CastleButtonAndWarriorLocation(
    modifier: Modifier = Modifier,
    onCallClick: () -> Unit,
    onPauseClick: () -> Unit,
    onReturnClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Knight",
                modifier = Modifier.weight(1f)
            )
            LinearProgressIndicator(
                modifier = Modifier.weight(3f),
                progress = 0.5f
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Cavalry",
                modifier = Modifier.weight(1f)
            )
            LinearProgressIndicator(
                modifier = Modifier.weight(3f),
                progress = 0.5f
            )
        }
        CallAndReturnAndPauseButtons(
            onCallClick = onCallClick,
            onPauseClick = onPauseClick,
            onReturnClick = onReturnClick,
            isCalled = false
        )
    }
}

@Composable
fun CallAndReturnAndPauseButtons(
    modifier: Modifier = Modifier,
    onCallClick: () -> Unit,
    onPauseClick: () -> Unit,
    onReturnClick: () -> Unit,
    isCalled: Boolean
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            if (isCalled) {
                onCallClick()
            } else {
                onPauseClick()
            }
        }) {
            if (isCalled) {
                Text("Pause")
            } else {
                Text("Call")
            }
        }
        Button(onClick = onReturnClick) {
            Text("Return")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CastleAppTheme {
        CastleScreen()
    }
}