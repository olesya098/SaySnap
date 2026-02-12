package com.hfad.antiplag_2_0.screens.edit

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.hfad.antiplag_2_0.model.state.EditUIState
import com.hfad.antiplag_2_0.menu.SideBarMenu
import com.hfad.antiplag_2_0.screens.folders.FolderViewModel
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.edit.view.FileNotSelectView
import com.hfad.edit.view.FileSelectView
import com.hfad.theme.R
import com.hfad.theme.gray2
import kotlinx.coroutines.launch

@Composable
fun EditScreen(
    editViewModel: EditViewModel,
    folderViewModel: FolderViewModel
) {
    val scope = rememberCoroutineScope()

    var fileText by remember { mutableStateOf(TextFieldValue("")) }

    val showDialog = remember { mutableStateOf(false) }

    val state = editViewModel.editState.collectAsState()

    SideBarMenu(
        folderViewModel = folderViewModel,
        action = { drawerState ->
            CustomScaffold(
                title = "Edit",
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
                when (state.value) {
                    EditUIState.IsFileNotSelected -> {
                        FileNotSelectView {
                            fileText = TextFieldValue(
                                text = it,
                                selection = TextRange(it.length)
                            )
                            editViewModel.updateEditState(EditUIState.IsFileSelected)
                        }
                    }

                    EditUIState.IsFileSelected -> {
                        FileSelectView(
                            fileText = fileText,
                            showDialog = showDialog,
                            action = {
                                it.apply { fileText = it }
                            },
                            onValueChange = {
                                fileText = it.copy(
                                    annotatedString = AnnotatedString(
                                        text = it.text,
                                        spanStyles = fileText.annotatedString.spanStyles,
                                        paragraphStyles = fileText.annotatedString.paragraphStyles
                                    )
                                )
                            },
                            onSaveToFolder = { name, content ->
                                editViewModel.saveFile(name, content)

                            }
                        )
                    }
                }
            }
        }
    )
}