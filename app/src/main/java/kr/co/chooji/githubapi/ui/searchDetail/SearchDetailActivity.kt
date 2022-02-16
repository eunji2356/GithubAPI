package kr.co.chooji.githubapi.ui.searchDetail

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.databinding.ActivitySearchDetailBinding
import kr.co.chooji.githubapi.repository.SearchDetailRepository

class SearchDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchDetailBinding
    private lateinit var viewModel: SearchDetailViewModel

    private val viewModelFactory get() = SearchDetailViewModelFactory(SearchDetailRepository())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory).get(SearchDetailViewModel::class.java)

        binding = ActivitySearchDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        observeViewModel()
    }

    private fun initView(){
        binding.searchDetailBack.setOnClickListener {
            val intent = Intent()
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun observeViewModel(){
        viewModel.getUserOne("${intent.extras?.getString("userName")}")

        viewModel.userInfo.observe(this, { user ->
            with(binding){
                searchDetailUserName.text = user.name
                searchDetailUserBio.text = user.bio

                Glide.with(searchDetailUserImg.context)
                    .load(user.avatarUrl)
                    .transform(CenterCrop(), RoundedCorners(50))
                    .into(searchDetailUserImg)
            }
        })
    }
}