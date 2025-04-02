This project is my solution for the task for project **Rider Project Dependency Diagram**.

### Description
This is a GUI application built using the **Swing** library and following the **MVC** pattern. 
The **Observer** pattern has been implemented to ensure the UI is **automatically updated** when the model
changes. The **Command** pattern has been implemented to handle different types of notifications, enabling
flexible actions for different graph events. Additionally, the **Strategy** pattern has been implemented to
allow the use of various graph types.

Methods from the model are executed in a **separate thread using SwingWorker**, ensuring the UI remains
responsive and **doesn't freeze**, while updates to the UI are performed on the **Event Dispatch Thread (EDT)**.

Unit tests have been implemented to validate the core logic of the application.

### Visual presentation
![graph](https://github.com/user-attachments/assets/88ffce2c-ff94-432c-8ef7-6092021dbde4)
