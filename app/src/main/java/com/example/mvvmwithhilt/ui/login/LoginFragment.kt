package com.example.mvvmwithhilt.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmwithhilt.R
import com.example.mvvmwithhilt.databinding.FragmentLoginBinding
import com.example.mvvmwithhilt.ui.movie.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        binding.btnSubmit.isEnabled = false

        binding.txtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateUserInput()
            }
        })
        binding.txtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateUserInput()
            }
        })

        binding.btnSubmit.setOnClickListener {
            if (binding.btnSubmit.isEnabled)
                findNavController().navigate(R.id.action_movieFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validateUserInput() {
        val emailAddress = binding.txtEmail.text.toString()
        val password = binding.txtPassword.text.toString()
        val result = loginViewModel.validateCredentials(emailAddress, password)
        if (result.first) {
            binding.btnSubmit.isEnabled = true
            binding.btnSubmit.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            binding.btnSubmit.setBackgroundColor(resources.getColor(R.color.purple_700))
            binding.txtError.text = ""
        } else {
            binding.btnSubmit.isEnabled = false
            binding.btnSubmit.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.btnSubmit.setBackgroundColor(resources.getColor(R.color.light_gray))
            binding.txtError.text = result.second

        }

    }
}