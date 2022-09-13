package core.views.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

abstract class BaseViewModel<S : BaseState, A : BaseAction, M : BaseMutation> : ViewModel() {

    private val mutableAction = MutableSharedFlow<A>(
        replay = 0,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
        extraBufferCapacity = 1
    )

    private val mutableState = mutableState()
    val state: StateFlow<S> = mutableState.asStateFlow()
    private val mutex = Mutex()

    init {
        mutableAction.asSharedFlow().onEach { a ->
            handle(a).onEach { mutation ->
                mutex.withLock {
                    mutableState.value = reduce(mutation)
                }
            }.launchIn(viewModelScope)
        }.launchIn(viewModelScope)
    }

    fun tryEmitAction(action: A) = mutableAction.tryEmit(action)

    private fun mutableState() = MutableStateFlow(initialState())
    abstract fun initialState(): S
    abstract fun handle(action: A): Flow<M>
    abstract fun reduce(mutation: M): S
}
