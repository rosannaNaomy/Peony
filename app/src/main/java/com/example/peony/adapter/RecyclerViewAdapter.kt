package com.example.peony.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.peony.R
import com.example.peony.database.entities.MedicationData
import com.example.peony.database.entities.UserEntity
import kotlinx.android.synthetic.main.list_row.view.*

//val listener: RowClickListener
class RecyclerViewAdapter(): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private var listData: List<MedicationData>? = null
    fun setListData(listData: List<MedicationData>?){
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_row, parent, false)
        return MyViewHolder(view)//, listener)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        Log.d("RecyclerAdapter", "onBindViewholder Position: $position")
//        Log.d("RecyclerAdapter", "onBindViewholder opendfda: ${listData?.get(position)?.opendfda?.brand_name}")
//        Log.d("RecyclerAdapter", "onBindViewholder result: ${listData?.get(position)?.result!![position]?.drug_interactions}")
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null) return 0
        Log.d("RecyclerAdapter", "getItemCount: ${listData!!.size}")
        return listData?.size!!
    }

    //, val listener: RowClickListener
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameText = view.name_textView
        val descriptionText = view.description_textView
        val deleteButton = view.delete_button

        fun bind(data: MedicationData){
            nameText.text = data.opendfda!!.brand_name[0]
            descriptionText.text = data.result[0]?.drug_interactions.toString()
//            deleteButton.setOnClickListener {
//                listener.onDeleteCLickListener(data)
//            }
        }
    }

    interface RowClickListener{
        fun onDeleteCLickListener(userEntity: UserEntity)
    }
}