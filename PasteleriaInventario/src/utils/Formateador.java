package utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Formateador {
    
    // Formatos de números
    private static final DecimalFormat FORMATO_DINERO = new DecimalFormat("#,##0.00");
    private static final DecimalFormat FORMATO_PORCENTAJE = new DecimalFormat("#0.00'%'");
    private static final DecimalFormat FORMATO_CANTIDAD = new DecimalFormat("#,##0");
    private static final NumberFormat FORMATO_MONEDA_PERU = NumberFormat.getCurrencyInstance(new Locale("es", "PE"));
    
    /**
     * Formatea un número como dinero con dos decimales
     */
    public static String formatearDinero(double cantidad) {
        return "S/ " + FORMATO_DINERO.format(cantidad);
    }
    
    /**
     * Formatea un número como moneda peruana
     */
    public static String formatearMonedaPeru(double cantidad) {
        return FORMATO_MONEDA_PERU.format(cantidad);
    }
    
    /**
     * Formatea un número como porcentaje
     */
    public static String formatearPorcentaje(double porcentaje) {
        return FORMATO_PORCENTAJE.format(porcentaje);
    }
    
    /**
     * Formatea una cantidad entera (sin decimales)
     */
    public static String formatearCantidad(int cantidad) {
        return FORMATO_CANTIDAD.format(cantidad);
    }
    
    /**
     * Formatea un número decimal con la cantidad de decimales especificada
     */
    public static String formatearDecimal(double numero, int decimales) {
        String patron = "#0." + "0".repeat(decimales);
        DecimalFormat formato = new DecimalFormat(patron);
        return formato.format(numero);
    }
    
    /**
     * Capitaliza la primera letra de un string
     */
    public static String capitalizarPrimeraLetra(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }
    
    /**
     * Capitaliza cada palabra de un string
     */
    public static String capitalizarPalabras(String texto) {
        if (texto == null || texto.isEmpty()) {
            return texto;
        }
        
        String[] palabras = texto.split("\\s+");
        StringBuilder resultado = new StringBuilder();
        
        for (int i = 0; i < palabras.length; i++) {
            if (i > 0) {
                resultado.append(" ");
            }
            resultado.append(capitalizarPrimeraLetra(palabras[i]));
        }
        
        return resultado.toString();
    }
    
    /**
     * Limpia espacios en blanco al inicio y final, y múltiples espacios internos
     */
    public static String limpiarTexto(String texto) {
        if (texto == null) {
            return null;
        }
        return texto.trim().replaceAll("\\s+", " ");
    }
    
    /**
     * Formatea un texto con longitud máxima, agregando "..." si es necesario
     */
    public static String truncarTexto(String texto, int longitudMaxima) {
        if (texto == null || texto.length() <= longitudMaxima) {
            return texto;
        }
        return texto.substring(0, longitudMaxima - 3) + "...";
    }
    
    /**
     * Formatea un número de teléfono peruano
     */
    public static String formatearTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty()) {
            return telefono;
        }
        
        // Remover caracteres no numéricos
        String numeroLimpio = telefono.replaceAll("[^0-9]", "");
        
        // Formatear según longitud
        if (numeroLimpio.length() == 9) {
            // Celular: 999 999 999
            return numeroLimpio.substring(0, 3) + " " + 
                   numeroLimpio.substring(3, 6) + " " + 
                   numeroLimpio.substring(6);
        } else if (numeroLimpio.length() == 7) {
            // Fijo Lima: 999 9999
            return numeroLimpio.substring(0, 3) + " " + numeroLimpio.substring(3);
        }
        
        return telefono; // Devolver original si no coincide con formatos conocidos
    }
    
    /**
     * Genera un ID único para transacciones
     */
    public static String generarIdTransaccion(String prefijo) {
        long timestamp = System.currentTimeMillis();
        return prefijo + "-" + timestamp;
    }
    
    /**
     * Formatea texto para mostrar en reportes
     */
    public static String formatearTituloReporte(String titulo) {
        return "=== " + titulo.toUpperCase() + " ===";
    }
    
    /**
     * Formatea una línea de separación para reportes
     */
    public static String obtenerLineaSeparacion(int longitud) {
        return "-".repeat(longitud);
    }
    
    /**
     * Alinea texto a la derecha con espacios
     */
    public static String alinearDerecha(String texto, int ancho) {
        if (texto.length() >= ancho) {
            return texto;
        }
        return " ".repeat(ancho - texto.length()) + texto;
    }
    
    /**
     * Alinea texto a la izquierda con espacios
     */
    public static String alinearIzquierda(String texto, int ancho) {
        if (texto.length() >= ancho) {
            return texto;
        }
        return texto + " ".repeat(ancho - texto.length());
    }
}
