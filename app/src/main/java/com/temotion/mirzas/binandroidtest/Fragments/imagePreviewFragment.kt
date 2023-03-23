package com.temotion.mirzas.binandroidtest.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.squareup.picasso.Picasso
import com.temotion.mirzas.binandroidtest.R
import com.temotion.mirzas.binandroidtest.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class imagePreviewFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private  val TAG = "SecondFragment"

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    lateinit var iv_previewImage : ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        iv_previewImage=binding.ivPreviewImage

        var imageURL = arguments?.getString("downloadURL")

        Log.d(TAG, "onCreateView:" +imageURL)
        Picasso.get().load(imageURL).placeholder(R.drawable.ic_launcher_foreground).into(iv_previewImage)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}