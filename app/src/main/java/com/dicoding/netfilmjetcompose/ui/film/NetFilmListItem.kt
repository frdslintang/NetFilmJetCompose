package com.dicoding.netfilmjetcompose.ui.film

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.netfilmjetcompose.ui.theme.NetFilmTheme


@Composable
fun NetFilmListItem(
    name: String,
    rating: String,
    photoUrl: String,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(8.dp)
            .wrapContentHeight()
    ) {
        Column(
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(200.dp)
                    .clip(MaterialTheme.shapes.medium),
            )
            Text(
                text = name,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = rating,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = FontWeight.Medium,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NetFilmListItemPreview() {
    NetFilmTheme {
        NetFilmListItem(
            name = "Film Name",
            rating = "4.5",
            photoUrl = "",
        )
    }
}