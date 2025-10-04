package com.example.uthp3mv1;

import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ActivityresultActivity extends AppCompatActivity {

    EditText lbnombres, lbapellidos, lbedad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activityresult);

        lbnombres = (EditText) findViewById(R.id.lbnombres);
        lbapellidos = (EditText) findViewById(R.id.lbapellidos);
        lbedad = (EditText) findViewById(R.id.lbedad);

        lbnombres.setText(getIntent().getStringExtra("pnombres"));
        lbapellidos.setText(getIntent().getStringExtra("papellidos"));
        lbedad.setText(getIntent().getStringExtra("pedad"));
    }
}