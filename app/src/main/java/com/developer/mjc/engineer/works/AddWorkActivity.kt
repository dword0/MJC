package com.developer.mjc.engineer.works

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developer.mjc.R
import com.developer.mjc.util.CredsValidationHelper
import com.developer.mjc.util.ErrorViewHelper.Companion.enableError
import com.developer.mjc.util.MjcConstants
import kotlinx.android.synthetic.main.activity_add_work.*
import org.shagi.filepicker.ExtFile
import org.shagi.filepicker.FilePicker
import org.shagi.filepicker.FilePickerDialog
import org.shagi.filepicker.FilePickerFragment

class AddWorkActivity : AppCompatActivity() {
    lateinit var imageAdapter: AddWorkImageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_work)

        setupViews()
    }

    private fun setupViews() {

        btSaveWork.setOnClickListener{
            validateAndSaveWork()
        }
        val pickerFragment = FilePickerFragment.getFragment(supportFragmentManager, useCache = true)
        imageAdapter = AddWorkImageAdapter(this, arrayListOf<String>(""))
        ivAddPhoto.setOnClickListener{
            pickerFragment.use(FilePickerDialog.newInstance())
            pickerFragment.setOnLoadingListener(object : FilePicker.OnLoadingListener {
                override fun onLoadingStart(key: Long) {
                    //imageView.setImageResource(R.color.colorAccent)

                    imageAdapter.addImage(MjcConstants.IMAGE_PLACE_HOLDER)
                    imageAdapter.enableProgress(true)
                }

                override fun onLoadingSuccess(key: Long, file: ExtFile) {


                }

                override fun onLoadingFailure(key: Long, throwable: Throwable) {
                }

            })
            pickerFragment.show()
        }
    }

    private fun validateAndSaveWork() {
        val name = etAddWorkName.text.toString()
        val owner = etAddWorkOwnerName.text.toString()
        val dateOfComletion = etAddWorkDateOfCompletion.text.toString()
        val description = etAddWorkDescription.text.toString()

        if (!CredsValidationHelper.isValidInputText(name)){

            etAddWorkName.enableError("Invalid input")
            return
        }
        if (!CredsValidationHelper.isValidInputText(owner)){

            etAddWorkOwnerName.enableError("Invalid input")
            return
        }
        if (!CredsValidationHelper.isValidInputText(dateOfComletion)){

            Toast.makeText(this, "select a date", Toast.LENGTH_SHORT).show()
            return
        }
        if (!CredsValidationHelper.isValidInputText(description)){
            etAddWorkDescription.enableError("Invalid input")
            return
        }
    }
}