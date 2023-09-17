package com.pixelcreative.saveable.screens.profile

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pixelcreative.saveable.components.BaseScaffold
import com.pixelcreative.saveable.navigation.Router


@Composable
fun ProfileScreen(router: Router) {
    val profileViewModel: ProfileViewModel = hiltViewModel()
    val context = LocalContext.current
    val activity = context.findActivity()
    BaseScaffold(
        modifier = Modifier,
        iconClick = { router.goBack() },
        title = "Profile"
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = it.calculateTopPadding() + 16.dp)
        ) {

            Spacer(modifier = Modifier.height(16.dp))
            ProfileLabels("Share", Icons.Default.Share) {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Hey Check out this Great app:\n https://play.google.com/store/apps/details?id=com.keak.geniusai"
                    )
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Share to:")
                context.startActivity(shareIntent)
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProfileLabels("Contact Us", Icons.Default.MailOutline) {
                val send = Intent(Intent.ACTION_SENDTO)
                val uriText = "mailto:" + Uri.encode("keakgames@gmail.com") +
                        "?subject=" + Uri.encode("I have a question about GeniusAI") +
                        "&body=" + Uri.encode("I need help about...")
                val uri = Uri.parse(uriText)

                send.setData(uri)
                context.startActivity(Intent.createChooser(send, "Send mail..."))
            }
            Spacer(modifier = Modifier.height(16.dp))
            ProfileLabels("Rate Us", Icons.Default.Favorite) {
                //profileViewModel.showDialog(activity)
            }
        }
    }
}

@Composable
fun ProfileLabels(title: String, icon: ImageVector, clickAction: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(8.dp))
            .background(color = MaterialTheme.colorScheme.onBackground)
            .clickable { clickAction.invoke() },
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.surface)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.surface,
            )
        }
    }
}

internal fun Context.findActivity(): Activity {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }
    throw IllegalStateException("Permissions should be called in the context of an Activity")
}