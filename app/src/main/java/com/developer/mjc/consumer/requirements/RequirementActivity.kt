package com.developer.mjc.consumer.requirements

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import com.developer.mjc.R
import com.developer.mjc.consumer.engineerslist.EngineerListActivity
import com.developer.mjc.model.requirements.ConstructionRequirements
import com.developer.mjc.util.*
import com.developer.mjc.util.ErrorViewHelper.Companion.disableErrorBlueBg
import com.developer.mjc.util.ErrorViewHelper.Companion.enableError
import com.developer.mjc.util.ProccessingBottomSheet.Companion.DIALOG_TYPE_PROCESSING
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_requirement.*
import kotlinx.android.synthetic.main.main_toolbar.view.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.shagi.filepicker.ExtFile
import org.shagi.filepicker.FilePicker
import org.shagi.filepicker.FilePickerDialog
import org.shagi.filepicker.FilePickerFragment
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.concurrent.schedule

class RequirementActivity : AppCompatActivity() {
    private lateinit var fileListFrag: FileListHelperFragment

    private var planFileList = HashMap<String,File>()
    private var request: ConstructionRequirements? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_requirement)

        toolbar_requirement.tvAdminToolbarHeading.text = "Requirements"
        toolbar_requirement.button_back_toolbar.setOnClickListener{
            onBackPressed()
        }

        setupView()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this);
    }

    private fun setupView() {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
         fileListFrag = FileListHelperFragment.newInstance(arrayListOf(),false)
        transaction.replace(frame_file_list.id,fileListFrag)
        transaction.commit()

        btContinueRequirement.setOnClickListener{
            validateAndProcessRequirement()
        }
    }

    private fun validateAndProcessRequirement() {
        val buildingArea = tvMeasureBuildingArea.text.toString()
        val totalArea = tvMeasureTotalArea.text.toString()

        if (!CredsValidationHelper.isValidInputText(totalArea)){
            tvMeasureTotalArea.enableError("Invalid data!")
            return
        }else{
            tvMeasureTotalArea.disableErrorBlueBg()
        }
        if (!CredsValidationHelper.isValidInputText(buildingArea)){
            tvMeasureBuildingArea.enableError("Invalid data!")
            return
        }else{
            tvMeasureBuildingArea.disableErrorBlueBg()
        }
        if (planFileList.size == 0)
        {
            Toast.makeText(this, "Please add a plan!", Toast.LENGTH_SHORT).show()
            return
        }
        Prefs.putString(MjcConstants.KEY_BUILDING_AREA,buildingArea)
        Prefs.putString(MjcConstants.KEY_TOTAL_AREA,totalArea)
        uploadPlans()
        val userId = ""

        //request = ConstructionRequirements(userId,)

    }

    private fun uploadPlans() {

        val bottomSheetProcessing = ProccessingBottomSheet(DIALOG_TYPE_PROCESSING)
        bottomSheetProcessing.show(supportFragmentManager,"Uploading")

        //bottomSheetProcessing.setProcessingStatus("Uploading files..")
        Timer("Startup",false).schedule(1000){
            val planList = ArrayList<String>()
            for( item in planFileList.toList())
            {
                planList.add(item.first)
            }
            bottomSheetProcessing.dismiss()
            val intent = Intent(this@RequirementActivity,EngineerListActivity::class.java)
            intent.putStringArrayListExtra(MjcConstants.KEY_PLAN_LIST,planList)
            startActivity(intent)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onFileAddEvent(event: AddFileEvent){
        openFileChooser()
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onRemoveFileEvent(event: RemoveFileEvent){
        planFileList.remove(event.fileName)

    }

    private fun openFileChooser() {
        val pickerFragment = FilePickerFragment.getFragment(supportFragmentManager,true)
        pickerFragment.use(FilePickerDialog.newInstance())
        pickerFragment.setOnLoadingListener(object : FilePicker.OnLoadingListener {
            override fun onLoadingStart(key: Long) {

            }

            override fun onLoadingSuccess(key: Long, file: ExtFile) {
                //textView.text = file.toString()

                fileListFrag.addFile(file.file.name)
                planFileList.put(file.file.name,file.file)
            }

            override fun onLoadingFailure(key: Long, throwable: Throwable) {
                // textView.text = throwable.message
                Toast.makeText(
                    this@RequirementActivity,
                    "Unable to select file!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })
        pickerFragment.show()
    }
}