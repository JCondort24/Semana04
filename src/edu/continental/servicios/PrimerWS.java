package edu.continental.servicios;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.continental.dao.CiudadesDAO;


@Path("/primerws")
public class PrimerWS {

	@GET
	@Path("/{param}")
	@Produces(MediaType.TEXT_HTML)
	public Response getMensaje(@PathParam("param")String msg){
		
		System.out.println("msg: " + msg);
		
		String output = "Primer servicio web: BIENVENIDO: " + msg;
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("pruebaxml/{nombre}")
	@Produces(MediaType.TEXT_XML)
	public Response getXML (@PathParam("nombre") String nombre){
		
		String output = "<nombre>" + nombre + "</nombre>";
		return Response.status(200).entity(output).build();
	}
	
	@GET
	@Path("ciudades/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCiudades() throws Exception {
		String retorno = "";
		Response rb = null;
		
		try {
			CiudadesDAO dao = new CiudadesDAO();
			retorno = dao.listaCiudades().toString();
			rb = Response.ok(retorno).build();
			
		}
		catch (Exception ex){
			System.out.println("error: " + ex.getMessage());
		}
		return rb;
	}
}
