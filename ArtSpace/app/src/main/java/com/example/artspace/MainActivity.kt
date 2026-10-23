package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artspace.ui.theme.ArtSpaceTheme
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceLayout()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceLayout(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(
            imageRes = R.drawable.panda,
            modifier = Modifier.weight(1f)
        )
        ArtworkDescriptor(
            title = R.string.panda_title,
            artist = R.string.panda_artist,
            year = R.string.panda_year
        )
        DisplayController(
            onPrevious = { },
            onNext = { }
        )
    }
}


@Composable
fun ArtworkWall(
    @DrawableRes imageRes: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.padding(vertical = 16.dp),
        shadowElevation = 8.dp
    ) {
        Image(
            painter = painterResource(imageRes),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier.padding(24.dp)
        )
    }
}

@Composable
fun ArtworkDescriptor(
    @StringRes title: Int,
    @StringRes artist: Int,
    @StringRes year: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(title),
            fontSize = 28.sp,
            fontWeight = FontWeight.Light
        )
        Text(
            text = "${stringResource(artist)} (${stringResource(year)})",
            fontWeight = FontWeight.Bold
        )
    }
}
@Composable
fun DisplayController(
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Button(onClick = onPrevious, modifier = Modifier.weight(1f)) {
            Text(stringResource(R.string.previous))
        }
        Button(onClick = onNext, modifier = Modifier.weight(1f)) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceLayout()
    }
}