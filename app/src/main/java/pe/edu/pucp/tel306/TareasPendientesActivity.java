package pe.edu.pucp.tel306;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import pe.edu.pucp.tel306.bean.Alumno;

public class TareasPendientesActivity extends AppCompatActivity {

    private int contador = 0;
    private boolean darkMode = false;
    private Alumno alumno;
    private final int REQ_ADD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        this.alumno = (Alumno) intent.getSerializableExtra("alumno");
        int idImage;
        switch (alumno.getCarrera()) {
            case "Telecom":
                setTheme(R.style.telecom);
                idImage = R.drawable.telecom;
                break;
            case "Electronica":
                setTheme(R.style.electronica);
                idImage = R.drawable.electronica;
                break;
            case "Mecatronica":
            default:
                setTheme(R.style.mecatronica);
                idImage = R.drawable.mecatronica;
        }
        setContentView(R.layout.activity_tareas_pendientes);
        ImageView imageView = findViewById(R.id.imageView);

        TextView textView = findViewById(R.id.noTask);
        imageView.setImageResource(idImage);

        if (alumno.isTareasEmpty()) textView.setVisibility(View.VISIBLE);
        else textView.setVisibility(View.GONE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_ADD && resultCode == RESULT_OK) {
            final String task = data.getStringExtra("tarea");
            alumno.addTarea(task);
            LinearLayout ll = findViewById(R.id.tareasLayout);
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setPadding(0,10,0,10);
            checkBox.setText(task);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    LinearLayout ll = findViewById(R.id.tareasLayout);
                    ll.removeView(checkBox);
                    String tempTodo = compoundButton.getText().toString();
                    alumno.deleteTarea(task);
                    Toast.makeText(TareasPendientesActivity.this, "Tarea " + tempTodo + " finalizada", Toast.LENGTH_SHORT).show();
                }
            });
            ll.addView(checkBox);
        }
    }

    public void addTarea(View view) {
        Intent tareasAcvitity  = new Intent(this, AddTareaActivity.class);
        tareasAcvitity.putExtra("alumno", alumno);
        startActivityForResult(tareasAcvitity, REQ_ADD);
    }

    public void backToRegister(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void countClick(View view) {
        contador ++;
        if (contador == 5 ){
            if (!darkMode){
                Toast.makeText(this, "Has desbloqueado la opción oscura", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                Toast.makeText(this, "Has desactivado la opción oscura", Toast.LENGTH_SHORT).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
            darkMode = !darkMode;
            contador = 0;
        }
    }
}