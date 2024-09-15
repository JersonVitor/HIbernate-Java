package com.jcg.hibernate.crud.operations.controller;

import com.jcg.hibernate.crud.operations.util.Util;
import com.jcg.hibernate.crud.operations.entity.Paciente;
import com.jcg.hibernate.crud.operations.service.PacienteService;

import java.util.ArrayList;

public class PacienteController {
    PacienteService pacienteService = new PacienteService();


    public void createPaciente() {
        boolean exist;
        Paciente paciente = new Paciente();
        paciente.setCpf(Util.ReadString("\nDigite a CPF do Paciente: "));
        paciente.setNome(Util.ReadString("\nDigite o nome do Paciente: "));
        paciente.setEndereco(Util.ReadString("\nDigite o endereço: "));
        paciente.setTelefone(Util.ReadString("\nDigite o numero de telefone: "));
        paciente.setData_nasc(Util.ReadDate("\nDigite sua data de nascimento (dd/MM/yyyy): "));
        do {
            paciente.setQuarto(Util.ReadInt("\nDigite o numero de seu quarto: ",0, 100));
            exist = pacienteService.existElement(paciente.getQuarto(),"quarto");
            if (exist) System.out.println("\nQuarto já existente !!");
        }while (exist);
        pacienteService.createRecord(paciente);
    }
    public void createPaciente(Paciente paciente) {
        pacienteService.createRecord(paciente);
    }
    public void createPacientes(ArrayList<Paciente> pacientes) {
        pacienteService.createRecords(pacientes);
    }
    public void createPacientes() {
        ArrayList<Paciente> pacientes = new ArrayList<>();
        do {
            pacientes.add(Paciente.create());
        } while (Util.ReadBoolean("\nDeseja adicionar um novo paciente? : "));
        pacienteService.createRecords(pacientes);
    }
    public Paciente readPaciente(){
        Paciente paciente=  pacienteService.displayRecord(Util.ReadString("\n Digite o CPF do Paciente que deseja pesquisar: "));
        if (paciente != null){
            return paciente;
        } else {
            System.out.println("\n----- Paciente não encontrado!!------");
            return null;
        }
    }
    public Paciente readPaciente(String cpf){
        return pacienteService.displayRecord(cpf);
    }
    public void updatePaciente(){
        boolean exist;
        String idBusca = Util.ReadString("\n Digite o CPF do Paciente que deseja alterar: ");
        Paciente pacienteBuscado = pacienteService.displayRecord(idBusca);
        if (pacienteBuscado != null){
            System.out.println(pacienteBuscado);
            if (Util.ReadBoolean("\n Esse é o paciente que deseja alterar?\n1-sim\n0-não: ")){
                        pacienteBuscado.setNome(Util.ReadString("\nDigite o nome do Paciente: "));
                        pacienteBuscado.setEndereco(Util.ReadString("\nDigite o endereço: "));
                        pacienteBuscado.setTelefone(Util.ReadString("\nDigite o numero de telefone: "));
                        pacienteBuscado.setData_nasc(Util.ReadDate("\nDigite sua data de nascimento (dd/MM/yyyy): "));
                do {
                    pacienteBuscado.setQuarto(Util.ReadInt("\nDigite o numero de seu quarto: ",0, 100));
                    exist = pacienteService.existElement(pacienteBuscado.getQuarto(),"quarto");
                    if (exist) System.out.println("\nQuarto já existente !!");
                }while (exist);
                pacienteService.updateRecords(pacienteBuscado);
            }else{
                System.out.println("\n----- Operação cancelada!!------");
            }
        }else {
            System.out.println("\n----- Paciente não encontrado!!------");
        }
    }
    public void updatePaciente(Paciente pacienteAtualizado){
        pacienteService.updateRecords(pacienteAtualizado);
    }
    public void deletePaciente(String cpf){
        pacienteService.deleteRecord(cpf);
    }
    public void deletePaciente(){
        String idBusca = Util.ReadString("\n Digite o CPF do paciente que deseja apagar: ");
        Paciente pacienteBuscado = pacienteService.displayRecord(idBusca);
        if (pacienteBuscado != null){
            System.out.println(pacienteBuscado);
            if (Util.ReadBoolean("\n Esse é o paciente que deseja apagar?\n1-sim\n0-não: ")){
                pacienteService.deleteRecord(pacienteBuscado.getCpf());
            }else{
                System.out.println("----- Operação cancelada!!-----");
            }
        }else {
            System.out.println("------ Paciente não encontrado!!-----");
        }
    }
    public void deleteAllPacientes(){
        pacienteService.deleteAllRecords();
    }
}
