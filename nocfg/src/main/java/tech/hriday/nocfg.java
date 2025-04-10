package tech.hriday;

import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Help.Ansi;

import java.util.concurrent.Callable;

@Command(name = "nocfg", mixinStandardHelpOptions = true, version = "NOCFG 0.0.0", subcommands = { MakeCommand.class,
		TestCommand.class })
public class nocfg implements Callable<Integer> {
	@Override
	public Integer call() {
		System.out.println("NOCFG CLI — run `nocfg make <type> <name>` to get started.");
		return 0;
	}
}

@Command(name = "make", description = "Create a new project", mixinStandardHelpOptions = true)
class MakeCommand implements Callable<Integer> {
	@Parameters(index = "0", description = "Project type (e.g., react, java, worker)")
	private String type;
	@Parameters(index = "1", description = "Project name")
	private String name;
	@Override
	public Integer call() {
		System.out.printf("Project Type:  %s%nProject Named: %s%n", type, name);
		return 0;
	}
}

@Command(name = "test",aliases = {"t"} , sortOptions = false, header = "Test command.", description = "A test command to test stuff of picocli.")
class TestCommand implements Callable<Integer> {

	@Override
	public Integer call() {
		System.out.println(Ansi.ON.string("@|bg(green),bold,underline,red HAHA|@"));
		return 0;
	}
}
