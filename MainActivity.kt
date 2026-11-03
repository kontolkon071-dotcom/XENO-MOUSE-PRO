package com.xeno.mousepro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF09070F)
private val Panel = Color(0xFF171020)
private val Purple = Color(0xFFAD63FF)
private val Muted = Color(0xFFB9AEC7)
private val White = Color(0xFFF8F4FF)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { XenoApp() }
    }
}

@Composable
fun XenoApp() {
    var page by remember { mutableStateOf("HOME") }
    var x by remember { mutableFloatStateOf(50f) }
    var y by remember { mutableFloatStateOf(50f) }
    var drag by remember { mutableFloatStateOf(50f) }

    MaterialTheme(colorScheme = darkColorScheme(
        background = Bg, surface = Panel, primary = Purple,
        onBackground = White, onSurface = White
    )) {
        Column(Modifier.fillMaxSize().background(Bg).padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("◈", color = Purple, fontSize = 30.sp, fontWeight = FontWeight.Black)
                Spacer(Modifier.width(10.dp))
                Column {
                    Text("XENO", color = White, fontSize = 23.sp, fontWeight = FontWeight.Black)
                    Text("MOUSE PRO  •  V1.0 PROTOTYPE", color = Muted, fontSize = 9.sp)
                }
                Spacer(Modifier.weight(1f))
                Text("RAVEN", color = Purple, fontWeight = FontWeight.Bold, fontSize = 11.sp)
            }
            Spacer(Modifier.height(18.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(7.dp)) {
                listOf("HOME", "MOUSE", "GAMES", "ACCESS", "TURBO", "CAPTURE").forEach {
                    val selected = page == it
                    Box(
                        Modifier.weight(1f).background(
                            if (selected) Purple.copy(alpha = .24f) else Panel,
                            RoundedCornerShape(9.dp)
                        ).clickable { page = it }.padding(vertical = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(it.take(3), color = if (selected) Purple else Muted,
                            fontSize = 9.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
            Spacer(Modifier.height(16.dp))
            Column(Modifier.weight(1f).verticalScroll(rememberScrollState())) {
                when (page) {
                    "HOME" -> HomePage()
                    "MOUSE" -> MousePage(x, y, drag, { x = it }, { y = it }, { drag = it })
                    "GAMES" -> GamesPage()
                    "ACCESS" -> AccessPage()
                    "TURBO" -> TurboPage()
                    "CAPTURE" -> CapturePage()
                }
            }
            Text("RISE FROM THE SHADOWS.", color = Muted, fontSize = 9.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 12.dp))
        }
    }
}

@Composable
fun SectionTitle(title: String, subtitle: String) {
    Text(title, color = White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
    Text(subtitle, color = Muted, fontSize = 11.sp)
    Spacer(Modifier.height(12.dp))
}

@Composable
fun PanelCard(content: @Composable ColumnScope.() -> Unit) {
    Column(
        Modifier.fillMaxWidth().padding(bottom = 11.dp)
            .background(Panel, RoundedCornerShape(16.dp)).padding(15.dp),
        content = content
    )
}

@Composable
fun HomePage() {
    SectionTitle("RAVEN CONTROL CENTER", "Your gaming tools, in one place.")
    PanelCard {
        Text("WELCOME BACK", color = Purple, fontSize = 10.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(5.dp))
        Text("XENO MOUSE PRO", color = White, fontSize = 25.sp, fontWeight = FontWeight.Black)
        Text("Mouse control • Game profiles • Device tools", color = Muted, fontSize = 11.sp)
        Spacer(Modifier.height(15.dp))
        Text("ACCESS STATUS", color = Muted, fontSize = 10.sp)
        Text("BASIC MODE  •  READY", color = Purple, fontWeight = FontWeight.Bold)
    }
    Row(horizontalArrangement = Arrangement.spacedBy(9.dp)) {
        MetricCard("GAME PROFILES", "02", Modifier.weight(1f))
        MetricCard("MOUSE", "NOT SET", Modifier.weight(1f))
    }
    PanelCard {
        Text("QUICK TOOLS", color = White, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("Sensitivity • Smart Mouse • FPS Monitor • Capture Engine", color = Muted, fontSize = 12.sp)
    }
    Text("Prototype note: controls shown here are interface demonstrations.", color = Muted, fontSize = 10.sp)
}

@Composable
fun MetricCard(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier.background(Panel, RoundedCornerShape(14.dp)).padding(13.dp)) {
        Text(label, color = Muted, fontSize = 9.sp)
        Text(value, color = Purple, fontWeight = FontWeight.Bold, fontSize = 16.sp)
    }
}

@Composable
fun SliderSetting(label: String, value: Float, onChange: (Float) -> Unit) {
    Text("$label  ${value.toInt()}", color = White, fontWeight = FontWeight.SemiBold)
    Slider(value = value, onValueChange = onChange, valueRange = 0f..100f,
        colors = SliderDefaults.colors(thumbColor = Purple, activeTrackColor = Purple))
}

@Composable
fun MousePage(x: Float, y: Float, drag: Float,
              onX: (Float) -> Unit, onY: (Float) -> Unit, onDrag: (Float) -> Unit) {
    SectionTitle("MOUSE ENGINE", "Demo controls — not connected to game input.")
    PanelCard {
        SliderSetting("X SENSITIVITY", x, onX)
        SliderSetting("Y SENSITIVITY", y, onY)
        SliderSetting("DRAG RESPONSE", drag, onDrag)
    }
    PanelCard {
        Text("SMART MOUSE RECOMMENDATION", color = Purple, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(6.dp))
        Text("Connect a supported mouse to inspect available device information.", color = Muted, fontSize = 12.sp)
    }
    PanelCard {
        Text("TAP ENGINE", color = White, fontWeight = FontWeight.Bold)
        Text("1 Tap  •  2 Tap  •  Key mapping preview", color = Muted, fontSize = 12.sp)
    }
}

@Composable
fun GamesPage() {
    SectionTitle("GAME PROFILES", "Choose a profile to organize your settings.")
    listOf("Free Fire / Free Fire MAX", "PUBG Mobile", "Call of Duty Mobile",
        "Blood Strike", "Standoff 2").forEachIndexed { i, game ->
        PanelCard {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("0${i + 1}", color = Purple, fontWeight = FontWeight.Black)
                Spacer(Modifier.width(12.dp))
                Text(game, color = White, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}

@Composable
fun AccessPage() {
    SectionTitle("RAVEN ACCESS ENGINE", "Access availability depends on Android and device support.")
    listOf("NO ROOT" to "Standard app permissions",
        "WIRELESS ADB" to "Not connected",
        "SHIZUKU" to "Not connected",
        "ROOT" to "Not detected").forEach { (name, desc) ->
        PanelCard {
            Text(name, color = Purple, fontWeight = FontWeight.Bold)
            Text(desc, color = Muted, fontSize = 11.sp)
        }
    }
    Text("No privileged commands are run by this prototype.", color = Muted, fontSize = 10.sp)
}

@Composable
fun TurboPage() {
    SectionTitle("TURBO BOOSTER", "Visual demo only — no device overclocking.")
    PanelCard {
        Text("RAVEN DASH", color = Purple, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("A raven animation can play when a supported optimization action starts.", color = Muted, fontSize = 12.sp)
        Spacer(Modifier.height(12.dp))
        Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Purple)) {
            Text("PREVIEW BOOST")
        }
    }
    PanelCard {
        Text("DEVICE MONITOR", color = White, fontWeight = FontWeight.Bold)
        Text("FPS, ping, temperature and battery readings require separate platform integrations.", color = Muted, fontSize = 11.sp)
    }
}

@Composable
fun CapturePage() {
    SectionTitle("XENO CAPTURE ENGINE", "Capture tools require Android user permission.")
    PanelCard {
        Text("QUICK SCREENSHOT", color = Purple, fontWeight = FontWeight.Bold)
        Text("Screenshot action will be added in a later build.", color = Muted, fontSize = 11.sp)
    }
    PanelCard {
        Text("SCREEN RECORDER", color = Purple, fontWeight = FontWeight.Bold)
        Text("Uses Android MediaProjection permission flow; not active in this UI prototype.", color = Muted, fontSize = 11.sp)
    }
    PanelCard {
        Text("RAVEN GALLERY", color = White, fontWeight = FontWeight.Bold)
        Text("Saved captures will appear here after capture integration is implemented.", color = Muted, fontSize = 11.sp)
    }
}
