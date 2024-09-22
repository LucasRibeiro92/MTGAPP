package com.scout.mtgapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.scout.mtgapp.R
import com.scout.mtgapp.data.local.entity.card.Card
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.databinding.FragmentCardDetailBinding
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardDetailFragment : Fragment() {

    private val viewModel by activityViewModel<CardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCardDetailBinding.inflate(inflater, container, false)

        binding.cvCardDetail.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    val card = arguments?.getParcelable<CardResponse>("card")
                    if (card != null) {
                        CardDetailScreen(card)
                    } else {
                        Text("Nenhuma carta selecionada.")
                    }
                }
            }
        }
        return binding.root
    }

    // Função de conversão de CardResponse para CardEntity
    private fun CardResponse.toCardEntity(): Card {
        return Card(
            id = this.id,
            name = this.name,
            imageUri = this.imageUris?.large ?: "",
            typeLine = this.typeLine,
            oracleText = this.oracleText,
            power = this.power,
            toughness = this.toughness,
            setName = this.setName,
            rarity = this.rarity,
            cmc = this.cmc
        )
    }

}
