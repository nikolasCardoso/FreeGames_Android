package br.com.cwi.freegames.presentation.feature.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.entity.GameDetails
import br.com.cwi.freegames.domain.repository.GameRepository
import br.com.cwi.freegames.presentation.base.BaseViewModel

class GamesViewModel(
    private val repository: GameRepository
): BaseViewModel() {

    private val _games = MutableLiveData<List<Game>>()
    val games: LiveData<List<Game>> = _games

    private val _gameDetails = MutableLiveData<GameDetails>()
    val gameDetails: LiveData<GameDetails> = _gameDetails

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
                _gameDetails.postValue(game)
            }
        }
    }
}