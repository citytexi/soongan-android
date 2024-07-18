package com.captures2024.soongan.feature.home.route

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavOptions
import com.captures2024.soongan.feature.home.PhotoDetailViewModel
import com.captures2024.soongan.feature.home.ui.photo.PhotoDetailScreen
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PhotoDetailRoute(
    photoId: String,
    photoDetailViewModel: PhotoDetailViewModel = hiltViewModel(),
    navigateToBack: () -> Unit,
    navigateToControlImage: (String, NavOptions?) -> Unit
) {
    val uiState = photoDetailViewModel.uiState.collectAsStateWithLifecycle().value
    val modalBottomSheetState = rememberModalBottomSheetState()

    LaunchedEffect(key1 = true) {
        photoDetailViewModel.loadImage(photoId)
    }

    Timber.tag("PhotoDetailRoute").d("uiState = $uiState")

    PhotoDetailScreen(
        uiState = uiState,
        onClickBack = navigateToBack,
        onClickImage = navigateToControlImage
    )
//    PhotoDetailBottomSheetDialog(modalBottomSheetState = modalBottomSheetState)
}