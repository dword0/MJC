package com.developer.mjc.consumer.workrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.developer.mjc.R
import com.developer.mjc.util.ErrorViewHelper.Companion.disableErrorBlueBg
import com.developer.mjc.util.ErrorViewHelper.Companion.enableError
import com.developer.mjc.util.FileListHelperFragment
import com.developer.mjc.util.MjcConstants
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_create_request.*
import kotlinx.android.synthetic.main.activity_requirement.*
import kotlinx.android.synthetic.main.main_toolbar.view.*

class CreateRequestActivity : AppCompatActivity() {
    var planList = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_request)

        toolbar_createRequest.tvAdminToolbarHeading.text = "Create Request"
        toolbar_createRequest.button_back_toolbar.setOnClickListener{
            onBackPressed()
        }

        if (intent != null){
            planList.addAll(intent.getStringArrayListExtra(MjcConstants.KEY_PLAN_LIST)!!)
        }
        setupViews()
    }


    private fun setupViews() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        var fileListFrag = FileListHelperFragment.newInstance(planList,true)
        transaction.replace(frame_req_file_list.id,fileListFrag)
        transaction.commit()

        etReqBuildingArea.setText( Prefs.getString(MjcConstants.KEY_BUILDING_AREA,"100"))
        etReqTotalArea.setText(Prefs.getString(MjcConstants.KEY_TOTAL_AREA,"100"))

        setSelectedEngineer(Prefs.getString(MjcConstants.KEY_ENGINEER_ID,""))

        btSendRequest.setOnClickListener{
            validateAndSendReq()
        }

    }

    private fun validateAndSendReq() {

        val buildingArea = etReqBuildingArea.text
        val totalArea = etReqTotalArea.text
        val selectedEng = Prefs.getString(MjcConstants.KEY_ENGINEER_ID,"")

        if (totalArea.isNullOrBlank()){
            etReqTotalArea.enableError("Invalid data!")
            return
        }
        else{
            etReqTotalArea.disableErrorBlueBg()
        }
        if (buildingArea.isNullOrBlank()){
            etReqBuildingArea.enableError("Invalid data!")
            return
        }
        else{
            etReqBuildingArea.disableErrorBlueBg()
        }
        if (selectedEng.isNullOrBlank()){
            Toast.makeText(this, "No Engineer selected!", Toast.LENGTH_SHORT).show()
        }
        if (planList.isEmpty()){
            Toast.makeText(this, "No Plans added", Toast.LENGTH_SHORT).show()
            return
        }
        // Create request object and send request
    }

    private fun setSelectedEngineer(string: String?) {
        //get Engineer data from server

        tvSelectedEngname.text = "Chithlal K"
        tvSelectedEngOcupation.text = "Software Engineer"

        //add check for profile image
        if (true){
            Glide.with(this)
                .load("ivProfileImageRequest")
                .centerCrop()
                .placeholder(ContextCompat.getDrawable(this,R.drawable.ic_engineer))
                .into(ivProfileImageRequest)
        }
    }

}