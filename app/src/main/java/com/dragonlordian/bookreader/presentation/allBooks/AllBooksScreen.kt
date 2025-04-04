package com.dragonlordian.bookreader.presentation.allBooks

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dragonlordian.bookreader.core.Constants.MAX_ITEM_COUNT
import com.dragonlordian.bookreader.presentation.ViewModel
import com.dragonlordian.bookreader.presentation.components.BookCard
import com.dragonlordian.bookreader.presentation.effects.AnimateShimmer

@Composable
fun AllBooksScreen(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    viewModel: ViewModel = hiltViewModel()
) {

    LaunchedEffect(Unit) {
        viewModel.getAllBooks()
    }

    val res = viewModel.state.value
    when{
        res.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn {
                    items(MAX_ITEM_COUNT){
                        AnimateShimmer()
                    }
                }
            }
        }
        res.error.isNotEmpty() -> {
            Text(text = res.error, modifier = modifier)
        }
        res.items.isNotEmpty() -> {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = modifier.fillMaxSize()
                ) {
                    items(res.items){
                        BookCard(
                            imageUrl = it.image,
                            title = it.bookName,
                            author = it.bookAuthor,
                            description = it.bookDescription,
                            navHostController = navHostController,
                            bookUrl = it.bookUrl
                        )
                    }
                }
            }
        }else -> Text(text = "No books available", modifier = modifier)
    }
    
}