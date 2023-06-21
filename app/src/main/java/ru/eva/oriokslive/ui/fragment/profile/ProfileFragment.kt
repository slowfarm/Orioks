package ru.eva.oriokslive.ui.fragment.profile

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.databinding.FragmentProfileBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.activity.registration.RegistrationActivity
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.ui.dialog.showProfileDialog
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentProfileBinding =
        FragmentProfileBinding::inflate
    private val viewModel: ProfileViewModel by viewModels()

    override fun setupUI() {
        binding.btnExit.setOnClickListener { viewModel.clearAll() }

        viewModel.getStudent()
        viewModel.student.observe(viewLifecycleOwner) { student ->
            with(binding) {
                tvName.text = student.fullName
                tvGroup.text = student.group
                tvDirection.text = student.studyDirection
                cvProfile.setOnClickListener { showProfileDialog(requireContext(), student) }
            }
        }
        viewModel.onError.observe(viewLifecycleOwner) {
            if (it is NetworkException) viewModel.getLocalStudent()
            requireContext().showToast(it)
        }

        viewModel.finishActivity.observe(viewLifecycleOwner) {
            requireActivity().finishAffinity()
            val intent = Intent(requireContext(), RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}
