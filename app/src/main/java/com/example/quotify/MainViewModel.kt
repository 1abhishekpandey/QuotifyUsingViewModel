package com.example.quotify

import android.content.Context
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class MainViewModel(private val context: Context) : ViewModel() {
    private var quoteList: Array<Quote> = emptyArray()
    private var currentQuoteIndex = 0
    init {
        quoteList = loadQuoteFromAssets()
    }

    private fun loadQuoteFromAssets(): Array<Quote> {
        val inputStream = context.assets.open("quotes.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<Quote>::class.java)
    }

    fun getQuote(): Quote {
        val quote = quoteList[currentQuoteIndex]
        currentQuoteIndex = (currentQuoteIndex + 1) % quoteList.size
        return quote
    }

    fun nextQuote(): Quote {
        val quote = quoteList[currentQuoteIndex]
        currentQuoteIndex = (currentQuoteIndex + 1) % quoteList.size
        return quote
    }

    fun previousQuote(): Quote {
        val quote = quoteList[currentQuoteIndex]
        currentQuoteIndex = (currentQuoteIndex - 1) % quoteList.size
        return quote
    }
}