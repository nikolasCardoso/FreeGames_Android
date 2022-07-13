package br.com.cwi.freegames.presentation.base

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import br.com.cwi.freegames.R
import br.com.cwi.freegames.presentation.feature.games.GamesHostActivity
import br.com.cwi.freegames.presentation.feature.play_later.PlayLaterHostActivity
import br.com.cwi.freegames.presentation.feature.profile.ProfileHostActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class BaseBottomNavigation: AppCompatActivity() {

    abstract val selectedMenu: Int

    override fun onPause() {
        super.onPause()

        overridePendingTransition(0, 0)
    }

    override fun onResume() {
        super.onResume()

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        getBottomNavigation().selectedItemId = selectedMenu

        getBottomNavigation().setOnItemSelectedListener {
            if(it.itemId != selectedMenu) when(it.itemId) {
                R.id.menu_games -> {
                    startActivity(Intent(this, GamesHostActivity::class.java))
                }
                R.id.menu_play_later -> {
                    startActivity(Intent(this, PlayLaterHostActivity::class.java))
                }
                R.id.menu_profile -> {
                    startActivity(Intent(this, ProfileHostActivity::class.java))
                }
            }

            return@setOnItemSelectedListener true
        }
    }

    abstract fun getBottomNavigation(): BottomNavigationView
}