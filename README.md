# Projeto Java usando Hibernate

Este projeto foi desenvolvido através de um trabalho sobre persistência de dados utilizando Hibernate e MySQL. 
A aplicação modela uma clínica médica com as seguintes entidades: **Paciente**, **Medico** e **Tratamento**. 
Por escolha minha, fiz a implementação de uma classe genérica que trabalha com as operações CRUD com qualquer classe entidade e
com funções que podem trabalhar conforme o query que a pessoa quiser.


## Funcionalidades
- Cadastro de pacientes e médicos.
- Registro de tratamentos, associando pacientes a médicos.
- Atualização e exclusão de registros.
- Consulta de dados armazenados.

## Tecnologias Utilizadas
- **Java**: Linguagem de programação principal.
- **Hibernate**: Framework para mapeamento objeto-relacional (ORM).
- **MySQL**: SGBD relacional.
- **JPA**: Para abstração de persistência.

## Estrutura do Projeto
O projeto segue uma arquitetura em camadas, com as camadas de DAO e Service separadas.
- **dao**: Contém a classe `GenericDAO` para operações CRUD genéricas e DAOs específicos para cada entidade.
- **entity**: Contém as classes de entidade: `Paciente`, `Medico`, `Tratamento`.
- **service**: Contém a lógica de negócios.
- **util**: Contém utilitários para leitura de dados de entrada e outras funções auxiliares.
- **Controller**: Contém as funções que trabalham com a visualização no terminal

## Diagrama de classes
Este é o diagrama de classes que foi utilizado no projeto:


<img src="https://github.com/JersonVitor/HIbernate-Java/blob/master/src/main/java/com/jcg/hibernate/crud/operations/sql/1a_atividade_avaliativa_1.png" alt="Diagrama de classes contendo as entidades médico, tratamento e paciente">



## Como Executar o Projeto
1. Clone o repositório:
```bash
    git clone https://github.com/JersonVitor/Hibernate-Java.git
````
   
2. Atualize os dados do Banco de Dados nessas linhas [aqui](https://github.com/JersonVitor/HIbernate-Java/blob/master/src/main/resources/hibernate.cfg.xml#L12-L15).
  ```xml
    <!-- Database Connection Settings -->
    <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
    <property name="hibernate.connection.url"></property>
    <property name="hibernate.connection.username"></property>
    <property name="hibernate.connection.password"></property>
````
3. Verifique a versão do Java, esse projeto está utilizando a versão 20
   
4. Baixe as dependencias que estão no arquivo `pom.xml`
   
5. Rode o projeto
