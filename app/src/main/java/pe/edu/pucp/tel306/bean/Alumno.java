package pe.edu.pucp.tel306.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class Alumno implements Serializable {
    private String name;
    private String apellido;
    private int dni;
    private int codigo;
    private String carrera;
    private ArrayList<String> tareas;

    public String getCarrera() {
        return carrera;
    }

    public Alumno(String name, String apellido, int dni, int codigo, String carrera) {
        this.name = name;
        this.apellido = apellido;
        this.dni = dni;
        this.codigo = codigo;
        this.carrera = carrera;
        this.tareas = new ArrayList<>();
    }

    public boolean isTareasEmpty(){ return this.tareas.isEmpty();}

    public void addTarea(String tarea) {
        this.tareas.add(tarea);
    }

    public void deleteTarea(String tarea) {
        for (String tmp : this.tareas) {
            if (tmp.equals(tarea)) {
                this.tareas.remove(tmp);
                break;
            }
        }
    }

    public boolean isTareaInArray(String tarea) {
        for (String tmp : this.tareas) {
            if (tmp.equalsIgnoreCase(tarea)) {
                return true;
            }
        }
        return false;
    }
}
