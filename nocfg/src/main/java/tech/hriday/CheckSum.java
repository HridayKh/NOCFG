package tech.hriday;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.math.BigInteger;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.concurrent.Callable;

@Command(name = "checksum", mixinStandardHelpOptions = true, version = "checksum 4.0", description = "Prints the checksum (SHA-256 by default) of a file to STDOUT.")
public class CheckSum implements Callable<Integer> {

	@Parameters(index = "0", description = "The file whose checksum to calculate.", defaultValue = "hello.txt")
	private File file;

	@Option(names = { "-a", "--algorithm" }, description = "MD5, SHA-1, SHA-256, ...")
	private String algorithm = "SHA-256";

	@Option(names = { "-a2", "--aTwo" }, description = "MD5, SHA-1, SHA-256, ...")
	private String a2 = "SHA-256";

	@Override
	public Integer call() throws Exception {
		byte[] fileContents = Files.readAllBytes(file.toPath());
		byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
		System.out.printf("%0" + (digest.length * 2) + "x%n", new BigInteger(1, digest));
		System.out.println(a2);
		return 0;
	}

}