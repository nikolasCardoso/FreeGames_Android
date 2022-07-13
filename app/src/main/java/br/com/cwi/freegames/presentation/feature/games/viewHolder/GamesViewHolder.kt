package br.com.cwi.freegames.presentation.feature.games.viewHolder

import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ItemGameThumbnailBinding
import br.com.cwi.freegames.domain.constants.GameConstants
import br.com.cwi.freegames.domain.entity.Game
import com.bumptech.glide.Glide

class GamesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val tvGameTitle = ItemGameThumbnailBinding.bind(itemView).tvGameTitle
    private val tvGameGenre = ItemGameThumbnailBinding.bind(itemView).tvGameGenre
    private val ivGameBackground = ItemGameThumbnailBinding.bind(itemView).ivGameBackground
    private val ivGamePlatform = ItemGameThumbnailBinding.bind(itemView).ivGamePlatform

    fun bind(item: Game) {
        tvGameTitle.text = item.title
        tvGameGenre.text = item.genre
        ivGamePlatform.setImageDrawable(getPlatformIcon(item))

        Glide.with(itemView.context)
            .load(item.thumbnail)
            .placeholder(R.drawable.game_loading)
            .into(ivGameBackground)
    }

    private fun getPlatformIcon(item: Game): Drawable? {
        when(item.platform){
            GameConstants.PC_PLATFORM -> return ContextCompat.getDrawable(itemView.context, R.drawable.ic_desktop_windows)
            GameConstants.BROWSER_PLATFORM -> return ContextCompat.getDrawable(itemView.context, R.drawable.ic_browser)
        }

        return ContextCompat.getDrawable(itemView.context, R.drawable.ic_more)
    }
}