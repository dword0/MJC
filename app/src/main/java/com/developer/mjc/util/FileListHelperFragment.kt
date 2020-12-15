package com.developer.mjc.util

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.developer.mjc.R
import kotlinx.android.synthetic.main.fragment_file_list_helper.*
import java.io.File
import java.util.ArrayList
import kotlinx.android.synthetic.main.layout_file_viewer.*
import kotlinx.android.synthetic.main.layout_file_viewer.view.*
import org.greenrobot.eventbus.EventBus


private const val ARG_FILE_LIST = "file_list"
private const val ARG_IS_PREVIEW = "is Preview"



class FileListHelperFragment : Fragment() {

    private var fileList: ArrayList<String>? = null
    private var isPreview: Boolean = false
    private var adapter: FileListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            fileList = it.getStringArrayList(ARG_FILE_LIST)
            isPreview = it.getBoolean(ARG_IS_PREVIEW)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_file_list_helper, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (isPreview)
            btFileViewerAddFile.visibility = View.GONE
        rvFileViewer.layoutManager = LinearLayoutManager(context)
        if (fileList != null)
        adapter = FileListAdapter(context!!,fileList!!,object :FileListAdapter.FileViewerListener{
            override fun onFileRemoved(fileName: String) {

                if (fileList!!.isNotEmpty()){
                    fileList!!.remove(fileName)
                    EventBus.getDefault().post(RemoveFileEvent(fileName))
                }
            }

        },
        isPreview)
        rvFileViewer.adapter = adapter

        btFileViewerAddFile.setOnClickListener{
            EventBus.getDefault().post(AddFileEvent())
        }
    }
    fun addFile(fileName: String){
        if (adapter != null){
            adapter!!.addFile(fileName)
        }
    }


    companion object {

        @JvmStatic
        fun newInstance(fileList: List<String>,isPreview: Boolean ) =
                FileListHelperFragment().apply {
                    arguments = Bundle().apply {
                        putStringArrayList(ARG_FILE_LIST, fileList as ArrayList<String>)
                        putBoolean(ARG_IS_PREVIEW,isPreview)
                    }
                }
    }
}

class FileListAdapter(val context: Context,
                      val fileList: ArrayList<String>,
                    val listner: FileViewerListener,val isPreview: Boolean): RecyclerView.Adapter<FileListAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {

        val fileName = view.tvFileViewerFileName
        val btRemoveFile = view.ivCloseFile
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_file_viewer,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isPreview)
            holder.btRemoveFile.visibility = View.GONE
        holder.fileName.text = fileList[position]
        holder.btRemoveFile.setOnClickListener{

            listner.onFileRemoved(fileList[position])
            //fileList.remove(fileList[position])
            notifyDataSetChanged()
        }
    }
    fun addFile(fileName: String){
        fileList.add(fileName)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
       return fileList.size
    }

    interface FileViewerListener{
        fun onFileRemoved(fileName: String)
    }
}
/** create event handler with this Class as parameter signature to get add file button click callback*/
class AddFileEvent()
class RemoveFileEvent(val fileName: String)