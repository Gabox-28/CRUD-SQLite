package com.gabrielmayorga.crud_sqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MySQLiteHelper(context : Context) : SQLiteOpenHelper(context, "amigos.db", null, 1) {

    companion object{
        val NOMBRE_TABLA = "amigos"
        val CAMPO_ID = "_id"
        val CAMPO_NOMBRE = "nombre"
        val CAMPO_EMAIL = "email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val ordenCreacion = "CREATE TABLE amigos (${CAMPO_ID} INTEGER PRIMARY KEY AUTOINCREMENT, nombre TEXT, email TEXT)"
        db!!.execSQL(ordenCreacion)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val ordenBorrado = "DROP TABLE IF EXISTS amigos"
        db!!.execSQL(ordenBorrado)
        onCreate(db)
    }

    fun addDatos(nombre:String, email:String){
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase
        db.insert("amigos", null, datos)
        db.close()
    }

    fun delete(id: Int): Int{
        val args = arrayOf(id.toString())

        val db = this.writableDatabase
        val deleted = db.delete("amigos", "_id = ?", args)
        db.close()

        return deleted
    }

    fun update(id: Int, nombre:String, email:String){
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("email", email)

        val db = this.writableDatabase
        db.update("amigos", datos,"_id = ?", args)
        db.close()
    }

}