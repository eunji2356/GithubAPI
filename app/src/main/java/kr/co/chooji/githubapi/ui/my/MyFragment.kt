package kr.co.chooji.githubapi.ui.my

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.databinding.FragmentMyBinding

class MyFragment: Fragment() {

    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!

    private lateinit var myViewModel: MyViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false)

        myViewModel = ViewModelProvider(this).get(MyViewModel::class.java)
        myViewModel.getUser()

        observeViewModel()

        return binding.root
    }

    private fun observeViewModel(){
        myViewModel.userInfo.observe(viewLifecycleOwner, { user ->
            binding.userName.text = user.name
            binding.userBio.text = user.bio

            Glide.with(binding.userImg.context)
                .load(user.avatarUrl)
                .transform(CenterCrop(), RoundedCorners(50))
                .into(binding.userImg)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}