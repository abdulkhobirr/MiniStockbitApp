package com.example.ministockbitapp.data.stream

import android.util.Log
import com.example.ministockbitapp.data.stream.model.Subscribe
import com.example.ministockbitapp.data.stream.model.Transaction
import com.example.ministockbitapp.data.stream.model.TransactionBook
import com.example.ministockbitapp.data.stream.remote.StreamWebSocketClient
import com.tinder.scarlet.WebSocket
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.processors.BehaviorProcessor
import java.util.concurrent.atomic.AtomicReference

class StreamDataStore(
    private val gdaxService: StreamWebSocketClient
): StreamRepository {
    private val transactionBookRef = AtomicReference(TransactionBook())
    private val transactionBookProcessor = BehaviorProcessor.create<TransactionBook>()

    init {
        gdaxService.observeWebSocketEvent()
            .filter { it is WebSocket.Event.OnConnectionOpened<*> }
            .subscribe({
                when(it){
                    is WebSocket.Event.OnConnectionOpened<*> -> {
                        Log.d("ScarletWebsocket", "ConnectionOpened")
                        val subscribe = Subscribe(
                            action = "SubAdd",
                            subs = listOf("5~CCCAGG~BTC~USD")
                        )
                        gdaxService.sendSubscribe(subscribe)
                    }
                    is WebSocket.Event.OnMessageReceived -> {
                        Log.d("ScarletWebsocket", "MessageReceived")
                    }
                    is WebSocket.Event.OnConnectionClosing -> {
                        Log.d("ScarletWebsocket", "ConnectionClosing")
                    }
                    is WebSocket.Event.OnConnectionClosed -> {
                        Log.d("ScarletWebsocket", "ConnectionClosed")
                    }
                    is WebSocket.Event.OnConnectionFailed -> {
                        Log.d("ScarletWebsocket", "ConnectionFailed")
                    }
                }
            }, { e ->
                Log.e(e.message, e.toString())
            })

        gdaxService.observeTicker()
            .filter { it.type == "5" && it.price != null }
            .map { ticker ->
                val product = ticker.fromSymbol
                val price = ticker.price.toFloat() ?: 0
                val time = ticker.time
                product to Transaction(price as Float, time)
            }
            .subscribe({ (product, transaction) ->
                Log.d("TickerReceived", "")
                addTransaction(product, transaction)
            }, { e ->
                Log.e(e.message, e.toString())
            })
    }

    override fun observeTransactionBook(): Flowable<TransactionBook> {
        return transactionBookProcessor
    }

    private fun addTransaction(product: String, transaction: Transaction) {
        val transactionBook = transactionBookRef.get()
            .addingTransaction(product, transaction)
        transactionBookRef.set(transactionBook)
        transactionBookProcessor.onNext(transactionBook)
    }
}