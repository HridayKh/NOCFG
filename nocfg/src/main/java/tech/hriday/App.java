package tech.hriday;

import java.util.List;
import java.util.Scanner;

import picocli.CommandLine;

public class App {
	private static final Scanner globalScanner = new Scanner(System.in);

	public static void main(String... args) {

		if (System.getProperty("org.graalvm.nativeimage.imagecode") != null) {
			int exitCode = new CommandLine(new nocfg()).execute(args);
			System.exit(exitCode);
		} else {
			System.out.println("Running in IDE or maven, args ignroed");
			System.out.print("Command: ");
			String in = globalScanner.nextLine();
			String[] a = {};
			int exitCode = new CommandLine(new nocfg()).execute(in.isBlank() ? a : in.split("\\s+"));
			System.exit(exitCode);
		}
	}

	public static String prompt(String question) {
		System.out.print(question + " ");
		if (!globalScanner.hasNextLine()) {
			System.err.println("\n❌ Input stream closed. Cannot prompt user.");
			System.exit(1);
		}
		return globalScanner.nextLine();
	}

	public static String promptWithOptions(String question, List<String> options) {
		String input;

		while (true) {
			System.out.print(question + " " + options + ": ");
			input = globalScanner.nextLine().trim().toLowerCase();

			if (options.contains(input)) {
				return input;
			}
			System.out.println("Invalid option. Please choose from " + options);
		}
	}

}
