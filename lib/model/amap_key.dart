/// FileName amap_key
///
/// @Author LinGuanYu
/// @Date 2023/4/3 18:20
///
/// @Description TODO 高德地图key

class EchoAmapKey {
  String androidKey;
  String iosKey;
  EchoAmapKey({
    required this.androidKey,
    required this.iosKey,
  });
  factory EchoAmapKey.fromJson(Map<String, dynamic> json) => EchoAmapKey(
        androidKey: json["androidKey"],
        iosKey: json["iosKey"],
      );

  Map<String, dynamic> toJson() {
    final Map<String, dynamic> data = <String, dynamic>{};
    data['iosKey'] = iosKey;
    data["androidKey"] = androidKey;
    return data;
  }
  @override
  String toString() {
    return "the setting androidKey is $androidKey,iosKey $iosKey";
  }
}
