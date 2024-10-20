package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val artPieces = listOf(
                        ArtPiece("Still Life of Blue Rose", "Owen Scott", 2021, R.drawable.capture1),
                        ArtPiece("Sunset Over the Sea", "Jane Doe", 2022, R.drawable.capture2)
                    )
                    ArtSpaceScreen(artList = artPieces)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(artList: List<ArtPiece>) {
    var currentArtIndex by remember { mutableStateOf(0) }
    val currentArt = artList[currentArtIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(2f)
                .padding(16.dp)
                .border(4.dp, Color.White)
                .padding(8.dp)
        ) {
            Image(
                painter = painterResource(id = currentArt.imageResource),
                contentDescription = currentArt.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }


        Surface(
            color = Color.LightGray,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .weight(1f)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = currentArt.title,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${currentArt.artist} (${currentArt.year})",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp
                )
            }
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                if (currentArtIndex > 0) currentArtIndex--
            }) {
                Text("Previous")
            }

            Button(onClick = {
                if (currentArtIndex < artList.size - 1) currentArtIndex++
            }) {
                Text("Next")
            }
        }
    }
}

data class ArtPiece(
    val title: String,
    val artist: String,
    val year: Int,
    val imageResource: Int
)

@Preview
@Composable
fun PreviewArtSpace() {
    val artPieces = listOf(
        ArtPiece("Still Life of Blue Rose", "Owen Scott", 2021, R.drawable.capture1),
        ArtPiece("Sunset Over the Sea", "Jane Doe", 2022, R.drawable.capture2)
    )
    ArtSpaceScreen(artList = artPieces)
}
