package com.dragonlordian.bookreader.presentation.effects

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.dragonlordian.bookreader.core.Constants.CARD_ALL_MEDIUM_PADDING
import com.dragonlordian.bookreader.core.Constants.CARD_HEIGHT
import com.dragonlordian.bookreader.core.Constants.HUGE_SPACER
import com.dragonlordian.bookreader.core.Constants.LARGE_SPACER
import com.dragonlordian.bookreader.core.Constants.CARD_MEDIUM_PADDING
import com.dragonlordian.bookreader.core.Constants.MEDIUM_SPACER
import com.dragonlordian.bookreader.core.Constants.CARD_SMALL_PADDING
import com.dragonlordian.bookreader.core.Constants.XLARGE_SPACER
import com.dragonlordian.bookreader.core.Constants.CARD_XSMALL_PADDING
import com.dragonlordian.bookreader.core.Constants.CATEGORY_CARD_SIZE
import com.dragonlordian.bookreader.core.Constants.ROUND_CORNER

@Composable
fun AnimateShimmer(modifier: Modifier = Modifier) {

    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )

    ShimmerGridItem(brush = brush)
}

@Composable
fun ShimmerGridItem(
    brush: Brush
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(CARD_MEDIUM_PADDING),
        shape = RoundedCornerShape(ROUND_CORNER)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(CARD_HEIGHT)
                .padding(all = CARD_ALL_MEDIUM_PADDING),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(
                modifier = Modifier
                    .size(HUGE_SPACER)
                    .clip(RoundedCornerShape(ROUND_CORNER))
                    .background(brush)
            )
            Spacer(modifier = Modifier.padding(CARD_ALL_MEDIUM_PADDING))

            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.padding(CARD_SMALL_PADDING))
                Spacer(
                    modifier = Modifier
                        .size(MEDIUM_SPACER)
                        .clip(RoundedCornerShape(ROUND_CORNER))
                        .fillMaxWidth(0.7f)
                        .background(brush)
                )

                Spacer(modifier = Modifier.padding(CARD_XSMALL_PADDING))
                Spacer(
                    modifier = Modifier
                        .size(LARGE_SPACER)
                        .clip(RoundedCornerShape(ROUND_CORNER))
                        .fillMaxWidth(0.9f)
                        .background(brush)
                )

                Spacer(modifier = Modifier.padding(CARD_XSMALL_PADDING))
                Spacer(
                    modifier = Modifier
                        .size(LARGE_SPACER)
                        .clip(RoundedCornerShape(ROUND_CORNER))
                        .fillMaxWidth(0.9f)
                        .background(brush)
                )

                Spacer(modifier = Modifier.padding(CARD_XSMALL_PADDING))
                Spacer(
                    modifier = Modifier
                        .size(LARGE_SPACER)
                        .clip(RoundedCornerShape(ROUND_CORNER))
                        .fillMaxWidth(0.3f)
                        .background(brush)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ShimmerGridItemPreview() {
    ShimmerGridItem(brush = Brush.linearGradient(listOf(Color.White, Color.LightGray, Color.White)))
}

@Composable
fun CategoryShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )

    Card(
        modifier = Modifier
            .padding(CARD_MEDIUM_PADDING),
        shape = RoundedCornerShape(ROUND_CORNER)
    ) {
        Column(
            modifier = Modifier
                .size(CATEGORY_CARD_SIZE)
                .height(CARD_HEIGHT)
                .padding(all = CARD_ALL_MEDIUM_PADDING),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(CARD_MEDIUM_PADDING)
        ) {
            Spacer(
                modifier = Modifier
                    .clip(RoundedCornerShape(ROUND_CORNER))
                    .background(brush)
                    .fillMaxWidth()
                    .weight(1f)
                    .clip(RoundedCornerShape(ROUND_CORNER))
            )
            Spacer(modifier = Modifier.padding(CARD_MEDIUM_PADDING))

            Spacer(
                modifier = Modifier
                    .height(LARGE_SPACER)
                    .clip(RoundedCornerShape(ROUND_CORNER))
                    .background(brush)
                    .fillMaxWidth(0.5f)
            )
        }
    }
}

@Composable
fun ImageAni() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )
    val transition = rememberInfiniteTransition()
    val translateAnimation = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ), label = ""
    )
    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset(10f, 10f),
        end = Offset(translateAnimation.value, translateAnimation.value)
    )

    Spacer(
        modifier = Modifier
            .size(XLARGE_SPACER)
            .clip(RoundedCornerShape(ROUND_CORNER))
            .background(brush)
    )

}