package com.example.albertsonscodingchallenge.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.albertsonscodingchallenge.databinding.LongFormItemBinding
import com.example.albertsonscodingchallenge.model.AcronymMeaning

class AcronymRVAdapter : RecyclerView.Adapter<AcronymRVAdapter.AcronymViewHolder>() {

    private val acronymList: MutableList<AcronymMeaning> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        LongFormItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let { AcronymViewHolder(it) }

    override fun getItemCount() = acronymList.size

    override fun onBindViewHolder(holder: AcronymViewHolder, position: Int) = with(holder) {
        setMeaning(acronymList[position])
    }

    fun update(newMeanings: List<AcronymMeaning>) = acronymList.run {
        clear();addAll(newMeanings);notifyDataSetChanged()
    }

    class AcronymViewHolder(
        private val binding: LongFormItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setMeaning(meaning: AcronymMeaning) = with(binding) {
            tvLongForm.text = meaning.meaning
        }
    }
}


