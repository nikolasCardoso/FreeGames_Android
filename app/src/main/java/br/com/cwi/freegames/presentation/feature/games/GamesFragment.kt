package br.com.cwi.freegames.presentation.feature.games

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.FragmentGamesBinding
import br.com.cwi.freegames.domain.constants.GameConstants.GAME_ID
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GamesFragment : Fragment() {

    private val viewModel: GamesViewModel by sharedViewModel()

    private lateinit var binding: FragmentGamesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGamesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.games.observe(viewLifecycleOwner) { gamesList ->
            val recyclerView = binding.rvGames

            recyclerView.adapter = GamesAdapter(
                gamesList,
                onGameClick = { gameId ->
                    navigateToGameDetails(gameId)
                }
            )
        }

        viewModel.fetchGames()
    }

    private fun navigateToGameDetails(gameId: Int) {
        findNavController().navigate(
            R.id.gameDetailsFragment,
            bundleOf(Pair(GAME_ID, gameId))
        )
    }
}