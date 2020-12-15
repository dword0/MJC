package com.developer.mjc.profile

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.developer.mjc.R
import kotlinx.android.synthetic.main.fragment_profile.*
import org.shagi.filepicker.ExtFile
import org.shagi.filepicker.FilePicker
import org.shagi.filepicker.FilePickerDialog
import org.shagi.filepicker.FilePickerFragment


private const val ARG_USER_ID = "user_id"
private const val ARG_IS_EDITABLE = "Editable"

class ProfileFragment : Fragment() {

    private var userId: String = ""
    private var isEditable: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userId = it.getString(ARG_USER_ID)!!
            isEditable = it.getBoolean(ARG_IS_EDITABLE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        isEditable = true
        if (isEditable){
            btEditProfile.visibility = View.VISIBLE
        }
        btEditProfile.setOnClickListener{
            enableEditMode(true)
        }
        btSaveProfile.setOnClickListener{
            enableEditMode(false)
        }

        llUploadID.setOnClickListener{
            openFileChooser()
        }
    }

    private fun enableEditMode(enable: Boolean) {
        if(enable) {
            btEditProfile.visibility = View.GONE
            btSaveProfile.visibility = View.VISIBLE

            tvPlaceProfile.visibility = View.GONE
            tvDistrictProfile.visibility = View.GONE
            tvStateProfile.visibility = View.GONE

            etPlaceProfile.visibility = View.VISIBLE
            etDistrictProfile.visibility = View.VISIBLE
            etStateProfile.visibility = View.VISIBLE

            //if(isEngineer)
            if(true){
                etFeesProfile.visibility = View.VISIBLE
                tvFeesProfile.visibility = View.GONE
            }
        }
        else{
            btEditProfile.visibility = View.VISIBLE
            btSaveProfile.visibility = View.GONE

            tvPlaceProfile.visibility = View.VISIBLE
            tvDistrictProfile.visibility = View.VISIBLE
            tvStateProfile.visibility = View.VISIBLE

            etPlaceProfile.visibility = View.GONE
            etDistrictProfile.visibility = View.GONE
            etStateProfile.visibility = View.GONE

            //if(isEngineer)
            if(true){
                etFeesProfile.visibility = View.GONE
                tvFeesProfile.visibility = View.VISIBLE
            }
        }

    }
    private fun openFileChooser() {
        val pickerFragment = FilePickerFragment.getFragment(activity!!.supportFragmentManager, true)
        pickerFragment.use(FilePickerDialog.newInstance())
        pickerFragment.setOnLoadingListener(object : FilePicker.OnLoadingListener {
            override fun onLoadingStart(key: Long) {

            }

            override fun onLoadingSuccess(key: Long, file: ExtFile) {
                //textView.text = file.toString()
                val bitmap = BitmapFactory.decodeFile(file.file.absolutePath)
                ivIDCard.setImageBitmap(bitmap)
                ivIDCard.visibility = View.VISIBLE
                ivIDHolder.visibility = View.GONE
            }

            override fun onLoadingFailure(key: Long, throwable: Throwable) {
                // textView.text = throwable.message
                Toast.makeText(
                    context,
                    "Unable to select file!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        pickerFragment.show()
    }

    companion object {

        @JvmStatic
        fun newInstance(userId: String, isEditable: Boolean) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_ID, userId)
                    putBoolean(ARG_IS_EDITABLE, isEditable)
                }
            }
    }
}