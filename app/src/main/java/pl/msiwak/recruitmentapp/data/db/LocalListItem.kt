package pl.msiwak.recruitmentapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class LocalListItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val url: String,
    val imageUrl: String
)