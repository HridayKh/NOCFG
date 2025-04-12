package tech.hriday.templates.comands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import tech.hriday.App;
import tech.hriday.templates.Template;

@Command(name = "update", description = "Update an existing template from a project.")
public class Update implements Runnable {

	@Parameters(index = "0", arity = "0..1", description = "The template to update", paramLabel = "TYPE")
	private String type;

	@Parameters(index = "1", arity = "0..1", description = "The project folder to update from", paramLabel = "PROJECT")
	private Path proj;

	@Override
	public void run() {
		if (type == null) {
			type = App.promptWithOptions("Template to update?", Arrays.asList(List.getTemplateList()));
		}

		if (proj == null) {
			String input = App.prompt("Path to the updated project directory?");
			proj = Paths.get(input);
		}

		if (!Files.exists(proj) || !Files.isDirectory(proj)) {
			System.err.println("❌ Invalid project path: " + proj);
			return;
		}

		Path target = Template.getTemplateBasePath().resolve(type);

		if (!Files.exists(target)) {
			System.err.println("❌ Template '" + type + "' does not exist. Use `create` to add new templates.");
			return;
		}

		// Confirm before overwrite
		String confirm = App.prompt("⚠️ This will overwrite the existing template. Continue? [y/N]");
		if (!confirm.trim().toLowerCase().startsWith("y")) {
			System.out.println("Cancelled.");
			return;
		}

		try {
			// Clean old template
			Files.walk(target).sorted((a, b) -> b.compareTo(a)) // delete files before dirs
					.forEach(path -> {
						try {
							Files.delete(path);
						} catch (IOException e) {
							System.err.println("❌ Failed to delete: " + path + " — " + e.getMessage());
						}
					});

			// Copy updated project
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
					System.out.println("✓ Updated: " + destination);
				} catch (IOException e) {
					throw new RuntimeException("⚠️ Error copying file: " + source, e);
				}
			});

			System.out.println("✅ Template '" + type + "' updated successfully.");
		} catch (IOException e) {
			System.err.println("❌ Error during update: " + e.getMessage());
		}
	}
}
