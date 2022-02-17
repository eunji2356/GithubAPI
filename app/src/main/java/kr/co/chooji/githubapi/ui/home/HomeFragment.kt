package kr.co.chooji.githubapi.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.adapter.SearchAdapter
import kr.co.chooji.githubapi.databinding.FragmentHomeBinding
import kr.co.chooji.githubapi.repository.HomeRepository
import kr.co.chooji.githubapi.ui.searchDetail.SearchDetailActivity

class HomeFragment: Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var homeViewModel: HomeViewModel
    private val adapter = SearchAdapter()

    private var page: Int = 1
    var search: String = ""

    private val viewModelFactory get() = HomeViewModelFactory(HomeRepository())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        homeViewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)

        initView()
        observerViewModel()

        return binding.root
    }

    private fun initView(){
        binding.lifecycleOwner = viewLifecycleOwner

        binding.searchBtn.setOnClickListener {
            binding.searchRecycler.scrollToPosition(0)

            page = 1
            search = binding.searchEditText.text.toString()
            homeViewModel.getSearchUser(search, page)

            val imm = ContextCompat.getSystemService(view!!.context, InputMethodManager::class.java)
            imm?.hideSoftInputFromWindow(view!!.windowToken, 0)
        }

        binding.searchRecycler.also {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = this.adapter

            it.addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    if(!recyclerView.canScrollVertically(1)){
                        homeViewModel.getSearchUser(search, ++page)
                    }
                }
            })
        }

        adapter.setClickListener {
            val intent = Intent(context, SearchDetailActivity::class.java)
            intent.putExtra("userName", it)
            startActivity(intent)
        }
    }

    private fun observerViewModel(){
        homeViewModel.isNotEmpty.observe(viewLifecycleOwner, { isNotEmpty ->
            if(isNotEmpty)
                binding.searchNone.visibility = View.GONE
            else
                binding.searchNone.visibility = View.VISIBLE
        })

        homeViewModel.userList.observe(viewLifecycleOwner, { list ->
            adapter.updateUserList(page, list)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        page = 1
        search = ""
        adapter.list = mutableListOf()

        homeViewModel.isNotEmpty.value = false
        homeViewModel.userList.value = mutableListOf()

        _binding = null
    }
}