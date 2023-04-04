// import 'package:flutter_test/flutter_test.dart';
// import 'package:echo_amap/echo_amap.dart';
// import 'package:echo_amap/echo_amap_platform_interface.dart';
// import 'package:echo_amap/echo_amap_method_channel.dart';
// import 'package:plugin_platform_interface/plugin_platform_interface.dart';
//
// class MockEchoAmapPlatform
//     with MockPlatformInterfaceMixin
//     implements EchoAmapPlatform {
//
//   @override
//   Future<String?> getPlatformVersion() => Future.value('42');
// }
//
// void main() {
//   final EchoAmapPlatform initialPlatform = EchoAmapPlatform.instance;
//
//   test('$MethodChannelEchoAmap is the default instance', () {
//     expect(initialPlatform, isInstanceOf<MethodChannelEchoAmap>());
//   });
//
//   test('getPlatformVersion', () async {
//     EchoAmap echoAmapPlugin = EchoAmap();
//     MockEchoAmapPlatform fakePlatform = MockEchoAmapPlatform();
//     EchoAmapPlatform.instance = fakePlatform;
//
//     expect(await echoAmapPlugin.getPlatformVersion(), '42');
//   });
// }
