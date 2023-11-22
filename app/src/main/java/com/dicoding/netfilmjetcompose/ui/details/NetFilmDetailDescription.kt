package com.dicoding.netfilmjetcompose.ui.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.netfilmjetcompose.ui.theme.NetFilmTheme

@Composable
fun NetFilmDetailDescription(
    name: String,
    genre: String,
    rating: String,
    sinopsis: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(top = 10.dp, bottom = 10.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = name, 
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold, // Menjadikan teks menjadi tebal (bold)
                textAlign = TextAlign.Center)
            Text(
                text = "Genre: $genre",
                style = MaterialTheme.typography.bodyMedium
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    tint = Color(0xFFFF9800),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Rating: $rating",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Text(
                text = "Description: $sinopsis",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NetFilmDetailDescriptionPreview() {
    NetFilmTheme {
        NetFilmDetailDescription(
            name = "The Flash",
            genre = "Action",
            rating = "8.0",
            sinopsis = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
                    "Pellentesque quis risus sit amet dolor fringilla feugiat vel quis ligula."
        )
    }
}
