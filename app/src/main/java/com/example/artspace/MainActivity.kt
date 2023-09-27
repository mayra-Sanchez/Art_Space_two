package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
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
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onBackground
                ) {
                    ArtSpaceScreen()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    val artworks = listOf(
        R.drawable.anne , R.drawable.daryl, R.drawable.eleven, R.drawable.gloria, R.drawable.sheldon, R.drawable.serena, R.drawable.tokyo, R.drawable.monica, R.drawable.pablo, R.drawable.jughead
    )

    val titles = listOf(
        R.string.Anne, R.string.Daryl, R.string.Eleven, R.string.Gloria, R.string.Sheldon, R.string.Serena, R.string.Tokyo, R.string.Monica, R.string.Pablo, R.string.Jughead
    )

    val description = listOf(
        R.string.Anne_text, R.string.Daryl_text, R.string.Eleven_text, R.string.Gloria_text, R.string.Sheldon_text, R.string.Serena_text, R.string.Tokyo_text, R.string.Monica_text, R.string.Pablo_text, R.string.Jughead_text
    )

    var currentIndex by remember { mutableStateOf(0) }
    var currentArtwork by remember { mutableStateOf(artworks[0]) }
    var title by remember { mutableStateOf(titles[0]) }
    var texto_serie by remember { mutableStateOf(description[0]) }

    fun changeArtwork(index: Int) {
        currentArtwork = artworks[index]
        title = titles[index]
        texto_serie = description[index]
    }

    var restartButtonPressed by remember { mutableStateOf(false) }

    DisposableEffect(restartButtonPressed) {
        if (restartButtonPressed) {
            restartButtonPressed = false
        }
        onDispose { }
    }

    fun onButtonClick(isNext: Boolean) {
        val newIndex = if (isNext) (currentIndex + 1) % artworks.size else (currentIndex - 1 + artworks.size) % artworks.size
        currentIndex = newIndex
        changeArtwork(newIndex)
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = modifier.size(16.dp))
        Text(text = "Mayra Alejandra Sanchez Salinas", fontSize = 20.sp, color = Color.White)
        Spacer(modifier = modifier.size(16.dp))
        Text(text = "- 202040506 -", fontSize = 20.sp, color = Color.Cyan)
        Spacer(modifier = modifier.size(16.dp))
        Text(text = "My favorite characters", fontSize = 20.sp, color = Color.White)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkDisplay(currentArtwork = currentArtwork)
        Spacer(modifier = modifier.size(16.dp))
        ArtworkTitle(title = title, texto_serie = texto_serie)
        Spacer(modifier = modifier.size(25.dp))
        Row(
            modifier = modifier.padding(horizontal = 2.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    onClick = {
                        currentIndex = (currentIndex - 1 + artworks.size) % artworks.size
                        changeArtwork(currentIndex)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow
                    )
                ) {
                    Text(
                        text = "Previous character",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black)
                    )
                }

                Button(
                    onClick = {
                        currentIndex = (currentIndex + 1) % artworks.size
                        changeArtwork(currentIndex)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.teal_200)
                    )
                ) {
                    Text(
                        text = "Next character",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.black)
                    )
                }
            }

            IconButton(
                onClick = {
                    restartButtonPressed = true
                    currentIndex = 0
                    changeArtwork(0)
                },
                modifier = Modifier.size(60.dp),
                content = {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Restart",
                        tint = Color.Red
                    )
                }
            )

        }
    }
}

@Composable
fun ArtworkDisplay(
    modifier: Modifier = Modifier,
    @DrawableRes currentArtwork: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(currentArtwork),
            contentDescription = stringResource(id = R.string.Daryl),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(15.dp))
        )
    }
}

@Composable
fun ArtworkTitle(
    @StringRes title: Int,
    @StringRes texto_serie: Int
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Artwork title
        Text(
            text = stringResource(id = title),
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.purple_200),
            fontSize = 32.sp
        )

        // Artwork year
        Text(
            text = "— ${stringResource(id = texto_serie)} —",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = colorResource(id = R.color.white)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceScreen()
    }
}