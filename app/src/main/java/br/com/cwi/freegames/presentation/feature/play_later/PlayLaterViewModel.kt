package br.com.cwi.freegames.presentation.feature.play_later

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.domain.repository.PlayLaterRepository
import br.com.cwi.freegames.presentation.base.BaseViewModel

class PlayLaterViewModel(
    private val localRepository: PlayLaterRepository
): BaseViewModel() {

    private val _playLaterGames = MutableLiveData<MutableList<Game>>()
    val playLaterGames: LiveData<MutableList<Game>> = _playLaterGames

    fun fetchGames(){
        localRepository.getAllGames().let { gameList ->
            _playLaterGames.postValue(gameList)
        }
    }

    fun removeGame(game: Game) {
        localRepository.deleteGame(game)
    }

    fun fetchFilteredGames(word: String?){
        localRepository.getFilteredTitleGames(word)?.let {
            _playLaterGames.postValue(it)
        }
    }
}