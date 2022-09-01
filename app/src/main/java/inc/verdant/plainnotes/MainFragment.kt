package inc.verdant.plainnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import inc.verdant.plainnotes.databinding.FragmentMainBinding

class MainFragment : Fragment(), NotesListAdapter.ListItemListener {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: NotesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)

        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        setHasOptionsMenu(true)

        requireActivity().title = getString(R.string.app_name)

        with(binding.recyclerview) {
            setHasFixedSize(true)
            val divider = DividerItemDecoration(context, LinearLayoutManager(context).orientation)
            addItemDecoration(divider)
        }

        viewModel.notes.observe(viewLifecycleOwner, Observer {
            Log.i("NOTES TAG", "Notes: ${it.toString()}")
            adapter = NotesListAdapter(it, this@MainFragment)
            binding.recyclerview.adapter = adapter
            binding.recyclerview.layoutManager = LinearLayoutManager(activity)

        })

        binding.floatingActionButton.setOnClickListener {
            onItemClick(NEW_NOTE_ID)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val menuId =
            if (this::adapter.isInitialized && adapter.selectedNotes.isNotEmpty()) {
                R.menu.menu_selected_items
            } else {
                R.menu.main_menu
            }
        inflater.inflate(menuId, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.add_note -> addSampleData()
            R.id.delete_note -> deleteNotes()
            R.id.delete_all_notes -> deleteAllNotes()
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllNotes(): Boolean {
        viewModel.deleteAllNotes()
return true
    }

    private fun deleteNotes(): Boolean {
        viewModel.deleteNotes(adapter.selectedNotes)
        Handler(Looper.getMainLooper()).postDelayed({
            adapter.selectedNotes.clear()
            requireActivity().invalidateOptionsMenu()
        }, 100)
        return true
    }

    private fun addSampleData(): Boolean {
        viewModel.addSampleData()
        return true
    }

    override fun onItemClick(noteId: Int) {
        Log.i("TAG", "TAG: ${noteId}")
        val action = MainFragmentDirections.actionNoteEditor(noteId)
        findNavController().navigate(action)
    }

    override fun onItemSelectionChange() {
        requireActivity().invalidateOptionsMenu()
    }

}