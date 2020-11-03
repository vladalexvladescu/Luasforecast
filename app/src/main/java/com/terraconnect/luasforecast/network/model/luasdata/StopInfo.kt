package com.terraconnect.luasforecast.network.model.luasdata

data class StopInfo(
    val created: String,
    val direction: List<Direction?>,
    val message: String,
    val stop: String,
    val stopAbv: String
)