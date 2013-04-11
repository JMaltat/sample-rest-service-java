package nc.isi.sample.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import nc.isi.fragaria_adapter_rewrite.entities.FragariaObjectMapper;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ResteasyJacksonProvider extends JacksonJsonProvider {

	public ResteasyJacksonProvider() {
		super(FragariaObjectMapper.INSTANCE.get());
	}
}
