package com.scout.mtgapp.ui.activity

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import com.scout.mtgapp.R
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.databinding.ActivityHomeBinding
import com.scout.mtgapp.ui.fragment.CardDetailFragment
import com.scout.mtgapp.ui.fragment.CardListFragment

class HomeActivity : AppCompatActivity() {

    /*
    * Declaring general variables
    */
    private lateinit var binding: ActivityHomeBinding
    private val fragmentManager = supportFragmentManager
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
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bundle = Bundle().apply { putString("query", query) }
        val fragment = CardListFragment().apply { arguments = bundle }

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)  // Permite navegar para trás
        fragmentTransaction.commit()
    }

    fun showCardDetails(card: CardResponse) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bundle = Bundle().apply { putParcelable("card", card) }
        val fragment = CardDetailFragment().apply { arguments = bundle }

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}