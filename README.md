# echo_amap

高德地图相关

## Getting Started
import 'package:echo_amap/echo_amap.dart';

## Usage 简单使用

```dart
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
  final _echoAmapPlugin = EchoAmap();


  @override
  void initState() {
    super.initState();
    initPlatformState();
  }
  
  Future<void> initPlatformState() async {
    String platformVersion;
    try {
      platformVersion =
          await _echoAmapPlugin.getPlatformVersion() ?? 'Unknown platform version';
      var startPoint =  EchoLngAndLat(longitude: 103.822956, latitude: 30.066668);
      var endPoint =  EchoLngAndLat(longitude: 104.190556, latitude: 30.812500);
      distance1 = await _echoAmapPlugin.calculateLineDistance(startPoint, endPoint)??'';
      distance2 = await _echoAmapPlugin.calculateLineDistanceByAmap(startPoint, endPoint)??'';

    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }
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
            ],
          ),
        ),
      ),
    );
  }
}

```



