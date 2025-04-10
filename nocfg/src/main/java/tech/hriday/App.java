package tech.hriday;

import java.util.Scanner;
import picocli.CommandLine;

public class App {
	public static void main(String... args) {

		if (System.getProperty("org.graalvm.nativeimage.imagecode") != null) {
			int exitCode = new CommandLine(new nocfg()).execute(args);
			System.exit(exitCode);
		} else {
			System.out.println("Running in IDE or maven, args ignroed");
			Scanner sc = new Scanner(System.in);
			System.out.print("Command: ");
			String in = sc.nextLine();
			sc.close();
			String[] a = {};
			int exitCode = new CommandLine(new nocfg()).execute(in.isBlank() ? a : in.split("\\s+"));
			System.exit(exitCode);
		}

	}
}
