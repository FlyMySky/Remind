package com.skwen.remind.dialog

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.RecyclerView
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.skwen.remind.R
import kotlinx.android.synthetic.main.dialog_cycle_select.*

/**
 * 作者：vicent
 * 时间：2019/1/30
 */
class CycleSelectDialog : DialogFragment() {

    private var list: MutableList<String> = mutableListOf()

    private lateinit var adapter: MyAdapter

    companion object {
        private lateinit var callback: CallBack

        fun getInstance(back: CallBack): CycleSelectDialog {
            this.callback = back
            return CycleSelectDialog()
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_cycle_select, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mLeftImage.setOnClickListener {
            callback.onSelect(arrayListOf())
            dismiss()
        }
        mRightImage.setOnClickListener {
            callback.onSelect(adapter.getSelectItem())
            dismiss()
        }
        list.add(getStr(R.string.add_week_monday))
        list.add(getStr(R.string.add_week_tuesday))
        list.add(getStr(R.string.add_week_wednesday))
        list.add(getStr(R.string.add_week_thursday))
        list.add(getStr(R.string.add_week_friday))
        list.add(getStr(R.string.add_week_staurday))
        list.add(getStr(R.string.add_week_sunday))

        adapter = MyAdapter(list)
        mRecyclerView.adapter = adapter
    }

    interface CallBack {
        fun onSelect(list: List<String>)
    }


    class MyAdapter(private var list: List<String>) : RecyclerView.Adapter<MyHolder>() {

        private var sparseBooleanArray: SparseBooleanArray = SparseBooleanArray()

        override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyHolder {
            return MyHolder(
                LayoutInflater.from(viewGroup.context).inflate(
                    R.layout.dialog_cycle_list_item,
                    viewGroup,
                    false
                )
            )

        }

        override fun getItemCount(): Int {
            return list.size
        }

        private fun getItem(position: Int): String {
            return list[position]
        }

        override fun onBindViewHolder(holder: MyHolder, position: Int) {
            holder.itemName.text = getItem(position)

            holder.itemCheckBox.isChecked = sparseBooleanArray.get(position)

            holder.itemCheckBox.setOnClickListener {
                sparseBooleanArray.put(position, holder.itemCheckBox.isChecked)
            }
        }

        fun getSelectItem(): List<String> {
            val selects: MutableList<String> = mutableListOf()
            for (i in 0..list.size) {
                if (sparseBooleanArray[i]) {
                    selects.add(list[i])
                }
            }
            return selects
        }
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var itemName = itemView.findViewById<TextView>(R.id.itemName)
        var itemCheckBox = itemView.findViewById<CheckBox>(R.id.itemCheckBox)
    }


    fun getStr(id: Int): String {
        return activity!!.resources.getString(id)
    }
}