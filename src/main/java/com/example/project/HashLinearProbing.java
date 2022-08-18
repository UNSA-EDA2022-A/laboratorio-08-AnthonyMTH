package com.example.project;

import java.util.Random;

public class HashLinearProbing {
    private int hsize; // tamano de la tabla hash
    private Persona[] buckets; // array que representa la tabla hash
    private String AVAILABLE;
    private int size; // cantidad de elementos en la tabla hash

    public HashLinearProbing(int hsize) {
        this.buckets = new Persona[hsize];
        this.hsize = hsize;
        this.AVAILABLE = "";
        this.size = 0;
    }

    public int hashing(String key) {
        int hash = convertToInt(key);
        hash = hash % 100;
        return hash;
    }

    public void insertHash(Persona persona) {
        Persona wrappedPersona = persona;
        int hash = hashing(persona.DNI);
        System.out.println(hash);

        if (isFull()) {
            System.out.println("Tabla hash esta llena!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] == null || buckets[hash].DNI.equals(AVAILABLE)) {
                buckets[hash] = wrappedPersona;
                size++;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
    }

    public void deleteHash(String key) {
        String wrappedKey = key;
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return;
        }

        for (int i = 0; i < hsize; i++) {
            if (buckets[hash] != null && buckets[hash].DNI.equals(wrappedKey)) {
                buckets[hash].DNI = AVAILABLE;
                size--;
                return;
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada");
    }

    public void displayHashtable() {
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] == null || buckets[i].DNI.equals(AVAILABLE)) {
                System.out.println("Celda " + i + ": Vacia");
            } else {
                System.out.println("Celda " + i + ": " + buckets[i].toString());
            }
        }
    }

    public int findHash(String key) {
        String wrappedKey = key;
        int hash = hashing(key);

        if (isEmpty()) {
            System.out.println("Tabla hash esta vacia!");
            return -1;
        }

        for (int i = 0; i < hsize; i++) {
            try {
                if (buckets[hash].DNI.equals(wrappedKey)) {
                    buckets[hash].DNI = AVAILABLE;
                    return hash;
                }
            } catch (Exception E) {
            }

            if (hash + 1 < hsize) {
                hash++;
            } else {
                hash = 0;
            }
        }
        System.out.println("Clave " + key + " no encontrada!");
        return -1;
    }    
   
    public boolean isFull() {        
        return size == hsize;
    }

    public boolean isEmpty() {
        boolean response = true;
        for (int i = 0; i < hsize; i++) {
            if (buckets[i] != null) {
                response = false;
                break;
            }
        }
        return response;
    }
    
    //Métodos añadidos

    private int convertToInt(String s) {
        int [] valoresInt= new int [s.length()]; 
        int sInt = 0;
        for (int i = 0; i < valoresInt.length; i++) {
            valoresInt [i] = Integer.valueOf(s.charAt(i));
            sInt = sInt + (valoresInt[i]*i);
        }
        return sInt;
    }

    public int size() {
        return this.size;
    }

    public String getName(int index) {
        if(this.buckets[index].nombre == null) return null;
        return this.buckets[index].nombre;
    }
}
