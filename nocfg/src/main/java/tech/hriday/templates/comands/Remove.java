package tech.hriday.templates.comands;

import picocli.CommandLine.Command;

@Command(name = "remove", description = "Remove/Delete a template", aliases = { "delete" })
public class Remove implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}
}