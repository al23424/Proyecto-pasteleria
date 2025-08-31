package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

public class FechaUtil {
    
    // Formatos de fecha comunes
    public static final String FORMATO_FECHA_CORTA = "dd/MM/yyyy";
    public static final String FORMATO_FECHA_LARGA = "dd 'de' MMMM 'de' yyyy";
    public static final String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMATO_HORA = "HH:mm:ss";
    
    /**
     * Obtiene la fecha actual
     */
    public static LocalDate obtenerFechaActual() {
        return LocalDate.now();
    }
    
    /**
     * Obtiene la fecha y hora actual
     */
    public static LocalDateTime obtenerFechaHoraActual() {
        return LocalDateTime.now();
    }
    
    /**
     * Formatea una fecha con el formato especificado
     */
    public static String formatearFecha(LocalDate fecha, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            return fecha.format(formatter);
        } catch (Exception e) {
            return fecha.toString();
        }
    }
    
    /**
     * Formatea una fecha y hora con el formato especificado
     */
    public static String formatearFechaHora(LocalDateTime fechaHora, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            return fechaHora.format(formatter);
        } catch (Exception e) {
            return fechaHora.toString();
        }
    }
    
    /**
     * Formatea una fecha con formato corto (dd/MM/yyyy)
     */
    public static String formatearFechaCorta(LocalDate fecha) {
        return formatearFecha(fecha, FORMATO_FECHA_CORTA);
    }
    
    /**
     * Formatea fecha actual con formato corto
     */
    public static String obtenerFechaActualFormateada() {
        return formatearFechaCorta(obtenerFechaActual());
    }
    
    /**
     * Formatea fecha y hora actual
     */
    public static String obtenerFechaHoraActualFormateada() {
        return formatearFechaHora(obtenerFechaHoraActual(), FORMATO_FECHA_HORA);
    }
    
    /**
     * Convierte un string a LocalDate
     */
    public static LocalDate parsearFecha(String fechaStr, String formato) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(formato);
            return LocalDate.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido: " + fechaStr);
        }
    }
    
    /**
     * Convierte un string a LocalDate usando formato corto
     */
    public static LocalDate parsearFechaCorta(String fechaStr) {
        return parsearFecha(fechaStr, FORMATO_FECHA_CORTA);
    }
    
    /**
     * Calcula días entre dos fechas
     */
    public static long calcularDiasEntre(LocalDate fechaInicio, LocalDate fechaFin) {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }
    
    /**
     * Verifica si una fecha es válida
     */
    public static boolean esFechaValida(String fechaStr, String formato) {
        try {
            parsearFecha(fechaStr, formato);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
    /**
     * Verifica si una fecha es de hoy
     */
    public static boolean esHoy(LocalDate fecha) {
        return fecha.equals(LocalDate.now());
    }
    
    /**
     * Verifica si una fecha es del mes actual
     */
    public static boolean esMesActual(LocalDate fecha) {
        LocalDate hoy = LocalDate.now();
        return fecha.getMonth() == hoy.getMonth() && fecha.getYear() == hoy.getYear();
    }
    
    /**
     * Obtiene el primer día del mes actual
     */
    public static LocalDate obtenerPrimerDiaMesActual() {
        return LocalDate.now().withDayOfMonth(1);
    }
    
    /**
     * Obtiene el último día del mes actual
     */
    public static LocalDate obtenerUltimoDiaMesActual() {
        LocalDate hoy = LocalDate.now();
        return hoy.withDayOfMonth(hoy.lengthOfMonth());
    }
}
