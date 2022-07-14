package br.com.cwi.freegames.presentation.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.ComponentInformationGameBinding

class GameInformationComponent : ConstraintLayout {

    private lateinit var binding: ComponentInformationGameBinding

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        binding = ComponentInformationGameBinding.inflate(LayoutInflater.from(context), this)

        context.obtainStyledAttributes(
            attrs,
            R.styleable.GameInformationComponent,
            0,
            0
        ).run {
            binding.tvNameInfo.text =
                getString(R.styleable.GameInformationComponent_component_title_information)
            recycle()
        }
    }

    fun bindGameInfo(text: String?){
        binding.tvGameInfo.text = text
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr)
}