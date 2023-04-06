// import 'dart:ffi';
//
// import 'package:flutter/cupertino.dart';
// import 'package:flutter/services.dart';
//
// import '../model/echo_lng_lat.dart';
// import 'echo_amap_util_platform_interface.dart';
//
// /// FileName echo_amap_util_method_channel
// ///
// /// @Author LinGuanYu
// /// @Date 2023/4/3 18:40
// ///
// /// @Description TODO
// class MethodChannelEchoAmapUtils extends EchoAmapUtilPlatform {
//   /// The method channel used to interact with the native platform.
//   @visibleForTesting
//   final methodChannel = const MethodChannel('echo_amap_util');
//
//
//
//   @override
//   Future<bool> setApiKey(String key) {
//     // TODO: implement setApiKey
//     return super.setApiKey(key);
//   }
//
//   @override
//   Future<String?> calculateLineDistance(EchoLngAndLat lngAndLat1, EchoLngAndLat lngAndLat2) async{
//     final version = await methodChannel.invokeMethod<String>('calculateLineDistance',[
//       lngAndLat1.longitude,lngAndLat1.latitude,lngAndLat2.longitude,lngAndLat2.latitude
//     ]);
//
//     return version;
//   }
//
//   @override
//   Future<String?> calculateLineDistanceByAmap(EchoLngAndLat lngAndLat1, EchoLngAndLat lngAndLat2) async{
//     final version = await methodChannel.invokeMethod<String>('calculateLineDistanceByAmap',[
//       lngAndLat1.longitude,lngAndLat1.latitude,lngAndLat2.longitude,lngAndLat2.latitude
//     ]);
//     return version;
//   }
//
//   @override
//   Future<EchoLngAndLat?> getCurrentLocation() async{
//
//     Map? res = await methodChannel.invokeMapMethod("getCurrentLocation");
//     var temp = Map<String,double>.from(res??{});
//     return EchoLngAndLat(longitude: temp["longitude"]??0, latitude:  temp["latitude"]??0);
//   }
//
//   @override
//   Future<EchoLngAndLat?> getCurrentLocationByAmap() async{
//     // TODO: implement getCurrentLocationByAmap
//     return super.getCurrentLocationByAmap();
//   }
//
//
// }
