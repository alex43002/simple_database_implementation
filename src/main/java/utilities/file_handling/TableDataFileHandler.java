package src.main.java.utilities.file_handling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

public class TableDataFileHandler implements FileHandler
{

	private String filePath;

	/**
	 * Used to initialize base filepath
	 * @param filePath
	 */
    public TableDataFileHandler(String filePath) {
        this.filePath = filePath;
    }
	
    /**
     * <p>Reads the specified file and returns the output</p>
     * <p>O(n)</p>
     */
    @Override
    public String[] searchFileForValue(String searchValue, String searchColumn, Optional<String> filePath) throws IOException {
    	int searchColumnIndex = -1;
        boolean firstLineSkipped = false;
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.orElse(this.filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!firstLineSkipped) {
                    // Skip the first line (header)
                    firstLineSkipped = true;
                    // Find the index of the searchColumn in the header
                    String[] headerColumns = line.split(",");
                    for (int i = 0; i < headerColumns.length; i++) {
                        if (headerColumns[i].equals(searchColumn)) {
                            searchColumnIndex = i;
                            break;
                        }
                    }
                    // If searchColumn is not found in the header, return null
                    if (searchColumnIndex == -1) {
                        System.out.println("Search column not found.");
                        return null;
                    }
                    continue;
                }
                String[] parts = line.split(",");
                if (parts[searchColumnIndex].equals(searchValue)) {
                    System.out.println("Record found: " + line);
                    return parts;
                }
            }
            System.out.println("Record not found.");
        }
        return null;
    }

	/**
	 * Write new record to file
	 */
	@Override
	public boolean writeFile(String record, Optional<String> filePath) throws Exception
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.orElse(this.filePath), true))) {
	        writer.write(record);
	        writer.newLine();
	        return true;
	    }
	}

	/**
	 * Delete record in file based on criteria
	 */
	@Override
	public void deleteFile(String searchValue, String searchColumn)
	{
		
	}

	/**
	 * Create new table
	 */
	@Override
	public void createFile(String tableName)
	{
		
	}

	@Override
	public ArrayList<String> readEntireFile(Optional<String> filePath)
	{
		return null;
	}

}
