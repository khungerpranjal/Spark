package com.demo.sparklive.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.sparklive.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_view.*
import org.json.JSONArray
import org.json.JSONObject

class GridAdapter(obj: JSONArray, context: Context):
    RecyclerView.Adapter<GridViewHolder>() {

    private val inflater: LayoutInflater
    private val obj:JSONArray

    init {
        inflater = LayoutInflater.from(context)
        this.obj = obj
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false)

        return GridViewHolder(view)
    }

    override fun getItemCount(): Int {
        return obj.length()
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val expert = obj.getJSONObject(position).get("expert") as JSONObject
        val publisher = obj.getJSONObject(position).get("publisher") as JSONObject
        val videoDetails = obj.getJSONObject(position).get("action_counts") as JSONObject
        holder.name.text = expert.getString("name")
        Picasso.get().load(expert.getString("profile_pic")).into(holder.dp)
        holder.bio.text = expert.getString("short_bio")
        holder.description.text = obj.getJSONObject(position).get("description").toString()
        Picasso.get().load(obj.getJSONObject(position).get("thumbnail").toString()).into(holder.thumbnail)
        holder.likes.text = videoDetails.get("like").toString()+" likes"
        holder.time.text =  obj.getJSONObject(position).get("published_at").toString()
    }
}

class GridViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val name: TextView
    val dp: ImageView
    val bio: TextView
    val likes: TextView
    val description: TextView
    val thumbnail: ImageView
    val ic_like: ImageView
    val ic_comment: ImageView
    val ic_share: ImageView
    val views: TextView
    val time: TextView

    init {
        name = view.findViewById(R.id.name) as TextView
        dp = view.findViewById(R.id.dp) as ImageView
        bio = view.findViewById(R.id.bio) as TextView
        ic_like = view.findViewById(R.id.ic_like) as ImageView
        likes = view.findViewById(R.id.likes) as TextView
        ic_comment = view.findViewById(R.id.ic_comment) as ImageView
        ic_share = view.findViewById(R.id.ic_share) as ImageView
        thumbnail = view.findViewById(R.id.thumbnail) as ImageView
        description = view.findViewById(R.id.description) as TextView
        time = view.findViewById(R.id.time) as TextView
        views = view.findViewById(R.id.views) as TextView
    }
}