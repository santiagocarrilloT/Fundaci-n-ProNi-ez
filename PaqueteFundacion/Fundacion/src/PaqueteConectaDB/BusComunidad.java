package PaqueteConectaDB;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

public class BusComunidad {
    private String compara;

    public BusComunidad(){}

    public ArrayList<String> beneficiadosC(){
        ArrayList<String> comunidadB = new ArrayList<>();
        int indice = 0;

        try{
            Statement leer = AccionesBD.establecerConexion().createStatement();
            ResultSet resultado = leer.executeQuery
                    ("select nombre from comunidad where cod_comunidad in (select id_comunidad from beneficiados)");
            while (resultado.next()){
                comunidadB.add(indice, resultado.getString(1));
                indice++;
            }
            resultado.close();
        }catch (Exception e){
            System.out.println("No se lograron imprimir los datos");
        }
        return comunidadB;
    }

    public void nomComunidad(String comparar){this.compara = comparar;}
    public String[] ninosComuni (){
        ArrayList<String> datosC = new ArrayList<>();
        try{
            PreparedStatement sentencia = AccionesBD.establecerConexion().prepareStatement
                    ("select N.nombre from niños N inner join comunidad C on N.comunidad = C.cod_comunidad" +
                            " where C.nombre = ?");
            sentencia.setString(1, this.compara);

            ResultSet resultado = sentencia.executeQuery();
            while (resultado.next()){
                datosC.add(resultado.getString(1));
            }

        }catch (Exception e){
            System.out.println("No se encontraron los niños");
        }

        int contador = 0;
        String [] mostrarN = new String[datosC.size()];
        Iterator<String> iterator = datosC.iterator();
        while (iterator.hasNext()){
            String jovenes = iterator.next();
            mostrarN[contador] = jovenes;
            contador++;
        }
        return mostrarN;
    }
}