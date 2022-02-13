package kr.co.chooji.githubapi.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.databinding.FragmentMyBinding

class MyFragment: Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}