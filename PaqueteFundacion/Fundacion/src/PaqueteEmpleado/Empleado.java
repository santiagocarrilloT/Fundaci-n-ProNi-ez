package PaqueteEmpleado;

public class Empleado {
    int id;
    String nombre;
    Integer salario;
    String telefono;

    public Empleado(int id, String nom, int sal, String telf){
        this.id = id;
        this.nombre = nom;
        this.salario = sal;
        this.telefono = telf;
    }


    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public Integer getSalario() {
        return salario;
    }
    public String getTelefono() {
        return telefono;
    }
}
