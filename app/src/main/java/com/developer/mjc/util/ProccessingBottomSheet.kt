package com.developer.mjc.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.developer.mjc.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.layout_dialog_bottom_sheet.*

class ProccessingBottomSheet(val dialogType: Int): BottomSheetDialogFragment() {

    private var confirmationListener: ProccessingBottomSheet.ConfirmationListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.layout_dialog_bottom_sheet,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (dialogType == DIALOG_TYPE_CONFIRMATION){
            ll_confirm_dialog.visibility = View.VISIBLE
            ll_processing_dialog.visibility = View.GONE
        }else{
            ll_confirm_dialog.visibility = View.GONE
            ll_processing_dialog.visibility = View.VISIBLE
            isCancelable = false
        }
        setupViews()
    }

    fun setConfirmationListener(listener: ConfirmationListener){
        confirmationListener  = listener
    }
    fun setConfirmQuiestion(qstn: String){
        if (dialogType == DIALOG_TYPE_CONFIRMATION){
            tvBottomSheetConfirmQuestion.text = qstn
        }
    }
    fun setProcessingStatus(status: String){
        if (dialogType == DIALOG_TYPE_PROCESSING)
        {
            tvProgrssTextBottomSheet.text = status
        }
    }
    private fun setupViews() {

        btBottomSheetConfirmNo.setOnClickListener{
            if (confirmationListener != null){
                confirmationListener!!.onNoClicked()
            }
        }
        btBottomSheetConfirmYes.setOnClickListener{
            if (confirmationListener != null){
                confirmationListener!!.onYesClicked()
            }
        }

    }

    interface ConfirmationListener{
        fun onYesClicked()
        fun onNoClicked()
    }


    companion object{
        const val DIALOG_TYPE_CONFIRMATION = 1
        const val DIALOG_TYPE_PROCESSING = 2
    }

}