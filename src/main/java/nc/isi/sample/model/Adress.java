package nc.isi.sample.model;

import nc.isi.fragaria_adapter_rewrite.annotations.DsKey;
import nc.isi.fragaria_adapter_rewrite.entities.AbstractEntityGetterHelper;

import com.fasterxml.jackson.databind.node.ObjectNode;

@DsKey("rest-test")
public class Adress extends AbstractEntityGetterHelper {
	public static final String RUE = "rue";
	public static final String QUARTIER = "quartier";
	public static final String NUMERO = "numero";

	public Adress() {
		super();
	}

	public Adress(ObjectNode objectNode) {
		super(objectNode);
	}

	public void setRue(String rue) {
		writeProperty(RUE, rue);
	}

	public String getRue() {
		return readStringProperty(RUE);
	}

	public void setQuartier(String quartier) {
		writeProperty(QUARTIER, quartier);
	}

	public String getQuartier() {
		return readStringProperty(QUARTIER);
	}

	public void setNumero(String numero) {
		writeProperty(NUMERO, numero);
	}

	public String getNumero() {
		return readStringProperty(NUMERO);
	}

}
