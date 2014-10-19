/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class TempStore {

    private HashMap<String, Integer> hm;
    private int cant=0;
    public TempStore() {
        hm = new HashMap();
    }

    public TempStore(int size) {
        hm = new HashMap(size);
    }

    public TempStore(int size, float loadFactor) {
        hm = new HashMap(size, loadFactor);
    }

    public void addCount(String clave) {
        if (!hm.containsKey(clave)) {
            hm.put(clave, 1);
            cant++;
            return;
        }
        int old = this.getCount(clave);
        hm.replace(clave, old + 1);
    }
    public int getCount(String clave){
        return hm.get(clave);
    }
    public int getCantClaves(){
        return cant;
    }
    public String toString() {
        Iterator<String> it = hm.keySet().iterator();
        StringBuilder st = new StringBuilder("HashMap A\n");
        while (it.hasNext()) {
            String clave = it.next();
            st.append("[");
            st.append(clave);
            st.append("]=");
            st.append(this.getCount(clave));
            st.append("\n");
        }
        return st.toString();
    }
    public Iterator<String> getIterator(){
        Iterator<String> it = hm.keySet().iterator();
        return it;
    }
}
