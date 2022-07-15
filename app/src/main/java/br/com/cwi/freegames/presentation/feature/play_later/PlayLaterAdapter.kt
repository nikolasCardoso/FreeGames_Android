package br.com.cwi.freegames.presentation.feature.play_later

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.freegames.R
import br.com.cwi.freegames.domain.entity.Game
import br.com.cwi.freegames.presentation.feature.play_later.viewHolder.PlayLaterViewHolder

class PlayLaterAdapter(
    private val gamesList: MutableList<Game>,
    private val onRemoveClick: (game: Game) -> Unit,
    private val onGameClick: (gameId: Int) -> Unit
) : RecyclerView.Adapter<PlayLaterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayLaterViewHolder {
        return PlayLaterViewHolder(inflateView(R.layout.item_play_later_game, parent), onRemoveClick)
    }

    override fun onBindViewHolder(holder: PlayLaterViewHolder, position: Int) {
        val item = gamesList[position]

        holder.itemView.setOnClickListener{
            onGameClick(item.id)
        }

        holder.bind(item, onItemRemoved = {
            gamesList.remove(item)
            notifyItemRemoved(position)
            notifyItemChanged(position)
        })
    }

    override fun getItemCount(): Int = gamesList.size

    private fun inflateView(layout: Int, parent: ViewGroup): View = LayoutInflater
        .from(parent.context)
        .inflate(layout, parent, false)
}
