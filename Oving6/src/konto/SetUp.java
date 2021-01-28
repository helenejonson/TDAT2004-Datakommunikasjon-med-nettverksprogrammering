package konto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SetUp {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        KontoDao dao = new KontoDao(factory);
        Konto konto = new Konto("12343", 320, "Solan Gundersen");
        dao.lageNyKonto(konto);
        konto = new Konto("65434", 350, "Ludvig Gundersen");
        dao.lageNyKonto(konto);
        konto = new Konto("87345", 1076, "Reodor Felgen");
        dao.lageNyKonto(konto);
    }
}
