package pl.msiwak.recruitmentapp.util.config

import android.content.Context
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourceProviderImpl @Inject constructor(@ApplicationContext private val context: Context) :
    ResourceProvider {


    override fun getString(stringId: Int): String = context.getString(stringId)

    override fun getDrawable(iconId: Int) = ContextCompat.getDrawable(context, iconId)
}