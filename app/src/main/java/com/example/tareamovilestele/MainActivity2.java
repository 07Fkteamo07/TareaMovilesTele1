package com.example.tareamovilestele;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        //Localizar los controles
        TextView txtBienvenida = (TextView)findViewById(R.id.txtBienvenida);
        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        //Construimos el mensaje a mostrar
        txtBienvenida.setText("Hola!!! " + bundle.getString("NOMBRE")+"\n"+
                "Vemos que te identificas como "+bundle.getString("Genero")+"\n"+
                "Tu ciudad es "+bundle.getString("CIUDAD")+"\n"+
                "Tu correo es "+bundle.getString("CORREO")+"\n"+
                "Tu cédula es "+bundle.getString("CEDULA")+"\n"+
                "Tu fecha de nacimiento es el "+bundle.getString("FECHANACIMIENTO")+"\n"+
                "Tu número telefónico es "+bundle.getString("TELEFONO")+"\n"+
                "Que tengas un buen día!!!\n");
    }
}