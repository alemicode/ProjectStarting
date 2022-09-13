package app.lahzebar.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.lahzebar.features.home.databinding.FragmentHomeBinding
import core.views.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeState, HomeAction, HomeViewModel>(
    HomeViewModel::class.java
) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentHomeBinding.inflate(inflater, container, false)
        .let { binding = it; binding.root }

    override fun initialView(emitAction: (HomeAction) -> Boolean) {
        // STOPSHIP
    }

    override fun render(state: HomeState, emitAction: (HomeAction) -> Boolean) {
        // STOPSHIP
    }
}
