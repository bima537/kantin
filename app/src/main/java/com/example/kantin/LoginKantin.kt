package com.example.kantin

import KantinTheme
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

@Composable
fun LoginKantin(onLoginSuccess: () -> Unit = {}) {
    val backgroundColor = Color(0xFFFFF6F4)
    val primaryColor = Color(0xFFDE97A5)
    val textColor = Color(0xFF888888)

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    val googleSignInClient: GoogleSignInClient = remember {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.your_web_client_id))
            .requestEmail()
            .build()
        GoogleSignIn.getClient(context, gso)
    }

    val googleSignInLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                Log.d("GoogleSignIn", "ID Token: ${account.idToken}")

                account.idToken?.let { idToken ->
                    coroutineScope.launch {
                        val credential = GoogleAuthProvider.getCredential(idToken, null)
                        try {
                            auth.signInWithCredential(credential).await()

                            val firebaseUser = auth.currentUser
                            Log.d(
                                "FirebaseLogin",
                                "Login sukses: ${firebaseUser?.displayName} (${firebaseUser?.email})"
                            )
                            onLoginSuccess()
                        } catch (e: Exception) {
                            Log.w("FirebaseLogin", "Gagal melakukan autentikasi dengan Firebase", e)
                        }
                    }
                } ?: run {
                    Log.w("GoogleSignIn", "ID Token dari Google adalah null")
                }

            } catch (e: ApiException) {
                Log.w("GoogleSignIn", "Gagal masuk dengan Google. Status code: ${e.statusCode}", e)
            }
        } else {
            Log.w("GoogleSignIn", "Login Google dibatalkan atau gagal. Result Code: ${result.resultCode}")
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {
        //Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .clip(RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp))
                .background(primaryColor)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Image(
                painter = painterResource(id = R.drawable.logokantin),
                contentDescription = "Logo Kantin",
                modifier = Modifier.size(150.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("email", color = textColor) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("password", color = textColor) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = primaryColor,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (email.isNotBlank() && password.isNotBlank()) {
                        coroutineScope.launch {
                            try{
                                auth.signInWithEmailAndPassword(email, password).await()
                                Log.d("EmailLogin", "Login Berhasil")
                                onLoginSuccess()
                            } catch (e: Exception) {
                                Log.d("EmailLogin", "Login Gagal", e)
                                Toast.makeText(context, "Login gagal: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
                            }
                        }
                    } else {
                        Log.w("EmailLogin", "Email atau Password tidak boleh KOSONG")
                        Toast.makeText(context, "Email atau Password tidak boleh KOSONG", Toast.LENGTH_LONG).show()
                    }
                },
                modifier = Modifier
                    .width(250.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            ) {
                Text("Continue", color = Color.White)
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text("or", color = Color.Gray)

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val signInIntent = googleSignInClient.signInIntent
                    googleSignInLauncher.launch(signInIntent)
                },
                modifier = Modifier
                    .width(250.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColor
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = " Google Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Continue with Google", color = Color.White)
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        //Footer
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .background(primaryColor),
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_4")
@Composable
fun LoginKantinPreview() {
    KantinTheme {
        LoginKantin()
    }
}