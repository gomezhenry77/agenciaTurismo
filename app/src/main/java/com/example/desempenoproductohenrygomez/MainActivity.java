package com.example.desempenoproductohenrygomez;

import static java.lang.Double.parseDouble;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] destinosViajes = {"Cartagena", "Santa Marta", "San Andres", "Medellin"};
    String presSel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText nombre = findViewById(R.id.etnombre);
        Spinner destino = findViewById(R.id.spdestino);
        EditText fechaSalida = findViewById(R.id.etfechaSalida);
        EditText numeroPersonas = findViewById(R.id.etnumeroPersonas);
        RadioButton rb2 = findViewById(R.id.rb2);
        RadioButton rb4 = findViewById(R.id.rb4);
        RadioButton rb6 = findViewById(R.id.rb6);
        Switch tour = findViewById(R.id.swtour);
        Switch discoteca = findViewById(R.id.swcdiscoteca);
        TextView total = findViewById(R.id.ettotal);
        Button calcular = findViewById(R.id.btncalcular);
        Button limpiar = findViewById(R.id.btnlimpiar);
        ArrayAdapter adpprestamo = new ArrayAdapter(this, android.R.layout.simple_list_item_checked,destinosViajes);
        destino.setAdapter(adpprestamo);
        destino.setOnItemSelectedListener(this);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre.setText("");
                fechaSalida.setText("");
                numeroPersonas.setText("");
                nombre.requestFocus(); //Se envia el foco al nombre
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!nombre.getText().toString().isEmpty()){
                    //tengo dudas con el destinosViajes.getClas
                        if (!fechaSalida.getText().toString().isEmpty()){
                            if (!numeroPersonas.getText().toString().isEmpty()){
                            if (parseDouble(numeroPersonas.getText().toString()) <= 10){
                                // Chequear el tipo de credito seleccionado
                                double mDestino = 0;
                                switch (presSel){
                                    case "Cartagena":
                                        mDestino = 200000;
                                        break;
                                    case "Santa Marta":
                                        mDestino = 180000;
                                        break;
                                    case "San Andrés":
                                        mDestino = 170000;
                                        break;
                                    case "Medellin":
                                        mDestino = 190000;
                                        break;
                                }
                                double xnumeroPersonas = parseDouble(numeroPersonas.getText().toString());
                                double numeroDias = 0;
                                if (rb2.isChecked()){
                                    numeroDias = 2;
                                }
                                if (rb4.isChecked()){
                                    numeroDias = 4;
                                }
                                if (rb6.isChecked()){
                                    numeroDias = 6;
                                }
                                double totalBasico = xnumeroPersonas * numeroDias;
                                DecimalFormat valueFormat = new DecimalFormat("###,###,###,###.#");
                                Deuda.setText(valueFormat.format(totalDeuda));

                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Se permiten máximo 10 personas por cotizacion",Toast.LENGTH_SHORT).show();
                            }
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Ingrese número de personasa",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"La fecha es obligatoria",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"El nombre es obligatorio",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        );
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presSel = destinosViajes[position]; // tomar el valor de la opción seleccionado Tipo de Prestamo
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}
