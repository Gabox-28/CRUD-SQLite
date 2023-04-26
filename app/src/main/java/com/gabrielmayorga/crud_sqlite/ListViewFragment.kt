package com.gabrielmayorga.crud_sqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import com.gabrielmayorga.crud_sqlite.databinding.FragmentListViewBinding
import com.gabrielmayorga.crud_sqlite.databinding.ItemListviewBinding

class ListViewFragment : Fragment() {

    lateinit var binding : FragmentListViewBinding
    lateinit var amigosDBHelper : MySQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListViewBinding.inflate(layoutInflater)
        amigosDBHelper = MySQLiteHelper(requireContext())
        start()
        return binding.root
    }

    private fun start(){
        val db : SQLiteDatabase = amigosDBHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM amigos", null)

        val adapter = CursorAdapterListView(requireContext(), cursor)
        binding.lvDatos.adapter = adapter
        db.close()
    }

    inner class CursorAdapterListView(context: Context, cursor : Cursor) : CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER){

        override fun newView(context: Context?, cursor: Cursor?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            return inflater.inflate(R.layout.item_listview, parent, false)
        }

        override fun bindView(view: View?, context: Context?, cursor: Cursor?) {
            val bindingItems = ItemListviewBinding.bind(view!!)
            bindingItems.tvNombre.text = cursor!!.getString(1)
            bindingItems.tvEmail.text = cursor!!.getString(2)
            view.setOnClickListener{
                Toast.makeText(requireContext(), "${bindingItems.tvNombre.text}, ${bindingItems.tvNombre.text}", Toast.LENGTH_SHORT).show()
            }
        }

    }
}