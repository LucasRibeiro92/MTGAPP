package com.scout.mtgapp.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.scout.mtgapp.R
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.databinding.ItemCardBinding
import com.squareup.picasso.Picasso

class CardAdapter(
    private var cards: List<CardResponse>,  // Lista inicial de cartas
    private val onCardClick: (CardResponse) -> Unit  // Função de callback para cliques
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = cards[position]
        holder.bind(card)
    }

    override fun getItemCount(): Int = cards.size


    // Função para atualizar a lista de cartas
    fun updateCards(newCards: List<CardResponse>) {
        cards = newCards
        notifyDataSetChanged()  // Atualiza o adapter e o RecyclerView
    }

    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardResponse) {
            binding.cardName.text = card.name
            binding.cardType.text = card.typeLine

            Picasso.get()
                .load(card.imageUris?.normal)
                .placeholder(R.drawable.img_placeholder)
                .into(binding.cardImage)

            itemView.setOnClickListener {
                onCardClick(card)
            }
        }
    }
}