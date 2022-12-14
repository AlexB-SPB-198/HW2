package com.example.hw2

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hw2.databinding.ItemBinding

class TextAdapter (private val list: ArrayList<String>, val clickListener: (String)->Unit):
    RecyclerView.Adapter<TextAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.onBind(list[position])
    }
    override fun getItemCount() = list.size

    inner class MessageViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(tModel: String) {
            binding.item.text = "#$tModel"
            itemView.setOnClickListener {
                clickListener(tModel)
            }
        }
    }
}