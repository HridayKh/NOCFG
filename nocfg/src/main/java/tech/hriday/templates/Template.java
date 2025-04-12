package tech.hriday.templates;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Template {

	private static final Path TEMPLATE_BASE_PATH = Paths.get(System.getProperty("user.home"), ".nocfg", "templates");

	public Template() {
		try {
			if (!Files.exists(TEMPLATE_BASE_PATH)) {
				Files.createDirectories(TEMPLATE_BASE_PATH);
				System.out.println("Templates directory does not exist, creating it...\nCreated template directory: " + TEMPLATE_BASE_PATH);
			}
		} catch (IOException e) {
			System.err.println("Failed to create template directory: " + e.getMessage());
		}
	}

	public static String createProj(String type, String projPath) {
		Path sourcePath = TEMPLATE_BASE_PATH.resolve(type);
		Path targetPath = Paths.get(projPath);

		if (!Files.exists(sourcePath)) {
			return "Template type '" + type + "' does not exist at: " + sourcePath;
		}

		try {
			if (Files.exists(targetPath) && Files.list(targetPath).findAny().isPresent()) {
				return "Target project folder already exists and is not empty: " + targetPath;
			}

			Files.walk(sourcePath).forEach(source -> {
				try {
					Path relative = sourcePath.relativize(source);
					Path destination = targetPath.resolve(relative);

					if (Files.isDirectory(source)) {
						Files.createDirectories(destination);
					} else {
						String content = Files.readString(source);
						content = content.replace("{{projectName}}", targetPath.getFileName().toString());

						Files.writeString(destination, content);
					}
					System.out.println("Created: " + destination);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			});
			System.out.println("[Debug] absolute path" + targetPath.toAbsolutePath());
			return "Success";
		} catch (IOException | UncheckedIOException e) {
			return "Unknown Error during project creation: " + e.getMessage();
		}
	}

	public String getTemplatesAsString() {
		// TODO
		return "[java, react]";
	}

}
