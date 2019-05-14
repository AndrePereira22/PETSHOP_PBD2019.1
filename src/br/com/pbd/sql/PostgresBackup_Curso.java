/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pbd.sql;

import br.com.pbd.principal.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class PostgresBackup_Curso {


    public PostgresBackup_Curso() {

        try {
            realizaBackup();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    ;

    public static void realizaBackup() throws IOException, InterruptedException {
        final List<String> comandos = new ArrayList<String>();
        String dir = "C:/BKPCECOM";
        List<String> lista = new ArrayList<String>(9);
        File diretorio = new File(dir);
        File fList[] = diretorio.listFiles();
        if (fList.length == 0) {

            comandos.add("C:\\Program Files\\PostgreSQL\\9.6\\bin\\pg_dump.exe");

            comandos.add("-h");
            comandos.add("localhost");
            //comandos.add("192.168.0.25");
            comandos.add("-p");
            comandos.add("5432");
            comandos.add("-U");
            comandos.add("postgres");
            comandos.add("-F");
            comandos.add("c");
            comandos.add("-b");
            comandos.add("-v");
            comandos.add("-f");

            comandos.add("C:\\BKPCECOM\\" + 1 + " " + getDateTime() + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            comandos.add("petshop");
            ProcessBuilder pb = new ProcessBuilder(comandos);

            pb.environment().put("PGPASSWORD", "glenda");

            try {
                final Process process = pb.start();

                final BufferedReader r = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                    System.err.println(line);
                    line = r.readLine();
                }
                r.close();

                process.waitFor();
                process.destroy();
                new RemoveBKP();

            } catch (IOException e) {

            } catch (InterruptedException ie) {

            }

        } else {
            for (int i = 0; i < fList.length; i++) {
                //JOptionPane.showMessageDialog(null,fList[i].getName());
                lista.add(fList[i].getName());
            }

            char recebe = Collections.max(lista).charAt(0);
            //JOptionPane.showMessageDialog(null,Character.getNumericValue(recebe)); 

            comandos.add("C:\\Program Files\\PostgreSQL\\9.6\\bin\\pg_dump.exe");

            //comandos.add("-i");      
            comandos.add("-h");
            comandos.add("localhost");
            comandos.add("-p");
            comandos.add("5432");
            comandos.add("-U");
            comandos.add("postgres");
            comandos.add("-F");
            comandos.add("c");
            comandos.add("-b");
            comandos.add("-v");
            comandos.add("-f");

            comandos.add("C:\\BKPCECOM\\" + (Character.getNumericValue(recebe) + 1) + " " + getDateTime() + ".backup");   // eu utilizei meu C:\ e D:\ para os testes e gravei o backup com sucesso.  
            comandos.add("petshop");
            ProcessBuilder pb = new ProcessBuilder(comandos);

            pb.environment().put("PGPASSWORD", "glenda");

            try {
                final Process process = pb.start();

                final BufferedReader r = new BufferedReader(
                        new InputStreamReader(process.getErrorStream()));
                String line = r.readLine();
                while (line != null) {
                    System.err.println(line);
                    line = r.readLine();
                }
                r.close();

                process.waitFor();
                process.destroy();
                new RemoveBKP();

            } catch (IOException e) {
            } catch (InterruptedException ie) {
            }

        }
    }

    private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy HHmm");
        Date date = new Date();
        return dateFormat.format(date);
    }

}
