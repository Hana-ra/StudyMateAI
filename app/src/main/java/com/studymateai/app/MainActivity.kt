package com.studymateai.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // تنظیم راست‌به‌چپ (RTL) برای کل اپلیکیشن
        val layoutDirection = LayoutDirection.Rtl
        LocalLayoutDirection provides layoutDirection
        
        setContent {
            MaterialTheme {
                StudyMateApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudyMateApp() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("خانه", "دستیار AI", "مطالعه", "برنامه‌ریز", "پروفایل")
    val icons = listOf(
        Icons.Filled.Home, 
        Icons.Filled.SmartToy, 
        Icons.Filled.Book, 
        Icons.Filled.DateRange, 
        Icons.Filled.Person
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = "StudyMate AI", fontWeight = FontWeight.Bold) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                tabs.forEachIndexed { index, title ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = title) },
                        label = { Text(title) },
                        selected = selectedTab == index,
                        onClick = { selectedTab = index }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> PlaceholderScreen("دستیار AI")
                2 -> PlaceholderScreen("مطالعه")
                3 -> PlaceholderScreen("برنامه‌ریز")
                4 -> PlaceholderScreen("پروفایل")
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "سلام! به StudyMate AI خوش آمدی",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        Card(
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Text(
                text = "سهمیه امروز: ۱۰ درخواست",
                modifier = Modifier.padding(16.dp),
                fontSize = 16.sp
            )
        }

        val features = listOf("چت درسی", "خلاصه‌ساز", "فلش‌کارت و آزمون", "امتحان دارم!")
        features.forEach { feature ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
            ) {
                Text(
                    text = feature,
                    modifier = Modifier.padding(20.dp),
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun PlaceholderScreen(title: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "صفحه $title به‌زودی...", fontSize = 18.sp)
    }
}
