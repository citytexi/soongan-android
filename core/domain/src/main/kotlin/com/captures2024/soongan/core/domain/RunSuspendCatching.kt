package com.captures2024.soongan.core.domain

import timber.log.Timber
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract
import kotlin.coroutines.cancellation.CancellationException

@OptIn(ExperimentalContracts::class)
internal inline fun <T> runSuspendCatching(block: () -> T): Result<T> {
    contract { callsInPlace(block, InvocationKind.EXACTLY_ONCE) }
    return runCatching(block).also { result ->
        val exception = result.exceptionOrNull()

        if (exception != null) {
            Timber.tag("runSuspendCatching").e("exception = $exception")
        }

        if (exception is CancellationException) throw exception
    }
}