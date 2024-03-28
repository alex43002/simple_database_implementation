package src.main.java.utilities.file_handling;

import java.util.Optional;

public interface FileHandler
{
	String[] searchFileForValue(String searchValue, String searchColumn) throws Exception;
	boolean writeFile(String record, Optional<String> filePath) throws Exception;
    void deleteFile(String searchValue, String searchColumn);
    void createFile(String tableName);
}
