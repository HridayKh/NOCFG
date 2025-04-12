package tech.hriday.cmd;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import tech.hriday.App;
import tech.hriday.templates.*;

@Command(name = "make", description = "Creates a new project from a stored template.", mixinStandardHelpOptions = true, aliases = {
		"create", "init" })
public
class Make implements Runnable {

	@Parameters(index = "0", arity = "0..1", description = "The template type (e.g., java, react)", paramLabel = "TYPE")
	private String type;

	@Parameters(index = "1", arity = "0..1", description = "The project name", paramLabel = "NAME")
	private String name;

	@Override
	public void run() {
		Templates tl = new Templates();

		if (type == null) {
			type = App.prompt("What type of project do you want to create? [java, react]: ");
		}

		if (name == null) {
			name = App.prompt("What should be the name of your project?: ");
		}

		String result = tl.createProj(type, name);

		if (result.equals("Success")) {
			System.out.println("\n✅ Project '" + name + "' created using template '" + type + "'.");
		} else {
			System.err.println("❌ Failed to create project.\nError: " + result);
		}
	}

}