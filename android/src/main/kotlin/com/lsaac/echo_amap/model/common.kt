package com.lsaac.echo_amap.model

sealed class PluginMethod {
    object calculateLineDistance:PluginMethod()
    object fuckYou:PluginMethod()

    object unKown:PluginMethod()
    object calculateLineDistanceByAmap:PluginMethod()
    object getCurrentLocation:PluginMethod()
    object getCurrentLocationByAmap:PluginMethod()

    object getPlatformVersion:PluginMethod()
}