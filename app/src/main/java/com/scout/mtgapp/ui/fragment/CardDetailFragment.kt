package com.scout.mtgapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.scout.mtgapp.R
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.databinding.FragmentCardDetailBinding
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardDetailFragment : Fragment() {

    private var _binding: FragmentCardDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by activityViewModel<CardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val card = arguments?.getParcelable<CardResponse>("card")
        card?.let {
            binding.cardName.text = it.name
            binding.cardType.text = it.typeLine
            Picasso.get()
                .load(card.imageUris?.normal)
                .placeholder(R.drawable.img_placeholder)
                .into(binding.cardImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
