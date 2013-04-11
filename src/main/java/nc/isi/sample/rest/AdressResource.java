package nc.isi.sample.rest;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import nc.isi.fragaria_adapter_rewrite.dao.ByViewQuery;
import nc.isi.fragaria_adapter_rewrite.dao.IdQuery;
import nc.isi.fragaria_adapter_rewrite.dao.Session;
import nc.isi.fragaria_adapter_rewrite.dao.SessionManager;
import nc.isi.fragaria_adapter_rewrite.entities.FragariaObjectMapper;
import nc.isi.sample.model.Adress;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Path("/adress")
public class AdressResource {

	private final Session session;

	public AdressResource(SessionManager sessionManager) {
		this.session = sessionManager.create();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Adress> getAllAdresses() {
		return session.get(new ByViewQuery<>(Adress.class));
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Adress getAdress(@PathParam("id") String id) {
		return session.getUnique(new IdQuery<>(Adress.class, id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(String adressJson) throws JsonParseException,
			JsonMappingException, IOException {
		Adress adress = FragariaObjectMapper.INSTANCE.get().readValue(
				adressJson.getBytes(), Adress.class);
		session.register(adress);
		session.post();
		return corResponse(Response.ok());
	}

	protected Response corResponse(ResponseBuilder response) {
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}

}
