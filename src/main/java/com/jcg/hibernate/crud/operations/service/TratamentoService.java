package com.jcg.hibernate.crud.operations.service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jcg.hibernate.crud.operations.data.dao.GenericDao;
import com.jcg.hibernate.crud.operations.data.data_source.Database;
import com.jcg.hibernate.crud.operations.entity.Medico;
import com.jcg.hibernate.crud.operations.entity.Paciente;
import com.jcg.hibernate.crud.operations.entity.Tratamento;
import com.jcg.hibernate.crud.operations.entity.TratamentoID;


public class TratamentoService {

 GenericDao<Tratamento> dbTratamento = new GenericDao<Tratamento>(Tratamento.class);

 GenericDao<Medico> dbMedico = new GenericDao<Medico>(Medico.class);

 GenericDao<Paciente> dbPaciente = new GenericDao<Paciente>(Paciente.class);

 public void createRecord(Tratamento t){
     dbTratamento.updateRecord(t, Database.getOpenSession());
 }
 public Tratamento displayRecord(String cpf,String crm){
     return dbTratamento.displayRecord(new TratamentoID(crm,cpf),Database.getOpenSession());
 }
 public List<Tratamento> findPacienteInTratamento(String cpf){
     Map<String,Object> params = new HashMap<>();
     params.put("cpf_paciente", cpf);
     String hdl = "FROM Tratamento t WHERE t.ids.cpf = :cpf_paciente";
     return dbTratamento.executeListQuery(hdl, params, Database.getOpenSession());
 }
    public List<Tratamento> findMedicoInTratamento(String crm){
        Map<String,Object> params = new HashMap<>();
        params.put("crm_medico", crm);
        String hdl = "FROM Tratamento t WHERE t.ids.crm = :crm_medico";
        return dbTratamento.executeListQuery(hdl, params, Database.getOpenSession());
    }

 public  void updateRecords(Tratamento atualizado){
     dbTratamento.updateRecord(atualizado, Database.getOpenSession());
 }
    public void deleteRecord(String cpf,String crm) {
        dbTratamento.deleteRecord(new TratamentoID(crm,cpf), Database.getOpenSession());
    }

    public void deleteAllRecords() {
        dbTratamento.deleteAllRecords(Database.getOpenSession());
    }
    public boolean existElement(Object o, String field){
        return dbTratamento.existElement(o,field,Database.getOpenSession());
    }
    public Medico buscaPorResponsavel(String cpf){
        Map<String,Object> params = new HashMap<>();
        params.put("cpf_paciente", cpf);
        String hdl = "SELECT t.medico FROM Tratamento t WHERE t.ids.cpf = :cpf_paciente AND t.responsavel = true";
        return dbMedico.executeSingleQuery(hdl, params, Database.getOpenSession());
    }
}
