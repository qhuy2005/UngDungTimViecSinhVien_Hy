package com.example.viecsv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.viecsv.data.Job
import kotlinx.coroutines.launch

@Composable
fun ManHinhThem(
    onThemCongViec: (Job) -> Unit
) {

    val snackbarHostState =
        remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    var daBamThem by remember {
        mutableStateOf(false)
    }

    var tenCongViec by remember {
        mutableStateOf("")
    }

    var congTy by remember {
        mutableStateOf("")
    }

    var luong by remember {
        mutableStateOf("")
    }

    var loaiCongViec by remember {
        mutableStateOf("")
    }

    Column(

        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(12.dp)
    ) {

        SnackbarHost(
            hostState = snackbarHostState
        )

        OutlinedTextField(

            value = tenCongViec,

            onValueChange = {
                tenCongViec = it
            },

            label = {
                Text("Tên công việc")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        OutlinedTextField(

            value = congTy,

            onValueChange = {
                congTy = it
            },

            label = {
                Text("Công ty")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        OutlinedTextField(

            value = luong,

            onValueChange = {
                luong = it
            },

            label = {
                Text("Lương")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        OutlinedTextField(

            value = loaiCongViec,

            onValueChange = {
                loaiCongViec = it
            },

            label = {
                Text("Loại công việc")
            },

            modifier =
                Modifier.fillMaxWidth()
        )

        Button(

            onClick = {

                daBamThem = true

                if (
                    tenCongViec.isEmpty() ||
                    congTy.isEmpty() ||
                    luong.isEmpty() ||
                    loaiCongViec.isEmpty()
                ) {

                    scope.launch {

                        snackbarHostState.showSnackbar(
                            "Vui lòng nhập đầy đủ thông tin"
                        )
                    }

                    return@Button
                }

                val congViec = Job(

                    title = tenCongViec,

                    company = congTy,

                    salary = luong,

                    category = loaiCongViec
                )

                onThemCongViec(congViec)

                scope.launch {

                    snackbarHostState.showSnackbar(
                        "Thêm công việc thành công"
                    )
                }

                tenCongViec = ""
                congTy = ""
                luong = ""
                loaiCongViec = ""

                daBamThem = false
            }
        ) {

            Text("Thêm công việc")
        }
    }
}