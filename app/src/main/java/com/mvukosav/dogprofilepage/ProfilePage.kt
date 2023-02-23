package com.mvukosav.dogprofilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@Composable
fun ProfilePage() {
    Card(
        elevation = 6.dp, modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, bottom = 100.dp, start = 16.dp, end = 16.dp)
            .border(width = 2.dp, color = Color.White, shape = RoundedCornerShape(30.dp))
    ) {
        BoxWithConstraints {
            val constraints = if (minWidth < 600.dp) {
                portraitConstraint(margin = 16.dp)
            } else {
                landscapeConstraint(margin = 16.dp)
            }
            ConstraintLayout(constraints) {
                Image(
                    painter = painterResource(id = R.drawable.rimac_nevera_low_res_25),
                    contentDescription = "user profile picture",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .border(
                            width = 2.dp,
                            color = Color.Blue,
                            shape = CircleShape
                        )
                        .layoutId("image"),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = "Nevera interior",
                    modifier = Modifier.layoutId("nameText")
                )
                Text(text = "Croatia", Modifier.layoutId("countryText"))

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .layoutId("rowStats")
                ) {
                    ProfileStats("150", "Followers")
                    ProfileStats("100", "Following")
                    ProfileStats("15", "Post")
                }

                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("followButton")) {
                    Text(text = "Follow user")
                }
                Button(onClick = { /*TODO*/ }, modifier = Modifier.layoutId("directMsgButton")) {
                    Text(text = "Direct message")
                }
            }
        }
    }
}

private fun landscapeConstraint(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowStats")
        val followBtn = createRefFor("followButton")
        val directMsgBtn = createRefFor("directMsgButton")
        val guideLine = createGuidelineFromTop(0.1f)

        constrain(image) {
            top.linkTo(guideLine)
            start.linkTo(parent.start, margin = margin)
        }

        constrain(nameText) {
            top.linkTo(image.bottom, margin = 10.dp)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }
        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(nameText.start)
            end.linkTo(nameText.end)
        }

        constrain(rowStats) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }

        constrain(followBtn) {
            top.linkTo(rowStats.bottom, margin = 10.dp)
            start.linkTo(rowStats.start)
            end.linkTo(directMsgBtn.start)
            bottom.linkTo(countryText.bottom)
            width = Dimension.wrapContent
        }
        constrain(directMsgBtn) {
            top.linkTo(rowStats.bottom, margin = 10.dp)
            start.linkTo(followBtn.end)
            end.linkTo(parent.end)
            bottom.linkTo(countryText.bottom)
            width = Dimension.wrapContent
        }
    }
}

private fun portraitConstraint(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val image = createRefFor("image")
        val nameText = createRefFor("nameText")
        val countryText = createRefFor("countryText")
        val rowStats = createRefFor("rowStats")
        val followBtn = createRefFor("followButton")
        val directMsgBtn = createRefFor("directMsgButton")
        val guideLine = createGuidelineFromTop(0.1f)

        constrain(image) {
            top.linkTo(guideLine)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(nameText) {
            top.linkTo(image.bottom, margin = 10.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(countryText) {
            top.linkTo(nameText.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(rowStats) {
            top.linkTo(countryText.bottom)
        }
        constrain(followBtn) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(directMsgBtn.start)
            width = Dimension.wrapContent
        }
        constrain(directMsgBtn) {
            top.linkTo(rowStats.bottom, margin = margin)
            start.linkTo(followBtn.end)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}

@Composable
fun ProfileStats(count: String, title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = count, fontWeight = FontWeight.Bold)
        Text(text = title)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfilePagePreview() {
    ProfilePage()
}