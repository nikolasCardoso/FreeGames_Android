package br.com.cwi.freegames.presentation.feature.profile

import android.os.Bundle
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ActivityProfileBinding
import br.com.cwi.freegames.presentation.base.BaseBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileActivity : BaseBottomNavigation() {
    override val selectedMenu: Int = R.id.menu_profile

    private lateinit var binding: ActivityProfileBinding

    override fun getBottomNavigation(): BottomNavigationView = binding.bottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}