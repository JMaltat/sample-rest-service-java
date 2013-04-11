package nc.isi.sample;

import org.apache.tapestry5.ioc.Registry;
import org.apache.tapestry5.ioc.RegistryBuilder;

public enum TapestryRegistry {
	INSTANCE;

	private final Registry registry = RegistryBuilder
			.buildAndStartupRegistry(QaModule.class);

	public Registry getRegistry() {
		return registry;
	}

}
