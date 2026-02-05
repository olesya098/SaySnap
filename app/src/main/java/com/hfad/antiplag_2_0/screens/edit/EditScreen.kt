package com.hfad.antiplag_2_0.screens.edit

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.hfad.antiplag_2_0.model.state.EditUIState
import com.hfad.common_components.menu.SideBarMenu
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.edit.view.FileNotSelectView
import com.hfad.edit.view.FileSelectView
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun EditScreen(
    editViewModel: EditViewModel
) {
    val scope = rememberCoroutineScope()

    var fileText by remember { mutableStateOf(TextFieldValue("")) }

    val showDialog = remember { mutableStateOf(false) }

    val state = editViewModel.editState.collectAsState()

    SideBarMenu(
        action = { drawerState ->
            CustomScaffold(
                title = "Edit",
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = null
                        )
                    }
                }
            ) {
               when (state.value){
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
                           }
                       ) {
                           fileText = it.copy(
                               annotatedString = AnnotatedString(
                                   text = it.text,
                                   spanStyles = fileText.annotatedString.spanStyles,
                                   paragraphStyles = fileText.annotatedString.paragraphStyles
                               )
                           )
                       }
                   }
               }
           }
        }
    )
}