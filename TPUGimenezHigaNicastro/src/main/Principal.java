
package main;

import clases.FileRead;

/**
 *
 * @author ricky
 */
public class Principal {

    public static void main(String[] args) {
        FileRead fr = new FileRead("22975-8.txt");//204k
        FileRead f2 = new FileRead("16082-8.txt");//370k
        FileRead f3 = new FileRead("18166-8.txt");//559k
        FileRead f4 = new FileRead("41575-8.txt");//531k

        long startTime,endTime,duration;
        
        startTime = System.currentTimeMillis();
        fr.readFile();
        fr.saveCount();
        endTime = System.currentTimeMillis();
        duration = (endTime - startTime);
        System.out.println(duration+ "ms");
        
//        startTime = System.currentTimeMillis();
//        f2.readFile();
//        f2.saveCount();
//        endTime = System.currentTimeMillis();
//        duration = (endTime - startTime);
//        System.out.println(duration+"ms");
//        /*Total de palabras 15590*/
//        
//        startTime = System.currentTimeMillis();
//        f3.readFile();
//        f3.saveCount();
//        endTime = System.currentTimeMillis();
//        duration = (endTime - startTime);
//        System.out.println(duration);
//        
//        startTime = System.currentTimeMillis();
//        f4.readFile();
//        f4.saveCount();
//        endTime = System.currentTimeMillis();
//        duration = (endTime - startTime);
//        System.out.println(duration);

    }
}
