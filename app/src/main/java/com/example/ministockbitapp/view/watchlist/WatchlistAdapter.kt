package com.example.ministockbitapp.view.watchlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.ministockbitapp.R
import com.example.ministockbitapp.data.crypto.model.CryptoData
import com.example.ministockbitapp.utils.trimTrailingZero
import kotlinx.android.synthetic.main.item_crypto.view.*

class WatchlistAdapter (
    val data: MutableList<CryptoData> = mutableListOf()
): RecyclerView.Adapter<WatchlistAdapter.ViewHolder>(){

    fun setCryptoData(cryptoList: List<CryptoData>) {
        if (data.size > 0) {
            data.clear()
        }
        data.addAll(cryptoList)
        Log.d("DataAdapter", data.count().toString())
        notifyDataSetChanged()
    }

    fun loadMoreData(moreData: List<CryptoData>){
        data.addAll(moreData)
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: CryptoData) {
        val i: Int = data.indexOf(item)
        if (i >= 0) {
            data[i] = item
            notifyDataSetChanged()
        } else {
            data.add(item)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_crypto,
            viewGroup, false
        )
        return WatchlistViewHolder(view)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cryptoItem: CryptoData = data[position]
        val watchlistViewHolder = holder as WatchlistViewHolder
        watchlistViewHolder.bindLectureItem(cryptoItem)
    }

    open inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class WatchlistViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindLectureItem(cryptoItem: CryptoData) {
            with(itemView) {
                Log.d("RenderingItemNo", adapterPosition.toString() )
                Log.d("RenderingItemName", cryptoItem.coinInfo.fullName)
                Log.d("RenderingItemPrice", cryptoItem.display?.USD.toString())
                tvCryptoName.text = cryptoItem.coinInfo.name
                tvCryptoFullName.text = cryptoItem.coinInfo.fullName

                if (cryptoItem.RAW != null){
                    if (cryptoItem.RAW.USD.changeDay.toFloat()<0) tvPriceChange.setTextColor(ContextCompat.getColor(context, android.R.color.holo_red_dark))
                    else tvPriceChange.setTextColor(ContextCompat.getColor(context, R.color.green))
                }

                if (cryptoItem.display != null){
                    tvCryptoPrice.text = cryptoItem.display.USD?.price
                    val priceChange = trimTrailingZero(cryptoItem.display.USD?.changeDay)
                    tvPriceChange.text = String.format("${priceChange}(${cryptoItem.display.USD?.changePct}%%)")
                } else {
                    tvCryptoPrice.text = context.getString(R.string.label_no_data)
                    tvPriceChange.text = context.getString(R.string.label_no_data)
                }

                setOnClickListener {}
            }
        }
    }
}