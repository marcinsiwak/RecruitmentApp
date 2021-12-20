package pl.msiwak.recruitmentapp.util.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single
import pl.msiwak.recruitmentapp.data.ListItem

@Dao
interface DataDao {

    @Query("SELECT * FROM listitem")
    fun getData(): Single<List<ListItem>>

    @Insert
    fun insertAll(list: List<ListItem>): Completable
}