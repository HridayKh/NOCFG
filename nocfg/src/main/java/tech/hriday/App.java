package tech.hriday;

import java.util.Scanner;

import picocli.CommandLine;

public class App {
	public static void main(String... args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Command: ");
		String cmd = sc.nextLine();
		sc.close();
		
		if (cmd.isBlank() || cmd.isEmpty() || cmd == null)
			cmd = "--algorithm SHA-1 hello.txt";

		int exitCode = new CommandLine(new CheckSum()).execute(cmd.split(" "));
		System.exit(exitCode);
	}
}
