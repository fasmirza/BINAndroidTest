package com.temotion.mirzas.binandroidtest.Fragments

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.temotion.mirzas.binandroidtest.Adapters.GalleryAdapter
import com.temotion.mirzas.binandroidtest.Interface.RecyclerViewInterface
import com.temotion.mirzas.binandroidtest.Model.ImageList
import com.temotion.mirzas.binandroidtest.R
import com.temotion.mirzas.binandroidtest.ViewModels.GalleryViewModel
import com.temotion.mirzas.binandroidtest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GalleryFragment : Fragment(), RecyclerViewInterface{

    private var _binding: FragmentFirstBinding? = null
    lateinit var rv_gallery : RecyclerView
    lateinit var galleryAdapter: GalleryAdapter
    lateinit var imageData : List<ImageList>

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)

        initView()
        initViewModel()

        return binding.root

    }

    fun initView(){
        rv_gallery = binding.rvGallery
        galleryAdapter = GalleryAdapter()
        var layoutmanager  = GridLayoutManager(activity, 2)
        rv_gallery.layoutManager = layoutmanager
    }
    fun initViewModel(){
        val viewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        /*setting Observer to the viewmodel*/
        viewModel.getLiveDataObserver().observe(viewLifecycleOwner, Observer{
            if (it != null){
                imageData= it
                galleryAdapter.setupData(it,this) // "it" is the ListData sent by observer

                rv_gallery.adapter = galleryAdapter
            }else{
                Log.d(ContentValues.TAG, "initViewModel: Error")
            }
        })
        viewModel.makeApiCall()   //calling API call throw ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun imageRVOnclickListener(position: Int) {
        var bundle = Bundle()
        bundle.putString("downloadURL",imageData.get(position).downloadUrl)

        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment,bundle)
    }
}