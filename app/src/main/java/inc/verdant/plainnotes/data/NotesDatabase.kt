package inc.verdant.plainnotes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class NotesDatabase: RoomDatabase() {

    abstract fun noteDao(): NoteDao?

    companion object {
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context) : RoomDatabase?{
            if (INSTANCE == null){
                synchronized(NotesDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        NotesDatabase::class.java,
                        "plainnotes.db"
                    ).build()
                }
            }

            return INSTANCE
        }
    }
}