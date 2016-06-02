/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LibraryCod;

import java.sql.*;

/**
 *
 * @author Pablite5
 */
public class BaseDatos {

    /**
     * @param args the command line arguments
     */
   
       Connection co;
       
       
       
       public BaseDatos(String server,String nome,String contra,String Basededatos) {
           try{
               Class.forName("com.mysql.jdbc.Driver");
               co = DriverManager.getConnection("jdbc:mysql://" + server + "/" + Basededatos,nome,contra);
               //método para que acceda a la base de datos mysql
              
           }
           catch(ClassNotFoundException exc){
               System.out.println("Erro driver");
           }
           catch(SQLException ex) {
               System.out.println("Erro logeo");
           }
           
       }
       
       public  void apagar() {
        try {
            co.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
       /**
        * Metodo para insertar datos
        * @param tabla es la tabla de la base para hacer la insercion
        * @param filas son los valores a insertar
        */
      public void insertar(String tabla,String[] filas) {
       
      Statement stm=null;
       try {
          stm= co.createStatement();
          String consulta=" insert into " + tabla + " values ( " ;
           for (int i = 0; i < filas.length; i++) {
             if(i==filas.length-1){
                 consulta+="'" + filas[i] + "');";
             }else{
                 consulta+="'" +filas[i] + "',";
             }
               }
            stm.executeUpdate(consulta);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
     
/**
 * Sirve para consultar los datos que hay en las tablas
 * @param tabla la tabla
 * @param columna son los nombres de las columnas
 * @return develve la consulta
 */
         public ResultSet consultar()  {
        Statement stm=null;
        ResultSet rs=null;//necesario para mostrar los results
         try {
            stm = co.createStatement();
            rs = stm.executeQuery("select * from datosliga;");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
         return rs;

    }
       
        /**
         * Sirve para eliminar los datos almacenados
         * @param ColumnaPrimaryKey
         * @param tabla es la tabla
         * @param valorPrimaryKey valor de la clave primaria
         */
     
       
       
        public void eliminar(String ColumnaPrimaryKey, String tabla,String valorPrimaryKey) {
           
            Statement stm=null;
        try {
            stm = co.createStatement();
            stm.executeUpdate("delete from "+ tabla + " where " + ColumnaPrimaryKey + "='" + valorPrimaryKey + "'; ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                stm.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        }
        /**
         * Sirve para actualizar
         * @param ColumnaPrimaryKey columna del valor
         * @param tabla es la tabla
         * @param columna Las columnas de la tabla
         * @param valorPrimaryKey el valor de la clave primaria
         * @param valorCampo valor que le vamos a dar
         */
         
        
      
        
        public void actualizar(String ColumnaPrimaryKey, String tabla, String columna,String valorPrimaryKey,String valorCampo) {
       
                Statement stm=null;
        try {
           stm = co.createStatement();
            stm.executeUpdate("update "+tabla + " set " + columna + "='" + valorCampo + "' where " + ColumnaPrimaryKey + " ='" + valorPrimaryKey + "';");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
        
        
         
}
    


