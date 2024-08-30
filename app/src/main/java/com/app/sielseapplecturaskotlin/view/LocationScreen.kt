package com.app.sielseapplecturaskotlin.view


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.location.LocationManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.viewinterop.NoOpUpdate
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import com.app.sielseapplecturaskotlin.R
import com.app.sielseapplecturaskotlin.utils.toast
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.animation.flyTo
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(onBackClick: () -> Unit,navController:NavController) {

  var point: Point? by remember {
    mutableStateOf(null)
  }
  var relaunch by remember {
    mutableStateOf(false)
  }

  val context = LocalContext.current

  val permissionRequest = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.RequestMultiplePermissions(),
    onResult = { permissions ->
      if (!permissions.values.all { it }) {
        //handle permission denied
      } else {
        relaunch = !relaunch
      }
    }
  )
  val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

  Column(
    modifier = Modifier.fillMaxSize(),
  ) {
    // TopAppBar with a Back button
    TopAppBar(
      colors = TopAppBarDefaults.topAppBarColors(
        containerColor = colorResource(id = R.color.primary)
      ),
      title = {
        Text(
          text = "Ubicación de suministros",
          color = Color.White,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
      },
      navigationIcon = {
        IconButton(onClick = { navController.popBackStack() }) {
          Icon(painterResource(id = R.drawable.ic_back), contentDescription = "Back",tint= colorResource(id = R.color.white))
        }
      },
    )
    MapBoxMap(
      onPointChange = { point = it },
      point = point,
      modifier = Modifier
        .fillMaxSize()
    )
  }

  LaunchedEffect(key1 = relaunch) {
    val isGPSEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
        locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)

    if (!isGPSEnabled) {
      context.toast("Activa tu GPS para obtener la ubicación exacta")
    }

    try {
      val location = LocationService().getCurrentLocation(context)
      point = Point.fromLngLat(location.longitude, location.latitude)
    } catch (e: LocationService.LocationServiceException) {
      when (e) {
        is LocationService.LocationServiceException.LocationDisabledException -> {
          // Handle location disabled, show dialog or a snack-bar to enable location
        }
        is LocationService.LocationServiceException.MissingPermissionException -> {
          permissionRequest.launch(
            arrayOf(
              android.Manifest.permission.ACCESS_FINE_LOCATION,
              android.Manifest.permission.ACCESS_COARSE_LOCATION
            )
          )
        }
        is LocationService.LocationServiceException.NoNetworkEnabledException -> {
          // Handle no network enabled, show dialog or a snack-bar to enable network
        }
        is LocationService.LocationServiceException.UnknownException -> {
          // Handle unknown exception
        }
      }
    }
  }

  DisposableEffect(Unit) {
    val gpsReceiver = object : BroadcastReceiver() {
      override fun onReceive(context: Context?, intent: Intent?) {
        if (LocationManager.PROVIDERS_CHANGED_ACTION == intent?.action) {
          val isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
              locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
          if (isEnabled) {
            // GPS was enabled, try getting the location again
            relaunch = !relaunch
          }
        }
      }
    }

    val filter = IntentFilter(LocationManager.PROVIDERS_CHANGED_ACTION)
    context.registerReceiver(gpsReceiver, filter)

    onDispose {
      context.unregisterReceiver(gpsReceiver)
    }
  }
}


@Composable
fun MapBoxMap(
  modifier: Modifier = Modifier,
  onPointChange: (Point) -> Unit,
  point: Point?,
) {
  val context = LocalContext.current
  val desiredWidth = 100  // Cambia este valor según sea necesario
  val desiredHeight = 100 // Cambia este valor según sea necesario

  val originalMarker = remember(context) {
    context.getDrawable(R.drawable.ubication)!!.toBitmap()
  }

  val marker = remember(originalMarker) {
    Bitmap.createScaledBitmap(originalMarker, desiredWidth, desiredHeight, false)
  }

  var pointAnnotationManager: PointAnnotationManager? by remember {
    mutableStateOf(null)
  }

  AndroidView(
    factory = {
      MapView(it).also { mapView ->
        mapView.mapboxMap.loadStyle(Style.TRAFFIC_DAY)
        val annotationApi = mapView.annotations
        pointAnnotationManager = annotationApi.createPointAnnotationManager()

        mapView.mapboxMap.addOnMapClickListener { p ->
          onPointChange(p)

          val latitude = p.latitude()
          val longitude = p.longitude()
          Toast.makeText(
            context,
            "Latitud: $latitude, Longitud: $longitude",
            Toast.LENGTH_SHORT
          ).show()
          true
        }
      }
    },
    update = { mapView ->
      if (point != null) {
        pointAnnotationManager?.let {
          it.deleteAll()
          val pointAnnotationOptions = PointAnnotationOptions()
            .withPoint(point)
            .withIconImage(marker)

          it.create(pointAnnotationOptions)
          mapView.mapboxMap
            .flyTo(CameraOptions.Builder().zoom(16.0).center(point).build())
        }
      }
      NoOpUpdate
    },
    modifier = modifier
  )
}
