package konto;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
/*
SetUp fyller databasen med 3 testbrukere. TearDown sletter alle brukere fra databasen. Må gjøres dersom Klient og Transfer skal kjøres flere ganger
 */


public class Klient {
    private static final String PERSISTENCE_UNIT_NAME = "todos";
    private static EntityManagerFactory factory;

    public static void main(String[] args) {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        KontoDao dao = new KontoDao(factory);

        List<Konto> kontoList = dao.getAlleKontoer();
        System.out.println("Liste med alle kontoer");
        for (Konto kont : kontoList) {
            System.out.println(kont);
        }
        System.out.println();
        /*Endrer kontoen*/
        System.out.println("Konto før endring:");
        Konto min = dao.finnKonto("12343");
        System.out.println(min.toString());
        min.setName("Mr Solan Gundersen");
        min.trekk(50);
        dao.endreKonto(min);
        min = dao.finnKonto("12343");
        System.out.println("Konto etter endring:");
        System.out.println(min.toString());


        System.out.println();
        System.out.println("Liste med alle kontoer som har mer enn 300 kr");
        kontoList = dao.getBestemteKontoer(300);
        for (Konto kont : kontoList) {
            System.out.println(kont);
        }

        System.out.println();
        kontoList = dao.getAlleKontoer();
        System.out.println("Liste med alle kontoer");
        for (Konto kont : kontoList) {
            System.out.println(kont);
        }

        //dao.rivNed();

    }
}
