package resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import business.LampadaireBusiness;
import entities.Lampadaire;

@Path("lampadaire")
public class LampadaireServices {

	@Inject
	private LampadaireBusiness lampadaireBusiness;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllLampadaires() {
		return Response.ok(lampadaireBusiness.getAllLampadaires()).build();
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLampadaire(@PathParam("id") int id) {
		return Response.ok(lampadaireBusiness.get(id)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addLampadaire(Lampadaire lampadaire) {
		lampadaireBusiness.add(lampadaire);
		return Response.noContent().build();
	}

	@DELETE
	@Path("{id}")
	public Response deleteLampadaire(@PathParam("id") int id) {
		lampadaireBusiness.delete(id);
		return Response.noContent().build();
	}
	
	@GET
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchLampadaire(@QueryParam("coordinate") Double[] coordinates) {
		return Response.ok(personneBusiness.search(coordinates)).build();
	}
	
}