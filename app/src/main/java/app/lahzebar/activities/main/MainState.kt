package app.lahzebar.activities.main

import core.views.base.BaseState

sealed class MainState : BaseState {
    object Init : MainState()
}
