package com.bbm.ataramor.core.utils

import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.NonCancellable.cancel
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

internal class CoroutineScopeInstance(context: CoroutineContext) : InstanceKeeper.Instance, CoroutineScope {
    override val coroutineContext: CoroutineContext = context + SupervisorJob()

    override fun onDestroy() {
        cancel()
    }
}

fun InstanceKeeper.coroutineScope(context: CoroutineContext = Dispatchers.Main): CoroutineScope =
    getOrCreate { CoroutineScopeInstance(context) }