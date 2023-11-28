package com.example.quotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quotify.DatabaseHandler.Quote
import com.example.quotify.DatabaseHandler.QuoteDAO
import com.example.quotify.DatabaseHandler.QuoteDatabase
import com.example.quotify.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao: QuoteDAO =
            QuoteDatabase.getInstance(this)
                .quoteDAO()
        val repository = QuoteRepository(dao)
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.getQuotes().observe(this) {
            binding.quotes = it.toString()
        }
        binding.btnAddQuote.setOnClickListener {
            val quote = Quote(0, "This is a quote", "Author")
            mainViewModel.insertQuote(quote)
        }

    }


}