package com.example.quotify

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotify.DatabaseHandler.Quote
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {
    fun getQuotes(): LiveData<List<Quote>> {
        return quoteRepository.getQuotes()
    }

    fun insertQuote(quote: Quote) {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.insertQuote(quote)
        }
    }
}