package nc.isi.sample;

import nc.isi.fragaria_adapter_rewrite.dao.Session;
import nc.isi.fragaria_adapter_rewrite.dao.SessionManager;
import nc.isi.sample.model.Adress;
import nc.isi.sample.model.Person;

import org.apache.tapestry5.ioc.Registry;

public class Initializer {
	private final static Registry REGISTRY = TapestryRegistry.INSTANCE
			.getRegistry();
	private final static Session SESSION = REGISTRY.getService(
			SessionManager.class).create();

	public static void main(String[] args) {
		Person person = SESSION.create(Person.class);
		person.setNom("Maltat");
		person.setPrenom("Justin");
		person.setAge(30);
		Adress adress = SESSION.create(Adress.class);
		adress.setNumero("75");
		adress.setRue("av Foch");
		adress.setQuartier("quartier latin");
		person.setAdress(adress);
		SESSION.post();
	}

}
