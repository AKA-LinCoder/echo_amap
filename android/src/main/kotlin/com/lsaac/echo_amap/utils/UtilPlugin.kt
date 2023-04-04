package com.lsaac.echo_amap.utils

import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import androidx.annotation.NonNull
import com.amap.api.maps.AMapUtils
import com.amap.api.maps.model.LatLng
import com.lsaac.echo_amap.EchoAmapPlugin
import com.lsaac.echo_amap.extension.toUtilPluginMethod
import com.lsaac.echo_amap.model.LngAndLat
import com.lsaac.echo_amap.model.UtilPluginMethod
import io.flutter.Log
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry
import io.flutter.plugin.common.PluginRegistry.Registrar

class UtilPlugin:FlutterPlugin,MethodCallHandler,ActivityAware {

    private lateinit var utilChannel: MethodChannel

    private  var locationManager:LocationManager? = null
    private lateinit var locationListener: LocationListener

    var mContext: Context? = null

    private lateinit var globalBinding: FlutterPlugin.FlutterPluginBinding


    private var binding:ActivityPluginBinding? = null

//       fun  UtilPlugin(binding: FlutterPlugin.FlutterPluginBinding) {
//            this.mContext = binding.applicationContext
//            globalBinding = binding
//        }
//    companion object{
//        @JvmStatic
//        fun  registerWith(binding: FlutterPlugin.FlutterPluginBinding){
//
//        }
//    }
    //这个方法只会被调用一次
    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        utilChannel = MethodChannel(binding.binaryMessenger,"echo_amap_util")
        utilChannel.setMethodCallHandler(this)

        this.mContext = binding.applicationContext
        globalBinding = binding

        locationManager = binding.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationListener = object : LocationListener {
            override fun onLocationChanged(location: Location) {
                val latitude = location.latitude
                val longitude = location.longitude
                val result = mapOf("latitude" to latitude, "longitude" to longitude)
                utilChannel.invokeMethod("onLocationChanged", result)
            }
            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {}
            override fun onProviderEnabled(provider: String) {}
            override fun onProviderDisabled(provider: String) {}

        }
    }
    //执行多次
    override fun onMethodCall(call: MethodCall, result: Result) {

        if(locationManager==null){
            locationManager = mContext?.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
        }

        when (call.method.toUtilPluginMethod()){
            is UtilPluginMethod.calculateLineDistance ->{
                val  arguments = call.arguments as List<Double>
                val lngAndLat1 = LngAndLat(latitude = arguments[1], longitude = arguments[0])
                val lngAndLat2 = LngAndLat(latitude = arguments[3], longitude = arguments[2])
                calculateLineDistance( lngAndLat1 = lngAndLat1, lngAndLat2 = lngAndLat2, result = result)
            }
            is UtilPluginMethod.calculateLineDistanceByAmap ->{
                val  arguments = call.arguments as List<Double>
                val lngAndLat1 = LngAndLat(latitude = arguments[1], longitude = arguments[0])
                val lngAndLat2 = LngAndLat(latitude = arguments[3], longitude = arguments[2])
                calculateLineDistanceByAmap( lngAndLat1 = lngAndLat1, lngAndLat2 = lngAndLat2, result = result)
            }

            is UtilPluginMethod.getCurrentLocation ->{

                // 通过GPS定位
                val location = locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                val latitude = location?.latitude ?: 0.0
                val longitude = location?.longitude ?: 0.0
                result.success(mapOf<String,Double>("latitude" to latitude, "longitude" to longitude))
            }
            is UtilPluginMethod.getCurrentLocationByAmap ->{

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

    private fun gpsLocation(){
        if(locationManager==null){
            locationManager = globalBinding.applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }
        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER,0, 0f, locationListener)
    }


    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        utilChannel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
       this.binding = binding
    }

    override fun onDetachedFromActivityForConfigChanges() {
        TODO("Not yet implemented")
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        TODO("Not yet implemented")
    }

    override fun onDetachedFromActivity() {
        TODO("Not yet implemented")
    }


}