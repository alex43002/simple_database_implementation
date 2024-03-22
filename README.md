# Java Project Setup and Javadoc Usage Guide

This guide provides instructions on setting up the Java project in Eclipse and using Javadoc to generate documentation.

## Project Setup in Eclipse

1. **Open Eclipse**: Launch Eclipse IDE on your system.

2. **Create a New Java Project**:
   - Go to `File > New > Java Project`.
   - Enter a project name and click `Finish`.

3. **Import Source Files**:
   - Copy your Java source files into the `src` directory of the newly created project.
   - If you already have source files organized in packages, maintain the package structure within the `src` directory.

4. **Set Java Version**:
   - Right-click on the project in the `Package Explorer`.
   - Go to `Properties > Java Build Path`.
   - In the `Libraries` tab, ensure that the JRE System Library is set to `JavaSE-1.8`.
   - If not, click `Edit...` and select `JRE System Library` with `JavaSE-1.8`.

5. **(Optional) Configure JUnit 5**:
   - If you're using JUnit 5 for testing, add the JUnit 5 library to your project's build path.

6. **Build the Project**:
   - Right-click on the project in the `Package Explorer`.
   - Select `Build Project`.

## Generating Javadoc Documentation

To generate Javadoc documentation for your project, follow these steps:

1. **Open Command Prompt or Terminal**:
   - Navigate to the root directory of your Eclipse project.

2. **Execute the Javadoc Command**:
   - Run the following command:
     ```
     javadoc -verbose -d documentation -subpackages src
     ```
   - This command generates Javadoc documentation for all packages and classes found within the `src` directory and its subdirectories.
   - Make sure you have JDK 8 installed on your system, as Javadoc is bundled with the JDK.

3. **View Generated Documentation**:
   - After executing the command, you'll find the generated documentation in the `documentation` directory within your project's root directory.

4. **Accessing the Documentation**:
   - Open the `index.html` file in any web browser to view the generated Javadoc documentation.
   - The documentation provides detailed information about classes, methods, fields, and other elements of your Java project.

## Additional Notes

- Ensure that your Java source files contain proper Javadoc comments to generate comprehensive documentation.
- Customize the Javadoc command options as needed based on your project's requirements.

That's it! You've successfully set up your Java project in Eclipse and generated Javadoc documentation.
