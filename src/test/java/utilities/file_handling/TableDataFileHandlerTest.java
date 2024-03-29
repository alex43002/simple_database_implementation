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
/**
 * Unit tests for {@link TableDataFileHandler}.
 */
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
    
    /**
     * Tests the reading of records from a file.
     * 
     * <p>Complexity: O((n^2)*m)</p>
     * 
     * @return Stream<DynamicTest>
     * @throws Exception if an error occurs during the test
     */
    @TestFactory
    public Stream<DynamicTest> testReadFile() throws Exception {
    	String testReadFile = "testReadFile.dat";
        List<String[]> records = readRecordsFromFile();
        return records.stream().map(record -> DynamicTest.dynamicTest(
                "Read record: " + record[0],
                () -> {
                    String[] fileRecord = fileHandler.searchFileForValue(record[0], "ID", Optional.ofNullable(TestFilePaths.getResourceFilePath(testReadFile)));
                    assertNotNull(fileRecord, "Record should not be null");
                    assertEquals(record[1], fileRecord[1], "Name should match");
                    assertEquals(record[2], fileRecord[2], "Age should match");
                    assertEquals(record[3], fileRecord[3], "Email should match");
                }));
    }
    
    /**
     * Tests the case where reading a file fails to find the specified record.
     * 
     * @throws Exception if an error occurs during the test
     */
    @Test
    @Order(2)
    public void testFailReadFile() throws Exception {
    	String testReadFile = "testReadFile.dat";
        String[] record1 = fileHandler.searchFileForValue("nonExistentValue", "searchColumn", Optional.ofNullable(TestFilePaths.getResourceFilePath(testReadFile)));
        String[] record2 = fileHandler.searchFileForValue("nonExistentValue", "ID", Optional.ofNullable(TestFilePaths.getResourceFilePath(testReadFile)));
        assertNull(record1);
        assertNull(record2);
    }

    /**
     * Tests writing a record to a file.
     * 
     * @throws Exception if an error occurs during the test
     */
    @Test
    @Order(3)
    public void testWriteFile() throws Exception {
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
    
    /**
     * 
     * @throws Exception
     */
    @Test
    @Order(4)
    public void speedReadTest() throws Exception {
    	
    }

    /**
     * Reads records from the specified file.
     * 
     * <p>Complexity: O(n)</p>
     * 
     * @return List<String[]> a list of records read from the file
     * @throws IOException if an I/O error occurs during file reading
     */
    private List<String[]> readRecordsFromFile() throws IOException {
    	String testReadFile = "testReadFile.dat";
        List<String[]> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(TestFilePaths.getResourceFilePath(testReadFile)))) {
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