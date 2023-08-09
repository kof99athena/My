package com.athena.projectgroupwareapp.drawer.approval.recycler

import android.view.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import com.google.android.gms.common.SignInButton.ColorScheme
import com.google.android.material.theme.overlay.MaterialThemeOverlay
import com.google.firebase.database.collection.LLRBNode

data class CertiListItem (
    var title : String,
    var dateOfIssue : String)

@Composable
fun ComposeRecyclerItem(item : CertiListItem){

}



