package com.dragonlordian.bookreader.presentation.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import com.dragonlordian.bookreader.core.Constants.CARD_HEIGHT
import com.dragonlordian.bookreader.core.Constants.CARD_IMG_SIZE
import com.dragonlordian.bookreader.core.Constants.CARD_MEDIUM_PADDING
import com.dragonlordian.bookreader.core.Constants.CARD_SMALL_PADDING
import com.dragonlordian.bookreader.core.Constants.ROUND_CORNER
import com.dragonlordian.bookreader.domain.navigation.Routes
import com.dragonlordian.bookreader.presentation.effects.ImageAni
import com.dragonlordian.bookreader.presentation.ui.theme.BookReaderTheme

@Composable
fun BookCard(
    imageUrl: String,
    title: String,
    author: String = null.toString(),
    description: String,
    navHostController: NavHostController,
    bookUrl: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CARD_MEDIUM_PADDING)
            .clickable {
                navHostController.navigate(
                    Routes.ShowPdfScreen(url = bookUrl)
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(CARD_HEIGHT)
                .padding(CARD_MEDIUM_PADDING)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "Book cover image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(CARD_IMG_SIZE)
                    .clip(RoundedCornerShape(ROUND_CORNER)),
                loading = {
                    ImageAni()
                },
                error = {
                    Text(text = "Error loading image")
                }
            )
            Spacer(modifier = Modifier.width(CARD_MEDIUM_PADDING))
            Column {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp, //TODO(CHANGE TO MT-TYPOGRAPHY STYLES)
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(CARD_SMALL_PADDING))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.width(CARD_SMALL_PADDING))
                Text(
                    text = "-${author}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    maxLines = 2,
                    fontStyle = FontStyle.Italic
                )
            }
        }
    }
}

@Preview
@Composable
fun BookCardPreview(){
    BookReaderTheme {
        //TODO()
    }
}