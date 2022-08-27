package inc.verdant.plainnotes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import inc.verdant.plainnotes.data.NoteEntity
import inc.verdant.plainnotes.data.SampleDataProvider

class MainViewModel : ViewModel() {
    val notesList = MutableLiveData<List<NoteEntity>>()

    init {
        notesList.value = SampleDataProvider.getNotes()
    }
}