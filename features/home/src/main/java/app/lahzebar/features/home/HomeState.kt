package app.lahzebar.features.home

import core.views.base.BaseState

sealed class HomeState : BaseState {
    object Init : HomeState()
}
