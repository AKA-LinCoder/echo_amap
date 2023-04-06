package com.lsaac.echo_amap.extension

import com.lsaac.echo_amap.model.PluginMethod


fun String.toPluginMethod(): PluginMethod {
    return  when(this){
        "calculateLineDistance" ->PluginMethod.calculateLineDistance
        "fuckYou" -> PluginMethod.fuckYou
        "calculateLineDistanceByAmap" -> PluginMethod.calculateLineDistanceByAmap
        "getCurrentLocation" -> PluginMethod.getCurrentLocation
        "getCurrentLocationByAmap" -> PluginMethod.getCurrentLocationByAmap
        "getPlatformVersion" -> PluginMethod.getPlatformVersion
        else -> PluginMethod.unKown
    }
}