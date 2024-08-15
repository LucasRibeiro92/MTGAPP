package com.scout.mtgapp.ui.activity

import android.os.Bundle
import android.widget.SearchView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.scout.mtgapp.R
import com.scout.mtgapp.data.entity.card.Card
import com.scout.mtgapp.databinding.ActivityHomeBinding
import com.scout.mtgapp.ui.fragment.CardDetailFragment
import com.scout.mtgapp.ui.fragment.CardListFragment

class HomeActivity : AppCompatActivity() {

    /*
    * Declaring general variables
    */
    private lateinit var binding: ActivityHomeBinding
    private val TAG: String = "MT - Home Activity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        * Setups
        */
        // Binding
        setupBindings()

        if (savedInstanceState == null) {
            showCardListFragment(null)
        }

        setupSearchView()
    }

    private fun setupBindings() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    showCardListFragment(it)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

    private fun showCardListFragment(query: String?) {
        val bundle = Bundle().apply { putString("query", query) }
        val fragment = CardListFragment().apply { arguments = bundle }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    fun showCardDetails(card: Card) {
        val bundle = Bundle().apply { putParcelable("card", card) }
        val fragment = CardDetailFragment().apply { arguments = bundle }
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}