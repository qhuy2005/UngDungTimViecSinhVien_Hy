
package com.example.viecsv

import android.os.Bundle
import android.util.Log
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

        Log.d("LifeCycle", "onCreate")

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

    override fun onStart() {
        super.onStart()
        Log.d("LifeCycle", "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifeCycle", "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifeCycle", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("LifeCycle", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifeCycle", "onDestroy")
    }
}

