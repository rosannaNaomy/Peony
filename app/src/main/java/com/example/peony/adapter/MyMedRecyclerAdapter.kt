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
import kotlinx.android.synthetic.main.my_med_item.view.*

class MyMedRecyclerAdapter(val listener: RowClickListener, val userEntity: UserEntity): RecyclerView.Adapter<MyMedRecyclerAdapter.MyViewHolder>() {

    private var listData: List<MedicationData>? = null
    fun setListData(listData: List<MedicationData>?){
        this.listData = listData
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyMedRecyclerAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_med_item, parent, false)
        return MyViewHolder(view, listener, userEntity)
    }

    override fun onBindViewHolder(holder: MyMedRecyclerAdapter.MyViewHolder, position: Int) {
        holder.bind(listData?.get(position)!!)
    }

    override fun getItemCount(): Int {
        if(listData == null) return 0
        return listData?.size!!
    }

    class MyViewHolder(view: View, val listener: RowClickListener, val userEntity: UserEntity): RecyclerView.ViewHolder(view){
        val nameText = view.medName_textView
        //        val descriptionText = view.description_textView
        val moreInfoButton = view.moreInfo_button

        fun bind(data: MedicationData){
            nameText.text = data.opendfda.brand_name[0]
//            if(data.result[0]?.drug_interactions == null){
//                descriptionText.visibility = View.INVISIBLE
//            }else{
//                descriptionText.text = data.result[0]?.drug_interactions.toString()
//            }
            moreInfoButton.setOnClickListener {
                Log.d("RecyclerAdapter", "setOnClick: inside")
                listener.itemIntereact(MedicationData(data.opendfda, data.result, userName = userEntity.userName))
            }
        }
    }

    interface RowClickListener{
        fun itemIntereact(medicationData: MedicationData)
    }
}