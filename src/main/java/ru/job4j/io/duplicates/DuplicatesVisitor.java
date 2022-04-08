package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
	
	private Map<FileProperty, List<Path>> files = new HashMap<>();
	
	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		if (file.toFile().isFile()) {
			long fileSize = file.toFile().length();
			String fileName = file.getFileName().toString();
			FileProperty property = new FileProperty(fileSize, fileName);
			files.computeIfAbsent(property, k -> new ArrayList<Path>());
			files.get(property).add(file.toAbsolutePath());
		}
		return super.visitFile(file, attrs);
	}
	
	public List<Path> getDuplicates() {
		List<Path> duplicates = new ArrayList<>();
		for (List<Path> value : files.values()) {
			if (value.size() > 1) {
				value.forEach(duplicates::add);
			}
		}
		return duplicates;
	}

}
