package com.scout.mtgapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.scout.mtgapp.R
import com.scout.mtgapp.data.entity.card.Card
import com.scout.mtgapp.databinding.FragmentCardDetailBinding
import com.scout.mtgapp.ui.viewmodel.CardViewModel

class CardDetailFragment : Fragment() {

    private var _binding: FragmentCardDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(CardViewModel::class.java)

        val card = arguments?.getParcelable<Card>("card")
        card?.let {
            binding.cardName.text = it.name
            binding.cardType.text = it.typeLine
            //binding.cardImage.setImageURI(it.imageUris.normal)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
