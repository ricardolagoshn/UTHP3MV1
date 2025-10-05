package com.example.uthp3mv1;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.uthp3mv1.configuraciones.SQLiteConexion;
import com.example.uthp3mv1.configuraciones.Transacciones;

public class MainActivity extends AppCompatActivity {


    EditText nombres, apellidos, edad, correo;
    ImageView imageView;
    Button btnprocesar, btnfoto;

    private static final int PERMISO_CAMARA = 101;

    ActivityResultLauncher<Intent> tomarFotoLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apelllidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        imageView  = (ImageView)findViewById(R.id.imageView);

        btnprocesar = (Button) findViewById(R.id.btnprocesar);
        btnfoto  =  (Button) findViewById(R.id.btnfoto);


        btnfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Permisos();
            }
        });

        btnprocesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               AddPersona();

                /*
                Toast.makeText(MainActivity.this,nombres.getText().toString(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, ActivityresultActivity.class);

                intent.putExtra("pnombres", nombres.getText().toString());
                intent.putExtra("papellidos", apellidos.getText().toString());
                intent.putExtra("pedad", edad.getText().toString());

                startActivity(intent);

                 */
            }
        });



    }

    private void Permisos()
    {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) !=
                PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{ Manifest.permission.CAMERA}, PERMISO_CAMARA);
        }
        else
        {
            OpenCamara();
        }
    }

    private void OpenCamara()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        tomarFotoLauncher.launch(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults, int deviceId) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults, deviceId);

        if(requestCode == PERMISO_CAMARA)
        {
            if(grantResults.length > 0  && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                OpenCamara();
            }
            else
            {
                Toast.makeText(this, "Permiso de camara denegado", Toast.LENGTH_LONG).show();
            }
        }

    }

    private void AddPersona()
    {
        try {
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.DBNAME, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.nombres,  nombres.getText().toString());
            valores.put(Transacciones.apellidos,  apellidos.getText().toString());
            valores.put(Transacciones.edad, Integer.parseInt(edad.getText().toString()));
            valores.put(Transacciones.correo,  correo.getText().toString());
            valores.put(Transacciones.foto,  nombres.getText().toString());

            long resultado = db.insert(Transacciones.TABLENAME, null, valores);
            db.close();

            if(resultado > 0)
            {
                Toast.makeText(this, "Registro guardado " + resultado , Toast.LENGTH_LONG ).show();
            }
            else
            {
                Toast.makeText(this, "Error Registro no guardado ", Toast.LENGTH_LONG ).show();
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }
}