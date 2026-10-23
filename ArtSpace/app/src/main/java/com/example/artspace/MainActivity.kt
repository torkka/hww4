package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

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
    // NEW: номер текущей картины (1, 2 или 3)
    var currentArtwork by remember { mutableIntStateOf(1) }

    // NEW: по номеру выбираем картинку и тексты
    val imageRes = when (currentArtwork) {
        1 -> R.drawable.panda
        2 -> R.drawable.nature
        else -> R.drawable.rainbow
    }
    val titleRes = when (currentArtwork) {
        1 -> R.string.panda_title
        2 -> R.string.nature_title
        else -> R.string.rainbow_title
    }
    val artistRes = when (currentArtwork) {
        1 -> R.string.panda_artist
        2 -> R.string.nature_artist
        else -> R.string.rainbow_artist
    }
    val yearRes = when (currentArtwork) {
        1 -> R.string.panda_year
        2 -> R.string.nature_year
        else -> R.string.rainbow_year
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ArtworkWall(
            imageRes = imageRes,
            modifier = Modifier.weight(1f)
        )
        ArtworkDescriptor(
            title = titleRes,
            artist = artistRes,
            year = yearRes
        )
        // NEW: кнопки меняют номер картины по кругу
        DisplayController(
            onPrevious = {
                currentArtwork = if (currentArtwork == 1) 3 else currentArtwork - 1
            },
            onNext = {
                currentArtwork = if (currentArtwork == 3) 1 else currentArtwork + 1
            }
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