package com.jcg.hibernate.crud.operations.controller;

import com.jcg.hibernate.crud.operations.util.Util;
import com.jcg.hibernate.crud.operations.entity.Medico;
import com.jcg.hibernate.crud.operations.service.MedicoService;

import java.util.ArrayList;

public class MedicoController {
    MedicoService medicoService = new MedicoService();


    public void createMedico() {
        medicoService.createRecord(Medico.create());
    }
    public void createMedico(Medico medico) {
        medicoService.createRecord(medico);
    }
    public void createMedicos(ArrayList<Medico> medicos) {
        medicoService.createRecords(medicos);
    }
    public void createMedicos() {
        ArrayList<Medico> medicos = new ArrayList<>();
        do {
            medicos.add(Medico.create());
        } while (Util.ReadBoolean("\nDeseja adicionar um novo médico? : "));
        medicoService.createRecords(medicos);
    }
    
    public Medico readMedico(){
        Medico medico=  medicoService.displayRecord(Util.ReadString("\n Digite o CRM do medico que deseja pesquisar: "));
        if (medico != null){
            return medico;
        } else {
            System.out.println("\n----- Médico não encontrado!!------");
            return null;
        }
    }
    public Medico readMedico(String crm){
        return medicoService.displayRecord(crm);
    }
    public void updateMedico(){
        String idBusca = Util.ReadString("\n Digite o CRM do medico que deseja alterar: ");
        Medico medicoBuscado = medicoService.displayRecord(idBusca);
        if (medicoBuscado != null){
            System.out.println(medicoBuscado);
            if (Util.ReadBoolean("\n Esse é o médico que deseja alterar?\n1-sim\n0-não: ")){
                medicoBuscado.setNome( Util.ReadString("\nDigite o nome do Médico: "));
                medicoBuscado.setEspecialidade(Util.ReadString("\nDigite a especialidade: "));
                medicoBuscado.setSalario(Util.ReadDouble("\nDigite o Salário: ", 0, 30000));
                medicoService.updateRecords(medicoBuscado);
            }else{
                System.out.println("\n----- Operação cancelada!!------");
            }
        }else {
            System.out.println("\n----- Médico não encontrado!!------");
        }
    }
    public void updateMedico(Medico medicoAtualizado){
        medicoService.updateRecords(medicoAtualizado);
    }
    public void deleteMedico(String crm){
        medicoService.deleteRecord(crm);
    }
    public void deleteMedico(){
        String idBusca = Util.ReadString("\n Digite o CRM do medico que deseja apagar: ");
        Medico medicoBuscado = medicoService.displayRecord(idBusca);
        if (medicoBuscado != null){
            System.out.println(medicoBuscado);
            if (Util.ReadBoolean("\n Esse é o médico que deseja apagar?\n1-sim\n0-não: ")){
                medicoService.deleteRecord(medicoBuscado.getCrm());
            }else{
                System.out.println("----- Operação cancelada!!-----");
            }
        }else {
            System.out.println("------ Médico não encontrado!!-----");
        }
    }
    public void deleteAllMedicos(){
        medicoService.deleteAllRecords();
    }
}
