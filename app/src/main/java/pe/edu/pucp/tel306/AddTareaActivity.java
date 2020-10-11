package pe.edu.pucp.tel306;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import pe.edu.pucp.tel306.bean.Alumno;

public class AddTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        final Alumno alumno = (Alumno) intent.getSerializableExtra("alumno");
        switch (alumno.getCarrera()) {
            case "Telecom":
                setTheme(R.style.telecom);
                break;
            case "Electronica":
                setTheme(R.style.electronica);
                break;
            case "Mecatronica":
            default:
                setTheme(R.style.mecatronica);

        }
        setContentView(R.layout.activity_add_tarea);


        Button add = findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.task);
                String task = editText.getText().toString();
                if (task.isEmpty()) editText.setError("Tarea no puede ser vacía");
                else {
                    if (alumno.isTareaInArray(task)) editText.setError("Tarea repetida");
                    else {
                        Intent intent1 = new Intent();
                        intent1.putExtra("tarea", task);
                        alumno.addTarea(task);
                        setResult(RESULT_OK, intent1);
                        finish();
                    }
                }
            }
        });

    }
}