
package com.mycompany.pruebanuevajpa.persistencia;

import com.mycompany.pruebanuevajpa.logica.Alumno;
import com.mycompany.pruebanuevajpa.logica.Carrera;
import com.mycompany.pruebanuevajpa.logica.Materia;
import com.mycompany.pruebanuevajpa.persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    AlumnoJpaController aluJPA = new AlumnoJpaController();
    CarreraJpaController carreJPA = new CarreraJpaController();
    MateriaJpaController mateJPA = new MateriaJpaController();

    //Alumno
    public void crearAlumno(Alumno alu) {
        aluJPA.create(alu);
    }

    public void eliminarAlumno(int id) {
        try {
            aluJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarAlumno(Alumno alu) {
        try {
            aluJPA.edit(alu);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Alumno traerAlumno(int id) {
        return aluJPA.findAlumno(id);
    }

    public ArrayList<Alumno> traerListaAlumnos() {
        List<Alumno> listita = aluJPA.findAlumnoEntities();
        ArrayList<Alumno> listaAlumnos = new ArrayList<Alumno>(listita);
    
        return listaAlumnos;
    }
    
    //Carrera
    public void crearCarrera(Carrera carre) {
        carreJPA.create(carre);
    }

    public void eliminarCarrera(int id) {
        try {
            carreJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCarrera(Carrera carre) {
        try {
            carreJPA.edit(carre);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Carrera traerCarrera(int id) {
        return carreJPA.findCarrera(id);
    }

    public ArrayList<Carrera> traerListaCarreras() {
        
        List<Carrera> listitaCarre = carreJPA.findCarreraEntities();
        ArrayList <Carrera> listasCarreras = new ArrayList<Carrera>(listitaCarre);
        
        return listasCarreras;
    }
    
    
    //Materia

    public void crearMateria(Materia mate) {
        mateJPA.create(mate);
    }

    public void eliminarMateria(int id) {
        try {
            mateJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarMateria(Materia mate) {
        try {
            mateJPA.edit(mate);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Materia traerMateria(int id) {
        return mateJPA.findMateria(id);
    }

    public ArrayList<Materia> traerListaMaterias() {
        List<Materia> listitaMate = mateJPA.findMateriaEntities();
        ArrayList <Materia> listaMaterias = new ArrayList<Materia>(listitaMate);
        
        return listaMaterias;
    }
    
}
