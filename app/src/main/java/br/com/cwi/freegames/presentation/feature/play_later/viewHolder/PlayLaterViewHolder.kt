package br.com.cwi.freegames.presentation.feature.play_later.viewHolder

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ItemPlayLaterGameBinding
import br.com.cwi.freegames.domain.constants.GameConstants
import br.com.cwi.freegames.domain.entity.Game
import com.bumptech.glide.Glide

class PlayLaterViewHolder(
    itemView: View,
    private val onRemoveClick: (game: Game) -> Unit
): RecyclerView.ViewHolder(itemView) {

    private val tvGameName = ItemPlayLaterGameBinding.bind(itemView).tvGameName
    private val tvGameDescription = ItemPlayLaterGameBinding.bind(itemView).tvGameDescription
    private val tvGameGenre = ItemPlayLaterGameBinding.bind(itemView).tvGameGenre
    private val ivGamePlatform = ItemPlayLaterGameBinding.bind(itemView).ivGamePlatform
    private val ivGameThumb = ItemPlayLaterGameBinding.bind(itemView).ivGameThumb
    private val ivRemove = ItemPlayLaterGameBinding.bind(itemView).ivRemove

    fun bind(item: Game, onItemRemoved: () -> Unit) {
        tvGameName.text = item.title
        tvGameDescription.text = item.description
        tvGameGenre.text = item.genre
        ivGamePlatform.setImageDrawable(getPlatformIcon(item))

        Glide.with(itemView.context)
            .load(item.thumbnail)
            .placeholder(R.drawable.game_loading)
            .into(ivGameThumb)

        with(ivRemove){
            setOnClickListener {
                onRemoveClick(item)
                onItemRemoved()
            }
        }
    }

    private fun getPlatformIcon(item: Game): Drawable? {
        when(item.platform){
            GameConstants.PC_PLATFORM -> return ContextCompat.getDrawable(itemView.context, R.drawable.ic_desktop_windows)
            GameConstants.BROWSER_PLATFORM -> return ContextCompat.getDrawable(itemView.context, R.drawable.ic_browser)
            GameConstants.WINDOWS_PLATFORM -> return ContextCompat.getDrawable(itemView.context, R.drawable.ic_desktop_windows)
        }

        return ContextCompat.getDrawable(itemView.context, R.drawable.ic_more)
    }
}