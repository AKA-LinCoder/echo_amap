import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'echo_amap_method_channel.dart';
import 'model/echo_lng_lat.dart';

abstract class EchoAmapPlatform extends PlatformInterface {
  /// Constructs a EchoAmapPlatform.
  EchoAmapPlatform() : super(token: _token);

  static final Object _token = Object();

  static EchoAmapPlatform _instance = MethodChannelEchoAmap();

  /// The default instance of [EchoAmapPlatform] to use.
  ///
  /// Defaults to [MethodChannelEchoAmap].
  static EchoAmapPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [EchoAmapPlatform] when
  /// they register themselves.
  static set instance(EchoAmapPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
  ///@title setApiKey
  ///@description TODO 设置对应平台的key
  ///@param: key
  ///@return: Future<bool>
  ///@updateTime 2023/4/3 18:30
  ///@author LinGuanYu
  Future<bool> setApiKey(String key) {
    throw UnimplementedError('setApiKey() has not been implemented.');
  }

  ///@title calculateLineDistance
  ///@description TODO 通过系统自带的办法计算经纬度距离
  ///@param: lngAndLat1
  ///@param: lngAndLat2
  ///@return: Future<String?>
  ///@updateTime 2023/4/3 18:49
  ///@author LinGuanYu
  Future<String?> calculateLineDistance(EchoLngAndLat lngAndLat1,EchoLngAndLat lngAndLat2)async{
    throw UnimplementedError('calculateLineDistance() has not been implemented.');

  }
  ///@title calculateLineDistanceByAmap
  ///@description TODO 通过高德地图计算经纬度距离
  ///@param: lngAndLat1
  ///@param: lngAndLat2
  ///@return: Future<String?>
  ///@updateTime 2023/4/3 18:50
  ///@author LinGuanYu
  Future<String?> calculateLineDistanceByAmap(EchoLngAndLat lngAndLat1,EchoLngAndLat lngAndLat2)async{
    throw UnimplementedError('calculateLineDistanceByAmap() has not been implemented.');
  }

  ///@title getCurrentLocation
  ///@description TODO  通过系统自带的办法获取当前经纬度
  ///@return: Future
  ///@updateTime 2023/4/3 18:58
  ///@author LinGuanYu
  Future<EchoLngAndLat?> getCurrentLocation()async{
    throw UnimplementedError('getCurrentLocation() has not been implemented.');
  }

  ///@title getCurrentLocationByAmap
  ///@description TODO 通过高德地图获取当前经纬度
  ///@return: Future
  ///@updateTime 2023/4/3 18:59
  ///@author LinGuanYu
  Future<EchoLngAndLat?> getCurrentLocationByAmap()async{
    throw UnimplementedError('getCurrentLocationByAmap() has not been implemented.');
  }
}
