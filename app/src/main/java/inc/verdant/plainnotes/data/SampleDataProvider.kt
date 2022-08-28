package inc.verdant.plainnotes.data

import java.util.*

class SampleDataProvider {

    companion object {
        private val sampleNote1 = "A simple note"
        private val sampleNote2 = "A simple note with \n some good texts around us"
        private val sampleNote3 = """
            Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source.
        """.trimIndent()

        fun getDate(diff: Long): Date {
            return Date(Date().time + diff)
        }

        fun getNotes() = arrayListOf(
            NoteEntity(getDate(0), sampleNote1),
            NoteEntity(getDate(1), sampleNote2),
            NoteEntity(getDate(2), sampleNote3),
        )
    }


}