package nc.isi.sample.rest;

import java.io.IOException;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import nc.isi.fragaria_adapter_rewrite.enums.State;
import nc.isi.sample.model.Person;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Path("/persons")
public class PersonResource {

	private final Session session;

	public PersonResource(SessionManager sessionManager) {
		this.session = sessionManager.create();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Person> getAll() {
		return session.get(new ByViewQuery<>(Person.class));
	}

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Person get(@PathParam("id") String id) {
		return session.getUnique(new IdQuery<>(Person.class, id));
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(String personJson) throws JsonParseException,
			JsonMappingException, IOException {
		Person person = FragariaObjectMapper.INSTANCE.get().readValue(
				personJson.getBytes(), Person.class);
		person.setState(State.NEW);
		if (get(person.getId()) != null) {
			person.setState(State.MODIFIED);
		}
		session.register(person);
		session.post();
		return corResponse(Response.ok());
	}

	protected Response corResponse(ResponseBuilder response) {
		response.header("Access-Control-Allow-Origin", "*");
		return response.build();
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") String id) {
		Person person = get(id);
		if (person == null) {
			return corResponse(Response.notModified());
		}
		session.delete(person);
		session.post();
		return corResponse(Response.ok());
	}
}
