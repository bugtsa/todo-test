package com.bugtsa.todo.data

import android.content.Context
import com.bugtsa.todo.network.utils.connectivity.ConnectivityChecker
import com.bugtsa.todo.network.utils.connectivity.ConnectivityCheckerImpl
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class ConnectivityCheckerTest : AutoCloseKoinTest() {

    private val context: Context = mockk()

    private lateinit var connectivityChecker: ConnectivityChecker

    @Before
    fun setup() {
        clearAllMocks()
        every {context.applicationContext } returns context
        connectivityChecker = ConnectivityCheckerImpl(context)
    }

    @Test
    fun setupChannelConnected() {
        connectivityChecker.setChannelConnected(true)
    }

    @Test
    fun observeConnectionStateToServer() {
        connectivityChecker.observeConnectionStateToServer()
                .test()
                .assertNoErrors()
                .assertSubscribed()
    }

    @Test
    fun getIsInternetAvailable() {
        connectivityChecker.getIsInternetAvailable()
                .test()
                .assertNoErrors()
                .assertSubscribed()
    }

    @Test
    fun observeInternetAvailable() {
        connectivityChecker.observeIsInternetAvailable()
                .test()
                .assertNoErrors()
                .assertSubscribed()
    }

}