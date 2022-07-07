package br.com.cwi.freegames.presentation.feature.games

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.cwi.freegames.databinding.FragmentGameDetailsBinding
import br.com.cwi.freegames.domain.constants.GameConstants
import br.com.cwi.freegames.domain.entity.GameDetails
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GameDetailsFragment : Fragment() {

    private lateinit var binding: FragmentGameDetailsBinding

    private val viewModel: GamesViewModel by sharedViewModel()

    private val gameId by lazy {
        arguments?.getInt(GameConstants.GAME_ID)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGameDetailsBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.gameDetails.observe(viewLifecycleOwner) { game ->
            bindGameInfo(game)

            if(game.min_system_requirements == null)
                binding.viewMinSystemRequirements.root.visibility = View.GONE
            else
                binding.viewMinSystemRequirements.root.visibility = View.VISIBLE
        }

        viewModel.fetchGameDetails(gameId)
    }

    private fun bindGameInfo(game: GameDetails) {
        val tvTitle = binding.viewTitle.tvTitle
        val tvDescription = binding.viewTitle.tvDescription
        val tvGameDeveloper = binding.viewAdditionalInfo.tvGameDeveloper
        val tvGamePublisher = binding.viewAdditionalInfo.tvGamePublisher
        val tvGamePlatform = binding.viewAdditionalInfo.tvGamePlatform
        val tvGameReleaseDate = binding.viewAdditionalInfo.tvGameReleaseDate
        val tvGameGenre = binding.viewAdditionalInfo.tvGameGenre
        val tvGameOs = binding.viewMinSystemRequirements.tvGameOs
        val tvGameProcessor = binding.viewMinSystemRequirements.tvGameProcessor
        val tvGameStorage = binding.viewMinSystemRequirements.tvGameStorage
        val tvGameMemory = binding.viewMinSystemRequirements.tvGameMemory
        val tvGameGraphics = binding.viewMinSystemRequirements.tvGameGraphics
        val ivGameImage = binding.ivGameImage
        val tvPlayNowButton = binding.viewButtons.tvPlayNowButton

        (activity as GamesHostActivity).supportActionBar?.title = game.title

        tvTitle.text = game.title
        tvDescription.text = game.short_description
        tvGameDeveloper.text = game.developer
        tvGamePublisher.text = game.publisher
        tvGamePlatform.text = game.platform
        tvGameReleaseDate.text = game.release_date
        tvGameGenre.text = game.genre
        tvGameOs.text = game.min_system_requirements?.os
        tvGameProcessor.text = game.min_system_requirements?.processor
        tvGameStorage.text = game.min_system_requirements?.storage
        tvGameMemory.text = game.min_system_requirements?.memory
        tvGameGraphics.text = game.min_system_requirements?.graphics

        Glide.with(this)
            .load(game.thumbnail)
            .into(ivGameImage)

        tvPlayNowButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(game.game_url)

            startActivity(intent)
        }
    }
}