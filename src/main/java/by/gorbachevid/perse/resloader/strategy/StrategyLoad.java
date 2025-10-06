package by.gorbachevid.perse.resloader.strategy;

import java.net.URL;
import java.util.List;

public interface StrategyLoad {
	void defineOrderLoading(List<URL> resources);
}
