package com.gangofdevelopers.currencyrates.presentation.screens.currency.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.gangofdevelopers.currencyrates.R
import com.gangofdevelopers.currencyrates.presentation.common.view.BaseFragment
import com.gangofdevelopers.currencyrates.presentation.common.view.viewModel
import com.gangofdevelopers.currencyrates.presentation.common.view.visibility
import com.gangofdevelopers.currencyrates.presentation.common.viewmodel.ViewModelFactory
import com.gangofdevelopers.currencyrates.presentation.common.viewmodel.observeViewState
import com.gangofdevelopers.currencyrates.presentation.common.viewmodel.viewSateWatcher
import com.gangofdevelopers.currencyrates.presentation.screens.currency.recyclerview.CurrencyAdapter
import com.gangofdevelopers.currencyrates.presentation.screens.currency.viewmodel.CurrencyViewModel
import kotlinx.android.synthetic.main.fragment_currency.*
import javax.inject.Inject

class CurrencyFragment : BaseFragment(R.layout.fragment_currency) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: CurrencyViewModel

    private val watcher =
        viewSateWatcher<CurrencyViewModel.ViewState> {
            CurrencyViewModel.ViewState::currencyPresentationModel{
                //To keep scroll position on the same place after updating list we need save and set it's state
                val state = currencyRecyclerView.layoutManager?.onSaveInstanceState()
                currencyAdapter.submitList(it.items)
                currencyRecyclerView.layoutManager?.onRestoreInstanceState(state)
            }

            CurrencyViewModel.ViewState::isLoading{
                currencyProgressBar.visibility(it)
            }

            CurrencyViewModel.ViewState::isFirstLoadingError { firstLoadingError ->
                showEmptyListError(firstLoadingError)
            }
        }

    private val currencyAdapter: CurrencyAdapter by lazy {
        CurrencyAdapter().apply {
            listener = {
                //when we choose item to become as responder we should scroll to first position
                currencyRecyclerView.smoothScrollToPosition(0)
                viewModel.changeOrder(it)
            }

            textWatcherListener = {
                viewModel.setDefaultValue(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()

        viewModel = viewModel(viewModelFactory) {
            observeViewState(viewState) {
                watcher.render(it)
            }
        }
    }

    private fun initRecyclerView() {
        currencyRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = currencyAdapter
            setHasFixedSize(true)
            itemAnimator = null
        }
    }

    private fun showEmptyListError(firstLoadingError: Boolean) {
        currencyRecyclerView.visibility(!firstLoadingError)
        noInternet.visibility(firstLoadingError)
    }

    override fun closeScope() {
        viewModel.clearResources()
    }
}
