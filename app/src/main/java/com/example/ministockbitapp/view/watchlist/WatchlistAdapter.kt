package com.example.ministockbitapp.view.watchlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.ministockbitapp.R
import com.example.ministockbitapp.data.crypto.model.Data
import kotlinx.android.synthetic.main.item_crypto.view.*

class WatchlistAdapter (
    val data: MutableList<Data> = mutableListOf()
): RecyclerView.Adapter<WatchlistAdapter.ViewHolder>(){
    fun setCryptoData(cryptoList: List<Data>) {
        if (data.size > 0) {
            data.clear()
        }
        data.addAll(cryptoList)
        Log.d("DataAdapter", data.count().toString())
        notifyDataSetChanged()
    }

    fun addOrUpdate(item: Data) {
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
        val cryptoItem: Data = data[position]
        val watchlistViewHolder = holder as WatchlistViewHolder
        watchlistViewHolder.bindLectureItem(cryptoItem)
    }

    open inner class ViewHolder(@NonNull itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class WatchlistViewHolder(itemView: View) : ViewHolder(itemView) {
        fun bindLectureItem(cryptoItem: Data) {
            with(itemView) {
                tvCryptoName.text = cryptoItem.coinInfo?.name
                tvCryptoFullName.text = cryptoItem.coinInfo?.fullName
                tvCryptoPrice.text = cryptoItem.display?.USD?.price
                tvPriceChange.text = String.format("${cryptoItem.display?.USD?.changeDay}(${cryptoItem.display?.USD?.changePct})")
            }
        }
    }
}