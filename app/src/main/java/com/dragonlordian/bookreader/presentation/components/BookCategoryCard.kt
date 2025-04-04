package com.dragonlordian.bookreader.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.SubcomposeAsyncImage
import com.dragonlordian.bookreader.core.Constants.CARD_MEDIUM_PADDING
import com.dragonlordian.bookreader.core.Constants.CATEGORY_CARD_SIZE
import com.dragonlordian.bookreader.core.Constants.ROUND_CORNER
import com.dragonlordian.bookreader.domain.navigation.Routes

@Composable
fun BookCategoryCard(
    imageUrl: String,
    category: String,
    navHostController: NavHostController
) {
    Card(
        modifier = Modifier
            .padding(CARD_MEDIUM_PADDING)
            .clickable {
                navHostController.navigate(Routes.BooksByCategory(category))
            }
    ) {
        Column(
            modifier = Modifier
                .size(CATEGORY_CARD_SIZE)
                .padding(CARD_MEDIUM_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(CARD_MEDIUM_PADDING)
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                contentDescription = "Category cover image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(ROUND_CORNER)),
                loading = {
                    TODO()
                },
                error = {
                    Text(text = "Error in loading image")
                }
            )
            Spacer(modifier = Modifier.width(CARD_MEDIUM_PADDING))
            Text(
                text = category,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp, //TODO(CHANGE TO MT TYPOGRAPHY)
                maxLines = 2
            )
        }
    }
}