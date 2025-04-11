package tech.hriday;


import java.util.concurrent.Callable;

import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Parameters;

@Command(name = "nocfg", mixinStandardHelpOptions = true, version = "NOCFG 0.0.0", subcommands = { MakeCommand.class,
		TestCommand.class })
public class nocfg implements Callable<Integer> {
	@Override
	public Integer call() {
		System.out.println("NOCFG CLI — run `nocfg init/make/create <type> <name>` to get started.");
		return 0;
	}
}

@Command(name = "make", description = "Creates a new project from a stored template.", mixinStandardHelpOptions = true, aliases = {
		"create", "init" })
class MakeCommand implements Runnable {

	@Parameters(index = "0", arity = "0..1", description = "The template type (e.g., java, react)", paramLabel = "TYPE")
	private String type;

	@Parameters(index = "1", arity = "0..1", description = "The project name", paramLabel = "NAME")
	private String name;

	@Override
	// helper functions like promt as static in App
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

@Command(name = "test", aliases = {
		"t" }, sortOptions = false, header = "Test command.", description = "A test command to test stuff of picocli.")
class TestCommand implements Callable<Integer> {
	@Override
	public Integer call() {
		System.out.println(Ansi.ON.string("@|bg(green),bold,underline,red HAHA|@"));
		return 0;
	}
}
