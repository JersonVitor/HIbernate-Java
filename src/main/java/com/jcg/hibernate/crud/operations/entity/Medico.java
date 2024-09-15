package com.jcg.hibernate.crud.operations.entity;


import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

import com.jcg.hibernate.crud.operations.util.Util;


@Entity
@Table(name = "Medico785540")
public class Medico implements Serializable{

    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "crm")
    private String crm;


    @OneToMany(mappedBy = "medico")
    private Set<Tratamento> tratamentos;


    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "especialidade", nullable = false)
    private String especialidade;

    @Column(name = "salario", nullable = false)
    private Double salario;

    public Medico() {
    }

    public Medico(String crm, String nome, String especialidade, Double salario) {
        setCrm(crm);
        setNome(nome);
        setEspecialidade(especialidade);
        setSalario(salario);
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"crm\": \"" + getCrm() + "\",\n" +
                "  \"nome\": \"" + getNome() + "\",\n" +
                "  \"especialidade\": \"" + getEspecialidade() + "\",\n" +
                "  \"salario\": " + getSalario() + "\n" +
                "}";
    }

    public static Medico create(){
        return new Medico(
            Util.ReadString("\nDigite a CRM do Medico: "),
                Util.ReadString("\nDigite o nome do Médico: "),
                Util.ReadString("\nDigite a especialidade: "),
                Util.ReadDouble("\nDigite o Salário: ", 0, 30000)
        );
    }

    public Medico copy(){
        return new Medico(
                getCrm(),
                getNome(),
                getEspecialidade(),
                getSalario()
        );
    }

}
