package com.gabrielmayorga.crud_sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielmayorga.crud_sqlite.databinding.FragmentRecyclerViewBinding

class RecyclerViewFragment : Fragment() {

    private lateinit var binding: FragmentRecyclerViewBinding
    private lateinit var DBHelper: MySQLiteHelper
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(layoutInflater)
        DBHelper = MySQLiteHelper(requireContext())
        db = DBHelper.readableDatabase

        val cursor : Cursor = db.rawQuery("SELECT * FROM amigos", null)
        val adaptador = MyRecyclerViewAdapter()
        adaptador.MyRecyclerViewAdapter(requireContext(), cursor)

        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvDatos.adapter = adaptador

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}