package com.gabrielmayorga.crud_sqlite

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.gabrielmayorga.crud_sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var amigosDBHelper: MySQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        amigosDBHelper = MySQLiteHelper(this)

        binding.btnGuardar.setOnClickListener{
            if(binding.edNombre.text.isNotBlank() && binding.edEmail.text.isNotBlank()){
                amigosDBHelper.addDatos(binding.edNombre.text.toString(), binding.edEmail.text.toString())
                binding.edNombre.text.clear()
                binding.edEmail.text.clear()
                Toast.makeText(this, "Guardado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "No se ha podido guardar", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnConsultar.setOnClickListener{
            binding.tvResultado.text = ""
            val db : SQLiteDatabase = amigosDBHelper.readableDatabase
            val cursor = db.rawQuery("SELECT * FROM amigos", null)
            if (cursor.moveToFirst()){
                do{
                    binding.tvResultado.append(cursor.getInt(0).toString() + ": ")
                    binding.tvResultado.append(cursor.getString(1).toString() + ", ")
                    binding.tvResultado.append(cursor.getString(2).toString() + "\n")
                }while (cursor.moveToNext())
            }
        }

        binding.btnBorrar.setOnClickListener{
            var cantidad = 0

            if(binding.edId.text.isNotBlank()){
                cantidad = amigosDBHelper.delete(binding.edId.text.toString().toInt())
                binding.edId.text.clear()
                Toast.makeText(this, "Datos borrados: $cantidad", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnEditar.setOnClickListener {
            if(binding.edNombre.text.isNotBlank() && binding.edEmail.text.isNotBlank() && binding.edId.text.isNotBlank()){
                amigosDBHelper.update(binding.edId.text.toString().toInt(),binding.edNombre.text.toString(), binding.edEmail.text.toString())
                binding.edNombre.text.clear()
                binding.edEmail.text.clear()
                binding.edId.text.clear()
                Toast.makeText(this, "Modificado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Los campos no deben estar vacios", Toast.LENGTH_LONG).show()
            }
        }
    }
}