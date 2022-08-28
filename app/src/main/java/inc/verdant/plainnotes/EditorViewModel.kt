package inc.verdant.plainnotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import inc.verdant.plainnotes.data.NoteEntity
import inc.verdant.plainnotes.data.NotesDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EditorViewModel(application: Application) : AndroidViewModel(application) {
    val db = Room.databaseBuilder(
        application,
        NotesDatabase::class.java, "notes.db"
    ).build()
    val currentNote = MutableLiveData<NoteEntity?>()

    fun getNoteById(noteId: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val note =
                    if (noteId != NEW_NOTE_ID) {
                        db.noteDao()!!.getNoteById(noteId)
                    } else {
                        NoteEntity()
                    }

                currentNote.postValue(note)
            }
        }
    }
}