package com.jcg.hibernate.crud.operations.controller;

import com.jcg.hibernate.crud.operations.entity.Medico;
import com.jcg.hibernate.crud.operations.entity.Paciente;
import com.jcg.hibernate.crud.operations.entity.Tratamento;
import com.jcg.hibernate.crud.operations.entity.TratamentoID;
import com.jcg.hibernate.crud.operations.service.MedicoService;
import com.jcg.hibernate.crud.operations.service.PacienteService;
import com.jcg.hibernate.crud.operations.service.TratamentoService;
import com.jcg.hibernate.crud.operations.util.Util;

import java.util.List;

public class TratamentoController {
    private final TratamentoService tratamentoService = new TratamentoService();
    private final PacienteService pacienteService = new PacienteService();
    private final MedicoService medicoService = new MedicoService();

    public void createTratamento(){
        boolean exist;
        String str;
        String crm;
        Paciente p;
        Medico m;
        System.out.println("----- Pacientes : -----");
        List<Paciente> pacientes = pacienteService.displayRecords();
        for (Paciente paciente: pacientes) System.out.println(paciente);
        do {
            str = Util.ReadString("\nDigite o CPF do paciente que deseja colocar em tratamento: ");
            String finalStr = str;
            p = pacientes.stream()
                    .filter(paciente -> paciente.getCpf().equals(finalStr))
                    .findFirst().orElse(null);
            if (p != null) {
                exist = tratamentoService.existElement(str, "ids.cpf");
            } else {
                exist = true; // Paciente inexistente
            }
            if (exist) System.out.println("\nPaciente já está em tratamento ou inexistente !!");
        }while (exist);

        System.out.println("----- Medicos : -----");
        List<Medico> medicos = medicoService.displayRecords();
        for (Medico medico : medicos) System.out.println(medico);

        do {
            crm = Util.ReadString("\nDigite o CRM do medico que deseja analisar o tratamento: ");
            String finalStr = crm;
            m = medicos.stream()
                    .filter(medico -> medico.getCrm().equals(finalStr))
                    .findFirst().orElse(null);
            if (m == null) System.out.println("\nMedico inexistente !!");
        }while (m == null);
        Tratamento t = new Tratamento();

        t.setMedico(m);
        t.setPaciente(p);
        t.setResponsavel(Util.ReadBoolean("\n Esse médico será responsável pelo paciente?: "));
        t.setIds(new TratamentoID(m.getCrm(),p.getCpf()));

        tratamentoService.createRecord(t);
    }

    public void readTratamento() {
        int op =Util.ReadInt("\nEm quem deseja fazer a pesquisa?\n1-Medico\n2-Paciente: ",1,2);
        if (op == 1){
            List<Tratamento> tratamento=  tratamentoService.findMedicoInTratamento(Util.ReadString("\n Digite o CRM do medico que deseja pesquisar: "));
            if (tratamento != null){
                System.out.println("\n----- Tratamentos: ------");
                for (Tratamento t:tratamento) System.out.println(t);
            } else {
                System.out.println("\n----- Tratanento não encontrado!!------");
            }
        }else {
           List<Tratamento> tratamento=  tratamentoService.findPacienteInTratamento(Util.ReadString("\n Digite o CPF do paciente que deseja pesquisar: "));
            if (tratamento != null){
                System.out.println("\n----- Tratamentos: ------");
                for (Tratamento t: tratamento) System.out.println(t);
            } else {
                System.out.println("\n----- tratanento não encontrado!!------");
            }
        }
    }

    public void updateTratamento(){
        boolean exist;
        String cpf = Util.ReadString("\n Digite o CPF do paciente pertecente ao tratamento a ser alterado: ");
        String crm = Util.ReadString("\n Digite o CRM do medico responsavel: ");        
        Tratamento tratamentoBuscado = tratamentoService.displayRecord(cpf, crm);

        if (tratamentoBuscado != null){
           System.out.println(tratamentoBuscado);
            if (Util.ReadBoolean("\n Esse é o tratamento que deseja alterar?\n1-sim\n0-não: ")){
               switch (Util.ReadInt("\n-----Campo a ser alterado-----\n" +
                               "1-Paciente\n2-Responsável\n3-Incluir Médico: ", 1, 3)){
                                case 1:{
                                    do {
                                        cpf = Util.ReadString("\nDigite o cpf do novo paciente: ");
                                        exist = tratamentoService.existElement(crm, "ids.cpf") || pacienteService.existElement(crm,"cpf");
                                        if (!exist) System.out.println("\nPaciente não existe!!");
                                    }while (!exist);
                                    tratamentoService.deleteRecord(cpf,crm);
                                } break;
                                case 2:{
                                    Medico m = tratamentoService.buscaPorResponsavel(cpf);
                                    if(m != null){
                                        System.out.println("Esse é o medico responsavel: ");
                                        System.out.println(m);
                                        if(Util.ReadBoolean("\nDeseja alterar?\n1-sim\n0-não: ")){
                                            do {
                                                crm = Util.ReadString("Digite o crm do novo médico responsavel: ");
                                                exist = tratamentoService.existElement(crm, "ids.crm") || medicoService.existElement(crm,"crm");
                                                if (!exist) System.out.println("Médico já existente no tratamento !!");
                                            }while (!exist);
                                            tratamentoService.deleteRecord(cpf,m.getCrm());
                                        }else{
                                            System.out.println("\n----- Operação cancelada!!------");
                                        }
                                   }else {
                                        System.out.println("\n----- Médico não encontrado!!------");
                                    }
                                } break;
                                case 3:{
                                    do {
                                        crm = Util.ReadString("Digite o crm do novo médico: ");
                                        exist = tratamentoService.existElement(crm, "ids.crm");
                                        if (exist) System.out.println("Médico já existente no tratamento !!");
                                    }while (exist);
                                    tratamentoBuscado.setResponsavel(false);
                                }break;

               }

                tratamentoBuscado.setPaciente(pacienteService.displayRecord(cpf));
                tratamentoBuscado.setMedico(medicoService.displayRecord(crm));
                tratamentoBuscado.setIds(new TratamentoID(cpf,crm));
                tratamentoService.updateRecords(tratamentoBuscado);
               
           }else{
               System.out.println("\n----- Operação cancelada!!------");
          }
       }else {
           System.out.println("\n----- Tratamento não encontrado!!------");
      }
    }


    public void deleteTratamento() {
        String cpf = Util.ReadString("\n Digite o CPF do paciente que deseja remover o tratamento: ");
        String crm = Util.ReadString("\n Digite o CRM do medico responsavel: ");
        Tratamento tratamentoBuscado = tratamentoService.displayRecord(cpf, crm);

        if (tratamentoBuscado != null){
            System.out.println(tratamentoBuscado);
            if (Util.ReadBoolean("\n Esse é o tratamento que deseja apagar?\n1-sim\n0-não: ")){
                tratamentoService.deleteRecord(tratamentoBuscado.getPaciente().getCpf(),
                                                tratamentoBuscado.getMedico().getCrm());
            }else{
                System.out.println("----- Operação cancelada!!-----");
            }
        }else {
            System.out.println("------ Tratamento não encontrado!!-----");
        }
    }
    public void deleteAllTratamentos(){
        tratamentoService.deleteAllRecords();
    }
}
