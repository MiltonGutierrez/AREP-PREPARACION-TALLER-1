package edu.escuelaing.arep.network;

import java.io.*;
import java.net.*;

public class EchoServerMathFunction {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        String inputLine, outputLine, function;
        function = "cos";
        double number;
        while ((inputLine = in.readLine()) != null) {
            System.out.println("Mensaje:" + inputLine);
            try {
                number = Double.parseDouble(inputLine);
                if(function.equals("cos")){
                    number = Math.cos(number);
                }
                else if(function.equals("sin")){
                    number = Math.sin(number);
                }
                else{
                    number = Math.tan(number);
                }
                outputLine = "Respuesta " + number;
                out.println(outputLine);
                if (outputLine.equals("Respuestas: Bye."))break;
            } catch (Exception e) {
                function = inputLine.replaceAll("fun:", "");
                outputLine = "Respuesta " + function;
                out.println(outputLine);
                if (outputLine.equals("Respuestas: Bye."))break;
            }

        }
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

