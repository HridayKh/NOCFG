package tech.hriday.templates.comands;

import picocli.CommandLine.Command;

@Command(name = "create", description = "Create a new template from a project.", aliases = { "add" })
public class Create implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}

}