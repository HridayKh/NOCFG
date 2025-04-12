package tech.hriday.templates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine.Command;
import tech.hriday.templates.comands.Create;
import tech.hriday.templates.comands.List;
import tech.hriday.templates.comands.Remove;
import tech.hriday.templates.comands.Update;

@Command(name = "template", description = "Manage your templates", mixinStandardHelpOptions = true, aliases = {
		"templates" }, subcommands = { Create.class, List.class, Remove.class, Update.class })
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

}
