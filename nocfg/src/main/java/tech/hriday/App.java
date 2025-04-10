package tech.hriday;

import java.util.Scanner;

import picocli.CommandLine;

public class App {
	public static void main(String... args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Command: ");
		String in = sc.nextLine();
		sc.close();

		int exitCode = new CommandLine(new nocfg()).execute(in.isBlank() ? args : in.split("\\s+"));
		System.exit(exitCode);
	}
}
