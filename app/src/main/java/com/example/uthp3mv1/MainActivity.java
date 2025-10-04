package com.example.uthp3mv1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    EditText nombres, apellidos, edad;
    Button btnprocesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apelllidos);
        edad = (EditText) findViewById(R.id.edad);
        btnprocesar = (Button) findViewById(R.id.btnprocesar);

        btnprocesar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,nombres.getText().toString(),Toast.LENGTH_LONG).show();

                Intent intent = new Intent(MainActivity.this, ActivityresultActivity.class);

                intent.putExtra("pnombres", nombres.getText().toString());
                intent.putExtra("papellidos", apellidos.getText().toString());
                intent.putExtra("pedad", edad.getText().toString());

                startActivity(intent);
            }
        });



    }
}