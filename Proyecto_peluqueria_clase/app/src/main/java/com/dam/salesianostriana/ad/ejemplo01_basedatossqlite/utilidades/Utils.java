package com.dam.salesianostriana.ad.ejemplo01_basedatossqlite.utilidades;

/**
 * Created by Jesús Pallares on 10/12/2015.
 */
public class Utils {

    public static String capitalizePalabra(String palabra){
        String primera_letra = palabra.substring(0,1).toUpperCase();
        String letras_restantes = palabra.substring(1,palabra.length());
        return primera_letra+letras_restantes;
    }
}
