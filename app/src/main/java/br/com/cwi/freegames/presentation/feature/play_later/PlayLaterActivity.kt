package br.com.cwi.freegames.presentation.feature.play_later

import android.os.Bundle
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ActivityPlayLaterBinding
import br.com.cwi.freegames.presentation.base.BaseBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class PlayLaterActivity : BaseBottomNavigation() {
    override val selectedMenu: Int = R.id.menu_play_later

    private lateinit var binding: ActivityPlayLaterBinding

    override fun getBottomNavigation(): BottomNavigationView = binding.bottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPlayLaterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}