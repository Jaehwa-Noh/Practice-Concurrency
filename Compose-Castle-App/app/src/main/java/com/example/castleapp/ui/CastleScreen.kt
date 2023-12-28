package com.example.castleapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.castleapp.model.whereAreYou
import com.example.castleapp.ui.theme.CastleAppTheme

@Composable
fun CastleScreen(
    modifier: Modifier = Modifier
) {
    val castleViewModel: CastleViewModel = viewModel()
    val castleUiState by castleViewModel.uiState.collectAsState()

    if (castleUiState.isCalled) {
        LaunchedEffect(
            castleUiState.warriors
        ) {
            castleViewModel.moveToCastleWarriors()
            castleViewModel.pauseWarriors()
        }
    }

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
            onCallClick = { castleViewModel.callWarriors() },
            onPauseClick = { castleViewModel.pauseWarriors() },
            onReturnClick = { castleViewModel.returnWarriors() },
            isCalled = castleUiState.isCalled,
            knightLocation = castleUiState.warriors[0].whereAreYou,
            cavalryLocation = castleUiState.warriors[1].whereAreYou
        )
    }
}

@Composable
fun CastleButtonAndWarriorLocation(
    modifier: Modifier = Modifier,
    onCallClick: () -> Unit,
    onPauseClick: () -> Unit,
    onReturnClick: () -> Unit,
    isCalled: Boolean,
    knightLocation: Float,
    cavalryLocation: Float
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Text(
                "Knight",
                modifier = Modifier.weight(1f)
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(3f),
                progress = knightLocation
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        ) {
            Text(
                "Cavalry",
                modifier = Modifier.weight(1f)
            )
            LinearProgressIndicator(
                modifier = Modifier
                    .weight(3f),
                progress = cavalryLocation
            )
        }
        CallAndReturnAndPauseButtons(
            onCallClick = onCallClick,
            onPauseClick = onPauseClick,
            onReturnClick = onReturnClick,
            isCalled = isCalled
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
                onPauseClick()
            } else {
                onCallClick()
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