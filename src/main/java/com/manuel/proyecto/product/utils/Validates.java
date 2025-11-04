package com.manuel.proyecto.product.utils;

import com.manuel.proyecto.product.exceptions.InvalidProductException;

public class Validates {
    public static <T extends Number> void validarNumero(T numero, String mensaje) throws InvalidProductException {
        if (numero == null ) {
            throw new InvalidProductException(mensaje);
        }
    }

    public static <T> void ValidarObjeto(T objeto, String mensaje) throws InvalidProductException {
        if (objeto == null) {
            throw new InvalidProductException(mensaje);
        }
    }

    public static void validarTexto(String texto, String mensaje) throws InvalidProductException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new InvalidProductException(mensaje);
        }
    }


}
