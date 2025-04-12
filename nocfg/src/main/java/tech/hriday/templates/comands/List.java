package tech.hriday.templates.comands;

import java.io.File;
import java.util.Arrays;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import tech.hriday.templates.Template;

@Command(name = "list", description = "List all existing local templates.")
public class List implements Runnable {

	@Option(names = "--local", description = "List local templates")
	private boolean local;

	@Option(names = "--remote", description = "List remote templates")
	private boolean remote;

	@Override
	public void run() {
		if (local && remote) {
			System.out.println("⚠️ Choose either --local or --remote, not both.");
			return;
		}

		if (!local && !remote) {
			System.out.println("ℹ️ No option provided, you can choose by adding --local or --remote option\n"
					+ "defaulting to local templates\n");
			local = true;
		}

		if (local) {
			String[] templates = getTemplateList();
			if (templates.length == 0) {
				System.out.println("No local templates found.");
				return;
			}
			System.out.println("📦 Local templates:");
			for (String name : templates) {
				System.out.println("• " + name);
			}
		}

		if (remote) {
			System.out.println("Remote templates not yet implemented");
		}
	}

	public static String[] getTemplateList() {
		File templateDir = Template.getTemplateBasePath().toFile();
		File[] dirs = templateDir.listFiles(File::isDirectory);

		if (dirs == null)
			return new String[0];

		return Arrays.stream(dirs).map(File::getName).toArray(String[]::new);
	}

}
