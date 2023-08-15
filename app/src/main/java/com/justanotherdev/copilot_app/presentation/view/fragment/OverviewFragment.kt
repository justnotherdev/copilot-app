package com.justanotherdev.copilot_app.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.toUpperCase
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.justanotherdev.copilot_app.R
import com.justanotherdev.copilot_app.data.repository.MotorcycleRepositoryImpl
import com.justanotherdev.copilot_app.databinding.FragmentOverviewBinding
import com.justanotherdev.copilot_app.domain.model.Motorcycle
import com.justanotherdev.copilot_app.domain.repository.MotorcycleRepository
import com.justanotherdev.copilot_app.presentation.viewmodel.OverviewViewModel
import com.justanotherdev.copilot_app.presentation.viewmodel.OverviewViewModel.ViewState.*
import com.justanotherdev.copilot_app.presentation.viewmodel.OverviewViewModel.ActionState.*
import java.util.Locale

class OverviewFragment : Fragment() {

    private lateinit var binding: FragmentOverviewBinding
    private val viewModel by viewModels<OverviewViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDefaultViewsState()
        observeViewModelUIState(this)
        observeViewModelActionState(this)
    }

    private fun observeViewModelUIState(owner: LifecycleOwner) {
        viewModel.viewState.observe(owner) { state ->
            when (state) {
                is OverviewLoaded -> showOverviewContent(state.motorcycle)
                is OverviewLoadFailure -> showErrorMessage(state.reason)
                is Loading -> startShimmerIndicator()
            }
        }
    }

    private fun showOverviewContent(motorcycle: Motorcycle) {
        stopShimmerIndicator()
        binding.codename.text = motorcycle.codename
        binding.status.text = motorcycle.status.replaceFirstChar {
            it.titlecase(Locale.getDefault())
        }
        binding.preview.setImageResource(R.drawable.ic_motorcycle)
    }

    private fun showErrorMessage(error: String) {
        stopShimmerIndicator()
//        binding.codename.text = "C.O.D.E.N.A.M.E"
//        binding.statusMotorcycle.text = "Unknown"
        println(error)
    }

    private fun observeViewModelActionState(owner: LifecycleOwner) {
        viewModel.actionState.observe(owner) { state ->
            when (state) {
                is NavigatesToUserProfile -> {

                }

                is NavigatesToVehicleDetail -> {

                }


            }
        }
    }

    private fun setDefaultViewsState() {
        startShimmerIndicator()
//        binding.infoMessage.isVisible = false
//        binding.spinner.isVisible = false
//        binding.errorMessage.isVisible = false
//        binding.projectsRecyclerView.isVisible = false
//        binding.retryButton.isVisible = false
//        adapter.submitList(emptyList())
    }


    private fun startShimmerIndicator() {
        binding.dataView.isVisible = false
        binding.shimmerView.root.isVisible = true
        binding.shimmerView.root.startShimmer()
    }

    private fun stopShimmerIndicator() {
        binding.shimmerView.root.stopShimmer()
        binding.shimmerView.root.isVisible = false
        binding.dataView.isVisible = true
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment OverviewFragment.
         */
        @JvmStatic
        fun newInstance() = OverviewFragment()
    }
}