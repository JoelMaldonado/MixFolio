package com.jjmf.mixfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jjmf.mixfolio.ui.navigation.NavegacionPrincipal
import com.jjmf.mixfolio.ui.theme.MixFolioTheme
import com.jjmf.mixfolio.util.ScanCreen
import com.jjmf.mixfolio.util.test.GaleriaScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MixFolioTheme {
                ScanCreen()
                NavegacionPrincipal()
                //GaleriaScreen()
            }
        }
    }
}