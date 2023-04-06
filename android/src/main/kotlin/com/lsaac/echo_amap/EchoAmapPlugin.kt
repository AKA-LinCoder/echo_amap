package com.lsaac.echo_amap

import android.content.Context
import android.location.*
import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.amap.api.maps.AMapUtils
import com.amap.api.maps.model.LatLng
import com.lsaac.echo_amap.extension.toPluginMethod
import com.lsaac.echo_amap.model.LngAndLat
import com.lsaac.echo_amap.model.PluginMethod
import com.lsaac.echo_amap.utils.UtilPlugin

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import java.io.IOException
import java.util.*

/** EchoAmapPlugin */
class EchoAmapPlugin: FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel


  private  var locationManager: LocationManager? = null
  private lateinit var locationListener: LocationListener

  var mContext: Context? = null

  private lateinit var globalBinding: FlutterPlugin.FlutterPluginBinding


  private var binding: ActivityPluginBinding? = null




  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "echo_amap")
    channel.setMethodCallHandler(this)


    this.mContext = flutterPluginBinding.applicationContext
    globalBinding = flutterPluginBinding

    locationManager = flutterPluginBinding.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    locationListener = object : LocationListener {
      override fun onLocationChanged(location: Location) {
        //处理位置变化事件
        val latitude = location.latitude
        val longitude = location.longitude

        val result = mapOf("latitude" to latitude, "longitude" to longitude)
        channel.invokeMethod("onLocationChanged", result)
      }
      override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
      override fun onProviderEnabled(provider: String) {
        //处理位置提供者启用事件
      }
      override fun onProviderDisabled(provider: String) {
        //处理位置提供者停用事件
      }

    }

    val  utilChannel =  MethodChannel(flutterPluginBinding.binaryMessenger,"echo_amap_util")
    utilChannel.setMethodCallHandler(UtilPlugin())


  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
//    if (call.method == "getPlatformVersion") {
//      result.success("Android ${android.os.Build.VERSION.RELEASE}")
//    } else {
//      result.notImplemented()
//    }
    if(locationManager==null){
      locationManager = mContext?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    }

    when (call.method.toPluginMethod()){
      is PluginMethod.calculateLineDistance ->{
        val  arguments = call.arguments as List<Double>
        val lngAndLat1 = LngAndLat(latitude = arguments[1], longitude = arguments[0])
        val lngAndLat2 = LngAndLat(latitude = arguments[3], longitude = arguments[2])
        calculateLineDistance( lngAndLat1 = lngAndLat1, lngAndLat2 = lngAndLat2, result = result)
      }
      is PluginMethod.calculateLineDistanceByAmap ->{
        val  arguments = call.arguments as List<Double>
        val lngAndLat1 = LngAndLat(latitude = arguments[1], longitude = arguments[0])
        val lngAndLat2 = LngAndLat(latitude = arguments[3], longitude = arguments[2])
        calculateLineDistanceByAmap( lngAndLat1 = lngAndLat1, lngAndLat2 = lngAndLat2, result = result)
      }

      is PluginMethod.getCurrentLocation ->{
        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0f, locationListener)
        // 通过GPS定位
        val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        val latitude = location?.latitude ?: 0.0
        val longitude = location?.longitude ?: 0.0
        result.success(mapOf<String,Double>("latitude" to latitude, "longitude" to longitude))
      }
      is PluginMethod.getCurrentLocationByAmap ->{
        val locationClient = AMapLocationClient(mContext)
        val locationOption = AMapLocationClientOption()
        locationOption.isOnceLocation = true
        locationOption.isNeedAddress = false
        locationOption.isMockEnable = false
        locationOption.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        locationClient.setLocationOption(locationOption)
        locationClient.setLocationListener(object : AMapLocationListener {
          override fun onLocationChanged(location: AMapLocation?) {
            locationClient.stopLocation()
            if (location == null || location.errorCode != 0) {

            } else {

//              callback(Location(location.latitude, location.longitude))
            }
          }
        })
        locationClient.startLocation()
      }
      is PluginMethod.getPlatformVersion ->{
        result.success("Android ${android.os.Build.VERSION.RELEASE}")
      }
      else -> {
        result.success("unknown function")
      }
    }
  }




  private fun calculateLineDistance(lngAndLat1:LngAndLat, lngAndLat2:LngAndLat, @NonNull result: Result){
    val location1 = Location("")
    location1.latitude = lngAndLat1.latitude
    location1.longitude = lngAndLat1.longitude
    val location2 = Location("")
    location2.latitude = lngAndLat2.latitude
    location2.longitude = lngAndLat2.longitude
    result.success(location1.distanceTo(location2).toString())
  }


  private fun calculateLineDistanceByAmap(lngAndLat1:LngAndLat, lngAndLat2:LngAndLat, @NonNull result: Result){
    val startLatLng = LatLng(lngAndLat1.latitude,lngAndLat1.longitude)
    val endLatLng = LatLng(lngAndLat2.latitude,lngAndLat2.longitude)
    result.success(AMapUtils.calculateLineDistance(startLatLng,endLatLng).toString())
  }

//  private fun gpsLocation(){
//    if(locationManager==null){
//      locationManager = globalBinding.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
//    }
//    locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0f, locationListener)
//  }



  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }





}
