import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketTjener {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        Scanner leserFraKommandovindu = new Scanner(System.in);

        ServerSocket tjener = new ServerSocket(PORTNR);
        System.out.println("Logg for tjenersiden. Nå venter vi...");
        int ant = 0;
        while(ant < 2){
            Socket forbindelse = tjener.accept();  // venter inntil noen tar kontakt
            TraadKlientHaandterer trad = new TraadKlientHaandterer(forbindelse);
            trad.run();
            ant++;
        }
    }
}

