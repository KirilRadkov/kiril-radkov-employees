
### Summary

This application can identify pairs of employees who have worked together on common projects for the longest period of time (based on CSV file). 

### How to build and run the service
To build this Maven project via the command line, you need to use the **mvn** command.The command must be executed in the directory which contains the relevant pom file

```
mvn clean install 
```

### How to test the service 

1. Start the application vie command line

```
mvn spring-boot:run
```
2. Open your browser and go to http://localhost:8080/csv
3. Click on "Choose file" button and select **employees-csv-for-test** file.
4. After uploading the file with the button "Upload" you will see a new page where all common
   projects of the pairs are displayed in datagrid with the following columns:
   Employee ID #1, Employee ID #2, Project ID, Days worked


