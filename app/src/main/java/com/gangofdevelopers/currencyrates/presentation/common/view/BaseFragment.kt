package com.gangofdevelopers.currencyrates.presentation.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import dagger.android.support.DaggerFragment

abstract class BaseFragment(@LayoutRes val layout: Int) : DaggerFragment() {
    private var instanceStateSaved: Boolean = false

    override fun onResume() {
        super.onResume()
        instanceStateSaved = false
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layout, container, false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        instanceStateSaved = true
    }

    @CallSuper
    override fun onDestroy() {
        if (needCloseScope()) {
            closeScope()
        }
        super.onDestroy()
    }

    //It will be valid only for 'onDestroy()' method
    private fun needCloseScope(): Boolean =
        when {
            activity?.isChangingConfigurations == true -> false
            activity?.isFinishing == true -> true
            else -> isRealRemoving()
        }

    private fun isRealRemoving(): Boolean =
        (isRemoving && !instanceStateSaved) //because isRemoving == true for fragment in backstack on screen rotation
                || ((parentFragment as? BaseFragment)?.isRealRemoving() ?: false)

    abstract fun closeScope()
}