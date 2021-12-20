package pl.msiwak.recruitmentapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ListItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val date: String,
    val url: String,
    val imageUrl: String
)