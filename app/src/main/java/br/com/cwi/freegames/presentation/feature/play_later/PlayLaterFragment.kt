package br.com.cwi.freegames.presentation.feature.play_later

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.FragmentPlayLaterBinding
import br.com.cwi.freegames.domain.constants.GameConstants
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PlayLaterFragment : Fragment() {

    private lateinit var binding: FragmentPlayLaterBinding

    private val viewModel: PlayLaterViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPlayLaterBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupSearchView()
    }

    private fun setupViewModel() {
        viewModel.playLaterGames.observe(viewLifecycleOwner){ gamesList ->
            val recyclerView = binding.recyclerView

            recyclerView.adapter = PlayLaterAdapter(
                gamesList,
                onRemoveClick = { game ->
                    viewModel.removeGame(game)
                },
                onGameClick = { gameId ->
                    navigateToGameDetails(gameId)
                }
            )
        }

        viewModel.fetchGames()
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.fetchFilteredGames(newText)
                return true
            }
        })
    }

    private fun navigateToGameDetails(gameId: Int) {
        findNavController().navigate(
            R.id.gameDetailsFragment2,
            bundleOf(Pair(GameConstants.GAME_ID, gameId))
        )
    }
}