package com.gangofdevelopers.currencyrates.presentation.screens.currency.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gangofdevelopers.currencyrates.domain.model.CurrencyDomainModel
import com.gangofdevelopers.currencyrates.presentation.common.viewmodel.BaseViewModel
import com.gangofdevelopers.currencyrates.presentation.screens.currency.flow.CurrencyPuller
import com.gangofdevelopers.currencyrates.presentation.screens.currency.mapper.CurrencyModelMapper
import com.gangofdevelopers.currencyrates.presentation.screens.currency.mapper.PresentationParams
import com.gangofdevelopers.currencyrates.presentation.screens.currency.model.CurrencyItem
import com.gangofdevelopers.currencyrates.presentation.screens.currency.model.CurrencyPresentationModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    private val mapper: CurrencyModelMapper,
    private val dataPuller: CurrencyPuller
) : BaseViewModel() {
    private val _viewState: MutableLiveData<ViewState> by lazy {
        MutableLiveData<ViewState>().apply {
            value = ViewState()
        }
    }

    val viewState: LiveData<ViewState> by lazy {
        initCurrency()
        _viewState
    }

    private fun initCurrency() {
        _viewState.value = currentViewState().copy(
            isLoading = true,
            isFirstLoadingError = false
        )

        val flow = dataPuller.pull(1000) {
            _viewState.postValue(
                currentViewState().copy(
                    isLoading = false,
                    isFirstLoadingError = true
                )
            )
        }

        uiScope.launch {
            flow.collect { model ->
                handleCurrency(model)
            }
        }
    }

    fun changeOrder(currency: CurrencyItem) {
        dataPuller.defaultCurrency = currency.title
        currentViewState().currencyPresentationModel.baseCurrencyValue =
            if (currency.value == 0.0) 0.0 else currency.value
    }

    fun setDefaultValue(value: String) {
        currentViewState().currencyPresentationModel.baseCurrencyValue =
            if (value.isEmpty()) 0.0 else value.toDouble()
    }

    private fun currentViewState() = _viewState.value!!

    private fun handleCurrency(currency: CurrencyDomainModel) {
        _viewState.postValue(
            currentViewState().copy(
                isLoading = false,
                currencyPresentationModel = mapper.map(
                    currency,
                    PresentationParams(currentViewState().currencyPresentationModel.baseCurrencyValue)
                ),
                isFirstLoadingError = false
            )
        )
    }

    override fun clearResources() {
        super.clearResources()
        dataPuller.close()
    }

    data class ViewState(
        val isLoading: Boolean = false,
        val isFirstLoadingError: Boolean = false,
        val currencyPresentationModel: CurrencyPresentationModel = CurrencyPresentationModel(
            "EUR",
            items = listOf()
        )
    )
}
