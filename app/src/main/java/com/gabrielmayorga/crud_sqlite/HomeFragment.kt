package com.gabrielmayorga.crud_sqlite

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.gabrielmayorga.crud_sqlite.databinding.ActivityMainBinding
import com.gabrielmayorga.crud_sqlite.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var DBHelper: MySQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        DBHelper = MySQLiteHelper(requireContext())
        start()
        return binding.root
    }

    private fun start(){
        binding.btnGuardar.setOnClickListener{
            if(binding.edNombre.text.isNotBlank() && binding.edEmail.text.isNotBlank()){
                DBHelper.addDatos(binding.edNombre.text.toString(), binding.edEmail.text.toString())
                binding.edNombre.text.clear()
                binding.edEmail.text.clear()
                Toast.makeText(requireContext(), "Guardado", Toast.LENGTH_SHORT).show()

            }else{
                Toast.makeText(requireContext(), "No se ha podido guardar", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnBorrar.setOnClickListener{
            var cantidad = 0

            if(binding.edId.text.isNotBlank()){
                cantidad = DBHelper.delete(binding.edId.text.toString().toInt())
                binding.edId.text.clear()
                Toast.makeText(requireContext(), "Datos borrados: $cantidad", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnEditar.setOnClickListener {
            if(binding.edNombre.text.isNotBlank() && binding.edEmail.text.isNotBlank() && binding.edId.text.isNotBlank()){
                DBHelper.update(binding.edId.text.toString().toInt(),binding.edNombre.text.toString(), binding.edEmail.text.toString())
                binding.edNombre.text.clear()
                binding.edEmail.text.clear()
                binding.edId.text.clear()
                Toast.makeText(requireContext(), "Modificado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(requireContext(), "Los campos no deben estar vacios", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnConsultar.setOnClickListener{
            Navigation.findNavController(binding.root).navigate(R.id.recyclerViewFragment)
        }

    }

}