/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testear;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author user
 */
public class ArchivoTest {

    File f;
    ContadorTest c;
    public static final String EXAMPLE_TEST = "This is my, 489 sma4ll   . '0example string which? I'm going to /use for pattern_matching. \n Nueva linea continuando ejemplo.";

    public ArchivoTest() {
        f = new File("test.txt");
        c = new ContadorTest();
    }

    public void leer() {
        try {

            /*
             a=10
             b9
             c8
             resto abecedario=1
             ab5
             bc4
             ded3
             di4
             dif2
             */
            Scanner sc = new Scanner(f);

//            sc.useDelimiter(",");
//            sc.useDelimiter(" *");
            while (sc.hasNext()) {
                String clave = sc.nextLine();
                c.sumarCantA(clave);
                c.sumarCantB(clave);
                c.sumarCantC(clave);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void leerRegex() {
        //([A-Za-z])+
        Pattern pattern = Pattern.compile("[A-Za-z][a-zA-z]+");
       
        //Pattern pattern = Pattern.compile("[a-zA-Z_0-9]");
        // in case you would like to ignore case sensitivity,
        // you could use this statement:
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(EXAMPLE_TEST);
        // check all occurance
        while (matcher.find()) {
            System.out.print("Start index: " + matcher.start());
            System.out.print(" End index: " + matcher.end() + " ");
            System.out.println(matcher.group());
        }
        // now create a new pattern and matcher to replace whitespace with tabs
        Pattern replace = Pattern.compile("\\s+");
        Matcher matcher2 = replace.matcher(EXAMPLE_TEST);
        System.out.println(matcher2.replaceAll("Hola"));
        
    }

    public String toString() {
        return c.toStringA();
    }

}
