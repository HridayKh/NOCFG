package tech.hriday.templates.comands;

import picocli.CommandLine.Command;

@Command(name = "update", description = "Update an existing template from a project.")
public class Update implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}
}
