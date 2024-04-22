package com.example.exp4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment

class BasicDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_basic_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Accessing EditTexts
        val registerNoEditText = view.findViewById<EditText>(R.id.editTextRegisterNo)
        val nameEditText = view.findViewById<EditText>(R.id.editTextName)
        val departmentEditText = view.findViewById<EditText>(R.id.editTextDepartment)

        // You can handle EditText inputs here
    }
}
