package io.untungs.nutrisport.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.untungs.nutrisport.core.domain.usecase.SignOutUseCase
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@Composable
fun HomeGraphScreen(
    modifier: Modifier = Modifier,
    signOutUseCase: SignOutUseCase = koinInject()
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(modifier = modifier) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Home")
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = {
                    coroutineScope.launch {
                        signOutUseCase()
                    }
                }) {
                    Text("Temporary Sign Out")
                }
            }
        }
    }
}
