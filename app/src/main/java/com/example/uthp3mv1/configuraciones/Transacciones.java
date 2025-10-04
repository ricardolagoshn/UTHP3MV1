package com.example.uthp3mv1.configuraciones;

public class Transacciones
{
    public static final String DBNAME = "PM013P";

    public static final String TABLENAME = "personas";

    // Campos de la tabla
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";
    public static final String foto = "foto";

    // DDL CREATE
    public static final String CREATETABLEPERSONAS =
            "CREATE TABLE " + TABLENAME + " ( " +
                    id  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    nombres  + " TEXT NOT NULL, "+
                    apellidos  + " TEXT NOT NULL, "+
                    edad  + " INTEGER, "+
                    correo  + " TEXT UNIQUE, "+
                    foto  + " TEXT ) ";

    public static final String DROPTABLEPERSONAS = "DROP TABLE IF EXISTS " + TABLENAME;

    // DML
    public static final String SELECTALLPERSONAS = "SELECT * FROM " + TABLENAME;
}
