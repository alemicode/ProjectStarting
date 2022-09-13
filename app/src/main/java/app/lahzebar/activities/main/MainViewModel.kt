package app.lahzebar.activities.main

import core.views.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class MainViewModel @Inject constructor() : BaseViewModel<MainState, MainAction, MainMutation>() {
    override fun initialState(): MainState = MainState.Init

    override fun handle(action: MainAction): Flow<MainMutation> = emptyFlow()

    override fun reduce(mutation: MainMutation): MainState = MainState.Init
}
