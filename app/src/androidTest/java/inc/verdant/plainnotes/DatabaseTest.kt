package inc.verdant.plainnotes

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import inc.verdant.plainnotes.data.NoteDao
import inc.verdant.plainnotes.data.NoteEntity
import inc.verdant.plainnotes.data.NotesDatabase
import inc.verdant.plainnotes.data.SampleDataProvider
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before

@RunWith(AndroidJUnit4::class)
class DatabaseTest {

    private lateinit var dao: NoteDao
    private lateinit var database: NotesDatabase

    @Before
    fun createDatabase() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(appContext,NotesDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.noteDao()!!
    }

    @Test
    fun createNotes() {
        dao.insertNotes(SampleDataProvider.getNotes())
        val count = dao.getCount()
        assertEquals(count,SampleDataProvider.getNotes().size)
    }

    @Test
    fun insertNote(){

        //prepare data
        val note = NoteEntity()
        val sampleNote = "This is a sample note"
        note.text = sampleNote
        dao.insertNote(note)

        //get data for testing
        val savedNote = dao.getNoteById(1)
        assertEquals(savedNote?.id ?: 0,1)
    }

    @After
    fun closeDb(){
        database.close()
    }
}