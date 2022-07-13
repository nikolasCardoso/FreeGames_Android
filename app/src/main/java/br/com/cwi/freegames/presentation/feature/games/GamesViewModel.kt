package br.com.cwi.freegames.presentation.feature.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.repository.GameRepository
import br.com.cwi.freegames.domain.repository.PlayLaterRepository
import br.com.cwi.freegames.presentation.base.BaseViewModel

class GamesViewModel(
    private val repository: GameRepository,
    private val localRepository: PlayLaterRepository
): BaseViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _gameDetails = MutableLiveData<Game>()
    val gameDetails: LiveData<Game> = _gameDetails

    fun fetchGames() {
        launch {
            repository.getGames().let { gamesList ->
                _games.postValue(gamesList)
            }
        }
    }

    fun fetchGameDetails(id: Int) {
        launch {
            repository.getGameDetails(id).let { game ->
                game.isInPlayLater = isGameInPlayLater(game.id)
                _gameDetails.postValue(game)
            }
        }
    }

    private fun isGameInPlayLater(gameId: Int): Boolean {
        val playLaterIdList = localRepository.getAllGames()!!.map { it.id }

        return playLaterIdList.contains(gameId)
    }

    fun setPlayLater(game: Game) {
        game.isInPlayLater = game.isInPlayLater.not()

        if(game.isInPlayLater){
            localRepository.addGame(game)
        }else{
            localRepository.deleteGame(game)
        }
    }

}