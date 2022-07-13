package br.com.cwi.freegames.presentation.feature.play_later

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ActivityHostPlayLaterBinding
import br.com.cwi.freegames.presentation.base.BaseBottomNavigation
import br.com.cwi.freegames.presentation.extension.visibleOrGone
import br.com.cwi.freegames.presentation.feature.games.GamesViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayLaterHostActivity : BaseBottomNavigation() {
    override val selectedMenu: Int = R.id.menu_play_later

    private lateinit var binding: ActivityHostPlayLaterBinding

    private val viewModel: GamesViewModel by viewModel()

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(binding.navHostContainer.id) as NavHostFragment)
            .findNavController()
    }

    override fun getBottomNavigation(): BottomNavigationView = binding.bottomNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHostPlayLaterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavController()
        setupViewModel()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupViewModel() {
        viewModel.loading.observe(this){ isLoading ->
            binding.viewLoading.root.visibleOrGone(isLoading)
        }

        viewModel.error.observe(this){ hasError ->
            binding.viewError.root.visibleOrGone(hasError)
            binding.viewError.tvRefresh.setOnClickListener {
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @SuppressLint("RestrictedApi")
    private fun setupNavController() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when(destination.id){
                R.id.playLaterFragment -> {
                    supportActionBar?.hide()
                    binding.viewError.root.visibleOrGone(false)
                    binding.viewLoading.root.visibleOrGone(false)
                }
                R.id.gameDetailsFragment2 -> {
                    supportActionBar?.show()
                    viewModel.gameDetails.observe(this){ game ->
                        supportActionBar?.title = game.title
                    }
                    supportActionBar?.setShowHideAnimationEnabled(false)
                }
            }
        }
    }
}