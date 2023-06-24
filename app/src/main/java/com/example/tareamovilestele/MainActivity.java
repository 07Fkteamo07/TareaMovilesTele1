package com.example.tareamovilestele;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.widget.EditText;
import android.util.Patterns;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtCorreo;
    private EditText txtCedula;
    private EditText txtCiudad;
    private EditText txtTelefono;
    private EditText etFechaNacimiento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inicializar vistas
        txtNombre = findViewById(R.id.txtNombre);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtCedula = findViewById(R.id.txtCedula);
        txtCiudad = findViewById(R.id.txtCiudad);
        txtTelefono = findViewById(R.id.txtTelefono);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        etFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }
    public void btEnviar(View view) {
        // Obtener los valores ingresados
        String nombre = txtNombre.getText().toString();
        String correo = txtCorreo.getText().toString();
        String cedula = txtCedula.getText().toString();
        String ciudad = txtCiudad.getText().toString();
        String telefono = txtTelefono.getText().toString();
        String fechaNacimiento = etFechaNacimiento.getText().toString();

        // Validar los campos requeridos
        if (nombre.isEmpty() || correo.isEmpty() || cedula.isEmpty() || ciudad.isEmpty() || telefono.isEmpty() || fechaNacimiento.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        // Valida el formato del correo utilizando una expresión regular
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            // El correo no tiene un formato válido
            txtCorreo.setError("Ingrese un correo válido");
            txtCorreo.requestFocus();
            return;
        }
        if (!correo.matches(".*\\.(com|es|ec)$")) {
            // El dominio del correo no tiene una extensión válida
            txtCorreo.setError("Ingrese un correo con una extensión válida (.com, .es, .ec, etc.)");
            txtCorreo.requestFocus();
            return;
        }
        // Validar el género seleccionado
        RadioButton checkBoxMasculino = findViewById(R.id.radioButtonMasculino);
        RadioButton checkBoxFemenino = findViewById(R.id.radioButtonFemenino);
        if (checkBoxMasculino.isChecked() && checkBoxFemenino.isChecked()) {
            // Ambos CheckBox están marcados, mostrar mensaje de error
            Toast.makeText(this, "Solo puedes seleccionar un género", Toast.LENGTH_SHORT).show();
            return;
        } else if (!checkBoxMasculino.isChecked() && !checkBoxFemenino.isChecked()) {
            // Ningún CheckBox está marcado, mostrar mensaje de error
            Toast.makeText(this, "Debes seleccionar un género", Toast.LENGTH_SHORT).show();
            return;
        }
        // Creamos la información a pasar entre actividades - Pares Key-Value
        Bundle b = new Bundle();
        b.putString("NOMBRE", nombre );
        b.putString("CIUDAD", ciudad );
        b.putString("Genero", checkBoxMasculino.isChecked() ? "Hombre" : "Mujer");
        b.putString("CORREO", correo );
        b.putString("CEDULA", cedula );
        b.putString("FECHANACIMIENTO", fechaNacimiento );
        b.putString("TELEFONO", telefono );
        // Creamos el Intent
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        // Añadimos la información al intent
        intent.putExtras(b);
        // Iniciamos la nueva actividad
        startActivity(intent);
    }
    private void showDatePicker() {
        // Obtener la fecha actual
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Crear el DatePickerDialog y establecer la fecha actual como fecha seleccionada
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                (view, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    // Actualizar el EditText con la fecha seleccionada
                    String selectedDate = selectedDayOfMonth + "/" + (selectedMonth + 1) + "/" + selectedYear;
                    etFechaNacimiento.setText(selectedDate);
                }, year, month, dayOfMonth);

        // Mostrar el diálogo del selector de fecha
        datePickerDialog.show();
    }
    public void btLimpiar(View view) {
        // Limpiar todos los campos
        txtNombre.setText("");
        txtCorreo.setText("");
        txtCedula.setText("");
        txtCiudad.setText("");
        txtTelefono.setText("");
        etFechaNacimiento.setText("");
    }
}
