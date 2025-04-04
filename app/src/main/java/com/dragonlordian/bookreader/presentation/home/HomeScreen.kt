package com.dragonlordian.bookreader.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BugReport
import androidx.compose.material.icons.filled.ContactSupport
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dragonlordian.bookreader.core.Constants.LARGE_SPACER
import com.dragonlordian.bookreader.core.Constants.SMALL_SPACER
import com.dragonlordian.bookreader.core.Constants.DRAWER_MEDIUM_PADDING
import com.dragonlordian.bookreader.core.Constants.DRAWER_WIDTH
import com.dragonlordian.bookreader.core.Constants.VERSION
import com.dragonlordian.bookreader.presentation.tab.TabScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val uriHandler = LocalUriHandler.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(DRAWER_WIDTH)
                        .padding(DRAWER_MEDIUM_PADDING)
                ) {
                    Spacer(modifier = Modifier.height(LARGE_SPACER))
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = true,
                        icon = { Icon(imageVector = Icons.Filled.Home, contentDescription = "Home")},
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                        }
                    )
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Version $VERSION") },
                        selected = true,
                        icon = { Icon(imageVector = Icons.Filled.Info, contentDescription = "Version")},
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            Toast.makeText(context, "Current version is $VERSION", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Contact") },
                        selected = true,
                        icon = { Icon(
                            imageVector = Icons.Filled.ContactSupport,
                            contentDescription = "Contact",
                            // TODO(UNCOMMENT) modifier = Modifier.size(CONTACT_ME_SIZE)
                        )},
                        onClick = {
                            uriHandler.openUri("https://nohello.net/en/")
                        }
                    )
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Bug report") },
                        selected = true,
                        icon = { Icon(
                            imageVector = Icons.Filled.BugReport,
                            contentDescription = "Bug Report",
                            // TODO(UNCOMMENT) modifier = Modifier.size(CONTACT_ME_SIZE)
                        )},
                        onClick = {
                            uriHandler.openUri("https://github.com/ArtificialDragon")
                        }
                    )
                    //TODO(ADD MORE ITEMS)
                }
            }
        }) {
        Scaffold(modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(SMALL_SPACER))
                            Text(
                                text = "Book Library",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp //TODO(CHANGE TO TYPOGRAPHY)
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                            Icon(imageVector = Icons.Filled.Menu, contentDescription = "Open menu")
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                TabScreen(navHostController = navHostController)
            }
        }
    }
}