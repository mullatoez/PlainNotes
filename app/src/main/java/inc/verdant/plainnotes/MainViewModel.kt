package inc.verdant.plainnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import inc.verdant.plainnotes.data.NoteDao
import inc.verdant.plainnotes.data.NoteEntity
import inc.verdant.plainnotes.data.NotesDatabase
import inc.verdant.plainnotes.data.SampleDataProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.ArrayList

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

    fun deleteNotes(notes: List<NoteEntity>) {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.noteDao()!!.deleteNotes(notes)
            }
        }
    }

    fun deleteAllNotes() {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                db.noteDao()!!.deleteAllNotes()
            }
        }
    }
}


