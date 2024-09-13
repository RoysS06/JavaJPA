package com.mycompany.pruebanuevajpa;

import com.mycompany.pruebanuevajpa.logica.Alumno;
import com.mycompany.pruebanuevajpa.logica.Carrera;
import com.mycompany.pruebanuevajpa.logica.ControladoraLogica;
import com.mycompany.pruebanuevajpa.logica.Materia;
import java.util.Date;
import java.util.LinkedList;

public class PruebaNuevaJpa {

    public static void main(String[] args) {
        
        ControladoraLogica controlLog = new ControladoraLogica();
        
        //Creamos lista de materias
        LinkedList<Materia> listaMaterias = new LinkedList<Materia>();
        
        // Creamos la carrera con lista de materias 
        Carrera carre = new Carrera(1, "Programación", listaMaterias);
        
        // Guardamos la carrera en la BD
        controlLog.crearCarrera(carre);
        
        
        //Creación de Materias
        Materia mate1 = new Materia(58, "Programación I", "Semestral", carre);
        Materia mate2 = new Materia(59, "Programación II", "Semestral", carre);
        Materia mate3 = new Materia(60, "Programación III", "Semestral", carre);
        
        //Guardado de Materias en BD
        controlLog.crearMateria(mate1);
        controlLog.crearMateria(mate2);
        controlLog.crearMateria(mate3);
        
        //Agregamos lista de materias
        listaMaterias.add(mate1);
        listaMaterias.add(mate2);
        listaMaterias.add(mate3);
        
        carre.setListaMaterias(listaMaterias);
        controlLog.editarCarrera(carre);
       
        
        // Creamos el Alumno
        Alumno alu = new Alumno(1, "Roy", "Rodriguez", new Date(), carre);
        
        // Guardamos el alumno en la BD
        controlLog.crearAlumno(alu);
        
        // Vemos el resultado
        System.out.println("----------------------");
        System.out.println("--------Datos del Alumno----------");
        Alumno al = controlLog.traerAlumno(1);
        System.out.println("El Alumno es: " + al.getNombre() + " " + al.getApellido());
        System.out.println("y lleva la carrera de: " + al.getCarre().getNombre());
        
    }
}
