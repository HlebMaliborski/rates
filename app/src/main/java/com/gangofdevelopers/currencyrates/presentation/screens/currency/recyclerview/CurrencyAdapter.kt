package com.gangofdevelopers.currencyrates.presentation.screens.currency.recyclerview

import android.text.InputFilter
import android.text.Spanned
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.widget.doAfterTextChanged
import com.gangofdevelopers.currencyrates.R
import com.gangofdevelopers.currencyrates.presentation.common.recyclerview.BaseRecyclerAdapter
import com.gangofdevelopers.currencyrates.presentation.common.recyclerview.BaseViewHolder
import com.gangofdevelopers.currencyrates.presentation.common.recyclerview.notify
import com.gangofdevelopers.currencyrates.presentation.screens.currency.model.CurrencyItem
import kotlinx.android.synthetic.main.currency_item.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class CurrencyAdapter :
    BaseRecyclerAdapter<CurrencyItem, BaseViewHolder<CurrencyItem>>() {
    var listener: ((item: CurrencyItem) -> Unit)? = null
    var textWatcherListener: ((value: String) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CurrencyItem> {
        return if (viewType == RESPONDER_ITEM) {
            ResponderCurrencyViewHolder(
                inflate(R.layout.currency_item, parent)
            )
        } else {
            CurrencyViewHolder(
                inflate(R.layout.currency_item, parent)
            )
        }
    }

    override fun submitList(newList: List<CurrencyItem>) {
        val oldList = items
        notify(oldList, newList) { o, n -> o.value == n.value }
        items = newList
    }

    override fun getItemViewType(position: Int) = if (position == 0) {
        RESPONDER_ITEM
    } else {
        NORMAL_ITEM
    }

    companion object {
        const val RESPONDER_ITEM = 0
        const val NORMAL_ITEM = 1
    }

    inner class CurrencyViewHolder(override val containerView: View) :
        BaseViewHolder<CurrencyItem>(containerView) {
        override fun bind(data: CurrencyItem) {
            currencyTitle.text = data.title
            currencySubtitle.text = data.subtitle
            currencyValue.setText(if (data.value == 0.0) "" else data.value.toString())
            currencyImage.setImageResource(data.imgRes)
            currencyValue.isEnabled = false

            if (listener != null) itemView.setOnClickListener {
                listener?.invoke(data)
            }
        }
    }

    inner class ResponderCurrencyViewHolder(override val containerView: View) :
        BaseViewHolder<CurrencyItem>(containerView) {
        override fun bind(data: CurrencyItem) {
            currencyTitle.text = data.title
            currencySubtitle.text = data.subtitle
            currencyValue.setText(if (data.value == 0.0) "" else data.value.toString())
            currencyValue.requestFocus()
            if (data.isResponder) {
                currencyValue.filters = arrayOf<InputFilter>(DecimalLimiter(10, 2))
                currencyValue.imeOptions = EditorInfo.IME_ACTION_DONE
                currencyValue.doAfterTextChanged { value ->
                    if (adapterPosition == 0) {
                        val convertedValue =
                            if (value.isNullOrEmpty() || value.toString() == ".") 0.0 else value.toString()
                                .toDouble()

                        items[adapterPosition].value = convertedValue
                        textWatcherListener?.invoke(convertedValue.toString())
                    }
                }
            }

            currencyImage.setImageResource(data.imgRes)

            if (listener != null) itemView.setOnClickListener {
                listener?.invoke(data)
            }
        }
    }

    class DecimalLimiter(digitsBeforeZero: Int, digitsAfterZero: Int) : InputFilter {
        private var mPattern: Pattern =
            Pattern.compile("[0-9]{0,$digitsBeforeZero}+((\\.[0-9]{0,$digitsAfterZero})?)||(\\.)?")

        override fun filter(
            source: CharSequence,
            start: Int,
            end: Int,
            dest: Spanned,
            dstart: Int,
            dend: Int
        ): CharSequence? {
            val sb = StringBuilder(dest)
            sb.insert(dstart, source, start, end)
            val matcher: Matcher = mPattern.matcher(sb.toString())
            return if (!matcher.matches()) "" else null
        }
    }
}