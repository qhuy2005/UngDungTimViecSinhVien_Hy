package com.example.viecsv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GioiThieu() {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement = Arrangement.Center,

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {

        Card(

            colors = CardDefaults.cardColors(
                containerColor =
                    MaterialTheme.colorScheme.primaryContainer
            )
        ) {

            Column(

                modifier = Modifier.padding(24.dp),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                Text(

                    text = "Ứng dụng tìm việc sinh viên",

                    style =
                        MaterialTheme.typography.headlineSmall
                )

                Text(
                    text = "Phiên bản: 1.0"
                )

                Text(
                    text = "Nhóm thực hiện: Nhóm 6"
                )

                Text(
                    text = "Công nghệ: Kotlin + Compose + Room"
                )

                Text(
                    text = "Đại học Hạ Long"
                )
            }
        }
    }
}