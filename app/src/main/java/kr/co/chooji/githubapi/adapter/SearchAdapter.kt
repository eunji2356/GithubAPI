package kr.co.chooji.githubapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.databinding.ItemSearchUserBinding
import kr.co.chooji.githubapi.model.search.SearchUser

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.Holder>() {

    var list: MutableList<SearchUser> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserList(page: Int, userList: MutableList<SearchUser>){
        if(page == 1) {
            list.clear()
        }
        list.addAll(userList)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemSearchUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            val item = list[position]

            Glide.with(binding.searchUserImg.context)
                .load(item.avatarUrl)
                .transform(CenterCrop(), RoundedCorners(10))
                .into(binding.searchUserImg)

            binding.searchUser.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = Holder(
        DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_search_user, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(position)

    override fun getItemCount() = list.size
}