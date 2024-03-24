package src.test.java.utilities.file_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import src.main.java.utilities.file_handling.TableDataFileHandler;

@Execution(ExecutionMode.CONCURRENT)
public class TableDataFileHandlerTest {
	
	
	private TableDataFileHandler fileHandler;
	private FilePaths testFilePath;
	@BeforeEach
	public void initializeCommonVariables() {
    	// Create TableDataFileHandler instance
		this.fileHandler = new TableDataFileHandler(FilePaths.getTestFilePath());
		this.testFilePath = new FilePaths();
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
    	String[] record = fileHandler.readFile("nonExistentValue", "searchColumn");
        assertNull(record);
    }

    @Test
    @Order(3)
    public void testWriteFile() {
        // Test writing a new record to the file
        try {
            // Perform write operation here
            // Add assertions to validate the result if needed
        } catch (Exception e) {
            fail("Exception occurred: " + e.getMessage());
        }
    }

    private List<String[]> readRecordsFromFile() throws IOException {
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FilePaths.getTestFilePath()))) {
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
