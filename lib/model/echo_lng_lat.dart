/// FileName echo_lng_lat
///
/// @Author LinGuanYu
/// @Date 2023/4/3 18:46
///
/// @Description TODO 线桩号model

class EchoLngAndLat {
  double longitude;
  double latitude;
  EchoLngAndLat({
    required this.longitude,
    required this.latitude,
  });
  factory EchoLngAndLat.fromJson(Map<String, dynamic> json) => EchoLngAndLat(
    longitude: json["longitude"],
    latitude: json["latitude"],
  );

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['latitude'] = latitude;
    data["longitude"] = longitude;
    return data;
  }
  @override
  String toString() {
    return "the setting longitude is $longitude,latitude $latitude";
  }
}