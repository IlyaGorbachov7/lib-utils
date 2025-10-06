package by.gorbachevid.perse.resloader;

import by.gorbachevid.perse.resloader.strategy.StrategyLoad;
import by.gorbachevid.perse.util.FilesUtil;
import lombok.Getter;
import lombok.NonNull;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class PropertiesResourceLoader {

	private Properties resource;

	@Getter
	private StrategyLoad strategyLoading;

	@Getter
	private ClassLoader classLoader;

	public PropertiesResourceLoader() {
	}


	/**
	 * Main point properties which will be loading at the end is path
	 */
	public Properties loadResource(@NonNull String name) throws IOException {
		if(Objects.isNull(classLoader.getResource(name))) throw new IllegalArgumentException("Param \"name\" should refer to on resource file located in inside work space application");
		if(Objects.equals(FilesUtil.PROPERTIES_EXCEPTION, FilesUtil.getExtension(name))) {
			throw new IllegalArgumentException(String.format("Value of the param \"name\" should be file extension %s", FilesUtil.PROPERTIES_EXCEPTION));
		}
		Iterator<URL> iteratorResources = classLoader.getResources(name).asIterator();
		List<URL> resources = toList(iteratorResources);
		strategyLoading.defineOrderLoading(resources);
		for(var res : resources) {
			Properties tmp = new Properties();
			tmp.load(res.openStream());
			overrideKeys(tmp);
		}
		return resource;
	}

	private void overrideKeys(Properties sourceKeys) {
		resource.putAll(sourceKeys);
	}

	private static List<URL> toList(Iterator<URL> iteratorResources) {
		List<URL> resources = new ArrayList<>();
		while(iteratorResources.hasNext()) {
			resources.add(iteratorResources.next());
		}
		return resources;
	}

	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		private Properties resource;
		private StrategyLoad strategyLoad;
		private ClassLoader classLoader;

		public PropertiesResourceLoader build() {
			PropertiesResourceLoader result = new PropertiesResourceLoader();
			result.resource = getResource(); // architecture pattern: factory method
			result.strategyLoading = getStrategyLoad();
			result.classLoader = getClassLoader();
			return result;
		}

		protected StrategyLoad defaultStrategyLoad() {
			return new StrategyLoad() {
				@Override
				public void defineOrderLoading(List<URL> resources) {

				}
			};
		}

		protected Properties defaultResource() {
			return new Properties();
		}

		private Properties getResource() {
			if(Objects.isNull(resource)) resource = defaultResource();
			return resource;
		}

		private ClassLoader defaultClassLoader() {
			return ClassLoader.getSystemClassLoader();
		}

		public Builder resource(Properties resource) {
			this.resource = resource;
			return this;
		}

		private StrategyLoad getStrategyLoad() {
			if(Objects.isNull(strategyLoad)) strategyLoad = defaultStrategyLoad();
			return strategyLoad;
		}

		public Builder strategyLoad(StrategyLoad strategyLoad) {
			this.strategyLoad = strategyLoad;
			return this;
		}

		private ClassLoader getClassLoader() {
			if(Objects.isNull(classLoader)) classLoader = defaultClassLoader();
			return classLoader;
		}

		public Builder classLoader(ClassLoader classLoader) {
			this.classLoader = classLoader;
			return this;
		}


	}
}
