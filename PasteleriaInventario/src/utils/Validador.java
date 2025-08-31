package utils;

import java.util.regex.Pattern;

public class Validador {
    
    // Patrones de validación
    private static final Pattern PATRON_EMAIL = Pattern.compile(
        "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );
    
    private static final Pattern PATRON_TELEFONO_PERU = Pattern.compile(
        "^(\\+51)?\\s?[0-9]{9}$|^[0-9]{7}$"
    );
    
    private static final Pattern PATRON_DNI = Pattern.compile("^[0-9]{8}$");
    
    private static final Pattern PATRON_RUC = Pattern.compile("^[0-9]{11}$");
    
    private static final Pattern PATRON_SOLO_LETRAS = Pattern.compile("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$");
    
    private static final Pattern PATRON_ALFANUMERICO = Pattern.compile("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ\\s]+$");
    
    /**
     * Valida si un string no es nulo ni vacío
     */
    public static boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }
    
    /**
     * Valida si un número es positivo
     */
    public static boolean esNumeroPositivo(double numero) {
        return numero > 0;
    }
    
    /**
     * Valida si un número es positivo o cero
     */
    public static boolean esNumeroPositivoOCero(double numero) {
        return numero >= 0;
    }
    
    /**
     * Valida si una cantidad es válida para inventario
     */
    public static boolean esCantidadValida(int cantidad) {
        return cantidad >= 0;
    }
    
    /**
     * Valida si un precio es válido
     */
    public static boolean esPrecioValido(double precio) {
        return precio > 0 && precio <= 999999.99; // Límite razonable para precios
    }
    
    /**
     * Valida formato de email
     */
    public static boolean esEmailValido(String email) {
        if (!esTextoValido(email)) {
            return false;
        }
        return PATRON_EMAIL.matcher(email.trim()).matches();
    }
    
    /**
     * Valida formato de teléfono peruano
     */
    public static boolean esTelefonoValido(String telefono) {
        if (!esTextoValido(telefono)) {
            return false;
        }
        String telefonoLimpio = telefono.replaceAll("[^0-9+]", "");
        return PATRON_TELEFONO_PERU.matcher(telefonoLimpio).matches();
    }
    
    /**
     * Valida formato de DNI peruano
     */
    public static boolean esDniValido(String dni) {
        if (!esTextoValido(dni)) {
            return false;
        }
        return PATRON_DNI.matcher(dni.trim()).matches();
    }
    
    /**
     * Valida formato de RUC peruano
     */
    public static boolean esRucValido(String ruc) {
        if (!esTextoValido(ruc)) {
            return false;
        }
        return PATRON_RUC.matcher(ruc.trim()).matches();
    }
    
    /**
     * Valida que un texto contenga solo letras y espacios
     */
    public static boolean esSoloLetras(String texto) {
        if (!esTextoValido(texto)) {
            return false;
        }
        return PATRON_SOLO_LETRAS.matcher(texto.trim()).matches();
    }
    
    /**
     * Valida que un texto sea alfanumérico
     */
    public static boolean esAlfanumerico(String texto) {
        if (!esTextoValido(texto)) {
            return false;
        }
        return PATRON_ALFANUMERICO.matcher(texto.trim()).matches();
    }
    
    /**
     * Valida la longitud mínima de un texto
     */
    public static boolean cumpleLongitudMinima(String texto, int longitudMinima) {
        return esTextoValido(texto) && texto.trim().length() >= longitudMinima;
    }
    
    /**
     * Valida la longitud máxima de un texto
     */
    public static boolean cumpleLongitudMaxima(String texto, int longitudMaxima) {
        if (!esTextoValido(texto)) {
            return true; // Si está vacío, no excede el máximo
        }
        return texto.trim().length() <= longitudMaxima;
    }
    
    /**
     * Valida que un texto esté dentro del rango de longitud especificado
     */
    public static boolean cumpleRangoLongitud(String texto, int minimo, int maximo) {
        return cumpleLongitudMinima(texto, minimo) && cumpleLongitudMaxima(texto, maximo);
    }
    
    /**
     * Valida que un número esté dentro del rango especificado
     */
    public static boolean estaEnRango(double numero, double minimo, double maximo) {
        return numero >= minimo && numero <= maximo;
    }
    
    /**
     * Valida que un número entero esté dentro del rango especificado
     */
    public static boolean estaEnRango(int numero, int minimo, int maximo) {
        return numero >= minimo && numero <= maximo;
    }
    
    /**
     * Valida nombre de producto para pastelería
     */
    public static boolean esNombreProductoValido(String nombre) {
        return esTextoValido(nombre) && 
               cumpleRangoLongitud(nombre, 2, 50) && 
               esAlfanumerico(nombre);
    }
    
    /**
     * Valida descripción de producto
     */
    public static boolean esDescripcionValida(String descripcion) {
        return cumpleLongitudMaxima(descripcion, 200);
    }
    
    /**
     * Valida datos básicos de un producto
     */
    public static boolean esProductoValido(String nombre, double precio, int cantidad) {
        return esNombreProductoValido(nombre) && 
               esPrecioValido(precio) && 
               esCantidadValida(cantidad);
    }
    
    /**
     * Genera mensaje de error personalizado para validaciones fallidas
     */
    public static String generarMensajeError(String campo, String tipoError) {
        return switch (tipoError.toLowerCase()) {
            case "vacio" -> "El campo '" + campo + "' no puede estar vacío.";
            case "invalido" -> "El campo '" + campo + "' tiene un formato inválido.";
            case "negativo" -> "El campo '" + campo + "' debe ser mayor a 0.";
            case "rango" -> "El campo '" + campo + "' está fuera del rango permitido.";
            case "longitud" -> "El campo '" + campo + "' no cumple con la longitud requerida.";
            default -> "Error en el campo '" + campo + "'.";
        };
    }
    
    /**
     * Valida múltiples condiciones y retorna el primer error encontrado
     */
    public static String validarProductoCompleto(String nombre, double precio, int cantidad) {
        if (!esTextoValido(nombre)) {
            return generarMensajeError("Nombre", "vacio");
        }
        if (!cumpleRangoLongitud(nombre, 2, 50)) {
            return generarMensajeError("Nombre", "longitud");
        }
        if (!esAlfanumerico(nombre)) {
            return generarMensajeError("Nombre", "invalido");
        }
        if (!esPrecioValido(precio)) {
            return generarMensajeError("Precio", "negativo");
        }
        if (!esCantidadValida(cantidad)) {
            return generarMensajeError("Cantidad", "negativo");
        }
        return null; // Todas las validaciones pasaron
    }
    
    /**
     * Convierte un string a double de forma segura
     */
    public static Double convertirADouble(String texto) {
        if (!esTextoValido(texto)) {
            return null;
        }
        try {
            return Double.parseDouble(texto.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Convierte un string a int de forma segura
     */
    public static Integer convertirAInt(String texto) {
        if (!esTextoValido(texto)) {
            return null;
        }
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Valida y convierte texto a double positivo
     */
    public static Double validarYConvertirDoublePositivo(String texto) {
        Double numero = convertirADouble(texto);
        if (numero != null && esNumeroPositivo(numero)) {
            return numero;
        }
        return null;
    }
    
    /**
     * Valida y convierte texto a int positivo
     */
    public static Integer validarYConvertirIntPositivo(String texto) {
        Integer numero = convertirAInt(texto);
        if (numero != null && numero >= 0) {
            return numero;
        }
        return null;
    }
}
