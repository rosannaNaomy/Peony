package com.example.peony.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.peony.R
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.TempMedData
import com.example.peony.database.entities.UserEntity
import kotlinx.android.synthetic.main.list_row.view.*

class RecyclerViewAdapter(val listener: RowClickListener, val userEntity: UserEntity): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<TempMedData>? = null
    fun setListData(listData: List<TempMedData>?){
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return MyViewHolder(view, listener, userEntity)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        Log.d("RecyclerAdapter", "onBindViewholder Position: $position")
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null) return 0
        Log.d("RecyclerAdapter", "getItemCount: ${listData!!.size}")
        return listData?.size!!
    }

    class MyViewHolder(view: View, val listener: RowClickListener, val userEntity: UserEntity): RecyclerView.ViewHolder(view){
        val nameText = view.name_textView
        val addButton = view.add_imageButton

        fun bind(data: TempMedData){
            nameText.text = data.opendfda.brand_name[0]
            addButton.setOnClickListener {
                Log.d("RecyclerAdapter", "setOnClick: inside")
                listener.onAddMedCLickListener(MedicationData(data.opendfda, data.result, userName = userEntity.userName))
            }
        }
    }

    interface RowClickListener{
        fun onAddMedCLickListener(medicationData: MedicationData)
    }
}