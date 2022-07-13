package br.com.cwi.freegames.presentation.feature.profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import br.com.cwi.freegames.R
import br.com.cwi.freegames.databinding.FragmentEditProfileBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.io.File

private const val CAMERA_PERMISSION_CODE = 1
private const val FILE_NAME = "photo.jpg"

class EditProfileFragment : Fragment() {

    private lateinit var binding: FragmentEditProfileBinding

    private var filePhoto: File? = null

    private val viewModel: ProfileViewModel by sharedViewModel()

    private val resultLauncher = registerForActivityResult(StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            Glide.with(this)
                .load(filePhoto)
                .placeholder(R.drawable.ic_profile)
                .into(binding.ivPhoto)
        }
    }

    private fun getPhotoFile(): File {
        val directoryStorage = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(FILE_NAME, ".jpg", directoryStorage)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentEditProfileBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
    }

    private fun setupViewModel() {
        viewModel.user.observe(viewLifecycleOwner){ user ->
            user?.run {
                binding.etName.setText(name)
                binding.etEmail.setText(email)
                Glide.with(this@EditProfileFragment)
                    .load(photo)
                    .placeholder(R.drawable.ic_profile)
                    .into(binding.ivPhoto)
            }
        }
        onEditPhotoClick()
        onSaveProfileClick()
    }

    private fun onEditPhotoClick() {
        binding.tvEditPhotoButton.setOnClickListener {
            if(isPermissionGranted()){
                filePhoto = getPhotoFile()
                val takePhotoIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                val providerFile = FileProvider.getUriForFile(
                    requireContext(),
                    "com.example.androidcamera.fileprovider",
                    filePhoto!!
                )

                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, providerFile)
                resultLauncher.launch(takePhotoIntent)
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CAMERA),
                    CAMERA_PERMISSION_CODE
                )
            }
        }
    }

    private fun isPermissionGranted(): Boolean{
        return checkSelfPermission(
            requireActivity().applicationContext,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun onSaveProfileClick() {
        binding.tvSave.setOnClickListener {
            val userName = binding.etName.text.toString()
            val userEmail = binding.etEmail.text.toString()
            val userPhoto = filePhoto?.absolutePath

            viewModel.createOrUpdateUser(userName, userEmail, userPhoto)
            activity?.onBackPressed()
        }
    }
}