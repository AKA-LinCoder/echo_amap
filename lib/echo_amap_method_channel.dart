import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'echo_amap_platform_interface.dart';
import 'model/echo_lng_lat.dart';

/// An implementation of [EchoAmapPlatform] that uses method channels.
class MethodChannelEchoAmap extends EchoAmapPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('echo_amap');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }

  @override
  Future<bool> setApiKey(String key) {
    // TODO: implement setApiKey
    return super.setApiKey(key);
  }

  @override
  Future<String?> calculateLineDistance(EchoLngAndLat lngAndLat1, EchoLngAndLat lngAndLat2) async{
    final version = await methodChannel.invokeMethod<String>('calculateLineDistance',[
      lngAndLat1.longitude,lngAndLat1.latitude,lngAndLat2.longitude,lngAndLat2.latitude
    ]);

    return version;
  }

  @override
  Future<String?> calculateLineDistanceByAmap(EchoLngAndLat lngAndLat1, EchoLngAndLat lngAndLat2) async{
    final version = await methodChannel.invokeMethod<String>('calculateLineDistanceByAmap',[
      lngAndLat1.longitude,lngAndLat1.latitude,lngAndLat2.longitude,lngAndLat2.latitude
    ]);
    return version;
  }

  // @override
  // Future<EchoLngAndLat?> getCurrentLocation() async{
  //   Map? res = await methodChannel.invokeMapMethod("getCurrentLocation");
  //   var temp = Map<String,double>.from(res??{});
  //   return EchoLngAndLat(longitude: temp["longitude"]??0, latitude:  temp["latitude"]??0);
  // }
  //
  // @override
  // Future<EchoLngAndLat?> getCurrentLocationByAmap() async{
  //   Map? res = await methodChannel.invokeMapMethod("getCurrentLocationByAmap");
  //   var temp = Map<String,double>.from(res??{});
  //   return EchoLngAndLat(longitude: temp["longitude"]??0, latitude:  temp["latitude"]??0);
  // }
}
