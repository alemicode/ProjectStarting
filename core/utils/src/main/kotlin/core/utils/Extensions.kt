package core.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

fun <T> Flow<T>.firstLaunchIn(scope: CoroutineScope): Job = scope.launch {
    firstOrNull() // tail-call
}
