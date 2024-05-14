package com.example.presenter.ui.bookmark.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import com.example.domain.model.KakaoBookmark

@Composable
fun KakaoBookmarkItem(
    item: KakaoBookmark
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {

        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (image, title, author, dateTime) = createRefs()

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
            Text(text = item.title, modifier = Modifier
                .constrainAs(title) {
                    linkTo(start = image.end, end = parent.end)
                    linkTo(top = image.top, bottom = author.top)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
                .padding(5.dp), maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 20.sp)



            Text(text = item.authors.toString(), modifier = Modifier
                .constrainAs(author) {
                    linkTo(start = image.end, end = parent.end)
                    linkTo(top = title.bottom, bottom = dateTime.top)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }
                .padding(5.dp), maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 8.sp)


            Text(text = item.datetime, modifier = Modifier
                .constrainAs(dateTime) {
                    linkTo(start = image.end, end = parent.end)
                    linkTo(top = author.bottom, bottom = parent.bottom)
                    width = Dimension.wrapContent
                    height = Dimension.wrapContent
                }, maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 8.sp)

        }

    }

}
