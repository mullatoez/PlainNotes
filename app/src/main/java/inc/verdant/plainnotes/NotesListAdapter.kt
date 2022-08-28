package inc.verdant.plainnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.verdant.plainnotes.data.NoteEntity
import inc.verdant.plainnotes.databinding.ItemLayoutBinding

class NotesListAdapter(
    private val notes: List<NoteEntity>,
    private val listener: ListItemListener
) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    var selectedNotes = arrayListOf<NoteEntity>()

    inner class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val binding = ItemLayoutBinding.bind(itemview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        with(holder.binding) {
            noteText.text = note.text
            root.setOnClickListener {
                listener.onItemClick(note.id)
            }
            fab.setOnClickListener {
                if (selectedNotes.contains(note)) {
                    selectedNotes.remove(note)
                    fab.setImageResource(R.drawable.ic_notes_24)
                } else {
                    selectedNotes.add(note)
                    fab.setImageResource(R.drawable.ic_check_24)
                }
                listener.onItemSelectionChange()
            }

            fab.setImageResource(
                if (selectedNotes.contains(note)) {
                    R.drawable.ic_check_24
                } else {
                    R.drawable.ic_notes_24
                }
            )
        }
    }

    override fun getItemCount() = notes.size

    interface ListItemListener {
        fun onItemClick(noteId: Int)
        fun onItemSelectionChange()
    }

}