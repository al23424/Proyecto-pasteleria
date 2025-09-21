package modelo;

public class Ingrediente {
    private int idIngrediente;
    private String nombre;
    private String unidadMedida; // kg, gramos, litros, etc.
    private double cantidadStock;
    private double stockMinimo;
    private double precioUnitario;
    private String proveedor;
    
    // Constructores
    public Ingrediente() {}
    
    public Ingrediente(int idIngrediente, String nombre, String unidadMedida, 
                      double cantidadStock, double stockMinimo, double precioUnitario) {
        this.idIngrediente = idIngrediente;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
        this.precioUnitario = precioUnitario;
    }
    
    // Método para verificar si necesita restock
    public boolean necesitaRestock() {
        return cantidadStock <= stockMinimo;
    }
    
    // Getters y Setters completos
}
