package com.example.presenter.ui.search.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import com.example.domain.model.KakaoBook
import com.example.presenter.R

@Composable
fun KakaoSearchItem(
    modifier: Modifier = Modifier,
    item: KakaoBook,
    onClick: (KakaoBook) -> Unit,
    onDeleteBookmark: (KakaoBook) -> Unit,
    onInsertBookmark: (KakaoBook) -> Unit,

    ) {

    val bookmarkImage = if (item.isBookmark) {
        painterResource(id = R.drawable.baseline_clicked_star_24)
    } else {
        painterResource(id = R.drawable.baseline_empty_star_border_24)
    }



    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable { onClick(item) }
            .height(100.dp)
            .then(modifier)
    ) {

        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            val (image, title, author, dateTime, favorite) = createRefs()

            SubcomposeAsyncImage(
                model = item.thumbnail, loading = {
                    CircularProgressIndicator()
                },
                contentDescription = null,
                modifier = Modifier.constrainAs(image) {
                    linkTo(start = parent.start, end = parent.end, bias = 0.05f)
                    linkTo(top = parent.top, bottom = parent.bottom)
                    width = Dimension.value(80.dp)
                    height = Dimension.value(80.dp)
                }
            )

            Image(
                painter = bookmarkImage,
                contentDescription = "",
                modifier = Modifier
                    .constrainAs(favorite) {
                        linkTo(start = parent.start, end = parent.end, bias = 1f, endMargin = 5.dp)
                        linkTo(top = parent.top, bottom = parent.bottom)
                        width = Dimension.value(32.dp)
                        height = Dimension.value(32.dp)
                    }
                    .clickable {
                        if (item.isBookmark) {
                            onDeleteBookmark(item)
                        } else {
                            onInsertBookmark(item)
                        }
                    })


            Text(text = item.title, modifier = Modifier
                .constrainAs(title) {
                    linkTo(start = image.end, end = favorite.start)
                    linkTo(top = image.top, bottom = author.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
                .padding(5.dp), maxLines = 1, overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center, fontSize = 20.sp)



            Text(text = item.authors.toString(),
                modifier = Modifier
                    .constrainAs(author) {
                        linkTo(start = image.end, end = favorite.start)
                        linkTo(top = title.bottom, bottom = dateTime.top)
                        width = Dimension.fillToConstraints
                        height = Dimension.wrapContent
                    }
                    .padding(5.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 8.sp,
                textAlign = TextAlign.Center)


            Text(
                text = item.datetime,
                modifier = Modifier
                    .constrainAs(dateTime) {
                        linkTo(start = image.end, end = favorite.start)
                        linkTo(top = author.bottom, bottom = parent.bottom)
                        width = Dimension.wrapContent
                        height = Dimension.wrapContent
                    },
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 8.sp,
                textAlign = TextAlign.Center

            )


        }

    }
}