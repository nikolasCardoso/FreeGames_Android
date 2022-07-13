package br.com.cwi.freegames.presentation.feature.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        onEditProfileClick()
    }

    private fun setupViewModel() {
        viewModel.user.observe(viewLifecycleOwner){ user ->
            user?.let {
                val tvName = binding.tvName
                val tvEmail = binding.tvEmail
                val ivPhoto = binding.ivPhoto

                tvName.text = user.name
                tvEmail.text = user.email

                Glide.with(this)
                    .load(user.photo)
                    .placeholder(R.drawable.ic_profile)
                    .into(ivPhoto)
            }
        }
        viewModel.fetchUser()
    }

    private fun onEditProfileClick() {
        binding.tvEditButton.setOnClickListener {
            navigateToEditProfile()
        }
    }

    private fun navigateToEditProfile() {
        findNavController().navigate(R.id.editProfileFragment)
    }
}