import java.io.*;
import java.net.*;
import java.util.*;

public class CalculatorServerThread extends Thread {

    protected DatagramSocket socket = null;
    protected BufferedReader in = null;
    protected boolean done = false;

    public CalculatorServerThread() throws IOException {
        this("CalculatorServer");
    }

    public CalculatorServerThread(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);

        /*

        try {
            in = new BufferedReader(new FileReader("one-liners.txt"));
        } catch (FileNotFoundException e) {
            System.err.println("Could not open quote file. Serving time instead.");
        }

         */


    }

    public void run() {

        while (!done) {
            try {
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println(received);

                // figure out response
                String dString = null;
                double doub;
                doub = getCalculation(received);
                dString = Double.toString(doub);

                buf = dString.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                done = true;
            }
        }
        socket.close();
    }

    protected double getCalculation(String enLinje) {
        double returnValue;

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
        if(operat == '/'){
            returnValue = del;
        }else{
            returnValue = sum;
        }

            /*
            if ((returnValue = in.readLine()) == null) {
                in.close();
                done = true;
                returnValue = "No more quotes. Goodbye.";
            }

             */
        return returnValue;
    }
}