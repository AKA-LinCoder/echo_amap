package com.lsaac.echo_amap.extension

import com.lsaac.echo_amap.model.UtilPluginMethod

fun String.toUtilPluginMethod(): UtilPluginMethod {
    return  when(this){
        "calculateLineDistance" ->UtilPluginMethod.calculateLineDistance
        "fuckYou" -> UtilPluginMethod.fuckYou
        "calculateLineDistanceByAmap" -> UtilPluginMethod.calculateLineDistanceByAmap
        "getCurrentLocation" -> UtilPluginMethod.getCurrentLocation
        "getCurrentLocationByAmap" -> UtilPluginMethod.getCurrentLocationByAmap
        else -> UtilPluginMethod.unKown
    }
}