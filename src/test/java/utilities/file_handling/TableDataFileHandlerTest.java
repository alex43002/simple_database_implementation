package src.test.java.utilities.file_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import src.main.java.utilities.file_handling.TableDataFileHandler;

@Execution(ExecutionMode.SAME_THREAD)
public class TableDataFileHandlerTest {
	
	
	private TableDataFileHandler fileHandler;
	private TestFilePaths testFilePath;
	
	@BeforeEach
	public void initializeCommonVariables() {
    	// Create TableDataFileHandler instance
		this.fileHandler = new TableDataFileHandler(TestFilePaths.getTestFilePath());
		this.testFilePath = new TestFilePaths();
	}
	
	@TestFactory
	public Stream<DynamicTest> testReadFile() throws Exception {
        List<String[]> records = readRecordsFromFile();
        return records.stream().map(record -> DynamicTest.dynamicTest(
                "Read record: " + record[0],
                () -> {
                	String[] fileRecord = fileHandler.readFile(record[0], "ID");
                    assertNotNull(fileRecord, "Record should not be null");
                    assertEquals(record[1], fileRecord[1], "Name should match");
                    assertEquals(record[2], fileRecord[2], "Age should match");
                    assertEquals(record[3], fileRecord[3], "Email should match");
                }));
    }
    
    @Test
    @Order(2)
    public void testFailReadFile() throws Exception {
    	String[] record1 = fileHandler.readFile("nonExistentValue", "searchColumn");
    	String[] record2 = fileHandler.readFile("nonExistentValue", "ID");
        assertNull(record1);
        assertNull(record2);
    }

    @Test
    @Order(3)
    public void
    testWriteFile() throws Exception{
    	String testWriteFile = "testWriteFile.dat";
        String recordToWrite = "NewRecord,NewName,30,new@example.com";
        // Write a new record to the file
        fileHandler.writeFile(recordToWrite, Optional.ofNullable(TestFilePaths.getResourceFilePath(testWriteFile)));

        // Read the file to verify if the record was written
        try (BufferedReader reader = new BufferedReader(new FileReader(TestFilePaths.getResourceFilePath(testWriteFile)))) {
        	String line;
            boolean recordFound = false;
            while ((line = reader.readLine()) != null) {
                if (line.equals(recordToWrite)) {
                    recordFound = true;
                    break;
                }
            }
            assertTrue(recordFound, "Record should have been written to the file");
        }
    }

    private List<String[]> readRecordsFromFile() throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TestFilePaths.getTestFilePath()))) {
            String line;
            // Skip the header line
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                records.add(parts);
            }
        }
        return records;
    }
}
