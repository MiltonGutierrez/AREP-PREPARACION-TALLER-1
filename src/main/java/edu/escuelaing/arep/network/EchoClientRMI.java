package edu.escuelaing.arep.network;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

/**
 *
 * @author dnielben
 */
public class EchoClientRMI {

    public void ejecutaServicio(String ipRmiregistry, int puertoRmiRegistry, String nombreServicio, String usuario) {

        try {
            Registry registry = LocateRegistry.getRegistry(ipRmiregistry, puertoRmiRegistry);
            EchoServerRMI echoServer = (EchoServerRMI) registry.lookup(nombreServicio);
            System.out.println(echoServer.echo("Hola como estas? " , usuario));
        } catch (Exception e) {
            System.err.println("Hay un problema:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EchoClientRMI ec = new EchoClientRMI();
        Scanner scanner = new Scanner(System.in);
        String usuario = scanner.nextLine();
        scanner.close();
        ec.ejecutaServicio("127.0.0.1", 23000, "echoServer", usuario);
    }
}

