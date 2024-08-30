package com.app.sielseapplecturaskotlin

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import coil.compose.rememberAsyncImagePainter
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects
data class ImageItem(
  val uri: Uri,
  val timestamp: Long,
  var isSelected: MutableState<Boolean>
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScreenPhoto() {
  val context = LocalContext.current
  val imageList = remember { mutableStateListOf<ImageItem>() }

  var currentUri: Uri? by remember { mutableStateOf(null) }

  val cameraLauncher =
    rememberLauncherForActivityResult(ActivityResultContracts.TakePicture()) { success ->
      if (success) {
        currentUri?.let {
          val timestamp = System.currentTimeMillis()
          imageList.add(ImageItem(it, timestamp, mutableStateOf(false)))
        }
      }
    }

  val permissionLauncher = rememberLauncherForActivityResult(
    ActivityResultContracts.RequestPermission()
  ) { isGranted ->
    if (isGranted) {
      Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
      currentUri = context.createImageFileUri()
      cameraLauncher.launch(currentUri!!)
    } else {
      Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
    }
  }
  val (selectedImage, setSelectedImage) = remember { mutableStateOf<ImageItem?>(null) }

  LaunchedEffect(Unit) {
    val permissionCheckResult =
      ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
    if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
      currentUri = context.createImageFileUri()
      cameraLauncher.launch(currentUri!!)
    } else {
      permissionLauncher.launch(Manifest.permission.CAMERA)
    }
  }
  Scaffold(
    topBar = {
      TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = colorResource(id = R.color.primary)
        ),
        title = {
          Text(
            text = "SIELSE LECTURAS",
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
          )
        }
      )
    },
    content = { paddingValues ->
      Column(
        modifier = Modifier.padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
      ) {
        Text(
          text = "Fotos tomadas",
          color= colorResource(id = R.color.grey_dark),
          fontWeight = FontWeight.Bold,
          fontSize = 16.sp,
          modifier = Modifier.padding(vertical = 15.dp)
        )
        LazyVerticalGrid(
          columns = GridCells.Fixed(2),
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
          verticalArrangement = Arrangement.Top,
          horizontalArrangement = Arrangement.Center
        ) {
          val sortedImageList = imageList.sortedByDescending { it.timestamp }

          itemsIndexed(sortedImageList) { index, imageItem ->
            Column(
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
              horizontalAlignment = Alignment.CenterHorizontally
            ) {
              Box(
                modifier = Modifier
                  .width(130.dp)
                  .height(200.dp)
              ) {
                Image(
                  painter = rememberAsyncImagePainter(imageItem.uri),
                  contentDescription = null,
                  contentScale = ContentScale.Crop,
                  modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(10.dp))
                    .clickable { setSelectedImage(imageItem) }
                )
                IconButton(
                  onClick = { imageList.remove(imageItem) },
                  modifier = Modifier
                    .padding(top = 5.dp, end = 5.dp)
                    .background(colorResource(id = R.color.red), shape = CircleShape)
                    .align(Alignment.TopEnd)
                    .size(24.dp)
                ) {
                  Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Eliminar Foto",
                    tint = Color.White
                  )
                }
              }
            }
          }
        }

      }
    },
    bottomBar = {
      Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
          .fillMaxWidth()
          .padding(bottom = 50.dp, end = 20.dp)
      ) {
        IconButton(
          onClick = {
            val permissionCheckResult =
              ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
            if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
              currentUri = context.createImageFileUri()
              cameraLauncher.launch(currentUri!!)
            } else {
              permissionLauncher.launch(Manifest.permission.CAMERA)
            }
          },
          modifier = Modifier
            .background(colorResource(id = R.color.green), shape = CircleShape)
            .padding(5.dp)
            .size(40.dp),
          colors = IconButtonDefaults.iconButtonColors(
            contentColor = colorResource(id = R.color.white)
          )
        ) {
          Icon(
            imageVector = Icons.Filled.Add, contentDescription = "Agregar foto",
            modifier = Modifier.size(120.dp)
          )
        }
      }
    }
  )
  AnimatedVisibility(
    visible = selectedImage != null,
    enter = fadeIn() + slideInVertically(initialOffsetY = { it }),
    exit = fadeOut() + slideOutVertically(targetOffsetY = { it })
  ) {
    if (selectedImage != null) {
      ImagePreviewDialog(imageItem = selectedImage, onDismiss = { setSelectedImage(null) })
    }
  }
}

fun Context.createImageFileUri(): Uri {
  val file = createImageFile()

  return FileProvider.getUriForFile(
    Objects.requireNonNull(this),
    "$packageName.provider",
    file
  )
}

fun Context.createImageFile(): File {
  val timeStamp = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(Date())
  val imageFileName = "JPEG_" + timeStamp + "_"
  val storageDir = externalCacheDir

  return File.createTempFile(
    imageFileName,
    ".jpg",
    storageDir
  )
}
@Composable
fun ImagePreviewDialog(imageItem: ImageItem, onDismiss: () -> Unit) {
  Dialog(onDismissRequest = onDismiss) {
    Box(
      modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ) {
      Image(
        painter = rememberAsyncImagePainter(imageItem.uri),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.Center)
      )
      IconButton(
        onClick = onDismiss,
        modifier = Modifier
          .padding(16.dp)
          .background(Color.Black.copy(alpha = 0.6f), shape = CircleShape)
          .align(Alignment.TopEnd)
      ) {
        Icon(
          imageVector = Icons.Filled.Close,
          contentDescription = "Close",
          tint = Color.White
        )
      }
    }
  }
}
@Preview(showBackground = true)
@Composable
private fun ShowPrevPhoto() {
  ScreenPhoto()
}