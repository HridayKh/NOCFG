package tech.hriday.templates.comands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import tech.hriday.App;
import tech.hriday.templates.Template;

@Command(name = "create", description = "Create a new template from a project.", aliases = { "add" })
public class Create implements Runnable {

	@Parameters(index = "0", arity = "0..1", description = "The template type (e.g., java, react)", paramLabel = "TYPE")
	private String type;

	@Parameters(index = "1", arity = "0..1", description = "The project to use as base for template", paramLabel = "PROJECT")
	private Path proj;

	@Override
	public void run() {
		if (type == null) {
			type = App.prompt("Template name?");
		}

		if (proj == null) {
			String input = App.prompt("Path to the base project directory?");
			proj = Paths.get(input);
		}

		if (!Files.exists(proj) || !Files.isDirectory(proj)) {
			System.err.println("❌ Invalid path: " + proj + " — not a directory.");
			return;
		}

		Path target = Template.getTemplateBasePath().resolve(type);

		if (Files.exists(target)) {
			System.err.println("⚠️ Template with name '" + type + "' already exists at: " + target);
			return;
		}

		try {
			Files.walk(proj).forEach(source -> {
				try {
					Path relative = proj.relativize(source);
					Path destination = target.resolve(relative);

					if (Files.isDirectory(source)) {
						Files.createDirectories(destination);
					} else {
						String content = Files.readString(source);
						content = content.replace(proj.getFileName().toString(), "{{projectName}}");
						Files.createDirectories(destination.getParent());
						Files.writeString(destination, content);
					}
					System.out.println("✓ Added: " + destination);
				} catch (IOException e) {
					throw new RuntimeException("⚠️ Error copying file: " + source, e);
				}
			});
			System.out.println("✅ Template '" + type + "' created successfully.");
		} catch (IOException e) {
			System.err.println("❌ Failed to create template: " + e.getMessage());
		}
	}
}