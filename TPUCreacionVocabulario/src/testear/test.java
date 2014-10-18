/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package testear;

import clases.Archivo;

/**
 *
 * @author a5
 */
public class test {
    public static void main(String[] args) {
       Archivo a=new Archivo("test.txt");
       a.leer();
        System.out.println(a.toString());
        //a.leerRegex();
    }
}
