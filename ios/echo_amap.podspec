#
# To learn more about a Podspec see http://guides.cocoapods.org/syntax/podspec.html.
# Run `pod lib lint echo_amap.podspec` to validate before publishing.
#
Pod::Spec.new do |s|
  s.name             = 'echo_amap'
  s.version          = '0.0.1'
  s.summary          = '高德地图相关'
  s.description      = <<-DESC
高德地图相关
                       DESC
  s.homepage         = 'http://example.com'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'Your Company' => 'email@example.com' }
  s.source           = { :path => '.' }
  s.source_files = 'Classes/**/*'
  s.dependency 'Flutter'
  s.dependency 'AMapLocation'
  s.dependency 'AMap3DMap'
  s.dependency 'AMapSearch'
  #解决'Pods-Runner' target has transitive dependencies that include statically linked binaries: (/Users/estim/Desktop/Lin/my_flutter/plugin_test/example/ios/Pods/AMapLocation/AMapLocationKit.framework)
  s.static_framework = true
  s.platform = :ios, '9.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.swift_version = '5.0'
end
