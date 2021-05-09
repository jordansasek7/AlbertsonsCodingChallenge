package com.example.albertsonscodingchallenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.albertsonscodingchallenge.R
import com.example.albertsonscodingchallenge.adapter.AcronymRVAdapter
import com.example.albertsonscodingchallenge.databinding.ActivityMainBinding
import com.example.albertsonscodingchallenge.utils.Resource
import com.example.albertsonscodingchallenge.viewmodel.MainViewModel


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvDefinitions.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = AcronymRVAdapter()
        }

        setUpListeners()
        setUpObservers()
    }

    private fun setUpListeners() {
        binding.btnFetch.setOnClickListener { getLongForm() }

        binding.etAcronym.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                getLongForm()
                true
            } else {
                false
            }
        }
    }

    private fun getLongForm() {
        viewModel.fetchAcronym(binding.etAcronym.text.toString())
    }

    private fun setUpObservers() {
        viewModel.dataState.observe(this) {

        }
        viewModel.dataState.observe(this) { state ->
            binding.apply {
                val rvData = if (state is Resource.Success) state.data else listOf()
                (rvDefinitions.adapter as AcronymRVAdapter).update(rvData)
                tvError.isVisible = state is Resource.Error
                tvError.text = if (state is Resource.Error) state.errorMsg else ""
                progressBar.isVisible = state is Resource.Loading
            }
        }
    }
}
