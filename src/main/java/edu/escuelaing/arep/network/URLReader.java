package edu.escuelaing.arep.network;

import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

public class URLReader {

    public static void readURLValues(String url) {
        try {
            URL site = new URL(url);
            System.out.println("Protocol: "+site.getProtocol());
            System.out.println("Authority: "+site.getAuthority());
            System.out.println("Host: "+site.getHost());
            System.out.println("Port: "+site.getPort());
            System.out.println("Path: "+site.getPath());
            System.out.println("Query: "+site.getQuery());
            System.out.println("File: "+site.getFile());
            System.out.println("Ref: "+site.getRef());


        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static void readPage(String url){
        try {
            URL siteURL = new URL(url);
            URLConnection urlConnection = siteURL.openConnection();
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            Set<Entry<String, List<String>>> entrySet = headers.entrySet();
            for (Entry<String, List<String>> entry : entrySet) {
                String headerName = entry.getKey();
                if (headerName != null) {
                    System.out.print(headerName + ": ");
                } else {
                    System.out.print("Status Line: ");
                }
                List<String> headerValues = entry.getValue();
                for (String value : headerValues) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            System.out.println("Error al obtener los encabezados: " + e.getMessage());
        }
    }

    public static void saveHTMLFile(){
        try {
            Scanner reader = new Scanner(System.in);
            System.out.println("Digite la url");

            String site = reader.nextLine();
            URL siteURL = new URL(site);

            String ruta = "Desktop\\page.html";
            File page = new File(ruta);

            BufferedReader readerPage = new BufferedReader(new InputStreamReader(siteURL.openStream()));
            BufferedWriter writer = new BufferedWriter(new FileWriter(page));
            String line;
            while ((line = readerPage.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            readerPage.close();
            writer.close();
            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        URLReader.readURLValues("http://ldbn.escuelaing.edu.co:80/index.html?docid=1#eventos");
        URLReader.readPage("https://google.com");
        //URLReader.saveHTMLFile();
    }
}
