//package com.hfad.common_components.menu
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.width
//import androidx.compose.material3.Icon
//import androidx.compose.material3.MaterialTheme
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import com.hfad.common_components.navigation.Navigator
//import com.hfad.theme.LiteBlue
//import com.hfad.theme.LitePurple
//import com.hfad.theme.PointGray
//
//@Composable
//fun SideBarModel.Menu(
//    navigator: Navigator,
//    tint: Color = LiteBlue,
//    isSelect: Boolean = false
//) {
//    val background = if (isSelect) PointGray else Color.Transparent
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                navigator.navigate(route)
//            }
//            .padding(horizontal = 16.dp, vertical = 12.dp)
//            .background(background),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        if (isSelect) {
//            Box(
//                modifier = Modifier
//                    .width(4.dp)
//                    .fillMaxHeight()
//                    .background(LitePurple)
//            )
//        } else {
//            Spacer(modifier = Modifier.width(4.dp))
//        }
//        Icon(
//            painterResource(imageId),
//            contentDescription = null,
//            tint = tint
//        )
//        Spacer(
//            modifier = Modifier.width(16.dp)
//        )
//        Text(
//            text = title,
//            style = MaterialTheme.typography.bodyMedium
//        )
//
//    }
//}