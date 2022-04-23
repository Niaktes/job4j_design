package ru.job4j.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CSVReaderTest {
	
	@TempDir
	File tempFolder;

	@Test
	void whenFilterTwoColumns() throws Exception {
		String data = String.join(
				System.lineSeparator(),
				"name;age;last_name;education",
				"Tom;20;Smith;Bachelor",
				"Jack;25;Johnson;Undergraduate",
				"William;30;Brown;Secondary special"
		);
		File file = new File(tempFolder, "source.csv");
		File target = new File(tempFolder, "target.csv");
		ArgsName argsName = ArgsName.of(new String[]{
				"-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
		});
		Files.writeString(file.toPath(), data);
		String expected = String.join(
				System.lineSeparator(),
				"name;age",
				"Tom;20",
				"Jack;25",
				"William;30"
		).concat(System.lineSeparator());
		CSVReader.handle(argsName);
		assertEquals(expected, Files.readString(target.toPath()));
	}

}
