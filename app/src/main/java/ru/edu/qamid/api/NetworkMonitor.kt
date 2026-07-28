package ru.edu.qamid.api

object NetworkMonitor {
    var onRequestStart: () -> Unit = {}
    var onRequestEnd: () -> Unit = {}
}
