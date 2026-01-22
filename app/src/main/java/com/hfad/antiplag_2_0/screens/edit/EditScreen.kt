package com.hfad.antiplag_2_0.screens.edit

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.antiplag_2_0.screens.edit.filePicker.FilePicker
import com.hfad.common_components.button.CustomButton
import com.hfad.common_components.dialog.Dialog
import com.hfad.common_components.dialog.DialogNotes
import com.hfad.common_components.menu.SideBarMenu
import com.hfad.common_components.scaffold.CustomScaffold
import com.hfad.edit.components.CustomIcon
import com.hfad.edit.until.wrapSelectedText
import com.hfad.theme.Black
import com.hfad.theme.LitePurple
import com.hfad.theme.PointGray
import com.hfad.theme.R
import kotlinx.coroutines.launch

@Composable
fun EditScreen() {
    val scope = rememberCoroutineScope()

    var fileText by remember { mutableStateOf(TextFieldValue("")) }
    var fileName by remember { mutableStateOf("") }
    var isFileSelected by remember { mutableStateOf(true) }

    val showDialog = remember { mutableStateOf(false) }

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
                if (!isFileSelected) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Выберите текст для обработки",
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(
                            modifier = Modifier.height(15.dp)
                        )
                        Box(
                            modifier = Modifier
                                .size(120.dp)
                                .clickable {

                                },
                            contentAlignment = Alignment.Center
                        ) {
                            FilePicker(
                                onTextReceived = { text, name ->

//                                    fileText = text
                                    fileName = name ?: "Выбранный файл"
                                    isFileSelected = true
                                },
                                modifier = Modifier
                                    .size(120.dp)
                                    .background(Color.Transparent)
                            )

                            Image(
                                painter = painterResource(id = R.drawable.donloadfile),
                                contentDescription = "Выбрать файл",
                                modifier = Modifier.size(80.dp)
                            )
                        }

                    }
                } else {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 15.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                                .clip(shape = RoundedCornerShape(20))
                                .background(LitePurple.copy(alpha = 0.5f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Row(
                                modifier = Modifier
                                    .padding(vertical = 10.dp),
                                horizontalArrangement = Arrangement.spacedBy(15.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                CustomIcon(
                                    R.drawable.edit
                                ) { }
                                CustomIcon(
                                    R.drawable.numberlist
                                ) { }
                                CustomIcon(
                                    R.drawable.bold
                                ) {
                                    fileText = wrapSelectedText(fileText, SpanStyle(fontWeight = FontWeight.Bold))
                                }
                                CustomIcon(
                                    R.drawable.cursive
                                ) {
                                    fileText = wrapSelectedText(fileText, SpanStyle(fontStyle = FontStyle.Italic))
                                }
                                CustomIcon(
                                    R.drawable.underlined
                                ) {
                                    fileText = wrapSelectedText(fileText, SpanStyle(textDecoration = TextDecoration.Underline))
                                }
                            }

                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            colors = CardDefaults.cardColors(
                                containerColor = PointGray
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                            ) {
//                                if (fileText.text.isEmpty()) {
//                                    Column(
//                                        modifier = Modifier
//                                            .fillMaxSize()
//                                            .padding(16.dp),
//                                        horizontalAlignment = Alignment.CenterHorizontally,
//                                        verticalArrangement = Arrangement.Center
//                                    ) {
//                                        Text(
//                                            text = "Файл пуст",
//                                            style = MaterialTheme.typography.bodyMedium,
//                                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
//                                        )
//                                    }
//                                } else {
                                    BasicTextField(
                                        value = fileText,
                                        onValueChange = { newText ->
                                            fileText = newText
                                        },
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .verticalScroll(rememberScrollState()),
                                        textStyle = TextStyle(
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.onSurface
                                        ),
                                        decorationBox = { innerTextField ->
                                            Box(
                                                modifier = Modifier.fillMaxSize(),
                                                contentAlignment = Alignment.TopStart
                                            ) {
                                                innerTextField()
                                            }
                                        }
                                    )
                                }
                            //}
                        }
                        CustomButton(
                            text = "Сохранить",
                            color = LitePurple
                        ) {
                            showDialog.value = true
                        }
                        if (showDialog.value) {
                            Dialog(
                                onDismiss = {
                                    showDialog.value = false
                                    scope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    }
                }
           }
        }
    )
}