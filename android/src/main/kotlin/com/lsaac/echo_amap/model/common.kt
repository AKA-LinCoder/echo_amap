package com.lsaac.echo_amap.model

sealed class UtilPluginMethod {
    object calculateLineDistance:UtilPluginMethod()
    object fuckYou:UtilPluginMethod()

    object unKown:UtilPluginMethod()
    object calculateLineDistanceByAmap:UtilPluginMethod()
    object getCurrentLocation:UtilPluginMethod()
    object getCurrentLocationByAmap:UtilPluginMethod()
}