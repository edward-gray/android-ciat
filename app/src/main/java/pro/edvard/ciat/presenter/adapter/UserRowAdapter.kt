package pro.edvard.ciat.presenter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_user_row.view.*
import pro.edvard.ciat.R
import pro.edvard.ciat.model.User

class UserRowAdapter(
    private val context: Context,
    private val listener: OnItemClickListener
): PagingDataAdapter<User, UserRowAdapter.UserRowViewHolder>(UserDiffUtilCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRowViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_row, parent, false)
        return UserRowViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserRowViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindUser(user = it)
        }
    }

    inner class UserRowViewHolder(itemView: View):
        RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val avatar: ImageView = itemView.image_avatar
        private val fullName: TextView = itemView.text_full_name

        init {
            itemView.setOnClickListener(this)
        }

        fun bindUser(user: User) {
            fullName.text = user.getFullName()
            Glide.with(context).load(user.avatar).into(avatar)
        }

        override fun onClick(v: View?) {
            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}

class UserDiffUtilCallBack : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
                && oldItem.getFullName() == newItem.getFullName()
                && oldItem.email == newItem.email
                && oldItem.avatar == newItem.avatar
    }
}