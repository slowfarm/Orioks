package ru.eva.oriokslive.ui.fragment.student

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.eva.oriokslive.R
import ru.eva.oriokslive.databinding.FragmentStudentBinding
import ru.eva.oriokslive.ui.activity.registration.RegistrationActivity
import ru.eva.oriokslive.ui.base.BaseFragment

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
                tvName.text = it.fullName
                tvGroup.text = it.group
                tvDepartment.text = it.department
                tvCourse.text = it.course.toString()
                tvId.text = it.recordBookId
                tvSemester.text = it.semester.toString()
                tvDirection.text = it.studyDirection
                tvProfile.text = it.studyProfile
                tvYear.text = it.year
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), R.string.no_connection, Toast.LENGTH_LONG).show()
        }

        viewModel.finishActivity.observe(viewLifecycleOwner) {
            requireActivity().finishAffinity()
            val intent = Intent(requireContext(), RegistrationActivity::class.java)
            startActivity(intent)
        }
    }
}