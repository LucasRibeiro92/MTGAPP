package com.scout.mtgapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.databinding.FragmentCardListBinding
import com.scout.mtgapp.ui.activity.HomeActivity
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardListFragment : Fragment() {

    private val viewModel by activityViewModel<CardViewModel>()
    private var cardList: List<CardResponse> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCardListBinding.inflate(inflater, container, false)

        // Recuperar a query do Bundle
        val query = arguments?.getString("query")

        // Buscar cartas e atribuí-las à cardList
        query?.let {
            viewModel.searchCards(it)
        }

        // Observa mudanças na lista de cartas
        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            cardList = cards
            updateComposeView(binding, cardList)
        }

        return binding.root
    }

    private fun updateComposeView(binding: FragmentCardListBinding, cardList: List<CardResponse>) {
        binding.cvCardList.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    // Passa a função de clique para a lista de cartas
                    CardListScreen(cardList) { cardId ->
                        viewModel.getCard(id = cardId)
                        // Chama a função da HomeActivity para exibir os detalhes do card
                        (activity as? HomeActivity)?.showCardDetails()
                    }
                }
            }
        }
    }
}

