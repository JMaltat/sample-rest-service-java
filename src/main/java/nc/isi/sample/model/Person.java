package nc.isi.sample.model;

import nc.isi.fragaria_adapter_rewrite.annotations.DsKey;
import nc.isi.fragaria_adapter_rewrite.entities.AbstractEntityGetterHelper;

import com.fasterxml.jackson.databind.node.ObjectNode;

@DsKey("rest-test")
public class Person extends AbstractEntityGetterHelper {
	public static final String NOM = "nom";
	public static final String PRENOM = "prenom";
	public static final String AGE = "age";
	public static final String ADRESS = "adress";

	public Person() {
		super();
	}

	public Person(ObjectNode objectNode) {
		super(objectNode);
	}

	public void setNom(String nom) {
		writeProperty(NOM, nom);
	}

	public String getNom() {
		return readStringProperty(NOM);
	}

	public void setPrenom(String prenom) {
		writeProperty(PRENOM, prenom);
	}

	public String getPrenom() {
		return readStringProperty(PRENOM);
	}

	public void setAge(Integer age) {
		writeProperty(AGE, age);
	}

	public Integer getAge() {
		return readIntegerProperty(AGE);
	}

	public void setAdress(Adress adress) {
		writeProperty(ADRESS, adress);
	}

	public Adress getAdress() {
		return readProperty(Adress.class, ADRESS);
	}

}
