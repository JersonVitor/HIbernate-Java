package com.jcg.hibernate.crud.operations.entity;

import com.jcg.hibernate.crud.operations.util.Util;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import jakarta.persistence.*;


// em table deve ser o nome que está exatamente no php
@Entity
@Table(name = "Paciente785540")
public class Paciente implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "cpf")
    private String cpf;

    @OneToMany(mappedBy = "paciente")
    private Set<Tratamento> tratamentos;

    @Column(name = "nome", nullable = false)
    private String Nome;

    @Column(name = "endereco", nullable = false)
    private String Endereco;

    @Column(name = "telefone", nullable = false)
    private String Telefone;

    @Column(name = "data_nasc", nullable = false)
    private Date data_nasc;

    @Column(name = "quarto",
            unique = true,
            nullable = false)
    private int quarto;

    public Paciente() {

    }

    public Paciente(String cpf, String nome, String endereco, String telefone, Date data_nasc, int quarto) {
        setCpf(cpf);
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        setData_nasc(data_nasc);
        setQuarto(quarto);
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public Date getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(Date data_nasc) {
        this.data_nasc = data_nasc;
    }

    public int getQuarto() {
        return quarto;
    }

    public void setQuarto(int quarto) {
        this.quarto = quarto;
    }

    @Override
    public String toString() {
        return "{\n" +
                "  \"cpf\": \"" + getCpf() + "\",\n" +
                "  \"nome\": \"" + getNome() + "\",\n" +
                "  \"endereco\": \"" + getEndereco() + "\",\n" +
                "  \"telefone\": \"" + getTelefone() + "\",\n" +
                "  \"data_nasc\": \"" + getData_nasc() + "\",\n" +
                "  \"quarto\": " + getQuarto() + "\n" +
                "}";
    }


    public static Paciente create() {
        return new Paciente(
                Util.ReadString("\nDigite o CPF do Paciente: "),
                Util.ReadString("\nDigite o nome do Paciente: "),
                Util.ReadString("\nDigite o endereço: "),
                Util.ReadString("\nDigite o nome: "),
                Util.ReadDate("\nDigite sua data de nascimento nesse formato dd/MM/yyyy: "),
                Util.ReadInt("\nDigite o numero do quarto: ", 0, 100)
        );
    }

    public Paciente copy(){
        return new Paciente(
                getCpf(),
                getNome(),
                getEndereco(),
                getTelefone(),
                getData_nasc(),
                getQuarto());
    }
}