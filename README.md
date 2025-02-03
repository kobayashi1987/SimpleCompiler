# SimpleCompiler

Use the following command to generate the Java classes (including a visitor):

```bash
java -jar lib/antlr-4.13.2-complete.jar -visitor -no-listener -o src/main/java Expr.g4
```

// Expr.g4 is in the root directory of the project
// -o specifies the output directory for the generated files

To compile the project, use the following command:

```bash
# For macOS/Linux:
javac -cp ".:lib/antlr-4.13.2-complete.jar" -d out src/main/java/*.java
```

To run the project, use the following command:

```bash
# For macOS/Linux:
java -cp "out:lib/antlr-4.13.2-complete.jar" main.java.Main

```