package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.EditText;
import android.widget.Spinner;

import pe.edu.pucp.tel306.bean.Alumno;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /***
     * register is a method for validate the information of Tel306's students
     */
    public void register(View view) {
        EditText nombreEditText = findViewById(R.id.nombreEditText);
        EditText apellidoEditText = findViewById(R.id.apellidoEditText);
        EditText dniEditText = findViewById(R.id.dniEditText);
        EditText codigoEditText = findViewById(R.id.codigoEditText);
        Spinner carreraSpinner = findViewById(R.id.carreraSpinner);
        EditText claveEditText = findViewById(R.id.claveEditText);

        String nombre = nombreEditText.getText().toString();
        String apellido = apellidoEditText.getText().toString();
        String clave = claveEditText.getText().toString();
        String dniStr = dniEditText.getText().toString();
        String codigoStr = codigoEditText.getText().toString();
        String carrera = "";

        Boolean nombreOK = !nombre.isEmpty();
        Boolean apellidoOK  = !apellido.isEmpty();
        Boolean carreraOK  = carreraSpinner.getSelectedItem() != null;
        Boolean claveOK = false;
        Boolean dniOK  = false;
        Boolean codigoOK  = false;

        int dni = 0;
        int codigo = 0;

        if (!nombreOK) nombreEditText.setError("Nombre vacio");

        if (!apellidoOK) apellidoEditText.setError("Apellido vacio");

        if (!carreraOK) apellidoEditText.setError("Seleecione su carrera");
        else carrera = carreraSpinner.getSelectedItem().toString();

        if (clave.isEmpty()) claveEditText.setError("Clave vacia");
        else {
            if (!clave.equals("S3cr3t306"))  claveEditText.setError("Clave invalida");
            else claveOK = true;
        }

        try {
            if (dniStr.isEmpty()) dniEditText.setError("Dni vacio");
            else {
                dni = Integer.parseInt(dniStr);
                if (dniStr.length() != 8) dniEditText.setError("Dni no tiene el largo adecuado");
                else dniOK = true;
            }
        } catch (NumberFormatException ex){
            dniEditText.setError("Dni no tiene formato numerico");
        }

        try {
            if (codigoStr.isEmpty()) codigoEditText.setError("Codigo vacio");
            else {
                codigo = Integer.parseInt(codigoStr);
                if (codigoStr.length() != 8) codigoEditText.setError("Codigo no tiene el largo adecuado");
                else if (codigo < 20120000 || codigo >= 20180000)  codigoEditText.setError("Codigo en rango no permitido");
                else codigoOK = true;
            }
        } catch (NumberFormatException ex){
            codigoEditText.setError("Codigo no tiene formato numerico");
        }

        if (nombreOK && apellidoOK && dniOK && codigoOK && carreraOK & claveOK){
            Alumno alumno = new Alumno(nombre, apellido, dni, codigo, carrera);
            Intent tareasAcvitity  = new Intent(this, TareasPendientesActivity.class);
            tareasAcvitity.putExtra("alumno", alumno);
            startActivity(tareasAcvitity);
            this.finish();
        }

    }
}