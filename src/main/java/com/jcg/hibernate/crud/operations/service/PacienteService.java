package com.jcg.hibernate.crud.operations.service;

import com.jcg.hibernate.crud.operations.data.dao.GenericDao;
import com.jcg.hibernate.crud.operations.data.data_source.Database;
import com.jcg.hibernate.crud.operations.entity.Paciente;

import java.util.ArrayList;
import java.util.List;

public class PacienteService {

    private GenericDao<Paciente> dbPaciente = new GenericDao<>(Paciente.class);

    public void createRecord(Paciente Paciente){
        dbPaciente.createRecord(Paciente, Database.getOpenSession());
    }

    public void createRecords(ArrayList<Paciente> Pacientes){
        dbPaciente.createRecords(Pacientes,Database.getOpenSession());
    }

    public Paciente displayRecord(String crm){
        return dbPaciente.displayRecord(crm,Database.getOpenSession());
    }
    public List<Paciente> displayRecords(){
        return dbPaciente.displayRecords(Database.getOpenSession());
    }

    public void updateRecords(Paciente atualizado){
        dbPaciente.updateRecord(atualizado,Database.getOpenSession());
    }

    public void deleteRecord(String crm){
        dbPaciente.deleteRecord(crm,Database.getOpenSession());
    }

    public void deleteAllRecords(){
        dbPaciente.deleteAllRecords(Database.getOpenSession());
    }
    public boolean existElement(Object o, String field){
        return dbPaciente.existElement(o,field,Database.getOpenSession());
    }

}
