package com.gabrielmayorga.crud_sqlite

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gabrielmayorga.crud_sqlite.databinding.ItemRecyclerviewBinding


class MyRecyclerViewAdapter : RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>() {

    lateinit var context : Context
    lateinit var cursor : Cursor


    fun MyRecyclerViewAdapter(context : Context, cursor : Cursor){
        this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_recyclerview, parent, false))
    }

    override fun getItemCount(): Int {
        if(cursor == null){
            return 0
        }else{
            return cursor.count
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)

        holder.tvNombre.text = cursor.getString(1)
        holder.tvEmail.text = cursor.getString(2)


    }

    inner class ViewHolder: RecyclerView.ViewHolder{

        val tvNombre: TextView
        val tvEmail : TextView

        constructor(view: View) : super(view){
            val bindingItemsRV = ItemRecyclerviewBinding.bind(view)
            val bundle = Bundle()

            tvNombre = bindingItemsRV.tvNombre
            tvEmail = bindingItemsRV.tvEmail

            bundle.putString("nombre", tvNombre.toString())
            bundle.putString("mail", tvEmail.toString())


            view.setOnClickListener{
                /*Toast.makeText(context, "${tvNombre}, ${tvEmail}", Toast.LENGTH_SHORT).show()*/
                /*var etNombre = itemView.findViewById<TextView>(R.id.edNombre)
                var etEmail= itemView.findViewById<TextView>(R.id.edEmail)*/
            }
        }
    }
}