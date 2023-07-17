package com.benrostudios.flowbump

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.benrostudios.flowbump.presentation.NftViewModel
import com.benrostudios.flowbump.ui.theme.FlowBumpTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlowBumpTheme {
                val viewModel = hiltViewModel<NftViewModel>()
                val state by viewModel.state.collectAsState()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Hello Rishabh!")
                        if (state.isLoading) {
                            CircularProgressIndicator()
                        } else {
                            Button(onClick = {
                                println("This button was clicked!")
                                viewModel.getWallet("1b839830-31a1-485d-9693-ddf6f7999560")
                                println(state.wallet)
                            }) {
                                if(state.wallet?.id != null) {
                                    Text(state.wallet?.address?: "no address")
                                } else {
                                    Text("Click me")
                                }
                            }
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}