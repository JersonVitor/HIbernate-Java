package com.jcg.hibernate.crud.operations.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;


import java.io.Serial;
import java.io.Serializable;

@Embeddable
public class TratamentoID implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "crm_medico")
    private String crm;
    @Column(name = "cpf_paciente")
    private String cpf;

    public TratamentoID(String crm, String cpf) {
        this.cpf = cpf;
        this.crm = crm;
    }
    public TratamentoID(){}
}
