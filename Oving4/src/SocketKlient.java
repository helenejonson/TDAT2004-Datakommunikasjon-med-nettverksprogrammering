import java.io.*;
import java.net.*;
import static javax.swing.JOptionPane.*;

class SocketKlient {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 1250;

        /* Setter opp forbindelsen til tjenerprogrammet */
        Socket forbindelse = new Socket("localhost", PORTNR);
        System.out.println("Nå er forbindelsen opprettet.");

        /* åpner en forbindelse for kommunikasjon med tjenerprogrammet */
        InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
        BufferedReader leseren = new BufferedReader(leseforbindelse);
        PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

        String [] operasjoner = {"Addere", "Sub", "Gange", "Dele", "Avslutt"};
        int valg = showOptionDialog(null, "Velg regneoperasjon", "Kalkulator", 0, PLAIN_MESSAGE, null, operasjoner, operasjoner[0]);

        String out = "";
        while(valg >= 0 && valg < 4) {
            boolean sjekk1 = false;
            boolean sjekk2 = false;
            int tall1 = 0;
            int tall2 = 0;
            while (!sjekk1) {
                String tall = "";
                if (valg == 3) {
                    tall = showInputDialog("Sett inn teller").trim();
                } else {
                    tall = showInputDialog("Sett inn første tall").trim();
                }
                boolean matches = tall.matches("\\d+");
                if (matches) {
                    tall1 = Integer.parseInt(tall);
                    sjekk1 = true;
                } else {
                    showMessageDialog(null, "Sett inn kun tall");
                }
            }
            while (!sjekk2) {
                String tall = "";
                if (valg == 3) {
                    tall = showInputDialog("Sett inn nevner").trim();
                } else {
                    tall = showInputDialog("Sett inn andre tall").trim();
                }
                boolean matches = tall.matches("\\d+");
                if (matches) {
                    tall2 = Integer.parseInt(tall);
                    sjekk2 = true;
                }
                if ((valg == 3 && tall2 == 0) || !matches){
                    sjekk2 = false;
                    if(tall2 == 0){
                        showMessageDialog(null, "Nevner kan ikke være 0");
                    }else{
                        showMessageDialog(null, "Sett inn kun tall");
                    }
                }

            }
            char operator;
            if(valg == 0){
                operator = '+';
            }else if(valg == 1){
                operator = '-';
            }else if(valg == 2){
                operator = '*';
            }else{
                operator = '/';
            }
            String enLinje = tall1 + " " + operator + " " + tall2;
            skriveren.println(enLinje);  // sender teksten til tjeneren
            String respons = leseren.readLine();  // mottar respons fra tjeneren
            out += enLinje + " = " + respons + "\n";
            System.out.println("Fra tjenerprogrammet: " + respons);
            showMessageDialog(null, out);

            valg = showOptionDialog(null, "Velg regneoperasjon", "Kalkulator", 0, PLAIN_MESSAGE, null, operasjoner, operasjoner[0]);

        }

        /* Lukker forbindelsen */
        leseren.close();
        skriveren.close();
        forbindelse.close();
    }
}

