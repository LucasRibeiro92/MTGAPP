package com.scout.mtgapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.scout.mtgapp.databinding.FragmentCardListBinding
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CardListFragment : Fragment() {

    private val viewModel by activityViewModel<CardViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCardListBinding.inflate(inflater, container, false)

        binding.cvCardList.apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                MaterialTheme {
                    CardListScreen(viewModel)
                }
            }
        }

        return binding.root
    }
}
