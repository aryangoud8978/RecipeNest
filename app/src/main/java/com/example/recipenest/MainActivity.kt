package com.example.recipenest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppScreen()
        }
    }
}

data class Recipe(
    val emoji: String,
    val name: String,
    val time: String,
    val rating: String
)

@Composable
fun AppScreen() {

    var showSplash by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        Handler(Looper.getMainLooper()).postDelayed({
            showSplash = false
        }, 2000)
    }

    if (showSplash) {
        SplashScreen()
    } else {
        HomeScreen()
    }
}

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "🍔",
                fontSize = 100.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "RecipeNest",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6B00)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Delicious Recipes Everyday",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun HomeScreen() {

    var searchText by remember {
        mutableStateOf("")
    }

    val recipeList = listOf(

        Recipe("🍕", "Cheese Pizza", "20 min", "4.8 ⭐"),
        Recipe("🍔", "Chicken Burger", "15 min", "4.7 ⭐"),
        Recipe("🍜", "Spicy Noodles", "18 min", "4.9 ⭐"),
        Recipe("🥗", "Healthy Salad", "10 min", "4.5 ⭐")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "RecipeNest",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF6B00)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Find Best Recipes For You 🍽️",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search Recipes")
            },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {

            items(recipeList) { recipe ->

                RecipeCard(recipe)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable {

                Toast.makeText(
                    context,
                    "${recipe.name} Clicked",
                    Toast.LENGTH_SHORT
                ).show()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = recipe.emoji,
                fontSize = 60.sp
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column {

                Text(
                    text = recipe.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Cooking Time: ${recipe.time}",
                    fontSize = 16.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Rating: ${recipe.rating}",
                    fontSize = 16.sp,
                    color = Color(0xFFFF9800)
                )
            }
        }
    }
}