package com.jcg.hibernate.crud.operations.entity;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name="Tratamento785540")
public class Tratamento implements Serializable{

    @EmbeddedId
    private TratamentoID ids;


    @ManyToOne
    @MapsId("cpf")
    @JoinColumn(name = "cpf_paciente", insertable = false, updatable = false)
    private Paciente paciente;  

    @ManyToOne
    @MapsId("crm")
    @JoinColumn(name= "crm_medico",
    insertable = false,
    updatable = false)
    private Medico medico;
    
    @Column(name = "responsavel",nullable = false)
    private boolean responsavel;

    public TratamentoID getIds() {
        return ids;
    }

    public void setIds(TratamentoID ids) {
        this.ids = ids;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public boolean isResponsavel() {
        return responsavel;
    }

    public void setResponsavel(boolean responsavel) {
        this.responsavel = responsavel;
    }

    public Tratamento() {
    }

    public Tratamento(Paciente paciente, Medico medico, boolean responsavel) {
        this.paciente = paciente;
        this.medico = medico;
        this.responsavel = responsavel;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"paciente\": " + (paciente != null ? paciente.toString() : "null") + ",\n" +
                "  \"medico\": " + (medico != null ? medico.toString() : "null") + ",\n" +
                "  \"responsavel\": " + responsavel + "\n" +
                "}";
    }
}

