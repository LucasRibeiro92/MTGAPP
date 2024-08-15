package com.scout.mtgapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.scout.mtgapp.R
import com.scout.mtgapp.data.entity.card.Card
import com.scout.mtgapp.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class CardAdapter(
    private val onCardClick: (Card) -> Unit
) :
    ListAdapter<Card, CardAdapter.CardViewHolder>(CardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
    }

    inner class CardViewHolder(private val binding: ItemCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(card: Card) {
            binding.cardName.text = card.name
            binding.cardType.text = card.typeLine

            /*Picasso.get()
                .load(card.imageUrl)
                .placeholder(R.drawable.placeholder_card_image)
                .into(binding.cardImage)

             */

            itemView.setOnClickListener {
                onCardClick(card)
            }
        }
    }

    class CardDiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }
    }
}