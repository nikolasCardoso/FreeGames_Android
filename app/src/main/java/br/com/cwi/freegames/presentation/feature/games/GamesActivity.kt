package br.com.cwi.freegames.presentation.feature.games

import android.os.Bundle
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ActivityGamesBinding
import br.com.cwi.freegames.presentation.base.BaseBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class GamesActivity : BaseBottomNavigation() {
    override val selectedMenu: Int = R.id.menu_games

    private lateinit var binding: ActivityGamesBinding

    override fun getBottomNavigation(): BottomNavigationView = binding.bottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}