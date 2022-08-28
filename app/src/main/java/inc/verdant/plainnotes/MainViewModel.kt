package inc.verdant.plainnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import inc.verdant.plainnotes.data.NoteDao
import inc.verdant.plainnotes.data.NotesDatabase
import inc.verdant.plainnotes.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val db = Room.databaseBuilder(
        application,
        NotesDatabase::class.java, "notes.db"
    ).build()

    val notes = db.noteDao()!!.getAllNotes()

    fun addSampleData() {
        viewModelScope.launch {
            withContext(
                Dispatchers.IO
            ) {
                val sampleNotes = SampleDataProvider.getNotes()
                db.noteDao()!!.insertNotes(sampleNotes)
            }

        }
    }
}


