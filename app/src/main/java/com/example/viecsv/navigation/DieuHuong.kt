package com.example.viecsv.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.viecsv.ui.screen.GioiThieu
import com.example.viecsv.ui.screen.ManHinhThem
import com.example.viecsv.ui.screen.ThongKe
import com.example.viecsv.ui.screen.TrangChu
import com.example.viecsv.viewmodel.JobViewModel

data class ItemMenu(

    val route: String,
    val title: String
)

@Composable
fun DieuHuong(
    navController: NavHostController,
    viewModel: JobViewModel
) {

    val danhSachMenu = listOf(

        ItemMenu("trangchu", "Trang chủ"),
        ItemMenu("them", "Thêm"),
        ItemMenu("thongke", "Thống kê"),
        ItemMenu("gioithieu", "About")
    )

    Scaffold(

        bottomBar = {

            NavigationBar(

                tonalElevation = 8.dp
            ) {

                val backStack =
                    navController.currentBackStackEntryAsState()

                val currentRoute =
                    backStack.value?.destination?.route

                danhSachMenu.forEach { item ->

                    NavigationBarItem(

                        selected =
                            currentRoute == item.route,

                        onClick = {

                            navController.navigate(item.route)
                        },

                        alwaysShowLabel = true,

                        icon = {

                            when (item.route) {

                                "trangchu" -> {

                                    Icon(
                                        Icons.Default.Home,
                                        contentDescription = null
                                    )
                                }

                                "them" -> {

                                    Icon(
                                        Icons.Default.Person,
                                        contentDescription = null
                                    )
                                }

                                "thongke" -> {

                                    Icon(
                                        Icons.Default.List,
                                        contentDescription = null
                                    )
                                }

                                else -> {

                                    Icon(
                                        Icons.Default.Info,
                                        contentDescription = null
                                    )
                                }
                            }
                        },

                        label = {

                            Text(

                                text = item.title,

                                style =
                                    MaterialTheme.typography.labelSmall
                            )
                        }
                    )
                }
            }
        }

    ) { paddingValues ->

        NavHost(

            navController = navController,

            startDestination = "trangchu",

            modifier =
                Modifier.padding(paddingValues)
        ) {

            composable("trangchu") {

                TrangChu(viewModel)
            }

            composable("them") {

                ManHinhThem(

                    onThemCongViec = {

                        viewModel.addJob(it)
                    }
                )
            }

            composable("thongke") {

                ThongKe()
            }

            composable("gioithieu") {

                GioiThieu()
            }
        }
    }
}