package com.example.ministockbitapp.view.watchlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ministockbitapp.R
import com.example.ministockbitapp.data.crypto.model.CryptoData
import com.example.ministockbitapp.databinding.ItemCryptoBinding
import com.example.ministockbitapp.utils.trimTrailingZero

class WatchlistAdapter (
    val data: MutableList<CryptoData> = mutableListOf()
): RecyclerView.Adapter<WatchlistAdapter.ViewHolder>(){

    fun setCryptoData(cryptoList: List<CryptoData>) {
        if (data.size > 0) {
            data.clear()
        }
        data.addAll(cryptoList)
        notifyDataSetChanged()
    }

    fun clearData(){
        data.clear()
        notifyDataSetChanged()
    }

    fun loadMoreData(moreData: List<CryptoData>){
        data.addAll(moreData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCryptoBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return WatchlistViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cryptoItem: CryptoData = data[position]
        val watchlistViewHolder = holder as WatchlistViewHolder
        watchlistViewHolder.bindCryptoItem(cryptoItem)
    }

    open inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class WatchlistViewHolder(private val binding: ItemCryptoBinding) : ViewHolder(binding.root) {
        fun bindCryptoItem(cryptoItem: CryptoData) {
            with(itemView) {
                binding.tvCryptoName.text = cryptoItem.coinInfo.name
                binding.tvCryptoFullName.text = cryptoItem.coinInfo.fullName

                if (cryptoItem.RAW != null){
                    if (cryptoItem.RAW.USD.changeDay.toFloat()<0) binding.tvPriceChange.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                    else binding.tvPriceChange.setTextColor(ContextCompat.getColor(context, R.color.green))
                }

                if (cryptoItem.display != null){
                    binding.tvCryptoPrice.text = cryptoItem.display.USD?.price
                    val priceChange = trimTrailingZero(cryptoItem.display.USD?.changeDay)
                    binding.tvPriceChange.text = String.format("${priceChange}(${cryptoItem.display.USD?.changePct}%%)")
                } else {
                    binding.tvCryptoPrice.text = context.getString(R.string.label_no_data)
                    binding.tvPriceChange.text = context.getString(R.string.label_no_data)
                }

                setOnClickListener {}
            }
        }
    }
}