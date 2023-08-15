package com.justanotherdev.copilot_app.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.justanotherdev.copilot_app.databinding.FragmentDiagnosticsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DiagnosticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DiagnosticsFragment : Fragment() {

    private lateinit var binding: FragmentDiagnosticsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDiagnosticsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProfileFragment.
         */
        @JvmStatic
        fun newInstance() = DiagnosticsFragment()
    }
}