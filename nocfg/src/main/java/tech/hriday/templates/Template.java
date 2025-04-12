package tech.hriday.templates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine.Command;

@Command(name = "template", description = "Manage your templates", mixinStandardHelpOptions = true, subcommands = {
		TemplateCreate.class, TemplateList.class, TemplateRemove.class, TemplateUpdate.class })
public class Template {

	private static final Path TEMPLATE_BASE_PATH = Paths.get(System.getProperty("user.home"), ".nocfg", "templates");

	public static Path getTemplateBasePath() {
		try {
			if (!Files.exists(TEMPLATE_BASE_PATH)) {
				Files.createDirectories(TEMPLATE_BASE_PATH);
				System.out.println("Templates directory does not exist, creating it...\nCreated template directory: "
						+ TEMPLATE_BASE_PATH);
			}
		} catch (IOException e) {
			System.err.println("Failed to create template directory: " + e.getMessage());
		}
		return TEMPLATE_BASE_PATH;
	}

	public static String getTemplatesAsString() {
		// TODO
		return "[java, react]";
	}

}

@Command(name = "create", description = "Create a new template from a project.", aliases = {"add"})
class TemplateCreate implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}

}

@Command(name = "list", description = "List all existing local and online templates.")
class TemplateList implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}
}

@Command(name = "remove", description = "Remove/Delete a template", aliases = { "delete" })
class TemplateRemove implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}
}

@Command(name = "update", description = "Update an existing template from a project.")
class TemplateUpdate implements Runnable {
	public void run() {
		System.out.println("Template command - use subcommands like create, list, fetch");
	}
}
