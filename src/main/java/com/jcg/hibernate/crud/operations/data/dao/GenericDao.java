package com.jcg.hibernate.crud.operations.data.dao;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.management.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.MutationQuery;

import jakarta.persistence.TypedQuery;

public class GenericDao<T> {
    private static final Logger logger = LogManager.getLogger(GenericDao.class);
    private final Class<T> entityClass;

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Method 1: Create Records
    public void createRecords(ArrayList<T> entities, Session session) {
        try {
            session.beginTransaction();
            for (T entity : entities) {
                session.persist(entity);
            }
            session.getTransaction().commit();
            logger.info("\n----- Criação dos registros no Banco de Dados concluída!! -----\n");
        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----\n", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void createRecord(T entity, Session session) {
        try {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            logger.info("\n----- Criação de registro no Banco de Dados concluída!! -----\n");
        } catch (Exception sqlException) {

            logger.error("\n----- Fazendo um RollBack na transação: \n" + sqlException.getMessage());
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public T displayRecord(Object id, Session session) {
        T entity = null;
        try {
            logger.info("\n----- Buscando no Banco de dados -----");
            session.beginTransaction();
            entity = session.get(entityClass, id);
            session.getTransaction().commit();
            if (entity != null) logger.info("\n----- Entidade encontrada com sucesso!! -----");
        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação: \n" + sqlException.getMessage());
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return entity;
    }

    public List<T> displayRecords(Session session) {
        List<T> entitiesList = new ArrayList<>();
        try {
            logger.info("\n----- Buscando no Banco de dados -----");
            session.beginTransaction();
            entitiesList = session.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
            session.getTransaction().commit();

        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----\n", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return entitiesList;
    }

    // Method 3: Update Record
    public void updateRecord(T entAtutalizada, Session session) {
        try {
            session.beginTransaction();
            session.merge(entAtutalizada);
            session.getTransaction().commit();
            logger.info("\n----- Entidade atualizada. -----");
        } catch (Exception e) {
            logger.error("Erro durante a atualização do registro", e);
            try {
                logger.error("\n----- Fazendo um RollBack na transação -----");
                session.getTransaction().rollback();
            } catch (HibernateException he) {
                throw new RuntimeException(he);
            }
        }
    }


    // Method 4: Delete Record
    public void deleteRecord(Object id, Session session) {
        try {
            logger.info("\n----- Buscando no Banco de dados -----");
            session.beginTransaction();
            T entity = session.get(entityClass, id);
            session.remove(entity);
            session.getTransaction().commit();
            logger.info("\n----- Registro excluído do Banco de Dados -----");
        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Method 5: Delete All Records
    public void deleteAllRecords(Session session) {
        try {
            logger.info("\n----- Buscando no Banco de dados -----");
            session.beginTransaction();
            MutationQuery queryObj = session.createMutationQuery("DELETE FROM " + entityClass.getName());
            queryObj.executeUpdate();
            session.getTransaction().commit();
            logger.info("\n----- Sucesso em apagar todos os dados da tabela!!-----");
        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public boolean existElement(Object value, String field, Session session){
        boolean resp = false;
        try{
            session.beginTransaction();
            String hql = "SELECT 1 FROM "+ entityClass.getName()+ " t WHERE t."+ field + "= :value";
            Object result = session.createQuery(hql,Object.class)
                    .setParameter("value",value).setMaxResults(1).uniqueResult();
            session.getTransaction().commit();
            if(result != null)resp = true;
        }catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return resp;
    }


    public T executeSingleQuery(String hdl, Map<String, Object> params, Session session){
        T entity = null;
        try {
            logger.info("\n----- Buscando no Banco de dados -----");
            session.beginTransaction();
            TypedQuery<T> query =  session.createQuery(hdl, entityClass);

            for(Map.Entry<String, Object> param : params.entrySet()){
                query.setParameter(param.getKey(),param.getValue());
            }
            entity = query.getSingleResult(); 
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return entity;
    }
    public List<T> executeListQuery(String hdl, Map<String, Object> params, Session session){
       List<T> entity = null;
        try {
            logger.info("\n----- Buscando no Banco de dados -----");
            session.beginTransaction();
            TypedQuery<T> query =  session.createQuery(hdl, entityClass);

            for(Map.Entry<String, Object> param : params.entrySet()){
                query.setParameter(param.getKey(),param.getValue());
            }
            entity = query.getResultList();
            session.getTransaction().commit();
        } catch (Exception sqlException) {
            logger.error("\n----- Fazendo um RollBack na transação -----", sqlException);
            try {
                session.getTransaction().rollback();
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }
        }
        return entity;
    }
}
