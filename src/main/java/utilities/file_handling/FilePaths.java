package src.main.java.utilities.file_handling;

public class FilePaths
{
	// Define constants for file paths
    public static final String FILE_PATH = "file.dat";
    public static final String RESOURCES_DIRECTORY = "src/main/resources";

    /**
     * <p>Empty Constructor</p>
     */
    public FilePaths() {}
    
    // Static method to get the full path of a file in the resources directory
    public static String getTestFilePath() {
        return RESOURCES_DIRECTORY + "/" + FILE_PATH;
    }
    
    // Static method to get the full path of a file in the resources directory
    public static String getResourceFilePath(String fileName) {
        return RESOURCES_DIRECTORY + "/" + fileName;
    }
}
