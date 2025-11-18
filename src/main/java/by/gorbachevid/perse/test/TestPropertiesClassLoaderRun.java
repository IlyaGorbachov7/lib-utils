package by.gorbachevid.perse.test;

import by.gorbachevid.perse.resbndl.impl.PropertiesManagerBase;
import by.gorbachevid.perse.resloader.PropertiesResourceLoader;

import java.io.IOException;
import java.util.Properties;

public class TestPropertiesClassLoaderRun {

	public static void main(String[] args) throws IOException {
		PropertiesResourceLoader resourceLoader = PropertiesResourceLoader.builder().build();
		Properties properties = resourceLoader.loadResource("inner.properties");
		PropertiesManagerBase managerProperties = PropertiesManagerBase.builder().build(properties);
		managerProperties.getProperties().forEach((k, v) -> {
			System.out.println("k : " + k + " value : " + v);
		});
	}
}
