package com.zihuan.demo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zihuan.view.crvlibrary.R
import com.zihuan.view.crvlibrary.ZRecyclerData

/**
 *
 * @Description
 * @author zihuan
 * @date 2019/12/4 11:06
 */
class ReAdapter : RecyclerView.Adapter<ReAdapter.Viewholder2>(), ZRecyclerData {
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): Viewholder2 {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.layout, viewGroup, false)
        return Viewholder2(view)
    }

    var mList = ArrayList<String>()
    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: Viewholder2, position: Int) {

    }

    class Viewholder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun update(data: List<*>) {
        mList.addAll(data as ArrayList<String>)
        Log.e("刷新", "刷新数据")
        notifyDataSetChanged()
    }
}