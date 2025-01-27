package edu.escuelaing.arep.network;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatagramTimeClient {

    public static void main(String[] args) {
        String lastDateRecieved = "There's no date recieved";
        boolean running = true;
        while(running){
            try {
                DatagramSocket socket = new DatagramSocket();
                byte[] buf = new byte[256];
                InetAddress address = InetAddress.getByName("127.0.0.1");
                DatagramPacket packet = new DatagramPacket(buf, buf.length, address, 4445);
                socket.send(packet);
                try {
                    socket.setSoTimeout(5000);  // Tiempo de espera de 5 segundos
                    packet = new DatagramPacket(buf, buf.length);
                    socket.receive(packet);
                    String received = new String(packet.getData(), 0, packet.getLength());
                    lastDateRecieved = received; // Actualizamos la hora con la que recibimos
                } catch (IOException e) {
                    // Si no se recibe ningún paquete, mantenemos la última hora
                    System.out.println("No se recibió la hora. Manteniendo la última hora conocida.");
                }
                System.out.println(lastDateRecieved);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (SocketException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnknownHostException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DatagramTimeClient.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
