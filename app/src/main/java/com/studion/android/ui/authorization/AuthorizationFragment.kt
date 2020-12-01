package com.studion.android.ui.authorization

import android.app.AppComponentFactory
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.studion.android.R
import com.studion.android.data.models.TokenObtainModel
import com.studion.android.databinding.FragmentAuthorizationBinding
import com.studion.android.utils.Event
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment : Fragment(R.layout.fragment_authorization) {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AuthorizationViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAuthorizationBinding.bind(view)

        binding.buttonRegistration.setOnClickListener {
            val action =
                AuthorizationFragmentDirections.actionAuthorizationFragmentToRegistrationFragment()
            findNavController().navigate(action)
        }

        binding.buttonAuthorization.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()
            viewModel.authorization(
                TokenObtainModel(
                    email = email,
                    password = password
                )
            ) { response ->
                when (response.status) {
                    Event.Status.SUCCESS -> {
                    }
                    Event.Status.ERROR -> {
                    }
                    Event.Status.LOADING -> {
                    }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}