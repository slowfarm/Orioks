package ru.eva.oriokslive.ui.fragment.student

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentStudentBinding
import ru.eva.oriokslive.network.exceptions.NetworkException
import ru.eva.oriokslive.ui.activity.registration.RegistrationActivity
import ru.eva.oriokslive.ui.base.BaseFragment
import ru.eva.oriokslive.utils.showToast

@AndroidEntryPoint
class StudentFragment : BaseFragment<FragmentStudentBinding>() {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentStudentBinding =
        FragmentStudentBinding::inflate
    private val viewModel: StudentViewModel by viewModels()

    override fun setupUI() {
        binding.btnExit.setOnClickListener { viewModel.clearAll() }

        viewModel.getStudent()
        viewModel.student.observe(viewLifecycleOwner) {
            with(binding) {
                tvName.text = getString(R.string.name, it.fullName)
                tvGroup.text = getString(R.string.group, it.group)
                tvDepartment.text = getString(R.string.cathedra, it.department)
                tvCourse.text = getString(R.string.course, it.course)
                tvId.text = getString(R.string.id, it.recordBookId)
                tvSemester.text = getString(R.string.semester, it.semester)
                tvDirection.text = getString(R.string.direction, it.studyDirection)
                tvProfile.text = getString(R.string.profile, it.studyProfile)
                tvYear.text = getString(R.string.year, it.year)
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