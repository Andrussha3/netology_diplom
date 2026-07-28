package ru.edu.qamid

import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.idling.CountingIdlingResource
import ru.edu.qamid.api.NetworkMonitor

object OkHttpIdlingResource {

    private val countingResource = CountingIdlingResource("OkHttp")

    fun register() {
        NetworkMonitor.onRequestStart = { countingResource.increment() }
        NetworkMonitor.onRequestEnd = { countingResource.decrement() }
        IdlingRegistry.getInstance().register(countingResource)
    }

    fun unregister() {
        NetworkMonitor.onRequestStart = {}
        NetworkMonitor.onRequestEnd = {}
        IdlingRegistry.getInstance().unregister(countingResource)
    }
}
