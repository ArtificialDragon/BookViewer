package com.dragonlordian.bookreader.presentation.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import com.dragonlordian.bookreader.core.Constants.SMALL_SPACER
import com.dragonlordian.bookreader.core.Constants.TAB_ICON_SIZE
import com.dragonlordian.bookreader.presentation.allBooks.AllBooksScreen
import com.dragonlordian.bookreader.presentation.category.CategoryScreen
import kotlinx.coroutines.launch

@Composable
fun TabScreen(
    navHostController: NavHostController
) {
    val tabs = listOf(
        TabItem("Category", Icons.Default.Category),
        TabItem("Books", Icons.Default.Book)
    )

    val pagerState = rememberPagerState(pageCount = {tabs.size})
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            modifier = Modifier.fillMaxWidth()
        ) {
            tabs.forEachIndexed { index, tabItem ->
                Tab(
                    modifier = Modifier.fillMaxWidth(),
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch { pagerState.animateScrollToPage(index) }
                    },
                    text = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = tabItem.icon, contentDescription = tabItem.title,
                                modifier = Modifier.size(TAB_ICON_SIZE)
                            )
                            Spacer(modifier = Modifier.width(SMALL_SPACER))
                            Text(tabItem.title)
                        }
                    }
                )
            }
        }

        HorizontalPager(state = pagerState) { page->
            when(page){
                0 -> CategoryScreen(navHostController = navHostController)
                1 -> AllBooksScreen(navHostController = navHostController)
            }
        }
    }
}

data class TabItem(val title: String, val icon: ImageVector)