package com.temotion.mirzas.binandroidtest.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.temotion.mirzas.binandroidtest.Model.ImageList
import com.temotion.mirzas.binandroidtest.R

class GalleryAdapter : RecyclerView.Adapter <GalleryAdapter.ImageViewHolder>() {
    lateinit private  var imageList : List<ImageList>
    private val TAG = "galleryAdapter"

    fun setupData(imageList : List<ImageList>){
        this.imageList = imageList
        notifyDataSetChanged()


    }

/*The Flow of the Adapter
* 1-- OncreateviewHolder : It inflate the Single_Item layout and create a view and return it to ViewHolder(CountryViewholder)
* 2-- OnBindViewHolder : it takes the Items of Position index from the list and pass it to ViewHolder.bind method
* it also get the Size of the List to determine how many times to run
* ViewHolder(CountryViewholder)  has the View and the Data, It set the data to view*/

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

        fun bind(data : ImageList){
            tv_imagename.text=data.author
            Picasso.get().load(data.downloadUrl).into(iv_preview)
        }

    }
}