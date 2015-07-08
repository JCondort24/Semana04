package edu.continental.dao;

import org.codehaus.jettison.json.JSONArray;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import edu.continental.util.ToJSON;
import edu.continental.util.conexion;

public class CiudadesDAO {

	public JSONArray listaCiudades(){
		// OBTENER LA CONEXION A LA BD
		conexion cn = new conexion();
		Connection con = cn.getConnection();
		
		//SENTENCIA SQL
		String sql= "select id, nombre, altitud, estado from ciudades where estado = 'A'";
			
		Statement st = null;
		ResultSet rs = null;
		
		//PARA CONVERTIR A JSON
		ToJSON convertidor = new ToJSON();
		JSONArray arreglo = new JSONArray();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			
			arreglo = convertidor.toJSONArray(rs);
			st.close();
		}
		
		catch (Exception ex) {
			System.out.println("error: " + ex.getMessage());
			ex.printStackTrace();
			return null;
		}
		
		return arreglo;
	}
}
