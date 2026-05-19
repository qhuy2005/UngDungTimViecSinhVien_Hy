package com.example.viecsv.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ThongKe() {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),

        verticalArrangement =
            Arrangement.spacedBy(16.dp)
    ) {

        CardThongKe(
            tieuDe = "Tổng công việc",
            giaTri = "15"
        )

        CardThongKe(
            tieuDe = "Lương trung bình",
            giaTri = "8 triệu"
        )

        CardThongKe(
            tieuDe = "Lương cao nhất",
            giaTri = "20 triệu"
        )
    }
}

@Composable
fun CardThongKe(
    tieuDe: String,
    giaTri: String
) {

    Card(

        modifier = Modifier.fillMaxWidth(),

        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Column(

            modifier = Modifier.padding(20.dp)
        ) {

            Text(

                text = tieuDe,

                style =
                    MaterialTheme.typography.titleMedium
            )

            Text(

                text = giaTri,

                style =
                    MaterialTheme.typography.headlineMedium
            )
        }
    }
}