package com.imobile.systembar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imobile.systembar.databinding.FragmentOtherBinding

/**
 * Created by ChinhQT on 08/04/2023.
 */
class OtherFragment : Fragment() {

    private var _binding: FragmentOtherBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("OtherFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.firstFragment.setOnClickListener {
            (activity as MainActivity).showFirstFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}