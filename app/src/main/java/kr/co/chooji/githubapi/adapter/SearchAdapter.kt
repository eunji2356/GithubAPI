package kr.co.chooji.githubapi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kr.co.chooji.githubapi.R
import kr.co.chooji.githubapi.databinding.ItemSearchUserBinding
import kr.co.chooji.githubapi.model.search.SearchUser

class SearchAdapter: RecyclerView.Adapter<SearchAdapter.Holder>() {

    var list: MutableList<SearchUser> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun updateUserList(userList: MutableList<SearchUser>){
        list.clear()
        list.addAll(userList)
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ItemSearchUserBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int){
            val item = list[position]

            Glide.with(binding.searchUserImg.context)
                .load(item.avatarUrl)
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