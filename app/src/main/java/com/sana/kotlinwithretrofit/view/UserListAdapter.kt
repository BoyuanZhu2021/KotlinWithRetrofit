package com.sana.kotlinwithretrofit

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.ArrayList

/* This adapter communications between UserActivity.kt and UserDetailsActivity.kt */

class UserListAdapter(

    val context: Context,
    var userList: ArrayList<User>,
    private val itemClickListener: (User, position: Int) -> Unit) : RecyclerView.Adapter<UserListAdapter.UserViewHolder>()
    {

    var position: Int = 0

    /* Initialization */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            context, LayoutInflater.from(context).inflate(R.layout.layout_user_row, parent, false)
        )
    }

    override fun getItemCount() = userList.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindView(userList[position], position, itemClickListener)
        this.position = position

    }


    /*This controls the layout_user_row.xml
      Append the edit text from the addItem section */
    class UserViewHolder(val context: Context, itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(user: User, selectedPosition: Int, itemClickListener: (User, pos: Int) -> Unit) {

            // val xxx = the the selected itemView
            val tv_website = itemView.findViewById<TextView>(R.id.tv_website)
            val tv_userName = itemView.findViewById<TextView>(R.id.tv_userName)
            //val tv_userType = itemView.findViewById<TextView>(R.id.tv_userType)
            val iv_avatar = itemView.findViewById<ImageView>(R.id.iv_avatar)

            try {
                //The text of the credential object = the text from the User object
                tv_website.text = user.website
                tv_userName.text = user.username

                //psw
                //tv_userType.text = user.userType
                //psw

                Glide.with(itemView).load(user.image)
                    .apply(RequestOptions().centerCrop())
                    .into(iv_avatar)

                /* When the user is clicked from UserActivity.kt*/
                itemView.setOnClickListener { itemClickListener(user, selectedPosition) }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    public fun addItem(user: User) {
        userList.add(user)
        notifyItemInserted(position)
        notifyItemRangeChanged(position, userList.size)
    }

    public fun removeItem(position: Int) {
        userList.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, userList.size)
    }

}