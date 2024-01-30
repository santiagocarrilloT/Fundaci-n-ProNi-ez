package PaqueteConectaDB;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static PaqueteConectaDB.AccionesBD.establecerConexion;

public class BusProyecto {
    private double rangoNota;
    private double presupuesto;
    private String comparar;

    public BusProyecto(){}

    public void actualizaPresu(double presupuestom, String comparar){
        this.presupuesto = presupuestom;
        this.comparar = comparar;
    }
    public void modificaTabla(){
        try{
            PreparedStatement sentencia = establecerConexion().prepareStatement("" +
                    "update proyecto" +
                    " set presupuesto = ? where titulo = ?");
            sentencia.setDouble(1, this.presupuesto);
            sentencia.setString(2, this.comparar);

            sentencia.executeUpdate();
            System.out.println("Los datos se agregaron correctamente");

        } catch (Exception e){
            System.out.println("No se modifico la tabla");
        }
    }

    public ArrayList<String> beneficiadosP(){
        ArrayList<String> proyectoB = new ArrayList<>();
        int indice = 0;

        try{
            Statement leer = establecerConexion().createStatement();
            ResultSet resultado = leer.executeQuery
                    ("select titulo from proyecto where codproyecto in (select id_proyecto from beneficiados)");
            while (resultado.next()){
                proyectoB.add(indice, resultado.getString(1));
                indice++;
            }
            resultado.close();
        }catch (Exception e){
            System.out.println("No se lograron imprimir los datos");
        }
        return proyectoB;
    }

    public void nota(double nota){
        this.rangoNota = nota;
    }
    public String[] notaP(){
        HashMap<String, String> proyectoB = new HashMap<>();

        try{
            PreparedStatement sentencia = establecerConexion().prepareStatement
                    ("select titulo, evaluacion from proyecto where evaluacion < ?");
            sentencia.setDouble(1, this.rangoNota);
            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()){
                proyectoB.put(resultado.getString(1), resultado.getString(2));
            }
            resultado.close();

        }catch (Exception e){
            System.out.println("No se encontraron bajas notas");
        }

        int contador = 0;

        String[] notas= new String[proyectoB.size()];
        Iterator<String> iterator = proyectoB.keySet().iterator();
        while (iterator.hasNext()){
            String nomP = iterator.next();
            String nota = proyectoB.get(nomP);
            notas[contador] = nomP + ", calificación: " + nota;
            contador++;
        }

        return notas;
    }

    public void nombreProyect(String compara){
        this.comparar = compara;
    }
    public String[] participaProf (){
        ArrayList<String> proyectoB = new ArrayList<>();
        int indice = 0;

        try{
            PreparedStatement sentencia = establecerConexion().prepareStatement
                    ("select E.nombre from empleado E inner join participan P on E.id = P.id_profesional " +
                            "where P.id_proyecto in (select codproyecto from proyecto where titulo = ?)");
            sentencia.setString(1, this.comparar);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                proyectoB.add(indice, resultado.getString(1));
                indice++;
            }
            resultado.close();

        }catch (Exception e){ System.out.println("No se encontraron profesinales");}

        int contador = 0;
        String[] prof= new String[proyectoB.size()];
        Iterator<String> iterator = proyectoB.iterator();
        while (iterator.hasNext()){
            String profesional = iterator.next();
            prof[contador] = "Profesional: "+profesional;
            contador++;
        }
        return prof;
    }


    public String[] objetivoP (){
        HashMap<String, String> datosP = new HashMap<>();
        try {
            PreparedStatement sentencia = establecerConexion().prepareStatement
                    ("select O.descripcion, O.nota from objetivos O inner join proyecto P on " +
                            "O.idproyecto = P.codproyecto where P.titulo = ? ");
            sentencia.setString(1, this.comparar);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                datosP.put(resultado.getString(1), resultado.getString(2));
            }
            resultado.close();

        }catch (Exception e){
            System.out.println("No se mostraron objetivos");
        }

        int contador = 0;
        String [] muestraOb = new String[datosP.size()];
        Iterator<String> iterator = datosP.keySet().iterator();
        while (iterator.hasNext()){
            String descrip = iterator.next();
            String nota = datosP.get(descrip);
            muestraOb[contador] = descrip +": "+ nota;
            contador++;
        }
        return muestraOb;
    }

    public ArrayList<String> consultarProyectosPorFechas(Date fechainicio, Date fechafinal, boolean consultaGeneral, boolean consultaFechaInicio, boolean consultaFechaFin) {
        ArrayList<String> proyectos = new ArrayList<>();

        try {
            PreparedStatement sentencia = null;
            String consulta = "";

            if (consultaGeneral) {
                consulta = "SELECT titulo FROM PROYECTO WHERE fechainicio >= ? AND fechafinal <= ?";
                sentencia = AccionesBD.establecerConexion().prepareStatement(consulta);
                sentencia.setDate(1, fechainicio);
                sentencia.setDate(2, fechafinal);
            } else if (consultaFechaInicio) {
                consulta = "SELECT titulo FROM PROYECTO WHERE fechainicio BETWEEN ? AND ?";
                sentencia = AccionesBD.establecerConexion().prepareStatement(consulta);
                sentencia.setDate(1, fechainicio);
                sentencia.setDate(2, fechafinal);
            } else if (consultaFechaFin) {
                consulta = "SELECT titulo FROM PROYECTO WHERE fechafinal BETWEEN ? AND ?";
                sentencia = AccionesBD.establecerConexion().prepareStatement(consulta);
                sentencia.setDate(1, fechainicio);
                sentencia.setDate(2, fechafinal);
            }

            ResultSet resultado = sentencia.executeQuery();

            while (resultado.next()) {
                proyectos.add(resultado.getString("titulo"));
            }

            resultado.close();
            sentencia.close();
        } catch (Exception e) {
            System.out.println("Error al consultar proyectos por fechas: " + e.getMessage());
        }

        return proyectos;
    }

    public double calcularEdadPromedio() {

        double edadPromedio = 0.0;

        try {
            PreparedStatement sentencia = AccionesBD.establecerConexion().prepareStatement(
                    "SELECT AVG(DATE_PART('year', age(CURRENT_DATE, N.fecha_nacimiento))) AS edadPromedio " +
                            "FROM niños N INNER JOIN comunidad C ON N.comunidad = C.cod_comunidad " +
                            "INNER JOIN beneficiados B ON C.cod_comunidad = B.id_comunidad " +
                            "INNER JOIN proyecto P ON B.id_proyecto = P.codproyecto " +
                            "WHERE P.titulo = ?"
            );
            sentencia.setString(1, this.comparar);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                edadPromedio = resultado.getDouble("edadPromedio");
            }

            resultado.close();
            sentencia.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return edadPromedio;
    }

    public double calcularEdadPromedioComuni() {

        double edadPromedio = 0.0;

        try {
            PreparedStatement sentencia = AccionesBD.establecerConexion().prepareStatement(
                    "SELECT AVG(DATE_PART('year', age(CURRENT_DATE, n.fecha_nacimiento))) AS edadPromedio\n" +
                            "FROM niños n INNER JOIN comunidad c ON n.comunidad = c.cod_comunidad \n" +
                            "where c.nombre=?"
            );
            sentencia.setString(1, this.comparar);

            // Ejecutar la consulta y obtener el resultado
            ResultSet resultado = sentencia.executeQuery();
            if (resultado.next()) {
                edadPromedio = resultado.getDouble("edadPromedio");
            }

            resultado.close();
            sentencia.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return edadPromedio;
    }
}
