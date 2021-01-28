import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class TraadKlientHaandterer extends Thread {
    public Socket forbindelse;

    public TraadKlientHaandterer(Socket forbindelse){
        this.forbindelse = forbindelse;
    }


    public void run(){

        InputStreamReader leseforbindelse = null;
        BufferedReader leseren = null;
        PrintWriter skriveren = null;
        try {
            leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
            leseren = new BufferedReader(leseforbindelse);
            skriveren = new PrintWriter(forbindelse.getOutputStream(), true);
            String enLinje = leseren.readLine();  // mottar en linje med tekst
            while (enLinje != null) {  // forbindelsen p� klientsiden er lukket
                String[] tall = enLinje.split("[+\\-*/]");
                int tall1 = Integer.parseInt(tall[0].trim());
                int tall2 = Integer.parseInt(tall[1].trim());
                String[] param = enLinje.split(" ");
                char operat = param[1].charAt(0);
                int sum = 0;
                double del = 0;
                switch (operat){
                    case '+':
                        sum = tall1 + tall2;
                        break;
                    case '-':
                        sum = tall1 - tall2;
                        break;
                    case '*':
                        sum = tall1 * tall2;
                        break;
                    case '/':
                        double tall11 = (double) tall1;
                        double tall22 = (double) tall2;
                        del = tall11 / tall22;
                        break;
                    default:
                        break;
                }
                System.out.println("En klient skrev: " + enLinje);
                if(operat == '/'){
                    skriveren.println("" + del);
                }else{
                    skriveren.println("" + sum);
                }
                enLinje = leseren.readLine();
            }
            leseren.close();
            skriveren.close();
            forbindelse.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        /* Lukker forbindelsen */

    }
}
