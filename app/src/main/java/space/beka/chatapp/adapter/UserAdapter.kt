package space.beka.chatapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import space.beka.chatapp.databinding.ItemRvBinding
import space.beka.chatapp.models.User

class UserAdapter(var list: ArrayList<User> = ArrayList()) : RecyclerView.Adapter<UserAdapter.Vh>() {
    inner  class Vh(var itemRvBinding: ItemRvBinding):RecyclerView.ViewHolder(itemRvBinding.root) {
        fun onBind(user: User, position: Int) {
itemRvBinding.tvName.text = user.displayName
            Picasso.get().load(user.imageLink).into(itemRvBinding.imageItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRvBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position] ,position)
    }

    override fun getItemCount(): Int =list.size



}