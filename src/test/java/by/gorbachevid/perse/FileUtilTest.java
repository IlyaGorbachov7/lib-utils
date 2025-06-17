package by.gorbachevid.perse;

import by.gorbachevid.perse.resbndl.Readable;
import by.gorbachevid.perse.resbndl.Writable;
import by.gorbachevid.perse.resbndl.impl.PropertiesManagerBase;
import by.gorbachevid.perse.util.FilesUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class FileUtilTest {
	static PropertiesManagerBase properties;
	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		properties = PropertiesManagerBase.builder().build(FilesUtil.resolveSourceLocationAsInputStream("app-test" +
				".properties"));
	}

	@Test
	public void test() {
		System.out.println(properties.getValue("app.int"));
		System.out.println(properties.getValue("app.str"));
		System.out.println(properties.getValue("app.username"));
		System.out.println(properties.getValue("app.str2"));
	}
	@Test
	public void test1() {
		System.getProperties().put("app.int.age", "15");

		String v = properties.getValue("app.userage");
		Assertions.assertEquals("15", v);
		System.out.println(v);

		System.getProperties().remove("app.int.age");

		v = properties.getValue("app.userage");
		Assertions.assertEquals("23", v);
		System.out.println(v);
	}

	@Test
	public void test2() {
		String v = properties.getValue("app.userage.def");
		Assertions.assertEquals("13",properties.getValue("app.useragedef"));
		System.out.println(v);
	}

	@Test
	public void test3() {
		String v = properties.getValue("app.userhome");
		Assertions.assertEquals("Luban", v);
		System.out.println(v);
	}
	@Test
	public void test4() {
		System.getProperties().put("app.str.home", "LIDA");

		String v = properties.getValue("app.userhome.def");
		Assertions.assertEquals("LIDA", v);
		System.out.println(v);

		System.getProperties().remove("app.str.home");

		v = properties.getValue("app.userhome.def");
		Assertions.assertEquals("Luban", v);
		System.out.println(v);
	}

	@Test
	public void test5() {
		System.getProperties().put("app.str.home", "LIDA");

		String v = properties.getValue("app.userhome.def2");
		Assertions.assertEquals("LIDA", v);
		System.out.println(v);

		System.getProperties().remove("app.str.home");

		v = properties.getValue("app.userhome.def2");
		Assertions.assertEquals("Minsk", v);
		System.out.println(v);
	}

	@Test
	public void test6() {
		String v = properties.getValue("app.value.duration");
		Assertions.assertEquals("Minsk==>Luban", v);
		System.out.println(v);
	}

	@Test
	public void test7() {
		String v = properties.getValue("app.env");
		System.out.println(v);
	}

	@Test
	public void test8() {
		String v = properties.getValue("app.env.def");
		System.out.println(v);
	}

	@Test
	public void testRemoveTest() {
		Assertions.assertEquals(4, properties.remove(
				"app.int", "app.str", "app.userhome", "app.str2", "NOFINT KEY"));

	}
}
