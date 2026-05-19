package com.example.viecsv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viecsv.viewmodel.JobViewModel

@Composable
fun TrangChu(
    viewModel: JobViewModel
) {

    val danhSachCongViec by
    viewModel.jobs.collectAsState()

    LazyColumn(

        modifier = Modifier.fillMaxSize(),

        contentPadding = PaddingValues(

            start = 16.dp,
            top = 16.dp,
            end = 16.dp,
            bottom = 100.dp
        ),

        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        items(danhSachCongViec) { congViec ->

            var hienDialogSua by remember {
                mutableStateOf(false)
            }

            var hienDialogXoa by remember {
                mutableStateOf(false)
            }

            var tenMoi by remember {
                mutableStateOf(congViec.title)
            }

            Card(

                modifier = Modifier.fillMaxWidth(),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.primaryContainer
                )
            ) {

                Column(

                    modifier = Modifier.padding(20.dp),

                    verticalArrangement =
                        Arrangement.spacedBy(8.dp)
                ) {

                    Text(

                        text = congViec.title,

                        style =
                            MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Công ty: ${congViec.company}"
                    )

                    Text(
                        text = "Lương: ${congViec.salary}"
                    )

                    Text(
                        text = "Loại công việc: ${congViec.category}"
                    )

                    Button(

                        onClick = {

                            hienDialogXoa = true
                        },

                        modifier = Modifier
                            .padding(top = 12.dp)
                            .fillMaxWidth()
                    ) {

                        Text("Xoá")
                    }

                    Button(

                        onClick = {

                            hienDialogSua = true
                        },

                        modifier = Modifier
                            .fillMaxWidth()
                    ) {

                        Text("Sửa")
                    }

                    if (hienDialogSua) {

                        AlertDialog(

                            onDismissRequest = {

                                hienDialogSua = false
                            },

                            confirmButton = {

                                TextButton(

                                    onClick = {

                                        viewModel.updateJob(

                                            congViec.copy(
                                                title = tenMoi
                                            )
                                        )

                                        hienDialogSua = false
                                    }
                                ) {

                                    Text("Lưu")
                                }
                            },

                            dismissButton = {

                                TextButton(

                                    onClick = {

                                        hienDialogSua = false
                                    }
                                ) {

                                    Text("Huỷ")
                                }
                            },

                            title = {

                                Text("Sửa công việc")
                            },

                            text = {

                                OutlinedTextField(

                                    value = tenMoi,

                                    onValueChange = {

                                        tenMoi = it
                                    },

                                    label = {

                                        Text("Tên mới")
                                    }
                                )
                            }
                        )
                    }

                    if (hienDialogXoa) {

                        AlertDialog(

                            onDismissRequest = {

                                hienDialogXoa = false
                            },

                            confirmButton = {

                                TextButton(

                                    onClick = {

                                        viewModel.deleteJob(congViec)

                                        hienDialogXoa = false
                                    }
                                ) {

                                    Text("Xoá")
                                }
                            },

                            dismissButton = {

                                TextButton(

                                    onClick = {

                                        hienDialogXoa = false
                                    }
                                ) {

                                    Text("Huỷ")
                                }
                            },

                            title = {

                                Text("Xác nhận xoá")
                            },

                            text = {

                                Text(
                                    "Bạn có chắc muốn xoá công việc này?"
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}