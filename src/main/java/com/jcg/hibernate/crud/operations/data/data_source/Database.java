package com.jcg.hibernate.crud.operations.data.data_source;

import com.jcg.hibernate.crud.operations.entity.Medico;
import com.jcg.hibernate.crud.operations.entity.Paciente;
import com.jcg.hibernate.crud.operations.entity.Tratamento;
import com.jcg.hibernate.crud.operations.entity.TratamentoID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;

public class Database {
    private static final SessionFactory sessionFactoryObj = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Cria a configuração do Hibernate
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");

            // Cria o ServiceRegistry
            StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            // Cria o MetadataSources e Metadata
            MetadataSources metadataSources = new MetadataSources(standardServiceRegistry);
            metadataSources
                    .addAnnotatedClass(Medico.class)
                    .addAnnotatedClass(Paciente.class)
                    .addAnnotatedClass(Tratamento.class)
                    .addAnnotatedClass(TratamentoID.class); // Adicione suas entidades aqui

            // Constrói o SessionFactory
            MetadataImplementor metadataImplementor = (MetadataImplementor) metadataSources.getMetadataBuilder().build();
            return metadataImplementor.buildSessionFactory();

        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getOpenSession() {
        return sessionFactoryObj.openSession();
    }

    public static void close() {
        if (sessionFactoryObj != null && !sessionFactoryObj.isClosed()) {
            sessionFactoryObj.close();
        }
    }
}
