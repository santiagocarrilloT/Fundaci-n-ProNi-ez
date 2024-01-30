package PaqueteConectaDB;

import PaqueteEmpleado.Empleado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

public class BusEmpleado {
    private String compara;
    private int salario;

    public ArrayList<String> triggerActualizacion(){
        int indice = 0;
        ArrayList<String> act = new ArrayList<>();

        try{
            Statement leer = AccionesBD.establecerConexion().createStatement();
            ResultSet resultado = leer.executeQuery("select * from aud_empleado");
            while (resultado.next()){
                int id = resultado.getInt(1);
                String nom = resultado.getString(2);
                int ant_sal = resultado.getInt(3);
                int act_sal = resultado.getInt(4);
                Timestamp fecha = resultado.getTimestamp(6);
                act.add(indice, id+" | "+nom+" | "+ant_sal+" -> "+act_sal+" | "+fecha);
                indice++;
            }

        }catch (Exception e){
            System.out.println("No se mostró el trigger");
        }
        return act;
    }
    public void nuevoEmpleado (Empleado empleado,String cargo, String tabla){
        try{
            PreparedStatement sentencia = AccionesBD.establecerConexion().prepareStatement
                    ("Insert into empleado values (?,?,?,?)");
            sentencia.setInt(1,empleado.getId());
            sentencia.setString(2,empleado.getNombre());
            sentencia.setInt(3,empleado.getSalario());
            sentencia.setString(4,empleado.getTelefono());
            sentencia.executeUpdate();
            sentencia.close();
            tipoCargo(empleado.getId(), cargo, tabla);
            System.out.println("Actualización correcta");
        }catch (Exception e){
            System.out.println("No se crearon nuevos datos");
        }
    }

    public void tipoCargo (int id, String cargo, String tabla){
        try{
            PreparedStatement sentencia;
            if(tabla.equals("profesional")){
                sentencia = AccionesBD.establecerConexion().prepareStatement("Insert into profesional values (?,?)");
                sentencia.setInt(1,id);
                sentencia.setString(2,cargo);
                sentencia.executeUpdate();
                sentencia.close();
            }
            if(tabla.equals("administrativo")){
                sentencia = AccionesBD.establecerConexion().prepareStatement("Insert into administrativo values (?,?)");
                sentencia.setInt(1,id);
                sentencia.setString(2,cargo);
                sentencia.executeUpdate();
                sentencia.close();
            }
            System.out.println("Cargo agregado con éxito");

        }catch (Exception e){
            System.out.println("No se agregó el cargo");
        }
    }

    public ArrayList<Empleado> empleadosTbl() {
        int indice= 0;
        ArrayList<Empleado> traerEmpleado = new ArrayList<>(21);
        try{
            Statement leer = AccionesBD.establecerConexion().createStatement();
            ResultSet resultado = leer.executeQuery("select * from empleado");

            while (resultado.next()){
                int id = resultado.getInt(1);
                String nom = resultado.getString(2);
                int sal = resultado.getInt(3);
                String tel = resultado.getString(4);
                traerEmpleado.add(indice, new Empleado(id, nom,sal,tel));
            }
            resultado.close();

        }catch (Exception io){
            System.out.println("No se motraron empleados "+ io.getMessage());
        }
        return traerEmpleado;
    }
    public ArrayList<String> responsableP (){
        ArrayList<String> datosResp = new ArrayList<>();
        int indice = 0;

        try{
            Statement leer = AccionesBD.establecerConexion().createStatement();
            ResultSet resultado = leer.executeQuery
                    ("select nombre from empleado where id in (select idresponsable from proyecto)");
            while (resultado.next()){
                datosResp.add(indice, resultado.getString(1));
                indice++;
            }
            resultado.close();
        }catch (Exception e){}

        return datosResp;
    }

    public ArrayList<String> especializacion(){
        int indice = 0;
        ArrayList<String> espz = new ArrayList<>();

        try{
            Statement leer = AccionesBD.establecerConexion().createStatement();
            ResultSet resultado = leer.executeQuery("select distinct especializacion from profesional");
            while (resultado.next()){
                espz.add(indice, resultado.getString(1));
                indice++;
            }

        }catch (Exception e){}

        return espz;
    }

    public void tipoEspz(String comparar){
        this.compara = comparar;
    }

    public ArrayList<String> especiaProf(){
        ArrayList<String> profSelect= new ArrayList<>();
         try{
             PreparedStatement sentencia = AccionesBD.establecerConexion().prepareStatement
                     ("select E.nombre from Profesional P inner join Empleado E on " +
                             "P.id = E.id where P.especializacion = ?");
             sentencia.setString(1,this.compara);

             ResultSet resultado = sentencia.executeQuery();
             while (resultado.next()){
                 profSelect.add(resultado.getString(1));
             }

         }catch (Exception e){
             System.out.println("No se encontraron profesionales");
         }
         return profSelect;
    }


    public void setSalario(int salario, String id){
        this.salario = salario;
        this.compara = id;
    }

    public void actualizarSalario(){
        try{
            PreparedStatement sentencia = AccionesBD.establecerConexion().prepareStatement
                    ("update empleado set salario =? where id=?");
            sentencia.setInt(1, this.salario);
            sentencia.setInt(2,Integer.parseInt(this.compara));
            sentencia.executeUpdate();
            System.out.println("Actualización correcta");
        }catch (Exception e){
            System.out.println("No se logró actualizar");
        }
    }
}
