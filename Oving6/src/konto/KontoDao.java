package konto;

import javax.persistence.*;
import java.util.List;

public class KontoDao {
    private EntityManagerFactory emf;

    public KontoDao(EntityManagerFactory emf){
        this.emf = emf;
    }

    public void lageNyKonto(Konto konto){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            em.persist(konto);
            em.getTransaction().commit();
        }finally {
            lukkEM(em);
        }
    }

    public Konto finnKonto(String kontonr){
        EntityManager em = getEM();
        try{
            return em.find(Konto.class, kontonr);
        }finally {
            lukkEM(em);
        }
    }

    public void endreKonto(Konto konto){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();
            Konto k = em.merge(konto);
            em.getTransaction().commit();
        }finally {
            lukkEM(em);
        }
    }

    public List<Konto> getAlleKontoer(){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("select object(k) from Konto k");
            return q.getResultList();
        }finally {
            lukkEM(em);
        }
    }

    public List<Konto> getBestemteKontoer(double verdi){
        EntityManager em = getEM();
        try{
            Query q = em.createQuery("select object(k) from Konto k where k.saldo > " + verdi);
            return q.getResultList();
        }finally {
            lukkEM(em);
        }
    }

    public void rivNed(){
        EntityManager em = getEM();
        try{
            em.getTransaction().begin();

            Query q = em.createQuery("delete from Konto");
            q.executeUpdate();
            em.getTransaction().commit();
        }finally {
            lukkEM(em);
        }
    }

    private EntityManager getEM(){
        return emf.createEntityManager();
    }

    private void lukkEM(EntityManager em){
        if (em != null && em.isOpen()) em.close();
    }
}
