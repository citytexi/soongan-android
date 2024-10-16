package com.captures2024.soongan.feature.home.ui.gallery

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.captures2024.soongan.core.designsystem.component.NonScaleText
import com.captures2024.soongan.core.designsystem.icon.MyIconPack
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterLike
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterNew
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconFilterOld
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillFillter
import com.captures2024.soongan.core.designsystem.icon.myiconpack.IconNonFillLeftArrow
import com.captures2024.soongan.core.designsystem.theme.PrimaryA
import com.captures2024.soongan.core.designsystem.theme.PrimaryC
import com.captures2024.soongan.core.designsystem.util.DevicePreviews
import com.captures2024.soongan.feature.home.R
import com.captures2024.soongan.feature.home.ui.component.HomeGalleryButton
import com.captures2024.soongan.feature.home.utils.GalleryPhotoSortFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeGalleryTopBar(
    modifier: Modifier = Modifier,
    lazyStaggeredGridState: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    isShowBottomSheet: Boolean,
    sortOrder: GalleryPhotoSortFilter,
    onBackPressed: () -> Unit = {},
    onClickFilter: () -> Unit = {},
    onClickSortFilter: (GalleryPhotoSortFilter) -> Unit = {},
    onBottomModalDismissRequest: () -> Unit = {}
) {
    var scrolledY = 0f
    var previousOffset = 0

    Row(
        modifier = modifier
            .graphicsLayer {
                scrolledY += lazyStaggeredGridState.firstVisibleItemScrollOffset - previousOffset
                translationY = scrolledY * 0.5f
                previousOffset = lazyStaggeredGridState.firstVisibleItemScrollOffset
            }
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        HomeGalleryButton(onClick = onBackPressed) {
            Icon(
                imageVector = MyIconPack.IconNonFillLeftArrow,
                contentDescription = "",
                tint = PrimaryA,
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 16.dp
                )
            )
        }
        Row {
            NonScaleText(
                text = stringResource(id = R.string.home_gallery_top_week_example),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            NonScaleText(
                text = stringResource(id = R.string.home_gallery_top_split),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(8.dp))
            NonScaleText(
                text = stringResource(id = R.string.home_gallery_top_topic_example),
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium
            )
        }

        HomeGalleryButton(onClick = onClickFilter) {
            Icon(
                imageVector = MyIconPack.IconNonFillFillter,
                contentDescription = "",
                tint = PrimaryA,
                modifier = Modifier.size(
                    width = 20.dp,
                    height = 20.dp
                )
            )
        }

        if (isShowBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = onBottomModalDismissRequest,
                containerColor = Color.White,
            ) {
                Column(
                    modifier = Modifier.padding(
                        horizontal = 20.dp,
                        vertical = 20.dp
                    )
                ) {
                    HomeGalleryFilterItem(
                        text = stringResource(id = R.string.filter_likes),
                        icon = MyIconPack.IconFilterLike,
                        selected = sortOrder == GalleryPhotoSortFilter.LIKES,
                        onClickItem = { onClickSortFilter(GalleryPhotoSortFilter.LIKES) }
                    )
                    HorizontalDivider(color = PrimaryC)
                    HomeGalleryFilterItem(
                        text = stringResource(id = R.string.filter_oldest),
                        icon = MyIconPack.IconFilterOld,
                        selected = sortOrder == GalleryPhotoSortFilter.OLDEST,
                        onClickItem = { onClickSortFilter(GalleryPhotoSortFilter.OLDEST) }
                    )
                    HorizontalDivider(color = PrimaryC)
                    HomeGalleryFilterItem(
                        text = stringResource(id = R.string.filter_newest),
                        icon = MyIconPack.IconFilterNew,
                        selected = sortOrder == GalleryPhotoSortFilter.NEWEST,
                        onClickItem = { onClickSortFilter(GalleryPhotoSortFilter.NEWEST) }
                    )
                }
            }
        }
    }
}


@DevicePreviews
@Composable
private fun HomeGalleryTopBarPreview() {
    HomeGalleryTopBar(
        isShowBottomSheet = false,
        sortOrder = GalleryPhotoSortFilter.LIKES
    )
}