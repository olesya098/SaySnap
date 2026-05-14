package com.hfad.antiplag_2_0.screens.folders

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.hfad.antiplag_2_0.menu.SideBarMenu
import com.hfad.antiplag_2_0.screens.edit.EditViewModel
import com.hfad.common_components.file.CustomFile
import com.hfad.common_components.navigation.LocalNavigator
import com.hfad.common_components.navigation.Routes
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.domain.model.FileTranscriptionDTO
import com.hfad.theme.R
import com.hfad.theme.cyan
import com.hfad.theme.gray2
import kotlinx.coroutines.launch

@Composable
fun FolderScreen(
    folderViewModel: FolderViewModel,
    editViewModel: EditViewModel
) {
    val scope = rememberCoroutineScope()
    val folder by folderViewModel.folders.collectAsState()
    val selectedFolder by folderViewModel.selectedFolderId.collectAsState()

    val transcriptions by folderViewModel.transcriptions.collectAsState()

    val navigator = LocalNavigator.current

    val screenTitle = folder.find { it.id == selectedFolder }?.nameFolder ?: "Home"

    var showDeleteDialog by remember { mutableStateOf(false) }
    var fileToDelete by remember { mutableStateOf<FileTranscriptionDTO?>(null) }

    SideBarMenu(
        folderViewModel = folderViewModel,
        action = { drawerState ->
            CustomScaffold(
                title = screenTitle,
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .size(30.dp)
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .border(
                                width = 1.5.dp,
                                color = gray2.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(15.dp)
                            )
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "Назад",
                            tint = Color(0xFF1A1A1A),
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 16.dp),
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(transcriptions) { item ->
                        CustomFile(
                            title = item.title,
                            onEditFile = {
                                folderViewModel.updateFileId(item.id)
                                editViewModel.updateFileText(TextFieldValue(item.text))
                                navigator.navigate(Routes.EDITSCREEN)
                            },
                            onLongClick = {
                                fileToDelete = item
                                showDeleteDialog = true
                            }
                        )
                    }
                }

            }
        }
    )
    if (showDeleteDialog && fileToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.White,
            titleContentColor = Color(0xFF1A1A1A),
            textContentColor = Color(0xFF666666),
            title = { Text("Удалить файл?") },
            text = { Text("Вы уверены, что хотите удалить \"${fileToDelete!!.title}\"?") },
            confirmButton = {
                TextButton(onClick = {
                    fileToDelete?.let { folderViewModel.deleteFileTranscription(it.id) }
                    showDeleteDialog = false
                    fileToDelete = null
                }) {
                    Text("Удалить", color = Color.Red)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Отмена", color = cyan)
                }
            }
        )
    }
}