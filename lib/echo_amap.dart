


import 'echo_amap_platform_interface.dart';
import 'model/echo_lng_lat.dart';

class EchoAmap {
  Future<String?> getPlatformVersion() {
    return EchoAmapPlatform.instance.getPlatformVersion();
  }

  Future<String?> calculateLineDistance(EchoLngAndLat lngAndLat1, EchoLngAndLat lngAndLat2) {
    return EchoAmapPlatform.instance.calculateLineDistance(lngAndLat1,lngAndLat2);
  }

  Future<String?> calculateLineDistanceByAmap(EchoLngAndLat lngAndLat1, EchoLngAndLat lngAndLat2) {
    return EchoAmapPlatform.instance.calculateLineDistanceByAmap(lngAndLat1,lngAndLat2);
  }

  Future<EchoLngAndLat?> getCurrentLocation() {
    return EchoAmapPlatform.instance.getCurrentLocation();
  }
  Future<EchoLngAndLat?> getCurrentLocationByAmap() {
    return EchoAmapPlatform.instance.getCurrentLocationByAmap();
  }
}
