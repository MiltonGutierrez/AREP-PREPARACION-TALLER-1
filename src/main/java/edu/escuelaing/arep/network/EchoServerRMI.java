package edu.escuelaing.arep.network;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface EchoServerRMI extends Remote {
    public String echo(String cadena, String usuario) throws RemoteException;
}
