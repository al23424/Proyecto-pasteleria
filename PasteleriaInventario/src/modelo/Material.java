package modelo;

public class Material {
    private int idMaterial;
    private String nombre;
    private String tipo; // Envases, utensilios, decoración, etc.
    private int cantidadStock;
    private int stockMinimo;
    private double precioUnitario;
    
    // Constructores y métodos similares a Ingrediente
    
    public boolean necesitaRestock() {
        return cantidadStock <= stockMinimo;
    }
}
