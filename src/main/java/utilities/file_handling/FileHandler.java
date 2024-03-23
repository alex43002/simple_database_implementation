package src.main.java.utilities.file_handling;

public interface FileHandler
{
	String[] readFile(String searchValue, String searchColumn) throws Exception;
	boolean writeFile(String record) throws Exception;
    void deleteFile(String searchValue, String searchColumn);
    void createFile(String tableName);
}
