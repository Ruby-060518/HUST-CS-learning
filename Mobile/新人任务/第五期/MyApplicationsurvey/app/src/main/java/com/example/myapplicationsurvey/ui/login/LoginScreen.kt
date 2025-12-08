package com.example.myapplicationsurvey.ui.login

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.myapplicationsurvey.R
import com.example.myapplicationsurvey.ui.theme.*

@Composable
fun LoginScreen(onContinue: (String?, String?) -> Unit) {
    var name by remember { mutableStateOf("") }
    var avatarUri by remember { mutableStateOf<String?>(null) }

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
        avatarUri = uri?.toString()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Cream
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Sign in to Vinyl",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = DeepBlack
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_music_note),
                    contentDescription = null,
                    tint = WarmOrangeRed
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .background(DeepBlack)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .clip(CircleShape)
                            .background(RiceWhite)
                    )
                    if (avatarUri == null) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_headphones),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp)
                                .align(Alignment.Center)
                        )
                    } else {
                        AsyncImage(
                            model = avatarUri,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .clickable { launcher.launch("image/*") }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Tap to add your avatar",
                style = MaterialTheme.typography.bodySmall,
                color = DeepBlack
            )

            Spacer(modifier = Modifier.height(16.dp))

            // 使用 Material3 兼容的 colors
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text("Your Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .background(RiceWhite, RoundedCornerShape(8.dp)),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = RiceWhite,
                    unfocusedContainerColor = RiceWhite,
                    focusedBorderColor = WarmOrangeRed,
                    unfocusedBorderColor = InkGreen,
                    cursorColor = WarmOrangeRed,
                    focusedLabelColor = WarmOrangeRed
                )
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { onContinue(name.ifBlank { null }, avatarUri) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = WarmOrangeRed)
            ) {
                Text(
                    text = "Get Started",
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
