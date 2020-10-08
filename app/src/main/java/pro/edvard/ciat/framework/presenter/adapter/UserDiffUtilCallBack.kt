package pro.edvard.ciat.framework.presenter.adapter

import androidx.recyclerview.widget.DiffUtil
import pro.edvard.ciat.business.domain.model.User

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