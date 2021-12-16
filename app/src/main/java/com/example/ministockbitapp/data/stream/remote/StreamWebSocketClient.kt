package com.example.ministockbitapp.data.stream.remote

import com.example.ministockbitapp.data.stream.model.Subscribe
import com.example.ministockbitapp.data.stream.model.Ticker
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import io.reactivex.Observable

interface StreamWebSocketClient {
    @Receive
    fun observeWebSocketEvent(): Observable<WebSocket.Event>
    @Send
    fun sendSubscribe(subscribe: Subscribe)
    @Receive
    fun observeTicker(): Observable<Ticker>
}