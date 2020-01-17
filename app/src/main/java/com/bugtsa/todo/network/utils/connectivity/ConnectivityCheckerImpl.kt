package com.bugtsa.todo.network.utils.connectivity

import android.content.Context
import android.net.NetworkInfo
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.observables.ConnectableObservable
import io.reactivex.schedulers.Schedulers

class ConnectivityCheckerImpl(context: Context) : ConnectivityChecker {
    private val channelReadyConsumer: Relay<Boolean>

    private val connectionState: ConnectableObservable<ConnectionState>

    private val internet = ReactiveNetwork
            .observeNetworkConnectivity(context.applicationContext)
            .map { connectivity -> connectivity.state() == NetworkInfo.State.CONNECTED }
            .subscribeOn(Schedulers.io())

    init {
        channelReadyConsumer = BehaviorRelay.createDefault(false)

        connectionState = Observable.combineLatest(
                internet,
                channelReadyConsumer,
                BiFunction<Boolean, Boolean, ConnectionState> { isNetworkConnected, isChannelReady ->
                    when {
                        !isNetworkConnected -> ConnectionState.DISCONNECTED
                        isChannelReady -> ConnectionState.CONNECTED
                        else -> ConnectionState.CONNECTING
                    }
                }).replay()
        connectionState.connect()
    }

    override fun observeConnectionStateToServer() = connectionState

    override fun setChannelConnected(isConnected: Boolean) {
        channelReadyConsumer.accept(isConnected)
    }

    override fun getIsInternetAvailable(): Single<Boolean> {
        return ReactiveNetwork.checkInternetConnectivity()
    }

    override fun observeIsInternetAvailable(): Observable<Boolean> = internet

}

enum class ConnectionState {
    /**
     * No network connection.
     */
    DISCONNECTED,
    /**
     * Network connection established. No server connection.
     */
    CONNECTING,
    /**
     * Server connection established.
     */
    CONNECTED
}