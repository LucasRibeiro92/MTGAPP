package com.scout.mtgapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.scout.mtgapp.R
import com.scout.mtgapp.databinding.FragmentCardListBinding
import com.scout.mtgapp.ui.activity.HomeActivity
import com.scout.mtgapp.ui.adapter.CardAdapter
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardListFragment : Fragment() {
    private var _binding: FragmentCardListBinding? = null
    private val binding get() = _binding!!
    private val TAG: String = "CardListFragment"

    // Injetando o ViewModel usando Koin
    private val viewModel by activityViewModel<CardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CardAdapter { card -> (activity as HomeActivity).showCardDetails(card) }
        binding.recyclerView.adapter = adapter

        val query = arguments?.getString("query")
        if (query.isNullOrEmpty()) {
            viewModel.loadRandomCards()
        } else {
            viewModel.searchCards(query)
        }

        viewModel.cards.observe(viewLifecycleOwner, Observer { cards ->
            Log.d("CardListFragment", "Number of cards received: ${cards.size}")
            adapter.submitList(cards)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}