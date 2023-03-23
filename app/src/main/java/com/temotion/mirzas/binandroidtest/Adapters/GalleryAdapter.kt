package com.temotion.mirzas.binandroidtest.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.temotion.mirzas.binandroidtest.Interface.RecyclerViewInterface
import com.temotion.mirzas.binandroidtest.Model.ImageList
import com.temotion.mirzas.binandroidtest.R

class GalleryAdapter : RecyclerView.Adapter <GalleryAdapter.ImageViewHolder>() {
    lateinit private  var imageList : List<ImageList>
    private val TAG = "galleryAdapter"
    //lateinit var recyclerViewInterface : RecyclerViewInterface
    companion object{
        lateinit var recyclerViewInterface : RecyclerViewInterface
    }

    fun setupData(imageList : List<ImageList>,recyclerViewInterface :RecyclerViewInterface){
        this.imageList = imageList
        GalleryAdapter.recyclerViewInterface = recyclerViewInterface
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList.get(position))
    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: "+ imageList.size )
        return imageList.size
    }

    class ImageViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val iv_preview = view.findViewById<ImageView>(R.id.iv_preview)
        val tv_imagename = view.findViewById<TextView>(R.id.tv_imagename)
        val ll_singleContainer = view.findViewById<LinearLayout>(R.id.ll_singleContainer)

        fun bind(data : ImageList){
            tv_imagename.text=data.author
            Picasso.get().load(data.downloadUrl).placeholder(R.drawable.ic_launcher_foreground).into(iv_preview)
            ll_singleContainer.setOnClickListener{
                recyclerViewInterface?.imageRVOnclickListener(adapterPosition)
            }

        }

    }
}