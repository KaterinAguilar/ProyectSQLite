package apps.IrvinTheSenpai.proyectsqlite;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetalleRegistroActivity extends AppCompatActivity {

    //vistas
    private CircleImageView profileIv;
    private TextView  nameTv;

    //ActionBar
    private ActionBar actionBar;

    //
    private String recordID;

    //BDHelper
    private MyDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_registro);

        //setting up actionbar with title and back button
        actionBar = getSupportActionBar();
        actionBar.setTitle("Detalle del Registro");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        //obtener la identificación de registro del adaptador mediante la intención
        Intent intent = getIntent();
        recordID = intent.getStringExtra("RECORD_ID");

        //Inicializacion BD Helper Clase
        dbHelper = new MyDbHelper(this);

        //Inicializamos las vistas
        profileIv = findViewById(R.id.profileIv);

        nameTv = findViewById(R.id.nameTv);


        showRecordDetails();
    }

    private void showRecordDetails() {
        //obtener detalles de registro
        //consulta para seleccionar el registro basado en la identificación del registro
        String selectQuery = " SELECT * FROM " + Base.TABLE_NAME + " WHERE " + Base.C_ID +" =\""+ recordID+"\"";

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // mantener comprobando toda la base de datos para ese registro
        if (cursor.moveToFirst()){
            do {

                //Obtenner datos
                String id = ""+ cursor.getInt(cursor.getColumnIndex(Base.C_ID));
                String name = ""+ cursor.getString(cursor.getColumnIndex(Base.C_NAME));
                String image = ""+ cursor.getString(cursor.getColumnIndex(Base.C_IMAGE));




                //Establecer datos
                nameTv.setText(name);


                // si el usuario no adjunta la imagen, imageUri será nulo, por lo tanto,
                // configure una imagen predeterminada en ese caso
                if (image.equals("null")){
                    // no hay imagen en el registro, establecer predeterminado
                    profileIv.setImageResource(R.drawable.ic_person_black);
                }
                else {
                    // tener imagen en el registro
                    profileIv.setImageURI(Uri.parse(image));
                }


            }while(cursor.moveToNext());
        }
        db.close();
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();//ir a la actividad anterior
        return super.onSupportNavigateUp();
    }
}
