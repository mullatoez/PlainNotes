package inc.verdant.plainnotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.verdant.plainnotes.data.NoteEntity
import inc.verdant.plainnotes.databinding.ItemLayoutBinding

class NotesListAdapter(private val notes: List<NoteEntity>,
                       private val listener: ListItemListener) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {
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
        }
    }

    override fun getItemCount() = notes.size

    interface ListItemListener {
        fun onItemClick(noteId: Int)
    }

}