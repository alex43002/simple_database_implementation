package src.test.java.utilities.file_handling;

public class TestFilePaths
{
	// Define constants for file paths
    public static final String TEST_FILE_PATH = "testfile.dat";
    public static final String RESOURCES_DIRECTORY = "src/test/resources";

    /**
     * <p>Empty Constructor</p>
     */
    public TestFilePaths() {}
    
    // Static method to get the full path of a file in the resources directory
    public static String getTestFilePath() {
        return RESOURCES_DIRECTORY + "/" + TEST_FILE_PATH;
    }
    
    // Static method to get the full path of a file in the resources directory
    public static String getResourceFilePath(String fileName) {
        return RESOURCES_DIRECTORY + "/" + fileName;
    }
}
