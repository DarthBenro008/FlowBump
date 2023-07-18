package com.benrostudios.flowbump.presentation.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.benrostudios.flowbump.ui.theme.FlowBumpTheme

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowBumpTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            Modifier
                                .padding(vertical = 24.dp)
                        ){
                            AsyncImage(
                                model = "https://lh3.googleusercontent.com/a/AAcHTtcBm59r7jDffHh9hngW4Tq75_4Z2QdcGXdP0qtZxyXXeA=s96-c",
                                contentDescription = "user image",
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(100.dp, 100.dp)
                                    .zIndex(10f),
                                contentScale = ContentScale.FillBounds
                            )
                        }

                        Card(Modifier.fillMaxWidth(0.9f)) {
                            Column(Modifier.padding(12.dp)) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                ) {
                                    Icon(Icons.Filled.Person, "link")
                                    Spacer(Modifier.width(24.dp))
                                    Text("")
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                ) {
                                    Icon(Icons.Filled.Email, "link")
                                    Spacer(Modifier.width(24.dp))
                                    Text("")
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                ) {
                                    Icon(Icons.Filled.Phone, "link")
                                    Spacer(Modifier.width(24.dp))
                                    Text("+91 9999988888")
                                }
                                Row(
                                    horizontalArrangement = Arrangement.SpaceEvenly,
                                    modifier = Modifier.padding(vertical = 12.dp)
                                ) {
                                    Icon(Icons.Filled.Share, "link")
                                    Spacer(Modifier.width(24.dp))
                                    Text("https://twitter.com/flowhackwinner")
                                }
                            }
                        }

                        Row(Modifier.weight(1f, false)) {
                            Button(
                                onClick = { println("My friends clicked") },
                                Modifier.fillMaxWidth(0.9f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    "Add to Friends",
                                    Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}
