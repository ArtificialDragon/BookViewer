package com.dragonlordian.bookreader.presentation.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dragonlordian.bookreader.core.Constants.AMOUNT_PER_COLUMN
import com.dragonlordian.bookreader.core.Constants.MAX_ITEM_COUNT
import com.dragonlordian.bookreader.presentation.ViewModel
import com.dragonlordian.bookreader.presentation.components.BookCategoryCard
import com.dragonlordian.bookreader.presentation.effects.CategoryShimmer

@Composable
fun CategoryScreen(
    viewModel: ViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    LaunchedEffect(Unit) {
        viewModel.getCategories()
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val res = viewModel.state.value
        when{
            res.isLoading -> {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    LazyVerticalGrid(
                        GridCells.Fixed(AMOUNT_PER_COLUMN),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(MAX_ITEM_COUNT){
                            CategoryShimmer()
                        }
                    }
                }
            }
            res.error.isNotEmpty() -> {
                Text(text = res.error)
            }
            res.category.isNotEmpty() -> {
                LazyVerticalGrid(
                    GridCells.Fixed(AMOUNT_PER_COLUMN),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(res.category){
                        BookCategoryCard(
                            imageUrl = it.categoryImageUrl,
                            category = it.name,
                            navHostController = navHostController
                        )
                    }
                }
            }else -> Text(text = "No Category Found", fontSize = 24.sp, textAlign = TextAlign.Center) //TODO(CHANGE TO TYPOGRAPHY)
        }
    }

}