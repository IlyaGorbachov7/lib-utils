package by.gorbachevid.perse.util;

import lombok.Getter;

import java.util.Locale;

public enum OS {
	LINUX("linux", "unix", "nix", "nux"), WINDOWS("win"), OSX("mac"), SOLARIS("solaris", "sunos"), UNKNOWN("unknown");

	public static final String NAME = System.getProperty("os.name"), VERSION = System.getProperty("os.version");

	public static final double JAVA_VERSION = getJavaVersion();
	public static final OS CURRENT = getCurrent();
	@Getter
	private final String name;
	private final String[] aliases;
	private static final String[] browsers = new String[]{"google-chrome", "firefox", "opera", "konqueror",
			"mozilla"};

	OS(String... aliases) {
		if (aliases == null)
			throw new NullPointerException();

		this.name = toString().toLowerCase(Locale.ROOT);
		this.aliases = aliases;
	}

	public boolean is(OS os) {
		return this == os;
	}

	public static OS getCurrent() {
		String osName = NAME.toLowerCase(Locale.ROOT);

		for (OS os : values())
			for (String alias : os.aliases)
				if (osName.contains(alias))
					return os;

		return UNKNOWN;
	}

	public static boolean is(OS... any) {
		if (any == null)
			throw new NullPointerException();

		if (any.length == 0)
			return false;

		for (OS compare : any)
			if (CURRENT == compare)
				return true;

		return false;
	}

	public static double getJavaVersion() {
		String version = System.getProperty("java.version");
		int pos, count = 0;

		for (pos = 0; pos < version.length() && count < 2; pos++) {
			if (version.charAt(pos) == '.') {
				count++;
			}
		}

		--pos; // EVALUATE double

		String doubleVersion = version.substring(0, pos);
		return Double.parseDouble(doubleVersion);
	}
}
