package konto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static javax.swing.JOptionPane.*;
/*
SetUp fyller databasen med 3 testbrukere. TearDown sletter alle brukere fra databasen. Må gjøres dersom Klient og Transfer skal kjøres flere ganger
Bruk for å teste med flere klienter
*/
public class Transfer {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        KontoDao dao = new KontoDao(factory);

        Konto min = dao.finnKonto("12343");
        System.out.println(min.toString());
        showMessageDialog(null, "Transfer");
        min.trekk(50);
        dao.endreKonto(min);
        min = dao.finnKonto("12343");
        System.out.println(min.toString());

    }
}
