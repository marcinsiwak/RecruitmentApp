package pl.msiwak.recruitmentapp.util.config

import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

interface ResourceProvider {

    fun getString(@StringRes stringId: Int): String
    fun getDrawable(iconId: Int): Drawable?
}