package com.example.ministockbitapp.data.stream.model

class TransactionBook(
    transactions: Map<String, List<Transaction>> = emptyMap()
) {
    private val transactions = transactions.toMutableMap()

    fun getTransactions(product: String): List<Transaction> = transactions[product] ?: emptyList()

    fun addingTransaction(product: String, transaction: Transaction): TransactionBook {
        val newHistory = TransactionBook(transactions)
        newHistory.transactions[product] = (newHistory.transactions[product] ?: emptyList()) + transaction
        return newHistory
    }
}