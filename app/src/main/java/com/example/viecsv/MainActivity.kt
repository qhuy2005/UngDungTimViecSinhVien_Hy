package com.example.viecsv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viecsv.data.JobDatabase
import com.example.viecsv.ui.screen.DanhSachCongViecScreen
import com.example.viecsv.ui.screen.ManHinhThem
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

            val danhSachCongViec by viewModel.jobs.collectAsState()

            ViecsvTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                    ) {

                        ManHinhThem(
                            onThemCongViec = { congViec ->

                                viewModel.addJob(congViec)
                            }
                        )

                        DanhSachCongViecScreen(
                            danhSachCongViec = danhSachCongViec,

                            onXoaCongViec = { congViec ->

                                viewModel.deleteJob(congViec)
                            }
                        )
                    }
                }
            }
        }
    }
}