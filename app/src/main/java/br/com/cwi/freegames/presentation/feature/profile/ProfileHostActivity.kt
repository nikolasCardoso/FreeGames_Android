package br.com.cwi.freegames.presentation.feature.profile

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ActivityHostProfileBinding
import br.com.cwi.freegames.presentation.base.BaseBottomNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileHostActivity : BaseBottomNavigation() {
    override val selectedMenu: Int = R.id.menu_profile

    private lateinit var binding: ActivityHostProfileBinding

    override fun getBottomNavigation(): BottomNavigationView = binding.bottomNavigation

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment)
            .findNavController()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavController()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("RestrictedApi")
    private fun setupNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.profileFragment -> {
                    supportActionBar?.hide()
                }
                R.id.editProfileFragment -> {
                    supportActionBar?.show()
                    supportActionBar?.title = "Edit profile"
                    supportActionBar?.setShowHideAnimationEnabled(false)
                }
            }
        }
    }
}