# Internship's project for JetBrains: To Do
##### Remark:
It wasn't mentioned in task about project type that's why I prefered to make a graphical shell

#### Starting from cmd:
1. Enter to the project root.
2. Select in cmd: mvn exec:java -Dexec.mainClass="com.jetbrains.steperev.App"
**Attention**: building produced using Maven.

#### How to add a task:
1. Push "Add tasks" button.
2. Enter the name of the task.
3. Press "Accept".

#### How to remove a task:
1. Push "Remove tasks" button.
2. Select tasks that should be deleted.
3. Press "Accept".

#### How to filter tasks:
1. Press "Filter tasks" button.
2. All selected tasks will be removed.

#### How to load tasks:
1. Press "Load tasks" button.
2. Enter your file name.
**Attention**: your file should be stored in folder: **loadFiles**.
You should input **only name** of the file (e.g. your file 'test1.json', enter 'test1')