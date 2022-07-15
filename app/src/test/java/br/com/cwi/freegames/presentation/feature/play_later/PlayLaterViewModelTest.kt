package br.com.cwi.freegames.presentation.feature.play_later

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.repository.PlayLaterRepository
import br.com.cwi.freegames.extensions.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
class PlayLaterViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PlayLaterViewModel
    private val localRepository: PlayLaterRepository = mockk()

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupTests(){
        Dispatchers.setMain(dispatcher)
        viewModel = PlayLaterViewModel(localRepository)
    }

    @Test
    fun whenFetchGames_thenReturnGamesSuccessfully() {
        //arrange
        val gamesObserver = viewModel.playLaterGames.test()
        val playLaterGamesList = mutableListOf(
            Game(
                id = 1,
                title = "Test title",
                thumbnail = "Test thumbnail",
                genre = "Test genre",
                description = "Test description",
                platform = "Test platform",
                game_url = null,
                publisher = null,
                developer = null,
                release_date = null,
                min_system_requirements = null
            )
        )

        coEvery { localRepository.getAllGames() } returns playLaterGamesList

        //act
        viewModel.fetchGames()

        //assert
        verify { gamesObserver.onChanged(any()) }
    }

    @Test
    fun whenRemoveGames_thenRemoveGameSuccessfully() {
        //arrange
        val game = Game(
                id = 1,
                title = "Test title",
                thumbnail = "Test thumbnail",
                genre = "Test genre",
                description = "Test description",
                platform = "Test platform",
                game_url = null,
                publisher = null,
                developer = null,
                release_date = null,
                min_system_requirements = null
            )

        coEvery { localRepository.deleteGame(game) } returns Unit

        //act
        viewModel.removeGame(game)

        //assert
        verify { localRepository.deleteGame(game) }
    }

    @Test
    fun whenFetchFilteredGames_thenReturnGamesSuccessfully() {
        //arrange
        val gamesObserver = viewModel.playLaterGames.test()
        val playLaterGamesList = mutableListOf(
            Game(
                id = 1,
                title = "Test title",
                thumbnail = "Test thumbnail",
                genre = "Test genre",
                description = "Test description",
                platform = "Test platform",
                game_url = null,
                publisher = null,
                developer = null,
                release_date = null,
                min_system_requirements = null
            )
        )

        coEvery { localRepository.getFilteredTitleGames("test") } returns playLaterGamesList

        //act
        viewModel.fetchFilteredGames("test")

        //assert
        verify { gamesObserver.onChanged(any()) }
        verify { localRepository.getFilteredTitleGames("test") }
    }
}