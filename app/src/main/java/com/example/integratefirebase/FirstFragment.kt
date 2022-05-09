package com.example.integratefirebase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.integratefirebase.databinding.FragmentFirstBinding
import com.google.firebase.firestore.FirebaseFirestore


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var db = FirebaseFirestore.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textFirstName.setOnClickListener {
            binding.textFirstName.text = null
        }

        binding.textLastName.setOnClickListener {
            binding.textLastName.text = null
        }

        binding.textAge.setOnClickListener {
            binding.textAge.text = null
        }

        binding.registerbutton.setOnClickListener {
            saveData()
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun saveData() {

        //getting values from bindings
        val firstName = binding.textFirstName.text.toString()
        val lastName = binding.textLastName.text.toString()
        val age = binding.textAge.text.toString()

        //creating a map
        var user = mapOf("FirstName" to firstName, "LastName" to lastName, "Age" to age)

        //storing the map in firestore
        db.collection("user").document("user1").set(user)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}