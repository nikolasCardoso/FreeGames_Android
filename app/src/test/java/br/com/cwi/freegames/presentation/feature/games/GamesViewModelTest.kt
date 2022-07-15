package br.com.cwi.freegames.presentation.feature.games

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.entity.GameMinSystemRequirements
import br.com.cwi.freegames.domain.repository.GameRepository
import br.com.cwi.freegames.domain.repository.PlayLaterRepository
import br.com.cwi.freegames.extensions.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
@ExperimentalCoroutinesApi
internal class GamesViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: GamesViewModel
    private val repository: GameRepository = mockk()
    private val localRepository: PlayLaterRepository = mockk()

    private val dispatcher = UnconfinedTestDispatcher()

    @Before
    fun setupTests(){
        Dispatchers.setMain(dispatcher)
        viewModel = GamesViewModel(repository, localRepository)
    }

    @Test
    fun whenFetchGames_thenReturnGamesSuccessfully() {
        //arrange
        val gamesObserver = viewModel.games.test()
        val gamesList = listOf(
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

        coEvery { repository.getGames() } returns gamesList

        //act
        viewModel.fetchGames()

        //assert
        verify { gamesObserver.onChanged(any()) }
    }

    @Test
    fun whenFetchGameDetailsNotInPlayLater_thenReturnGameSuccessfully() {
        //arrange
        val gamesObserver = viewModel.gameDetails.test()
        val game = Game(
                id = 1,
                title = "Test title",
                thumbnail = "Test thumbnail",
                genre = "Test genre",
                description = "Test description",
                platform = "Test platform",
                game_url = "Test game_url",
                publisher = "Test publisher",
                developer = "Test developer",
                release_date = "Test release_date",
                min_system_requirements = null
            )

        coEvery { repository.getGameDetails(1) } returns game
        coEvery { localRepository.getAllGames() } returns ArrayList()

        //act
        viewModel.fetchGameDetails(1)

        //assert
        verify { localRepository.getAllGames() }
        verify { gamesObserver.onChanged(any()) }
        assertFalse(game.isInPlayLater)
    }

    @Test
    fun whenFetchGameDetailsIsInPlayLater_thenReturnGameSuccessfully() {
        //arrange
        val gamesObserver = viewModel.gameDetails.test()
        val game = Game(
            id = 1,
            title = "Test title",
            thumbnail = "Test thumbnail",
            genre = "Test genre",
            description = "Test description",
            platform = "Test platform",
            game_url = "Test game_url",
            publisher = "Test publisher",
            developer = "Test developer",
            release_date = "Test release_date",
            min_system_requirements = null
        )
        val playLaterGames = mutableListOf(game)

        coEvery { repository.getGameDetails(1) } returns game
        coEvery { localRepository.getAllGames() } returns playLaterGames

        //act
        viewModel.fetchGameDetails(1)

        //assert
        verify { localRepository.getAllGames() }
        verify { gamesObserver.onChanged(any()) }
        assertTrue(game.isInPlayLater)
    }

    @Test
    fun givenGameSelected_whenInPlayLater_thenRemoveFromRepositoryAndSetFalseInPlayLater() {
        //arrange
        val game = Game(
            id = 1,
            title = "Test title",
            thumbnail = "Test thumbnail",
            genre = "Test genre",
            description = "Test description",
            platform = "Test platform",
            game_url = "Test game_url",
            publisher = "Test publisher",
            developer = "Test developer",
            release_date = "Test release_date",
            min_system_requirements = null,
            isInPlayLater = true
        )

        coEvery { localRepository.deleteGame(game) } returns Unit

        //act
        viewModel.setPlayLater(game)

        //assert
        verify { localRepository.deleteGame(game) }
        assertFalse(game.isInPlayLater)
    }

    @Test
    fun givenGameSelected_whenNotInPlayLater_thenAddToRepositoryAndSetTrueInPlayLater() {
        //arrange
        val game = Game(
            id = 1,
            title = "Test title",
            thumbnail = "Test thumbnail",
            genre = "Test genre",
            description = "Test description",
            platform = "Test platform",
            game_url = "Test game_url",
            publisher = "Test publisher",
            developer = "Test developer",
            release_date = "Test release_date",
            min_system_requirements = null,
            isInPlayLater = false
        )

        coEvery { localRepository.addGame(game) } returns Unit

        //act
        viewModel.setPlayLater(game)

        //assert
        verify { localRepository.addGame(game) }
        assertTrue(game.isInPlayLater)
    }

    @Test
    fun givenGame_whenSystemRequirementsIsNull_thenReturnFalse() {
        //arrange
        val game = Game(
            id = 1,
            title = "Test title",
            thumbnail = "Test thumbnail",
            genre = "Test genre",
            description = "Test description",
            platform = "Test platform",
            game_url = "Test game_url",
            publisher = "Test publisher",
            developer = "Test developer",
            release_date = "Test release_date",
            min_system_requirements = null
        )

        //act
        val tested = viewModel.isSystemRequirementsNotNull(game)

        //assert
        assertFalse(tested)
    }

    @Test
    fun givenGame_whenSystemRequirementsIsNotNull_thenReturnTrue() {
        //arrange
        val minSysReq = GameMinSystemRequirements(
            graphics = "Test graphics",
            os = "Test os",
            memory = "Test memory",
            processor = "Test processor",
            storage = "Test storage"
        )

        val game = Game(
            id = 1,
            title = "Test title",
            thumbnail = "Test thumbnail",
            genre = "Test genre",
            description = "Test description",
            platform = "Test platform",
            game_url = "Test game_url",
            publisher = "Test publisher",
            developer = "Test developer",
            release_date = "Test release_date",
            min_system_requirements = minSysReq
        )

        //act
        val tested = viewModel.isSystemRequirementsNotNull(game)

        //assert
        assertTrue(tested)
    }
}