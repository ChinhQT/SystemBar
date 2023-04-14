package com.imobile.systembar

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.imobile.systembar.databinding.FragmentSecondBinding

/**
 * Created by ChinhQT on 08/04/2023.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    companion object {
        const val TAG = "SecondFragment"

        fun newInstance(): SecondFragment = SecondFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("SecondFragment", "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.otherActivity.setOnClickListener {
            val intent = Intent(activity, OtherActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}