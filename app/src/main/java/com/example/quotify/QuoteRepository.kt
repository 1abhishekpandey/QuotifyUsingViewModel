package com.example.quotify

import androidx.lifecycle.LiveData
import com.example.quotify.DatabaseHandler.Quote
import com.example.quotify.DatabaseHandler.QuoteDAO

class QuoteRepository(private val quoteDAO: QuoteDAO) {
    fun getQuotes(): LiveData<List<Quote>> {
        return quoteDAO.getQuotes()
    }

    suspend fun insertQuote(quote: Quote) {
        quoteDAO.insertQuote(quote)
    }
}