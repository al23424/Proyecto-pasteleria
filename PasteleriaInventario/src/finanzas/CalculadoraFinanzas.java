package finanzas;

public class CalculadoraFinanzas {
  private double ingresos;
  private double gastos;
  private double inversion;
	public CalculadoraFinanzas(double ingresos, double gastos, double inversion) {
		this.ingresos = ingresos;
		this.gastos = gastos;
		this.inversion = inversion;
	}
	public double getIngresos() {
		return ingresos;
	}
	public void setIngresos(double ingresos) {
		this.ingresos = ingresos;
	}
	public double getGastos() {
		return gastos;
	}
	public void setGastos(double gastos) {
		this.gastos = gastos;
	}
	public double getInversion() {
		return inversion;
	}
	public void setInversion(double inversion) {
		this.inversion = inversion;
	}
}
