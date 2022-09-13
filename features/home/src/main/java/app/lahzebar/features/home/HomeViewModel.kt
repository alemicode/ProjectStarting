package app.lahzebar.features.home

import core.views.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor() : BaseViewModel<HomeState, HomeAction, HomeMutation>() {
    override fun initialState(): HomeState = HomeState.Init

    override fun handle(action: HomeAction): Flow<HomeMutation> = emptyFlow()

    override fun reduce(mutation: HomeMutation): HomeState = HomeState.Init
}
