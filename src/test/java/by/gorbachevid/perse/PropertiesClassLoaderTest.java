package by.gorbachevid.perse;

import by.gorbachevid.perse.resbndl.impl.PropertiesManagerBase;
import by.gorbachevid.perse.resloader.PropertiesResourceLoader;
import by.gorbachevid.perse.resloader.strategy.StrategyLoad;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

public class PropertiesClassLoaderTest {


	@Test
	public void test() throws IOException {
		URL url = ClassLoader.getSystemClassLoader().getResource("inner.properties");
		System.out.println(url);
		System.out.println();
		Iterator<URL> urls = ClassLoader.getSystemClassLoader().getResources("inner.properties").asIterator();
		urls.forEachRemaining(System.out::println);
	}


	/**
	 * To test this method worked out currently You must add dependency {@code bntu.fitr.gorbachev.ConstructorTickets}
	 * <p>
	 * This dependency contains file {@code application.properties}
	 *
	 * For testing we create properties file: see {@code java/resources/application.properties} and {@code test/java/resources/application.properties}
	 */
	@Test
	public void testReadingFromDependency() throws IOException {
		URL url = ClassLoader.getSystemClassLoader().getResource("application.properties");
		System.out.println(url);

		System.out.println();
		Iterator<URL> urls = ClassLoader.getSystemClassLoader().getResources("application.properties").asIterator();
		urls.forEachRemaining(System.out::println);
	}

	@Test
	public void testPropertiesResourceLoader() throws IOException {
		PropertiesResourceLoader propertiesLoader = PropertiesResourceLoader.builder().strategyLoad(new StrategyLoad() {
			@Override
			public void defineOrderLoading(List<URL> resources) {
				Collections.reverse(resources);
			}
		}).build();

		Properties properties = propertiesLoader.loadResource("inner.properties");
		PropertiesManagerBase managerProperties = PropertiesManagerBase.builder().build(properties);
		managerProperties.getProperties().forEach((key, value)-> {
			System.out.println(key + " : " + value);
		});
	}

}
