import java.io.*;
import java.net.*;
import java.util.*;

import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.showMessageDialog;

public class CalculatorClient {
    public static void main(String[] args) throws IOException {

        String [] operasjoner = {"Addere", "Sub", "Gange", "Dele", "Avslutt"};
        int valg = showOptionDialog(null, "Velg regneoperasjon", "Kalkulator", 0, PLAIN_MESSAGE, null, operasjoner, operasjoner[0]);

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
        System.out.println(enLinje);

        /*
        if (args.length != 1) {
            System.out.println("Usage: java QuoteClient <hostname>");
            return;
        }

         */

        // get a datagram socket
        DatagramSocket socket = new DatagramSocket();




        // send request
        System.out.println(enLinje);
        byte[] buf = enLinje.getBytes();
        DatagramPacket packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), 4445);
        socket.send(packet);

        // get response
        packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);

        // display response
        String received = new String(packet.getData(), 0, packet.getLength());
        System.out.println(enLinje + " = " + received);
        showMessageDialog(null, enLinje + " = " + received);

        socket.close();
    }
}