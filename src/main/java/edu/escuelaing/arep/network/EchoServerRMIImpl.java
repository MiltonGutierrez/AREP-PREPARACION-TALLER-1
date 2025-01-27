package edu.escuelaing.arep.network;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class EchoServerRMIImpl implements EchoServerRMI {

    public EchoServerRMIImpl(String ipRMIregistry, int puertoRMIregistry, String nombreDePublicacion) {
        try {
            EchoServerRMI echoServer = (EchoServerRMI) UnicastRemoteObject.exportObject(this, 0);
            Registry registry = LocateRegistry.getRegistry(ipRMIregistry, puertoRMIregistry);
            registry.rebind(nombreDePublicacion, echoServer);
            System.out.println("Echo server ready...");
        } catch (Exception e) {
            System.err.println("Echo server exception:");
            e.printStackTrace();
        }
    }

    public String echo(String cadena, String usuario) throws RemoteException {
        return "desde el servidor: " + cadena + " enviado por: " + usuario;
    }

    public static void main(String[] args) {
        EchoServerRMIImpl ec = new EchoServerRMIImpl("127.0.0.1", 23000, "echoServer");
    }
}
