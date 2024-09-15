package com.jcg.hibernate.crud.operations.service;

import com.jcg.hibernate.crud.operations.data.dao.GenericDao;
import com.jcg.hibernate.crud.operations.data.data_source.Database;
import com.jcg.hibernate.crud.operations.entity.Medico;

import java.util.ArrayList;
import java.util.List;

public class MedicoService {

    private GenericDao<Medico> dbMedico = new GenericDao<>(Medico.class);

    public void createRecord(Medico medico) {
        dbMedico.createRecord(medico, Database.getOpenSession());
    }

    public void createRecords(ArrayList<Medico> medicos) {
        dbMedico.createRecords(medicos, Database.getOpenSession());
    }

    public Medico displayRecord(String crm) {
        return dbMedico.displayRecord(crm, Database.getOpenSession());
    }

    public List<Medico> displayRecords() {
        return dbMedico.displayRecords(Database.getOpenSession());
    }

    public void updateRecords(Medico atualizado) {
        dbMedico.updateRecord(atualizado, Database.getOpenSession());
    }

    public void deleteRecord(String crm) {
        dbMedico.deleteRecord(crm, Database.getOpenSession());
    }

    public void deleteAllRecords() {
        dbMedico.deleteAllRecords(Database.getOpenSession());
    }
    public  boolean existElement(Object o, String field){
        return dbMedico.existElement(o,field,Database.getOpenSession());
    }
}
