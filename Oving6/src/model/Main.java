package model;
/*
setter opp en tom database. Er satt opp så kjøring vil gi error. Har kommentert vekk linjen for å opprette i persistence
 */

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import konto.Konto;


public class Main {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        // read the existing entries and write to console
        Query q = em.createQuery("select k from Konto k");
        List<Konto> kontoList = q.getResultList();
        for (Konto konto : kontoList) {
            System.out.println(konto);
        }
        System.out.println("Size: " + kontoList.size());

        em.close();
    }
}
