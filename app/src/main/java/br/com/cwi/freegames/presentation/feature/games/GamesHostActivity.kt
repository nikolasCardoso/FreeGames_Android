package br.com.cwi.freegames.presentation.feature.games

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ActivityHostGamesBinding
import br.com.cwi.freegames.presentation.base.BaseBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class GamesHostActivity : BaseBottomNavigation() {
    override val selectedMenu: Int = R.id.menu_games

    private lateinit var binding: ActivityHostGamesBinding

    private val viewModel: GamesViewModel by viewModel()

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment)
            .findNavController()
    }

    override fun getBottomNavigation(): BottomNavigationView = binding.bottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.fetchGames()

    }
}