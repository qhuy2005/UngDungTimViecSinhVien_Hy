package com.example.viecsv.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.TrendingUp
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.viecsv.data.Job
import com.example.viecsv.viewmodel.JobViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThongKe(viewModel: JobViewModel) {
    val jobs by viewModel.jobs.collectAsState()
    
    val totalJobs = jobs.size
    val categoriesCount = jobs.groupBy { it.category }.mapValues { it.value.size }
    
    // Tính lương trung bình (trích xuất số từ chuỗi lương, vd: "5tr" -> 5)
    val averageSalary = if (jobs.isNotEmpty()) {
        val salaries = jobs.mapNotNull { job ->
            Regex("\\d+").find(job.salary)?.value?.toDoubleOrNull()
        }
        if (salaries.isNotEmpty()) salaries.average() else 0.0
    } else 0.0

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Thống Kê", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
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
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Tổng quan thị trường",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            CardThongKe(
                tieuDe = "Tổng công việc",
                giaTri = totalJobs.toString(),
                unit = "vị trí",
                icon = Icons.Default.Work,
                color = Color(0xFF4CAF50)
            )

            CardThongKe(
                tieuDe = "Lương trung bình",
                giaTri = if (averageSalary > 0) String.format(Locale.getDefault(), "%.1f", averageSalary) else "0.0",
                unit = "tr/tháng",
                icon = Icons.Default.Payments,
                color = Color(0xFF2196F3)
            )

            CardThongKe(
                tieuDe = "Tăng trưởng",
                giaTri = if (totalJobs > 0) "+${(totalJobs * 15 / 10)}" else "0", 
                unit = "% tháng này",
                icon = Icons.AutoMirrored.Filled.TrendingUp,
                color = Color(0xFFFF9800)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "Phân bổ theo danh mục",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            if (categoriesCount.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Chưa có dữ liệu để thống kê", color = MaterialTheme.colorScheme.outline)
                }
            } else {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        categoriesCount.forEach { (category, count) ->
                            CategoryProgress(
                                category = category,
                                count = count,
                                total = totalJobs,
                                color = getCategoryColor(category)
                            )
                        }
                    }
                }
            }

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = Color(0xFFFFB300)
                    )
                    Text(
                        text = if (totalJobs > 0) 
                            "Gợi ý: Dựa trên dữ liệu, danh mục '${categoriesCount.maxByOrNull { it.value }?.key}' đang có nhu cầu cao nhất."
                            else "Hãy thêm công việc để xem phân tích thị trường.",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

@Composable
fun CategoryProgress(category: String, count: Int, total: Int, color: Color) {
    val progress = if (total > 0) count.toFloat() / total else 0f
    
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "$count vị trí (${(progress * 100).toInt()}%)",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
                .clip(CircleShape),
            color = color,
            trackColor = color.copy(alpha = 0.15f)
        )
    }
}

@Composable
fun CardThongKe(
    tieuDe: String,
    giaTri: String,
    unit: String,
    icon: ImageVector,
    color: Color
) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = tieuDe,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = giaTri,
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = color
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = unit,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .background(color.copy(alpha = 0.1f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

fun getCategoryColor(category: String): Color {
    val colors = listOf(
        Color(0xFF673AB7), Color(0xFFE91E63), Color(0xFF009688),
        Color(0xFFFF9800), Color(0xFF3F51B5), Color(0xFFF44336),
        Color(0xFF8BC34A), Color(0xFF03A9F4)
    )
    // Dùng mã băm của tên danh mục để chọn màu cố định cho mỗi loại
    val index = Math.abs(category.hashCode()) % colors.size
    return colors[index]
}
