package br.com.cwi.freegames.presentation.feature.games

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.freegames.R
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.presentation.feature.games.viewHolder.GamesViewHolder

class GamesAdapter(
    private val gamesList: List<Game>,
    private val onGameClick: (id: Int) -> Unit
) : RecyclerView.Adapter<GamesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        return GamesViewHolder(inflateView(R.layout.item_game_thumbnail,parent))
    }

    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
        val game = gamesList[position]

        holder.itemView.setOnClickListener {
            onGameClick(game.id)
        }

        holder.bind(game)
    }

    override fun getItemCount(): Int = gamesList.size

    private fun inflateView(layout: Int, parent: ViewGroup): View = LayoutInflater
        .from(parent.context)
        .inflate(layout, parent, false)
}
