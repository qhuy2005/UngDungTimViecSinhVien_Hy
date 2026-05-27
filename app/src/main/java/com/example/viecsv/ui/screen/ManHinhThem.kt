package com.example.viecsv.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viecsv.data.Job
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManHinhThem(
    onThemCongViec: (Job) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var tenCongViec by remember { mutableStateOf("") }
    var congTy by remember { mutableStateOf("") }
    var luong by remember { mutableStateOf("") }
    var loaiCongViec by remember { mutableStateOf("") }
    var daBamThem by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Đăng Tin Tuyển Dụng", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Thông tin chi tiết công việc",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier.align(Alignment.Start)
            )

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.large
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CustomTextField(
                        value = tenCongViec,
                        onValueChange = { tenCongViec = it },
                        label = "Tên công việc",
                        placeholder = "VD: Phục vụ cafe, Gia sư...",
                        icon = Icons.Default.Work,
                        isError = daBamThem && tenCongViec.isEmpty()
                    )

                    CustomTextField(
                        value = congTy,
                        onValueChange = { congTy = it },
                        label = "Tên công ty / Cửa hàng",
                        placeholder = "VD: Highlands Coffee...",
                        icon = Icons.Default.Business,
                        isError = daBamThem && congTy.isEmpty()
                    )

                    CustomTextField(
                        value = luong,
                        onValueChange = { luong = it },
                        label = "Mức lương",
                        placeholder = "VD: 20",
                        icon = Icons.Default.AttachMoney,
                        isError = daBamThem && luong.isEmpty()
                    )

                    CustomTextField(
                        value = loaiCongViec,
                        onValueChange = { loaiCongViec = it },
                        label = "Loại công việc",
                        placeholder = "VD: Part-time, Thời vụ...",
                        icon = Icons.Default.Category,
                        isError = daBamThem && loaiCongViec.isEmpty()
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    daBamThem = true
                    if (tenCongViec.isNotBlank() && congTy.isNotBlank() && luong.isNotBlank() && loaiCongViec.isNotBlank()) {
                        onThemCongViec(Job(
                            title = tenCongViec,
                            company = congTy,
                            salary = luong,
                            category = loaiCongViec
                        ))
                        scope.launch {
                            snackbarHostState.showSnackbar("Đăng tin thành công!")
                        }
                        tenCongViec = ""; congTy = ""; luong = ""; loaiCongViec = ""
                        daBamThem = false
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                Text("HOÀN TẤT ĐĂNG TIN", fontWeight = FontWeight.Bold, fontSize = 16.sp)
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    icon: ImageVector,
    isError: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth(),
        isError = isError,
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        supportingText = {
            if (isError) Text("Trường này không được để trống", color = MaterialTheme.colorScheme.error)
        }
    )
}
