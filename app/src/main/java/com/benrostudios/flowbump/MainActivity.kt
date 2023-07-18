package com.benrostudios.flowbump

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.benrostudios.flowbump.presentation.home.HomeActivity
import com.benrostudios.flowbump.presentation.sign_in.SignInActivity
import com.benrostudios.flowbump.ui.theme.FlowBumpTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        println("ggwp  ${auth.currentUser?.photoUrl}")
        setContent {
            FlowBumpTheme {
                var isSearching by rememberSaveable { mutableStateOf(false) }
                val bb by animateOffsetAsState(
                    animationSpec = tween(durationMillis = 1500),
                    targetValue = if (isSearching) Offset(0f, 200f) else Offset(0f, 0f)
                )
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.purple_ripple))
                Surface(Modifier.fillMaxSize()) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = 12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Image Loader
                        Box(Modifier.padding(vertical = 24.dp).offset(bb.x.dp, bb.y.dp)) {
                            Box(Modifier.size(100.dp, 100.dp).zIndex(1f)){
                                LottieAnimation(iterations = LottieConstants.IterateForever, isPlaying = isSearching, composition = composition, restartOnPlay = true, modifier = Modifier.size(100.dp, 100.dp).scale(2f))
                            }
                            AsyncImage(
                                model = auth.currentUser?.photoUrl ?: "",
                                contentDescription = "user image",
                                modifier = Modifier.clip(CircleShape).size(100.dp, 100.dp).zIndex(10f).clickable {
                                    isSearching = !isSearching
                                    Timer().schedule(6000) {
                                        startFound()
                                    }
                                },
                                contentScale = ContentScale.FillBounds,
                            )
                        }
                        if (!isSearching) {
                            Card(Modifier.fillMaxWidth(0.9f)) {
                                Column(Modifier.padding(12.dp)) {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier.padding(vertical = 12.dp)
                                    ) {
                                        Icon(Icons.Filled.Person, "link")
                                        Spacer(Modifier.width(24.dp))
                                        Text("${auth.currentUser?.displayName}")
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier.padding(vertical = 12.dp)
                                    ) {
                                        Icon(Icons.Filled.Email, "link")
                                        Spacer(Modifier.width(24.dp))
                                        Text("${auth.currentUser?.email}")
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier.padding(vertical = 12.dp)
                                    ) {
                                        Icon(Icons.Filled.Phone, "link")
                                        Spacer(Modifier.width(24.dp))
                                        Text("+91 9999999999")
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceEvenly,
                                        modifier = Modifier.padding(vertical = 12.dp)
                                    ) {
                                        Icon(Icons.Filled.Share, "link")
                                        Spacer(Modifier.width(24.dp))
                                        Text("https://twitter.com/elonmusk")
                                    }
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
                                    "My Friends",
                                    Modifier.padding(horizontal = 8.dp, vertical = 12.dp)
                                )
                            }
                        }
                    }
                }

            }
        }
    }


    private fun startFound(){
        startActivity(Intent(this, HomeActivity::class.java))
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun movingCard() {
        var isMoved by rememberSaveable { mutableStateOf(false) }
        val bb by animateOffsetAsState(
            animationSpec = tween(durationMillis = 1500),
            targetValue = if (isMoved) Offset(0f, 100f) else Offset(0f, 0f)
        )
        Card(onClick = { isMoved = !isMoved }, modifier = Modifier.offset(bb.x.dp, bb.y.dp)) {
            Box(
                Modifier
                    .fillMaxWidth(0.9f)
                    .padding(8.dp), contentAlignment = Alignment.Center
            ) {
                Text("damn card")
            }
        }
    }

    private fun startSignIn() {
        startActivity(Intent(this, SignInActivity::class.java))
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            startSignIn()
        }
    }
}
