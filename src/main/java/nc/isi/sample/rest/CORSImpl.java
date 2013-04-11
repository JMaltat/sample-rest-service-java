package nc.isi.sample.rest;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;

import org.jboss.resteasy.annotations.interception.ServerInterceptor;
import org.jboss.resteasy.spi.interception.MessageBodyWriterContext;
import org.jboss.resteasy.spi.interception.MessageBodyWriterInterceptor;

@Provider
@ServerInterceptor
public class CORSImpl implements MessageBodyWriterInterceptor {

	@Override
	public void write(MessageBodyWriterContext context) throws IOException,
			WebApplicationException {
		context.getHeaders().add("Access-Control-Allow-Origin", "*");
		context.proceed();
	}

}
