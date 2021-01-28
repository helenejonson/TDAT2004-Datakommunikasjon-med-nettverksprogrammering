import java.io.*;
import java.net.*;
import java.util.ArrayList;

class SocketTjenerWeb {
    public static void main(String[] args) throws IOException {
        final int PORTNR = 8080;

        ServerSocket tjener = new ServerSocket(PORTNR);
        System.out.println("Logg for tjenersiden. N� venter vi...");
        Socket forbindelse = tjener.accept();  // venter inntil noen tar kontakt

        /* �pner str�mmer for kommunikasjon med klientprogrammet */
        InputStreamReader leseforbindelse = new InputStreamReader(forbindelse.getInputStream());
        BufferedReader leseren = new BufferedReader(leseforbindelse);
        PrintWriter skriveren = new PrintWriter(forbindelse.getOutputStream(), true);

        ArrayList<String> headers = new ArrayList<>();
        while(leseren.ready()){
            headers.add(leseren.readLine());
        }
        skriveren.println("HTTP/1.0 200 OK");
        skriveren.println("Content-Type: text/html; charset=utf-8\n");
        skriveren.println("<h1>Hilsen. Du har koblet deg opp til min enkle web-tjener </h1>");
        String list = "";
        for(int i = 0; i < headers.size(); i++){
            if(!headers.get(i).equals("")){
                list += "<li>" + headers.get(i) + "</li>";
            }
        }
        skriveren.println("<ul>" + list + " </ul>");


        /* Lukker forbindelsen */
        leseren.close();
        skriveren.close();
        forbindelse.close();
    }
}
