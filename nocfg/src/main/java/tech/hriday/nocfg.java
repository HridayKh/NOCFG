package tech.hriday;

import java.util.concurrent.Callable;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi;
import tech.hriday.cmd.Make;
import tech.hriday.templates.Template;

@Command(name = "nocfg", mixinStandardHelpOptions = true, version = "NOCFG Alpha", subcommands = { Make.class,
		TestCommand.class, Template.class })
public class nocfg implements Callable<Integer> {
	@Override
	public Integer call() {
		System.out.println("NOCFG CLI — run `nocfg init/make/create <type> <name>` to get started.");
		return 0;
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
