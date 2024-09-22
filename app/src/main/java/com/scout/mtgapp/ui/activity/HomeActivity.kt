package com.scout.mtgapp.ui.activity

import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import com.scout.mtgapp.R
import com.scout.mtgapp.data.remote.entity.CardResponse
import com.scout.mtgapp.databinding.ActivityHomeBinding
import com.scout.mtgapp.ui.fragment.CardDetailFragment
import com.scout.mtgapp.ui.fragment.CardListFragment
import com.scout.mtgapp.ui.viewmodel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    /*
    * Declaring general variables
    */
    private lateinit var binding: ActivityHomeBinding
    private val fragmentManager = supportFragmentManager
    private val TAG: String = "MT - Home Activity"
    private val viewModel by viewModel<CardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*
        * Setups
        */
        // Binding
        setupBindings()

        if (savedInstanceState == null) {
            viewModel.loadRandomCard()
            showCardDetails()
        }

        //setupSearchView()
        //setupBottomToolbar()
    }

    private fun setupBindings() {
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configura a top bar e search view com Compose
        binding.cvTopBarNSearch.setContent {
            MaterialTheme {
                Column {
                    TopBar()  // Função Compose da sua top bar
                    SearchViewComponent { query ->
                        // Quando o usuário submeter a pesquisa, exibe a lista de resultados
                        showCardListFragment(query)
                    }
                }
            }
        }

        // Configura a bottom bar com Compose
        binding.cvBottomBar.setContent {
            MaterialTheme {
                BottomBarComponent()  // Função Compose da sua bottom bar
            }
        }
    }
    /*
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

    private fun setupBottomToolbar() {
        // Configurar o ícone de menu
        //binding.includeBottomToolbar.ivBottomBarCardList.setOnClickListener { showCardListFragment(null) }
        binding.includeBottomToolbar.ivBottomBarCardDetail.setOnClickListener { showCardDetails() }
        //binding.includeBottomToolbar.ivBottomBarCardListFavorite.setOnClickListener { showMenu(it) }
    }
*/
    private fun showCardListFragment(query: String?) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val bundle = Bundle().apply { putString("query", query) }
        val fragment = CardListFragment().apply { arguments = bundle }

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)  // Permite navegar para trás
        fragmentTransaction.commit()
    }

    internal fun showCardDetails() {
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragment = CardDetailFragment()

        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}