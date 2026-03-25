package com.bbm.ataramor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.defaultComponentContext
import com.bbm.ataramor.di.KoinApp
import com.bbm.ataramor.root.RootComponentImpl
import org.koin.core.context.startKoin
import org.koin.plugin.module.dsl.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        startKoin<KoinApp>{
            //androidContext(this@MainActivity)
        }

        val root = RootComponentImpl(
            componentContext = defaultComponentContext()
        )

        setContent {
            App(root = root)
        }
    }
}

