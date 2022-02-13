package kr.co.chooji.githubapi.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.adapter.SearchAdapter
import kr.co.chooji.githubapi.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: HomeViewModel
    private val adapter = SearchAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        binding.searchRecycler.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = this.adapter
        }

        viewModel.getSearchUser("eunji", 1)

        observerViewModel()

        return binding.root
    }

    private fun observerViewModel(){
        viewModel.isNotEmpty.observe(viewLifecycleOwner, { isNotEmpty ->
            if(isNotEmpty)
                binding.searchNone.visibility = View.GONE
            else
                binding.searchNone.visibility = View.VISIBLE
        })

        viewModel.userList.observe(viewLifecycleOwner, { list ->
            adapter.updateUserList(list)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}