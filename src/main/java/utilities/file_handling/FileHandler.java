package src.main.java.utilities.file_handling;

import java.util.ArrayList;
import java.util.Optional;

public interface FileHandler
{
	ArrayList<String> readEntireFile(Optional<String> filePath);
	String[] searchFileForValue(String searchValue, String searchColumn, Optional<String> filePath) throws Exception;
	boolean writeFile(String record, Optional<String> filePath) throws Exception;
    void deleteFile(String searchValue, String searchColumn);
    void createFile(String tableName);
}
