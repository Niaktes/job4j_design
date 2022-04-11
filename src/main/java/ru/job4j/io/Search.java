package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;

public class Search {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			throw new IllegalArgumentException("Root folder or file extension was not specified. Usage java -jar search.jar ROOT_FOLDER FILE_EXTENSION");
		}
		Path path = Path.of(args[0]);
		String extension = args[1];
		argumentsValidation(path, extension);
		search(path, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
	}
	
	public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
		SearchFiles searcher = new SearchFiles(condition);
		Files.walkFileTree(root, searcher);
		return searcher.getPaths();
	}
	
	private static void argumentsValidation(Path path, String extension) {
		if (!path.toFile().isDirectory()) {
			throw new IllegalArgumentException("Invalid root folder. Please check first entered argument.");
		}
		if (!extension.startsWith(".")) {
			throw new IllegalArgumentException("Invalid extension. Second entered argument need to be started with \".\"-symbol.");
		}
	}

}
