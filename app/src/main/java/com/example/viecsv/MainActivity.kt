package com.example.viecsv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import com.example.viecsv.data.JobDatabase
import com.example.viecsv.navigation.DieuHuong
import com.example.viecsv.ui.theme.ViecsvTheme
import com.example.viecsv.viewmodel.JobViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val database = JobDatabase.getDatabase(this)

        val viewModel = JobViewModel(
            database.jobDao()
        )

        setContent {

            ViecsvTheme {

                Surface(

                    color = MaterialTheme.colorScheme.background
                ) {

                    val navController =
                        rememberNavController()

                    DieuHuong(

                        navController = navController,

                        viewModel = viewModel
                    )
                }
            }
        }
    }
}