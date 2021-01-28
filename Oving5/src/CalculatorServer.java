import java.io.*;

public class CalculatorServer {
    public static void main(String[] args) throws IOException {
        new CalculatorServerThread().run();
    }
}
