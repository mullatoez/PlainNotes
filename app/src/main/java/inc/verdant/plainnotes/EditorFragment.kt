package inc.verdant.plainnotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import inc.verdant.plainnotes.databinding.FragmentEditorBinding

class EditorFragment : Fragment() {

    private lateinit var viewModel: EditorViewModel
    private val args: EditorFragmentArgs by navArgs()
    private lateinit var binding: FragmentEditorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    saveAndReturn()
                }
            }
        )

        viewModel = ViewModelProvider(this).get(EditorViewModel::class.java)

        binding = FragmentEditorBinding.inflate(inflater, container, false)
        binding.editor.setText("")

        viewModel.currentNote.observe(viewLifecycleOwner, Observer {
            binding.editor.setText(it!!.text)
        })

        viewModel.getNoteById(args.noteId)

        return binding.root
    }


    private fun saveAndReturn(): Boolean {
        findNavController().navigateUp()
        return true
    }
}