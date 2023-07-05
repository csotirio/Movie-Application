package com.movieApplication.ui.splash.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.movieApplication.R
import com.movieApplication.ui.general.BigText
import com.movieApplication.ui.theme.MovieApplicationTheme

internal typealias NavigateToLobby = () -> Unit

@Composable
fun SplashScreen(
    navigateToLobby: NavigateToLobby
) {
    SplashContent(navigateToLobby = navigateToLobby)
}

@Composable
fun SplashContent(
    navigateToLobby: NavigateToLobby
) {
    var state by remember {
        mutableStateOf(false)
    }

    var animationFinished by remember {
        mutableStateOf(false)
    }

    val alphaAnim by animateFloatAsState(
        targetValue = if (state) 1f else 0f,
        animationSpec = tween(durationMillis = 3000),
        finishedListener = {
            animationFinished = true
        }
    )

    LaunchedEffect(key1 = animationFinished) {
        if (animationFinished) {
            navigateToLobby()
        }
    }

    LaunchedEffect(key1 = Unit) {
        state = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f)
                .alpha(alpha = alphaAnim),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.empty)
        )
        BigText(
            modifier = Modifier.alpha(alpha = alphaAnim),
            text = stringResource(id = R.string.splash_title)
        )
    }
}

@Composable
@Preview
fun SplashScreenPreview() {
    MovieApplicationTheme() {
        SplashScreen(
            {}
        )
    }
}