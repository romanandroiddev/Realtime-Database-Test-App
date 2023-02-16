package com.example.realtimetest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.realtimetest.databinding.ItemMoneyBinding

class CurrencyAdapter : ListAdapter<Currency, CurrencyAdapter.ViewHolder>(myDiffCall) {
    inner class ViewHolder(private val binding: ItemMoneyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val d = getItem(adapterPosition)
            binding.tvCurrencyName.text = d.name
            binding.tvCurrency.text = d.amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMoneyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind()


    private object myDiffCall : DiffUtil.ItemCallback<Currency>() {
        override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
            return oldItem.name == newItem.name && oldItem.amount == newItem.amount
        }

    }
}