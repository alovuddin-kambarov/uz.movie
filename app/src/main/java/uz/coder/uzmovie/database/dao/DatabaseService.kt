package uz.coder.uzmovie.database.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import uz.coder.uzmovie.models.MovieClass

@Dao
interface DatabaseService {

    @Query("select * from MovieClass")
     fun getAll(): Flowable<List<MovieClass>>

    @Insert
     fun add(myClass: MovieClass): Single<Long>

    @Query("DELETE FROM MovieClass WHERE id = :id")
     fun delete(id: Int): Completable

}
