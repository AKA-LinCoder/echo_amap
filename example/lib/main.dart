import 'package:echo_amap/model/echo_lng_lat.dart';
import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:echo_amap/echo_amap.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({super.key});

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';
  String distance1 = "0";
  String distance2 = "0";
  EchoLngAndLat? location1;
  EchoLngAndLat? location2;
  final _echoAmapPlugin = EchoAmap();


  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      platformVersion =
          await _echoAmapPlugin.getPlatformVersion() ?? 'Unknown platform version';
      var startPoint =  EchoLngAndLat(longitude: 103.822956, latitude: 30.066668);
      var endPoint =  EchoLngAndLat(longitude: 104.190556, latitude: 30.812500);
      distance1 = await _echoAmapPlugin.calculateLineDistance(startPoint, endPoint)??'';
      distance2 = await _echoAmapPlugin.calculateLineDistanceByAmap(startPoint, endPoint)??'';
      // location1 = await _echoAmapPlugin.getCurrentLocation();
      // location2 = await _echoAmapPlugin.getCurrentLocationByAmap();

    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Echo amap'),
        ),
        body: Center(
          child: Column(
            children: [
              Text('Running on: $_platformVersion\n'),
              Text('原生距离: $distance1\n'),
              Text('高德距离: $distance2\n'),
              // Text("当前距离:$location1"),
              // Text("当前距离:$location2"),
            ],
          ),
        ),
      ),
    );
  }
}
