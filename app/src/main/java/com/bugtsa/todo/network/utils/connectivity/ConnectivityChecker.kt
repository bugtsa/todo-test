package com.bugtsa.todo.network.utils.connectivity

import io.reactivex.Observable
import io.reactivex.Single

interface ConnectivityChecker {

    fun observeConnectionStateToServer(): Observable<ConnectionState>

    fun setChannelConnected(isConnected: Boolean)

    fun getIsInternetAvailable(): Single<Boolean>

    fun observeIsInternetAvailable(): Observable<Boolean>
}