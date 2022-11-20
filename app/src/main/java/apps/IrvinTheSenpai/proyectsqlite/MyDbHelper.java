package apps.IrvinTheSenpai.proyectsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

// Clase DataBase Helper que contiene todos los métodos crud
public class MyDbHelper extends SQLiteOpenHelper {

    public MyDbHelper(@Nullable Context context) {
        super(context, Base.DB_NAME, null, Base.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //Crea la tabla de la base de datos
        db.execSQL(Base.CREATE_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

         // actualizar la base de datos (si hay alguna estructura, cambie la versión de db)

        //descartar la tabla anterior si existe
        db.execSQL("DROP TABLE IF EXISTS "+ Base.TABLE_NAME);
        //crear tabla de nuevo
        onCreate(db);
    }

    //Inserta datos a la base de datos
    public long insertRecord(String name, String image){

        //get databse grabable porque queremos escribir datos

        SQLiteDatabase db =this.getWritableDatabase();

        ContentValues values = new ContentValues();


        // la identificación se insertará automáticamente cuando configuremos AUTOINCREMENTO en la consulta

        // inserta datos
        values.put(Base.C_NAME, name);
        values.put(Base.C_IMAGE, image);


        // insertar fila, devolverá la identificación del registro guardado
        long id = db.insert(Base.TABLE_NAME, null, values);


        // cerrar db Connection
        db.close();


       // devuelve la identificación del registro insertado
        return id;

    }

    //Obtener todos datos
    public ArrayList<Photograh> getAllRecords(String orderBy){
        // la orden de consulta permitirá ordenar los datos más nuevo / más antiguo primero, nombre ascendente / descendente
        // devolverá la lista o registros ya que hemos utilizado return tipo ArrayList <ModelRecord>

        ArrayList<Photograh> recordsList = new ArrayList<>();
        // consulta para seleccionar registros
        String selectQuery = " SELECT * FROM " + Base.TABLE_NAME + " ORDER BY " + orderBy;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // recorrer todos los registros y agregarlos a la lista
        if ( cursor.moveToFirst()){
            do {
                Photograh modelRecord = new Photograh(
                        ""+cursor.getInt(cursor.getColumnIndex(Base.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Base.C_NAME)),
                       ""+cursor.getString(cursor.getColumnIndex(Base.C_IMAGE)));

                // Añadir registro a la list
                recordsList.add(modelRecord);
            }while (cursor.moveToNext());
        }

        //cierre de conexión db

        db.close();

        //retorna la lista
        return recordsList;
    }

    //Buscar todos datos
    public ArrayList<Photograh> searchRecords(String query){
        // la orden de consulta permitirá ordenar los datos más nuevo / más antiguo primero, nombre ascendente / descendente
        // devolverá la lista o registros ya que hemos utilizado return tipo ArrayList <ModelRecord>

        ArrayList<Photograh> recordsList = new ArrayList<>();
        // consulta para seleccionar registros
        String selectQuery = " SELECT * FROM " + Base.TABLE_NAME + " WHERE " + Base.C_NAME + " LIKE '%" + query +"%'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // recorrer todos los registros y agregarlos a la lista
        if ( cursor.moveToFirst()){
            do {
                Photograh modelRecord = new Photograh(
                        ""+cursor.getInt(cursor.getColumnIndex(Base.C_ID)),
                        ""+cursor.getString(cursor.getColumnIndex(Base.C_NAME)),
                        ""+cursor.getString(cursor.getColumnIndex(Base.C_IMAGE)));

                // Añadir registro a la list
                recordsList.add(modelRecord);
            }while (cursor.moveToNext());
        }

        //cierre de conexión db

        db.close();

        //retorna la lista
        return recordsList;
    }

    //Obtener el numero de registros
    public int getRecordsCount(){
        String countQuery = " SELECT * FROM " + Base.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        return count;
    }


}
