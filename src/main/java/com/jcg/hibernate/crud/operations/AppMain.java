package com.jcg.hibernate.crud.operations;

import com.jcg.hibernate.crud.operations.controller.TratamentoController;
import com.jcg.hibernate.crud.operations.util.Util;
import com.jcg.hibernate.crud.operations.controller.MedicoController;
import com.jcg.hibernate.crud.operations.controller.PacienteController;
import com.jcg.hibernate.crud.operations.data.data_source.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppMain {
	private static final Logger logger = LogManager.getLogger(AppMain.class);

	private static final PacienteController pacienteController = new PacienteController();
	private static final MedicoController medicoController = new MedicoController();
	private static final TratamentoController tratamentoController = new TratamentoController();

	public static void main(String[] args) {
		int op;
		do{
			op = Util.ReadInt("\n-----Controle do Banco de Dados-----\n" +
					"Em qual tabela deseja fazer as operações:\n" +
					"0- Sair\n1- Medico\n2-Paciente\n3-Tratamento:\n\n ->\t ",0,3);
			switch (op) {
				case 0 : Database.close();
				break;
				case 1 : CRUDMedico();
				break;
				case 2 : CRUDPaciente();
				break;
				case 3 : CRUDTratamento();
				break;
			}
		}while (op != 0 );

		System.exit(0);
	}

	private static void CRUDPaciente() {
		switch (Util.ReadInt(
				"\n-----Qual operação deseja fazer?-----\n" +
					"\n0- Voltar\n1-Create\n2-Read\n3-Update\n4-Delete\n5-Limpar Tabela: ", 0, 5)){
			case 0:
				break;
			case 1: pacienteController.createPaciente();
			break;
			case 2: System.out.println(pacienteController.readPaciente());
			break;
			case 3: pacienteController.updatePaciente();
			break;
			case 4: pacienteController.deletePaciente();
			break;
			case 5: pacienteController.deleteAllPacientes();
			break;
		}
	}
	private static void CRUDMedico() {
		switch (Util.ReadInt(
				"\n-----Qual operação deseja fazer?-----\n" +
						"\n0- Voltar\n1-Create\n2-Read\n3-Update\n4-Delete\n5-Limpar Tabela: ", 0, 5)){
			case 0:
				break;
			case 1: medicoController.createMedico();
				break;
			case 2: System.out.println(medicoController.readMedico());
				break;
			case 3: medicoController.updateMedico();
				break;
			case 4: medicoController.deleteMedico();
				break;
			case 5: medicoController.deleteAllMedicos();
				break;
		}
	}

	private static void CRUDTratamento() {
		switch (Util.ReadInt(
				"\n-----Qual operação deseja fazer?-----\n" +
						"\n0- Voltar\n1-Create\n2-Read\n3-Update\n4-Delete\n5-Limpar Tabela: ", 0, 5)){
				case 0:
					break;
				case 1: tratamentoController.createTratamento();
					break;
				case 2: tratamentoController.readTratamento();
					break;
				case 3: tratamentoController.updateTratamento();
					break;
				case 4: tratamentoController.deleteTratamento();
					break;
				case 5: tratamentoController.deleteAllTratamentos();
					break;
		}
	}

}