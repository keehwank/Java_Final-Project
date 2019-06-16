import java.io.*;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.scene.Scene;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;


public class FinalProject extends Application
{
	private static MenuBar menuBar;
	private static Menu studentMenu, courseMenu, enrollmentMenu, gradeMgtMenu, reportMenu;
	private static MenuItem searchStudent, addStudent, searchCourse, addCourse, searchEnroll, addEnroll, manageGrade, generateReport;
	private static BorderPane borderPane;
	private static Scene mainScene;
	private static Stage primaryStage, secondaryStage;
	private static TextField stIdSearchText, studentIdText, studentNameText, firstNameText, lastNameText, birthdayText, addressText;
	private static TextField csIdSearchText, courseIdText, courseNameText;
	private static ComboBox<String> instructorComboBox, locationComboBox;
	
	public static void main(String[] args)
	{
		launch(args);
	}
		
	public void start(Stage primaryStage)
	{
		menuBar = new MenuBar();
		studentMenu = new Menu("Student Info");
		studentMenu.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				
		courseMenu = new Menu("Course");
		courseMenu.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		
		enrollmentMenu = new Menu("Enrollment");
		enrollmentMenu.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		
		gradeMgtMenu = new Menu("Grade Management");
		gradeMgtMenu.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		
		reportMenu = new Menu("Report");
		reportMenu.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		
		searchStudent = new MenuItem("Search");
		searchStudent.setOnAction(event ->
		{
			try 
			{
				searchStudent(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		addStudent = new MenuItem("Add");
		addStudent.setOnAction(event -> 
		{
			try 
			{
				createStudent(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		searchCourse = new MenuItem("Search");
		searchCourse.setOnAction(event ->    
		{
			try 
			{
				searchCourse(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		addCourse = new MenuItem("Add");
		addCourse.setOnAction(event -> 
		{
			try 
			{
				createCourse(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		searchEnroll = new MenuItem("Search");
		searchEnroll.setOnAction(event -> 
		{
			try 
			{
				searchEnrollment(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		addEnroll = new MenuItem("Add");
		addEnroll.setOnAction(event -> 
		{
			try 
			{
				createEnrollment(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		manageGrade = new MenuItem("Manage Grade");
		manageGrade.setOnAction(event -> 
		{
			try 
			{
				manageGrade(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		generateReport = new MenuItem("Generate Report");
		generateReport.setOnAction(evetn -> 
		{
			try 
			{
				generateReport(primaryStage);
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		});
		
		studentMenu.getItems().add(searchStudent);
		studentMenu.getItems().add(addStudent);
		courseMenu.getItems().add(searchCourse);
		courseMenu.getItems().add(addCourse);
		enrollmentMenu.getItems().add(searchEnroll);
		enrollmentMenu.getItems().add(addEnroll);
		gradeMgtMenu.getItems().add(manageGrade);
		reportMenu.getItems().add(generateReport);
		
		menuBar.getMenus().addAll(studentMenu, courseMenu, enrollmentMenu, gradeMgtMenu, reportMenu);
		menuBar.setStyle("-fx-background-color: #C3D3D3");
		
		borderPane = new BorderPane();
		borderPane.setStyle("-fx-background-color: #D8D8D8");
		borderPane.setTop(menuBar);
		
		mainScene = new Scene(borderPane, 650, 260);
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Student Information System");
		
		primaryStage.show();
	}
	
	public static class Student
	{
		private String stId, firstName, lastName, birthday, address;
		
		public Student(String stdId, String fName, String lName, String bDay, String addr)
		{
			stId = stdId;
			firstName = fName;
			lastName = lName;
			birthday = bDay;
			address = addr;
		}
		
		public void setStudentId(String stdId)
		{
			stId = stdId;
		}
		
		public void setFirstName(String fName)
		{
			firstName = fName;
		}
		
		public void setLastName(String lName)
		{
			lastName = lName;
		}
		
		public void setBirthday(String bDay)
		{
			birthday = bDay;
		}
		
		public void setAddress(String addressSt)
		{
			address = addressSt;
		}
		
		public String getStId()
		{
			return stId;
		}
				
		public String getFirstName()
		{
			return firstName;
		}
		
		public String getLastName()
		{
			return lastName;
		}
		
		public String getBirthday()
		{
			return birthday;
		}
		
		
		public String getAddress()
		{
			return address;
		}
	}
	
	public static void stringWrite(RandomAccessFile raf, String str, int fieldLength) throws IOException
	{
		if (str.length() > fieldLength)
		{
			for (int i=0; i < fieldLength; i++)
			{
				raf.writeChar(str.charAt(i));
			}
		}
		else
			raf.writeChars(str);
		
		for (int i=0; i < (fieldLength - str.length()); i++)
			raf.writeChar(' ');
	}
	
	public static String stringRead(RandomAccessFile raf, int fieldLength) throws IOException
	{
		char[] charArray = new char[fieldLength];
		for (int i=0; i < fieldLength; i++)
			charArray[i] = raf.readChar();
		String str = new String(charArray);
		str.trim();
		return str;
	}
	
	
	public static class StudentFile
	{
		private final int RECORD_SIZE = 150;
		private RandomAccessFile studentFile;
		
		public StudentFile(String filename) throws FileNotFoundException
		{
			studentFile = new RandomAccessFile(filename, "rw");
		}
		
		public void writeStudentInfo(Student stInfo) throws IOException
		{
			stringWrite(studentFile, stInfo.getStId(), 5);
			stringWrite(studentFile, stInfo.getFirstName(), 15);
			stringWrite(studentFile, stInfo.getLastName(), 15);
			stringWrite(studentFile, stInfo.getBirthday(), 10);
			stringWrite(studentFile, stInfo.getAddress(), 30);
		}
	
		public Student readStudentInfo() throws IOException
		{
			String stId = stringRead(studentFile, 5);
			String firstName = stringRead(studentFile, 15);
			String lastName = stringRead(studentFile, 15);
			String birthday = stringRead(studentFile, 10);
			String address = stringRead(studentFile, 30);
			
			Student st = new Student(stId, firstName, lastName, birthday, address);
			return st;
		}
		
		private long getByteNum(long recordNum)
		{
			return RECORD_SIZE * recordNum;
		}
		
		public void moveFilePointer(long recordNum) throws IOException
		{
			studentFile.seek(getByteNum(recordNum));
		}
		
		public int getNumberOfRecords() throws IOException
		{
			return (int)(studentFile.length() / RECORD_SIZE);
		}
		
		public void close() throws IOException
		{
			studentFile.close();
		}
	}
	
	public static String spaceRemoveBeforeName(String name)
	{
		int index = 0, firstIndex = 0;
		char[] charArray = new char[name.length()];
		
		for (int i = 0; i < name.length(); i++)
		{
			if (name.charAt(i) != ' ')
			{
				firstIndex = i;
				break;
			}
		}
		for (int i = firstIndex; i < name.length(); i++)
		{
			charArray[index] = name.charAt(i);
			index++;
		}
		String nameCheck = new String(charArray);
		
		return nameCheck;
	}
		
	public static void createStudent(Stage primaryStage) throws IOException
	{
		Label firstNameLabel = new Label("First Name(*Required)");
		firstNameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		firstNameLabel.setMinWidth(280);
		firstNameText = new TextField();
				
		Label lastNameLabel = new Label("Last Name(*Required)");
		lastNameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		lastNameLabel.setMinWidth(280);
		lastNameText = new TextField();
		
		Label birthdayLabel = new Label("Birthday(*Required)");
		birthdayLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		birthdayLabel.setMinWidth(280);
		birthdayText = new TextField();
		
		Label addressLabel = new Label("Address");
		addressLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		addressLabel.setMinWidth(280);
		addressText = new TextField();
		
		Button addButton = new Button("Add");
		addButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		addButton.setMinWidth(280);
		Button resetButton = new Button("Reset");
		resetButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		resetButton.setMinWidth(280);
		Button okStudentAddButton = new Button();
		
		firstNameText.setMinWidth(280);
		firstNameText.setMinHeight(30);
		lastNameText.setMinWidth(280);
		lastNameText.setMinHeight(30);
		birthdayText.setMinWidth(280);
		birthdayText.setMinHeight(30);
		addressText.setMinWidth(280);
		addressText.setMinHeight(30);
		
		HBox fnHbox = new HBox(25, firstNameLabel, firstNameText);
		HBox lnHbox = new HBox(25, lastNameLabel, lastNameText);
		HBox birthdayHbox = new HBox(25, birthdayLabel, birthdayText);
		HBox addressHbox = new HBox(25, addressLabel, addressText);
		HBox buttonHbox = new HBox(25, addButton, resetButton);
		
		VBox vbox = new VBox(15, fnHbox, lnHbox, birthdayHbox, addressHbox, buttonHbox);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(10));
		borderPane.setCenter(vbox);
		
		addButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
	    	{
				final int NUMBER_STUDENT = 100;
	    		int lastRecordNumber = 0;
				Student[] student = new Student[NUMBER_STUDENT];
				
				StudentFile stdFile = null;
				try 
				{
					stdFile = new StudentFile("student.dat");
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
				try 
				{
					lastRecordNumber = stdFile.getNumberOfRecords();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				String stId = String.format("%05d", lastRecordNumber+1);
				
				String firstName = spaceRemoveBeforeName(firstNameText.getText());
				String lastName = spaceRemoveBeforeName(lastNameText.getText());
				
				student[lastRecordNumber] = new Student(stId, firstName, lastName, birthdayText.getText(), addressText.getText());
				try 
				{
					stdFile.moveFilePointer(lastRecordNumber);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				try 
				{
					stdFile.writeStudentInfo(student[lastRecordNumber]);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				Label messageIdAdded1 = new Label("Student Data has beed added for Student ID ");
				messageIdAdded1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				Label messageIdAdded2 = new Label(stId);
				messageIdAdded2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				HBox hboxMessage = new HBox(5, messageIdAdded1, messageIdAdded2);
				hboxMessage.setAlignment(Pos.CENTER);
				okStudentAddButton.setText("OK");
				okStudentAddButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold");
				okStudentAddButton.setMinWidth(60);
				VBox vbox = new VBox(20, hboxMessage, okStudentAddButton);
				vbox.setAlignment(Pos.CENTER);
								
				Scene sceneAddStudent = new Scene(vbox, 450, 100);
				sceneAddStudent.setFill(Color.CADETBLUE);
				
				secondaryStage = new Stage(StageStyle.UTILITY);
			    secondaryStage.setScene(sceneAddStudent);
			    secondaryStage.setTitle("Message");

			    secondaryStage.setX(800); secondaryStage.setY(600);
			    secondaryStage.show();
    			
				try 
				{
					stdFile.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
	    	}
		});
		
		resetButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				firstNameText.setText("");
	    		lastNameText.setText("");
	    		birthdayText.setText("");
	    		addressText.setText("");
			}
		});
		
		okStudentAddButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				
				firstNameText.setText("");
	    		lastNameText.setText("");
	    		birthdayText.setText("");
	    		addressText.setText("");
			}
		});
	}	
		
	public static void searchStudent(Stage primaryStage) throws IOException
	{
		Label enterLabel = new Label("Enter Student ID");
		enterLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		stIdSearchText = new TextField();
		studentNameText = new TextField();
		birthdayText = new TextField();
		addressText = new TextField();
		Button searchButton = new Button("Search");
		searchButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		searchButton.setMinWidth(60);
		Button okButton = new Button("OK");
		Button okErrorButton = new Button("OK");
		Button editButton = new Button("Edit");
		Button updateButton = new Button("Update");
		Button cancelUpdateButton = new Button("Cancel Update");
		
		Label idLabel = new Label("Student ID");
		Label nameLabel = new Label("Name");
		Label birthdayLabel = new Label("Birthday");
		Label addressLabel = new Label("Address");
		
		HBox searchHbox = new HBox(10, enterLabel, stIdSearchText, searchButton);
		searchHbox.setAlignment(Pos.CENTER);
				
		borderPane.setCenter(searchHbox);
		borderPane.setBottom(null);
		borderPane.setLeft(null);
		borderPane.setRight(null);
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Student Information System");
		primaryStage.show();
		
		searchButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int numberOfRecords = 0;
				studentIdText = new TextField();
				String studentId = stIdSearchText.getText();
				Student student = null;
				StudentFile stFile = null;
								 
				try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfRecords = stFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	    		if (studentId.length() == 5 && Integer.parseInt(studentId) <= numberOfRecords)
	    		{
	    			try {
	    				try {
	    					stFile.moveFilePointer(Integer.parseInt(studentId)-1);
	    				} catch (IOException e) {
	    					e.printStackTrace();
	    				}
	    			} catch (NumberFormatException e) {
	    				e.printStackTrace();
	    			} 
	        		try {
	    				student = stFile.readStudentInfo();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	        		    
	        		idLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		nameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		birthdayLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		addressLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		
	        		idLabel.setMinWidth(290);
	        		nameLabel.setMinWidth(290);
	        		birthdayLabel.setMinWidth(290);
	        		addressLabel.setMinWidth(290);

	        		studentIdText.setText(student.getStId());
	        		studentNameText.setText(student.getFirstName().trim() + "  " + student.getLastName());
	        		birthdayText.setText(student.getBirthday());
	        		addressText.setText(student.getAddress());
	        		
	        		studentIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		studentNameText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		birthdayText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		addressText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		editButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
	        		
	        		studentIdText.setMinWidth(290);
	        		studentNameText.setMinWidth(290);
	        		birthdayText.setMinWidth(290);
	        		addressText.setMinWidth(290);
	        		editButton.setMinWidth(290);
	        			        		
	        		HBox idHbox = new HBox(idLabel, studentIdText);
	        		HBox nameHbox = new HBox(nameLabel, studentNameText);
	        		HBox birthdayHbox = new HBox(birthdayLabel, birthdayText);
	        		HBox addressHbox = new HBox(addressLabel, addressText);
	        		HBox buttonHbox = new HBox(editButton);
	        			        		
	        		VBox vbox = new VBox(idHbox, nameHbox, birthdayHbox, addressHbox, buttonHbox);
	        		vbox.setAlignment(Pos.CENTER);
	        		vbox.setPadding(new Insets(10));
	        		borderPane.setBottom(vbox);
	        	}
	    		
	    		if (studentId.length() == 5 && Integer.parseInt(studentId) > numberOfRecords)
	        	{
        			Label errorInputLabel1 = new Label("This sutdent ID does not exist: ");
        			Label errorInputLabel2 = new Label(studentId);
        			Label errorInputLabel3 = new Label(". Maximum student ID is ");
        			Label errorInputLabel4 = new Label(Integer.toString(numberOfRecords));
        			
        			errorInputLabel1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel3.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel4.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			okErrorButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        			okErrorButton.setMinWidth(60);
        			
        			HBox errorInputHbox = new HBox(errorInputLabel1, errorInputLabel2, errorInputLabel3, errorInputLabel4);
        			errorInputHbox.setAlignment(Pos.CENTER);
        			VBox errorInputVbox = new VBox(20, errorInputHbox, okErrorButton);
        			errorInputVbox.setAlignment(Pos.CENTER);
        			
        			Scene sceneErr1 = new Scene(errorInputVbox, 550, 100);
        			sceneErr1.setFill(Color.CADETBLUE);
    				
    				secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr1);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(searchHbox);
	        	}
	    		
	    		try {
	    			if (studentId.length() != 5 ) throw new InputError();
	    		} catch(InputError e) {
	    			Label errorInputLabel = new Label(e.getMessage());
	    			errorInputLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	    			okErrorButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	    			okErrorButton.setMinWidth(60);
	    			VBox errorInputVbox = new VBox(20, errorInputLabel, okErrorButton);
	    			errorInputVbox.setAlignment(Pos.CENTER);
	    			
	    			Scene sceneErr2 = new Scene(errorInputVbox, 450, 100);
	    			sceneErr2.setFill(Color.CADETBLUE);
					
					secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr2);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(searchHbox);
	    		}
			}
	    });
		
		okErrorButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				stIdSearchText.setText("");
				secondaryStage.close();
			}
		});
		
		editButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int numberOfRecords = 0;
				String studentId = stIdSearchText.getText();
				Student student = null;
				StudentFile stFile = null;
				 
				try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfRecords = stFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	    		if (studentId.length() == 5 && Integer.parseInt(studentId) <= numberOfRecords)
	    		{
	    			try {
	    				try {
	    					stFile.moveFilePointer(Integer.parseInt(studentId)-1);
	    				} catch (IOException e) {
	    					e.printStackTrace();
	    				}
	    			} catch (NumberFormatException e) {
	    				e.printStackTrace();
	    			} 
	        		try {
	    				student = stFile.readStudentInfo();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	        		
	        		idLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		nameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		birthdayLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		addressLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
	        		
	        		idLabel.setMinWidth(290);
	        		nameLabel.setMinWidth(290);
	        		birthdayLabel.setMinWidth(290);
	        		addressLabel.setMinWidth(290);
	        
	        		
	        		int index = 0, firstIndex = 0;
					String firstNameString = student.getFirstName();
					String lastNameString = student.getLastName();
					char[] charArray = new char[firstNameString.length()];
					char[] charArray2 = new char[lastNameString.length()];
					
					for (int i = 0; i < firstNameString.length(); i++)
					{
						if (firstNameString.charAt(i) != ' ')
						{
							firstIndex = i;
							break;
						}
					}
					for (int i = firstIndex; i < firstNameString.length(); i++)
					{
						if (firstNameString.charAt(i) ==  ' ')
						{
							break;
						}
						else
						{
							charArray[index] = firstNameString.charAt(i);
							index++;
						}
					}
					String firstName = new String(charArray);
	        		
					index = 0;
					for (int i = 0; i < lastNameString.length(); i++)
					{
						if (lastNameString.charAt(i) != ' ')
						{
							firstIndex = i;
							break;
						}
					}
					for (int i = firstIndex; i < lastNameString.length(); i++)
					{
						charArray2[index] = lastNameString.charAt(i);
						index++;
					}
					String lastName = new String(charArray2);
	        		
	        		
					studentIdText.setText(student.getStId());
	        		studentNameText.setText(firstName + "  " + lastName);
	        		birthdayText.setText(student.getBirthday());
	        		addressText.setText(student.getAddress());
	        		
	        		studentIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		studentNameText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		birthdayText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		addressText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		updateButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	        		cancelUpdateButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	        		
	        		studentIdText.setMinWidth(290);
	        		studentNameText.setMinWidth(290);
	        		birthdayText.setMinWidth(290);
	        		addressText.setMinWidth(290);
	        		updateButton.setMinWidth(290);
	        		cancelUpdateButton.setMinWidth(290);
	        		
	        		studentIdText.setMinHeight(40);
	        		studentNameText.setMinHeight(40);
	        		birthdayText.setMinHeight(40);
	        		addressText.setMinHeight(40);
	        		updateButton.setMinHeight(40);
	        		cancelUpdateButton.setMinHeight(40);
	        		
	        		HBox idHbox = new HBox(5, idLabel, studentIdText);
	        		HBox nameHbox = new HBox(5, nameLabel, studentNameText);
	        		HBox birthdayHbox = new HBox(5, birthdayLabel, birthdayText);
	        		HBox addressHbox = new HBox(5, addressLabel, addressText);
	        		HBox buttonHbox = new HBox(5, updateButton, cancelUpdateButton);
	        			        		
	        		VBox vboxEdit = new VBox(idHbox, nameHbox, birthdayHbox, addressHbox, buttonHbox);
	        		vboxEdit.setAlignment(Pos.CENTER);
	        		vboxEdit.setPadding(new Insets(10));
	        		
	        		borderPane.setCenter(vboxEdit);
	        		borderPane.setBottom(null);
	    		}
			} 
		});
		
		updateButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				final String recordNumber = studentIdText.getText();
				Student student = null;
				StudentFile stFile = null;
				
				try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					student = stFile.readStudentInfo();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				int index = 0, firstIndex = 0, secondIndex = 0, thirdIndex = 0;
				String firstName = null, lastName = null;
				
				String nameString = studentNameText.getText();
				char[] charArray = new char[nameString.length()];
				
				for (int i = 0; i < nameString.length(); i++)
				{
					if (nameString.charAt(i) != ' ')
					{
						firstIndex = i;
						break;
					}
				}
				for (int i = firstIndex; i < nameString.length()-(firstIndex-1); i++)
				{
					if (nameString.charAt(i) ==  ' ')
					{
						secondIndex = i;
						break;
					}
					else
					{
						charArray[index] = nameString.charAt(i);
						index++;
					}
				}
				firstName = new String(charArray);
				
				index = 0;
				char[] charArray2 = new char[nameString.length()];
				
				for (int i = secondIndex; i < nameString.length(); i++)
				{
					if (nameString.charAt(i) != ' ')
					{
						thirdIndex = i;
						break;
					}
				}
				for (int i = thirdIndex ; i < nameString.length(); i++)
				{
					charArray2[index] = nameString.charAt(i);
					index++;
				}
				lastName = new String(charArray2);
				
				lastName = new String(charArray2);
				student.setStudentId(recordNumber);
				student.setFirstName(firstName);
				student.setLastName(lastName);
				student.setBirthday(birthdayText.getText());
				student.setAddress(addressText.getText());
				
				try {
					stFile.moveFilePointer(Integer.parseInt(recordNumber)-1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					stFile.writeStudentInfo(student);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Label updateLabel = new Label("Updated!");
				updateLabel.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold");
				okButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
				okButton.setMinWidth(60);
				VBox vboxUpdated = new VBox(20, updateLabel, okButton);
				vboxUpdated.setAlignment(Pos.CENTER);
				
				Scene sceneUpdated = new Scene(vboxUpdated, 450, 100);
				sceneUpdated.setFill(Color.CADETBLUE);
				
				secondaryStage = new Stage(StageStyle.UTILITY);
			    secondaryStage.setScene(sceneUpdated);
			    secondaryStage.setTitle("Message");

			    secondaryStage.setX(800); secondaryStage.setY(600);
			    secondaryStage.show();
			}
			
		});
		
		cancelUpdateButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				borderPane.setCenter(searchHbox);
				
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
			}
		});
		
		okButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				stIdSearchText.setText("");
				borderPane.setCenter(searchHbox);
				
				studentIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		studentNameText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		birthdayText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		addressText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		editButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
				
				HBox idHbox = new HBox(idLabel, studentIdText);
        		HBox nameHbox = new HBox(nameLabel, studentNameText);
        		HBox birthdayHbox = new HBox(birthdayLabel, birthdayText);
        		HBox addressHbox = new HBox(addressLabel, addressText);
        		HBox buttonHbox = new HBox(editButton);
        			        		
        		VBox vbox = new VBox(idHbox, nameHbox, birthdayHbox, addressHbox, buttonHbox);
        		vbox.setAlignment(Pos.CENTER);
        		vbox.setPadding(new Insets(10));
        		borderPane.setBottom(vbox);
				
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
				secondaryStage.close();
			}
		});
	}	
		
	public static class InputError extends Exception
	{
		public InputError()
		{
			super("Input Error: ID has to be 5 numbers. Ex. 00015");
		}
	}
	
	public static class Course
	{
		private String courseId, courseName, instructor, classroom;
		
		public Course(String cID, String cName, String inst, String clssRoom)
		{
			courseId = cID;
			courseName = cName;
			instructor = inst;
			classroom = clssRoom;
		}
		
		public void setCourseId(String cID)
		{
			courseId = cID;
		}
		
		public void setCourseName(String cName)
		{
			courseName = cName;
		}
		
		public void setInstructor(String instrctr)
		{
			instructor = instrctr;
		}
		
		public void setClassroom(String classRoom)
		{
			classroom = classRoom;
		}
		
		
		public String getCourseId()
		{
			return courseId;
		}
		
		public String getCourseName()
		{
			return courseName;
		}
		
		public String getInstructor()
		{
			return instructor;
		}
		
		public String getClassroom()
		{
			return classroom;
		}
	}
	
	public static class CourseFile
	{
		private final int RECORD_SIZE = 120;
		private RandomAccessFile courseFile;
		
		public CourseFile(String filename) throws FileNotFoundException
		{
			courseFile = new RandomAccessFile(filename, "rw");
		}
		
		public void writeCourseInfo(Course csInfo) throws IOException
		{
			stringWrite(courseFile, csInfo.getCourseId(), 5);
			stringWrite(courseFile, csInfo.getCourseName(), 15);
			stringWrite(courseFile, csInfo.getInstructor(), 30);
			stringWrite(courseFile, csInfo.getClassroom(), 10);
		}
	
		public Course readCourseInfo() throws IOException
		{
			String csId = new String(stringRead(courseFile, 5));
			String cName = new String(stringRead(courseFile, 15));
			String instructor = new String(stringRead(courseFile, 30));
			String classroom = new String(stringRead(courseFile, 10));
			
			Course cs = new Course(csId, cName, instructor, classroom);
			return cs;
		}
		
		private long getByteNum(long recordNum)
		{
			return RECORD_SIZE * recordNum;
		}
		
		public void moveFilePointer(long recordNum) throws IOException
		{
			courseFile.seek(getByteNum(recordNum));
		}
		
		public int getNumberOfRecords() throws IOException
		{
			return (int)(courseFile.length() / RECORD_SIZE);
		}
		
		public void close() throws IOException
		{
			courseFile.close();
		}
	}
	
	public static void createCourse(Stage primaryStage) throws IOException
	{
		Label courseNameLabel = new Label("Enter Course Name");
		courseNameText = new TextField();
				
		Label instructorLabel = new Label("Enter Course Instructor");
		instructorComboBox = new ComboBox<>();
		instructorComboBox.getItems().addAll("Patil, A", "Patel, M", "Chiang, A", "Ghaforyfard, P", "Butler, M");
		instructorComboBox.setValue("Patil, A");
		
		Label locationLabel = new Label("Enter Course Location");
		locationComboBox = new ComboBox<>();
		locationComboBox.getItems().addAll("CE103", "CE105A", "CE106A", "CE225", "ONLINE");
		locationComboBox.setValue("CE103");
		
		Button addButtonCourse = new Button("Add");
		
		courseNameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		instructorLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		locationLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		addButtonCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		courseNameText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		instructorComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		locationComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		
		courseNameLabel.setMinWidth(280);
		instructorLabel.setMinWidth(280);
		locationLabel.setMinWidth(280);
		addButtonCourse.setMinWidth(280);
		Button okCourseAddButton = new Button();
		
		courseNameText.setMinWidth(280);
		instructorComboBox.setMinWidth(280);
		locationComboBox.setMinWidth(280);
		
		courseNameText.setMinHeight(30);
		instructorComboBox.setMinHeight(30);
		locationComboBox.setMinHeight(30);
		
		HBox cnHbox = new HBox(25, courseNameLabel, courseNameText);
		HBox instHbox = new HBox(25, instructorLabel, instructorComboBox);
		HBox locHbox = new HBox(25, locationLabel, locationComboBox);
		HBox buttonCourseHbox = new HBox(30, addButtonCourse);
		
		VBox vboxCourse = new VBox(20, cnHbox, instHbox, locHbox, buttonCourseHbox);
		vboxCourse.setAlignment(Pos.CENTER);
		vboxCourse.setPadding(new Insets(10));
		borderPane.setCenter(vboxCourse);

		addButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
	    	{
				final int NUMBER_COURSE = 30;
	    		int lastRecordNumber = 0;
				Course[] course = new Course[NUMBER_COURSE];
				
				CourseFile csFile = null;
				try 
				{
					csFile = new CourseFile("course.dat");
				} 
				catch (FileNotFoundException e) 
				{
					e.printStackTrace();
				}
				try 
				{
					lastRecordNumber = csFile.getNumberOfRecords();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				String csId = String.format("%05d", lastRecordNumber+1);
				
				String className = spaceRemoveBeforeName(courseNameText.getText());
				
				course[lastRecordNumber] = new Course(csId, className, instructorComboBox.getValue(), locationComboBox.getValue());
				try 
				{
					csFile.moveFilePointer(lastRecordNumber);
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				try 
				{
					csFile.writeCourseInfo(course[lastRecordNumber]);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				
				Label messageIdAdded1 = new Label("Course Data has beed added for Course ID ");
				Label messageIdAdded2 = new Label(csId);
				
				messageIdAdded1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				messageIdAdded2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				
				HBox hboxMessage = new HBox(5, messageIdAdded1, messageIdAdded2);
				hboxMessage.setAlignment(Pos.CENTER);
				okCourseAddButton.setText("OK");
				okCourseAddButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold");
				okCourseAddButton.setMinWidth(60);
				VBox vbox = new VBox(20, hboxMessage, okCourseAddButton);
				vbox.setAlignment(Pos.CENTER);
								
				Scene sceneAdded = new Scene(vbox, 450, 100);
				sceneAdded.setFill(Color.CADETBLUE);
				
				secondaryStage = new Stage(StageStyle.UTILITY);
			    secondaryStage.setScene(sceneAdded);
			    secondaryStage.setTitle("Message");

			    secondaryStage.setX(800); secondaryStage.setY(600);
			    secondaryStage.show();
			    			    
				try 
				{
					csFile.close();
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
	    	}
		});
		
		okCourseAddButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				courseNameText.setText("");
			}
		});
	}	
	
	public static void searchCourse(Stage primaryStage) throws IOException
	{
		Label enterLabelCourse = new Label("Enter Course ID");
		enterLabelCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
		csIdSearchText = new TextField();
		courseIdText = new TextField();
		courseNameText = new TextField();
		instructorComboBox = new ComboBox<>();
		locationComboBox = new ComboBox<>();
		Button searchButtonCourse = new Button("Search");
		searchButtonCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		searchButtonCourse.setMinWidth(60);
		Button okButtonCourse = new Button("OK");
		Button okErrorButtonCourse = new Button("OK");
		Button editButtonCourse = new Button("Edit");
		Button updateButtonCourse = new Button("Update");
		Button cancelUpdateButtonCourse = new Button("Cancel Update");
		
		Label idLabelCourse = new Label("Course ID");
		Label nameLabelCourse = new Label("Name");
		Label instructorLabel = new Label("Instructor");
		Label locationLabel = new Label("Classroom");
		
		HBox searchHboxCourse = new HBox(10, enterLabelCourse, csIdSearchText, searchButtonCourse);
		searchHboxCourse.setAlignment(Pos.CENTER);
		
		borderPane.setCenter(searchHboxCourse);
		borderPane.setBottom(null);
		borderPane.setLeft(null);
		borderPane.setRight(null);
		
		primaryStage.setScene(mainScene);
		primaryStage.setTitle("Student Information System");
		primaryStage.show();
				
		searchButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int numberOfRecords = 0;
				courseIdText = new TextField();				
				String courseId = csIdSearchText.getText();
				Course course = null;
				CourseFile csFile = null;
				
				try {
					csFile = new CourseFile("course.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfRecords = csFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	    		if (courseId.length() == 5 && Integer.parseInt(courseId) <= numberOfRecords)
	    		{
	    			try {
	    				try {
	    					csFile.moveFilePointer(Integer.parseInt(courseId)-1);
	    				} catch (IOException e) {
	    					e.printStackTrace();
	    				}
	    			} catch (NumberFormatException e) {
	    				e.printStackTrace();
	    			} 
	        		try {
	    				course = csFile.readCourseInfo();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	        		
	        		idLabelCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		nameLabelCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		instructorLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		locationLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		
	        		idLabelCourse.setMinWidth(290);
	        		nameLabelCourse.setMinWidth(290);
	        		instructorLabel.setMinWidth(290);
	        		locationLabel.setMinWidth(290);
	        		
	        		courseIdText.setText(course.getCourseId());
	        		courseNameText.setText(course.getCourseName());
	        		instructorComboBox.setValue(course.getInstructor());
	        		locationComboBox.setValue(course.getClassroom());
	        		
	        		TextField csId = new TextField(courseIdText.getText());
	        		TextField cName = new TextField(courseNameText.getText());
	        		TextField cIns = new TextField(instructorComboBox.getValue());
	        		TextField cLoc = new TextField(locationComboBox.getValue());
        		
	        		csId.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		cName.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		cIns.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		cLoc.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
	        		editButtonCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
	        		
	        		csId.setMinWidth(290);
	        		cName.setMinWidth(290);
	        		cIns.setMinWidth(290);
	        		cLoc.setMinWidth(290);
	        		editButtonCourse.setMinWidth(290);
	        		
	        		HBox idHboxCourse = new HBox(idLabelCourse, csId);
	        		HBox nameHboxCourse = new HBox(nameLabelCourse, cName);
	        		HBox instructorHbox = new HBox(instructorLabel, cIns);
	        		HBox locationHbox = new HBox(locationLabel, cLoc);
	        		HBox buttonHbox = new HBox(editButtonCourse);
	        		
	        		VBox vbox = new VBox(idHboxCourse, nameHboxCourse, instructorHbox, locationHbox, buttonHbox);
	        		vbox.setAlignment(Pos.CENTER);
	        		vbox.setPadding(new Insets(10));
	        		borderPane.setBottom(vbox);
	        		
	        		csIdSearchText.setText("");
	        	}
	    		        		
        		if (courseId.length() == 5 && Integer.parseInt(courseId) > numberOfRecords)
        		{
        			Label errorInputLabel1 = new Label("This course ID does not exist: ");
        			Label errorInputLabel2 = new Label(courseId);
        			Label errorInputLabel3 = new Label(". Maximum course ID is ");
        			Label errorInputLabel4 = new Label(Integer.toString(numberOfRecords));
        			
        			errorInputLabel1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel3.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel4.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			okErrorButtonCourse.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        			okErrorButtonCourse.setMinWidth(60);
        			
        			HBox errorInputHbox = new HBox(errorInputLabel1, errorInputLabel2, errorInputLabel3, errorInputLabel4);
        			errorInputHbox.setAlignment(Pos.CENTER);
        			VBox errorInputVbox = new VBox(20, errorInputHbox, okErrorButtonCourse);
        			errorInputVbox.setAlignment(Pos.CENTER);
        			
        			Scene sceneErr1 = new Scene(errorInputVbox, 550, 100);
        			sceneErr1.setFill(Color.CADETBLUE);
    				
    				secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr1);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(searchHboxCourse);
        		}	
        		

	    		try {
        			if (courseId.length() != 5 ) throw new InputError();
        		} catch(InputError e) {
        			Label errorInputLabel = new Label(e.getMessage());
        			errorInputLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			okErrorButtonCourse.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        			okErrorButtonCourse.setMinWidth(60);
        			VBox errorInputVbox = new VBox(20, errorInputLabel, okErrorButtonCourse);
        			errorInputVbox.setAlignment(Pos.CENTER);
        			
        			Scene sceneErr2 = new Scene(errorInputVbox, 450, 100);
        			sceneErr2.setFill(Color.CADETBLUE);
    				
    				secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr2);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(searchHboxCourse);
        		}
			}
	    });
		
		okErrorButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				csIdSearchText.setText("");
			}
		});
		
		editButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int numberOfRecords = 0;
				String courseId = courseIdText.getText();
				Course course = null;
				CourseFile csFile = null;
				 
				try {
					csFile = new CourseFile("course.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfRecords = csFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
    				try {
    					csFile.moveFilePointer(Integer.parseInt(courseId)-1);
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			} catch (NumberFormatException e) {
    				e.printStackTrace();
    			} 
        		try {
    				course = csFile.readCourseInfo();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
        		
        		idLabelCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
        		nameLabelCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
        		instructorLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
        		locationLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold"); 
        		
        		idLabelCourse.setMinWidth(290);
        		nameLabelCourse.setMinWidth(290);
        		instructorLabel.setMinWidth(290);
        		locationLabel.setMinWidth(290);
        		
        		courseIdText.setText(course.getCourseId());
        		courseNameText.setText(course.getCourseName());
        		instructorComboBox.setValue(course.getInstructor());
        		locationComboBox.setValue(course.getClassroom());
        		
        		instructorComboBox.getItems().addAll("Patil, A", "Patel, M", "Chiang, A", "Ghaforyfard, P", "Butler, M");
        		locationComboBox.getItems().addAll("CE103", "CE105A", "CE106A", "CE225", "ONLINE");
        		        		
        		courseIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		courseNameText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        		instructorComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        		locationComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        		updateButtonCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        		cancelUpdateButtonCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        		
        		courseIdText.setMinWidth(290);
        		courseNameText.setMinWidth(290);
        		instructorComboBox.setMinWidth(290);
        		locationComboBox.setMinWidth(290);
        		updateButtonCourse.setMinWidth(290);
        		cancelUpdateButtonCourse.setMinWidth(290);
        		
        		courseIdText.setMinHeight(40);
        		courseNameText.setMinHeight(40);
        		instructorComboBox.setMinHeight(40);
        		locationComboBox.setMinHeight(40);
        		updateButtonCourse.setMinHeight(40);
        		cancelUpdateButtonCourse.setMinHeight(40);
        		
        		HBox idHboxCourse = new HBox(5, idLabelCourse, courseIdText);
        		HBox nameHboxCourse = new HBox(5, nameLabelCourse, courseNameText);
        		HBox instructorHbox = new HBox(5, instructorLabel, instructorComboBox);
        		HBox locationHbox = new HBox(5, locationLabel, locationComboBox);
        		HBox buttonHbox = new HBox(5, updateButtonCourse, cancelUpdateButtonCourse);
        			        		
        		VBox vbox = new VBox(idHboxCourse, nameHboxCourse, instructorHbox, locationHbox, buttonHbox);
        		vbox.setAlignment(Pos.CENTER);
        		vbox.setPadding(new Insets(10));
        		
        		borderPane.setCenter(vbox);
        		borderPane.setBottom(null);
        	}
		});
		
		updateButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				final String recordNumber = courseIdText.getText();
				Course course = null;
				CourseFile csFile = null;
				
				try {
					csFile = new CourseFile("course.dat");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					course = csFile.readCourseInfo();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				course.setCourseId(recordNumber);
				course.setCourseName(courseNameText.getText());
				course.setInstructor(instructorComboBox.getValue());
				course.setClassroom(locationComboBox.getValue());
								
				try {
					csFile.moveFilePointer(Integer.parseInt(recordNumber)-1);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					csFile.writeCourseInfo(course);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Label updateLabel = new Label("Updated!");
				updateLabel.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold");
				okButtonCourse.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
				okButtonCourse.setMinWidth(60);
				VBox vbox = new VBox(20, updateLabel, okButtonCourse);
				vbox.setAlignment(Pos.CENTER);
				
				Scene sceneUpdated = new Scene(vbox, 450, 100);
				sceneUpdated.setFill(Color.CADETBLUE);
				
				secondaryStage = new Stage(StageStyle.UTILITY);
			    secondaryStage.setScene(sceneUpdated);
			    secondaryStage.setTitle("Message");

			    secondaryStage.setX(800); secondaryStage.setY(600);
			    secondaryStage.show();
			}
		});
		
		okButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				
				borderPane.setCenter(searchHboxCourse);
				
				TextField csId = new TextField(courseIdText.getText());
        		TextField cName = new TextField(courseNameText.getText());
        		TextField cIns = new TextField(instructorComboBox.getValue());
        		TextField cLoc = new TextField(locationComboBox.getValue());
    		
        		csId.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		cName.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		cIns.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		cLoc.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
        		editButtonCourse.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
        		
        		csId.setMinWidth(290);
        		cName.setMinWidth(290);
        		cIns.setMinWidth(290);
        		cLoc.setMinWidth(290);
        		editButtonCourse.setMinWidth(290);
        		
        		HBox idHboxCourse = new HBox(idLabelCourse, csId);
        		HBox nameHboxCourse = new HBox(nameLabelCourse, cName);
        		HBox instructorHbox = new HBox(instructorLabel, cIns);
        		HBox locationHbox = new HBox(locationLabel, cLoc);
        		HBox buttonHbox = new HBox(editButtonCourse);
        			        		
        		VBox vbox = new VBox(idHboxCourse, nameHboxCourse, instructorHbox, locationHbox, buttonHbox);
        		vbox.setAlignment(Pos.CENTER);
        		vbox.setPadding(new Insets(10));
        		borderPane.setBottom(vbox);
				
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
			}
		});
		
		cancelUpdateButtonCourse.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				borderPane.setCenter(searchHboxCourse);
				
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
			}
		});
		
	}	
	
	public static class Enrollment
	{
		private String studentId, enrollmentId, courseId, courseName, year, semester, grade;
		
		private Enrollment(String stId, String enID, String cID, String cName, String yr, String sm, String gr)
		{
			studentId = stId;
			enrollmentId = enID;
			courseId = cID;
			courseName = cName;
			year = yr;
			semester = sm;
			grade = gr;
		}
		
		public void setStudentId(String stdId)
		{
			studentId = stdId;
		}
		
		public void setEnrollmentId(String enrollId)
		{
			enrollmentId = enrollId;
		}
		
		public void setCourseId(String courId)
		{
			courseId = courId;
		}
		
		public void setCourseName(String courName)
		{
			courseName = courName;
		}
		
		public void setYear(String yr)
		{
			year = yr;
		}
		
		public void setSemester(String smstr)
		{
			semester = smstr;
		}
		
		public void setGrade(String gr)
		{
			grade = gr;
		}
		
		public String getStudentId()
		{
			return studentId;
		}
		
		public String getEnrollmentId()
		{
			return enrollmentId;
		}
		
		public String getCourseId()
		{
			return courseId;
		}
		
		public String getCourseName()
		{
			return courseName;
		}
		
		public String getYear()
		{
			return year;
		}
		
		public String getSemester()
		{
			return semester;
		}
		
		public String getGrade()
		{
			return grade;
		}
	}
	
	public static class EnrollmentFile
	{
		private final int RECORD_SIZE = 84;
		private RandomAccessFile enrollmentFile;
		
		public EnrollmentFile(String filename) throws FileNotFoundException
		{
			enrollmentFile = new RandomAccessFile(filename, "rw");
		}
		
		public void writeEnrollmentInfo(Enrollment enInfo) throws IOException
		{
			stringWrite(enrollmentFile, enInfo.getStudentId(), 5);
			stringWrite(enrollmentFile, enInfo.getEnrollmentId(), 5);
			stringWrite(enrollmentFile, enInfo.getCourseId(), 5);
			stringWrite(enrollmentFile, enInfo.getCourseName(), 15);
			stringWrite(enrollmentFile, enInfo.getYear(), 4);
			stringWrite(enrollmentFile, enInfo.getSemester(), 6);
			stringWrite(enrollmentFile, enInfo.getGrade(), 2);
		}
	
		public Enrollment readEnrollmentInfo() throws IOException
		{
			String studentId = stringRead(enrollmentFile, 5);
			String enrollmentId = stringRead(enrollmentFile, 5);
			String courseId = stringRead(enrollmentFile, 5);
			String courseName = stringRead(enrollmentFile, 15);
			String year = stringRead(enrollmentFile, 4);
			String semester = stringRead(enrollmentFile, 6);
			String grade = stringRead(enrollmentFile, 2);
			
			Enrollment en = new Enrollment(studentId, enrollmentId, courseId, courseName, year, semester, grade);
			return en;
		}
		
		private long getByteNum(long recordNum)
		{
			return RECORD_SIZE * recordNum;
		}
		
		public void moveFilePointer(long recordNum) throws IOException
		{
			enrollmentFile.seek(getByteNum(recordNum));
		}
		
		public int getNumberOfRecords() throws IOException
		{
			return (int)(enrollmentFile.length() / RECORD_SIZE);
		}
		
		public void close() throws IOException
		{
			enrollmentFile.close();
		}
	}
	
	public static void createEnrollment(Stage primaryStage) throws IOException
	{
		ComboBox<String> csNameComboBox = new ComboBox<>();	
		ComboBox<String> yearComboBox = new ComboBox<>();
		ComboBox<String> semesterComboBox = new ComboBox<>();
		yearComboBox.getItems().setAll("2018", "2019", "2020", "2021");
		semesterComboBox.getItems().setAll("Spring", "Summer", "Fall", "Winter");
		yearComboBox.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
		semesterComboBox.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
		String sId;
		
		Label searchStudentLabel = new Label("---Search by Student--------------------------------------------------------------------");
		Label inputStudentIdLabel = new Label("Input Student ID");
		searchStudentLabel.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
		inputStudentIdLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		TextField studentIdText = new TextField();
		
		TextField stIdText = new TextField();
		TextField stNameText = null;
		Button srchStButton = new Button("Search Student");
		Button enrollButton = new Button("Enroll");
		Button cancelEnrollButton = new Button("Cancel Enroll");
		Button okEnButton = new Button("OK");
		Button okErrorButton = new Button("OK");
		srchStButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		enrollButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		cancelEnrollButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		okEnButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		enrollButton.setMinWidth(60);
		cancelEnrollButton.setMinWidth(60);
		
		HBox srchStdLabelHbox = new HBox(searchStudentLabel);
		srchStdLabelHbox.setAlignment(Pos.CENTER);
		HBox stIdSrchHbox = new HBox(10, inputStudentIdLabel, studentIdText, srchStButton);
		stIdSrchHbox.setAlignment(Pos.CENTER);
		VBox vbox = new VBox(10, srchStdLabelHbox, stIdSrchHbox);
		vbox.setPadding(new Insets(3,0,0,0));
		
		borderPane.setLeft(null);
		borderPane.setRight(null);
		borderPane.setBottom(null);
		borderPane.setCenter(vbox);
		
		srchStButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int numberOfRecords = 0;
				String sId = studentIdText.getText();
				Student student = null;
				StudentFile stFile = null;
			
				Label srStLabel = new Label("Search Student------------------------------------------");
				srStLabel.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
				Label csLabel = new Label("Course-----------------------------------------------------");
				csLabel.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
				
				CourseFile csFile = null;
				try {
					csFile = new CourseFile("course.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
        		Course[] course = null;
				try {
					course = new Course[csFile.getNumberOfRecords()];
				} catch (IOException e) {
					e.printStackTrace();
				}
        		try {
        			csNameComboBox.getItems().clear();
        			for (int i=0; i < csFile.getNumberOfRecords(); i++)
					{
						csFile.moveFilePointer(i);
						course[i] = csFile.readCourseInfo();
						csNameComboBox.getItems().add(course[i].getCourseName());
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	        		
        		csNameComboBox.setValue(course[0].getCourseName());
        		yearComboBox.setValue("2019");
        		semesterComboBox.setValue("Spring");
        		csNameComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        		yearComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        		semesterComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        		        		
        		HBox csLabelHbox = new HBox(csLabel);
        		HBox nameYearHbox = new HBox(5, csNameComboBox, yearComboBox);
        		nameYearHbox.setAlignment(Pos.CENTER);
        		HBox semesterHbox = new HBox(semesterComboBox);
        		semesterHbox.setAlignment(Pos.CENTER);
        		VBox vboxRight = new VBox(5, csLabelHbox, nameYearHbox, semesterHbox);
        		vboxRight.setPadding(new Insets(8,8,0,0));
        		
        		borderPane.setRight(vboxRight);
        		       		
        		HBox centerEnHbox = new HBox(10, enrollButton, cancelEnrollButton);
        		centerEnHbox.setAlignment(Pos.CENTER);
        		centerEnHbox.setPadding(new Insets(0,0,30,0));
        		
        		borderPane.setBottom(centerEnHbox);
        		borderPane.setCenter(null);
        		
        		
        		try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfRecords = stFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
	    		if (sId.length() == 5 && Integer.parseInt(sId) <= numberOfRecords)
	    		{
	    			try {
	    				try {
	    					stFile.moveFilePointer(Integer.parseInt(sId)-1);
	    				} catch (IOException e) {
	    					e.printStackTrace();
	    				}
	    			} catch (NumberFormatException e) {
	    				e.printStackTrace();
	    			} 
	        		try {
	    				student = stFile.readStudentInfo();
	    			} catch (IOException e) {
	    				e.printStackTrace();
	    			}
	        		
	        		Label stIdLabel = new Label("Student ID");
	        		Label stNameLabel = new Label("Student Name");
	        		stIdLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		stNameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	        		stIdLabel.setMinWidth(140);
	        		stNameLabel.setMinWidth(140);
	        		stIdLabel.setMinHeight(50);
	        		stNameLabel.setMinHeight(50);
				    
					stIdText.setText(sId);
				    TextField stNameText = new TextField(student.getFirstName().trim() + " " + student.getLastName());
				    stIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold;-fx-background-color: #D8D8D8");
				    stNameText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold;-fx-background-color: #D8D8D8");
				    stIdText.setMinWidth(160);
				    stNameText.setMinWidth(160);
				    stIdText.setMinHeight(50);
				    stNameText.setMinHeight(50);
				    				    
				    HBox stIdHbox = new HBox(5, stIdLabel, stIdText);
				    HBox stNameHbox = new HBox(5, stNameLabel, stNameText);
				    VBox vboxLeft = new VBox(srStLabel, stIdHbox, stNameHbox);	 
				    vboxLeft.setPadding(new Insets(8,0,0,8));
					
				    borderPane.setLeft(vboxLeft);
	    		}
      		
	    		if (sId.length() == 5 && Integer.parseInt(sId) > numberOfRecords)
	        	{
        			Label errorInputLabel1 = new Label("This sutdent ID does not exist: ");
        			Label errorInputLabel2 = new Label(sId);
        			Label errorInputLabel3 = new Label(". Maximum student ID is ");
        			Label errorInputLabel4 = new Label(Integer.toString(numberOfRecords));
        			
        			errorInputLabel1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel3.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel4.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			okErrorButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        			okErrorButton.setMinWidth(60);
        			
        			HBox errorInputHbox = new HBox(errorInputLabel1, errorInputLabel2, errorInputLabel3, errorInputLabel4);
        			errorInputHbox.setAlignment(Pos.CENTER);
        			VBox errorInputVbox = new VBox(20, errorInputHbox, okErrorButton);
        			errorInputVbox.setAlignment(Pos.CENTER);
        			
        			Scene sceneErr1 = new Scene(errorInputVbox, 550, 100);
    				sceneErr1.setFill(Color.CADETBLUE);
    				
    				secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr1);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);

				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(vbox);

        			secondaryStage.show();
        		}	
        		
	    		try {
	    			if (sId.length() != 5 ) throw new InputError();
	    		} catch(InputError e) {
	    			Label errorInputLabel = new Label(e.getMessage());
	    			errorInputLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	    			okErrorButton.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	    			okErrorButton.setMinWidth(60);
	    			VBox errorInputVbox = new VBox(20, errorInputLabel, okErrorButton);
	    			errorInputVbox.setAlignment(Pos.CENTER);
	    			
         			Scene sceneErr2 = new Scene(errorInputVbox, 450, 100);
					sceneErr2.setFill(Color.CADETBLUE);
	 
				    secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr2);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);

				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(vbox);

        			secondaryStage.show();
	    		}
			}
		});
		
		okErrorButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				studentIdText.setText("");
				secondaryStage.close();
			}
		});
        		
		enrollButton.setOnAction(new EventHandler<ActionEvent>()  
		{
			public void handle(ActionEvent event)
			{
				final int NUMBER_ENROLLMENT = 100;
				int lastRecordNumber = 0;
				String studentId, enrollmentId, courseId = null, courseName, year, semester;
				String grade = new String("*");
				Enrollment[] enrollment = new Enrollment[NUMBER_ENROLLMENT];
				
				studentId = stIdText.getText();
				courseName = csNameComboBox.getValue();
				year = yearComboBox.getValue();
				semester = semesterComboBox.getValue();
								
				CourseFile csFile = null;
				try {
					csFile = new CourseFile("course.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
        		Course[] course = null;
				try {
					course = new Course[csFile.getNumberOfRecords()];
				} catch (IOException e) {
					e.printStackTrace();
				}
        		try {
					for (int i=0; i < csFile.getNumberOfRecords(); i++)
					{
						csFile.moveFilePointer(i);
						course[i] = csFile.readCourseInfo();
						if (courseName.equals(course[i].getCourseName()) )
						{
							courseId = String.format("%05d",  i+1);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
        		
        		EnrollmentFile enFile = null;
				try {
					enFile = new EnrollmentFile("enrollment.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					lastRecordNumber = enFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				enrollmentId = String.format("%05d", lastRecordNumber+1);
				
				enrollment[lastRecordNumber] = new Enrollment(studentId, enrollmentId, courseId, courseName, year, semester, grade);
				try {
					enFile.moveFilePointer(lastRecordNumber);
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					enFile.writeEnrollmentInfo(enrollment[lastRecordNumber]);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Label enrollAdded1 = new Label("Enrollment has been added. Enroll ID: ");
				enrollAdded1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				Label enrollAdded2 = new Label(enrollmentId);
				enrollAdded2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				
				HBox enrollAddedHbox = new HBox(5, enrollAdded1, enrollAdded2);
				enrollAddedHbox.setAlignment(Pos.CENTER);
				okEnButton.setMinWidth(60);
				VBox vbox = new VBox(20, enrollAddedHbox, okEnButton);
				vbox.setAlignment(Pos.CENTER);
				
				Scene sceneEnrolled = new Scene(vbox, 450, 100);
				sceneEnrolled.setFill(Color.CADETBLUE);
				
				secondaryStage = new Stage(StageStyle.UTILITY);
			    secondaryStage.setScene(sceneEnrolled);
			    secondaryStage.setTitle("Message");

			    secondaryStage.setX(800); secondaryStage.setY(600);
			    secondaryStage.show();
			    
				try {
					csFile.close();
					enFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});	
			
		cancelEnrollButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				borderPane.setTop(menuBar);
				borderPane.setCenter(null);
				borderPane.setLeft(null);
				borderPane.setRight(null);
				borderPane.setBottom(null);
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
			}
		});
		
		
		okEnButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				
				borderPane.setTop(menuBar);
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
			}
		});
		
		
	}
	
	public static void searchEnrollment(Stage primaryStage) throws IOException
	{
		Label searchStudentLabel = new Label("---Search by Student------------------------------------------");
		Label inputStudentIdLabel = new Label("Input Student ID");
		searchStudentLabel.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
		inputStudentIdLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		TextField studentIdText = new TextField();
		
		Button srchStButton = new Button("Search by Student ID");
		Button okErrorButtonEn = new Button("OK");
		srchStButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		Button dropEnButton = new Button("Drop Enroll");
		Button cancelEnButton = new Button("Cancel");
		Button sureButton = new Button("Are you Sure?");
		GridPane gridPane = new GridPane();
		TextField enIdClickFound = new TextField("-1");
		
		TextArea textArea = new TextArea();
		textArea.setPrefColumnCount(20);
		textArea.setPrefRowCount(30);
		
		HBox srchStdLabelHbox = new HBox(searchStudentLabel);
		srchStdLabelHbox.setAlignment(Pos.CENTER);
		HBox stIdSrchHbox = new HBox(10, inputStudentIdLabel, studentIdText);
		stIdSrchHbox.setAlignment(Pos.CENTER);
		HBox srchByStIdButtonHbox = new HBox(srchStButton);
		srchByStIdButtonHbox.setAlignment(Pos.CENTER);
		VBox vboxSearch = new VBox(5, srchStdLabelHbox, stIdSrchHbox, srchByStIdButtonHbox);
		vboxSearch.setPadding(new Insets(3,0,0,0));
		
		borderPane.setCenter(vboxSearch);
		borderPane.setBottom(null);
		borderPane.setLeft(null);
		borderPane.setRight(null);
		
		srchStButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				int j = 0;
				Boolean flag = false;
				String[] enIdNumFound = null, csIdNumFound = null, yrFound = null, smFound = null;
				String recordNumber;
				String studentId = null, enrollmentId, courseId, courseName, year, semester, grade;
			
				EnrollmentFile enFile = null;
				
				int numberOfRecords = 0;
				studentId = studentIdText.getText();
				StudentFile stFile = null;
				
				try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfRecords = stFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					enFile = new EnrollmentFile("enrollment.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				Enrollment[] enrollment = null;
				try {
					enrollment = new Enrollment[enFile.getNumberOfRecords()];
					enIdNumFound = new String[enFile.getNumberOfRecords()];
					csIdNumFound = new String[enFile.getNumberOfRecords()];
					yrFound = new String[enFile.getNumberOfRecords()];
					smFound = new String[enFile.getNumberOfRecords()];
					
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					for (int i = 0; i < enFile.getNumberOfRecords(); i++)
					{
						enFile.moveFilePointer(i);
						enrollment[i] = enFile.readEnrollmentInfo();
						
						if (studentId.equals(enrollment[i].getStudentId()))
						{
							enIdNumFound[j] = enrollment[i].getEnrollmentId();
							csIdNumFound[j] = enrollment[i].getCourseId();
							yrFound[j] = enrollment[i].getYear();
							smFound[j] = enrollment[i].getSemester();
							j += 1;
							flag = true;
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				TextField[] resultFoundText = new TextField[j];
				
				if (flag == false)
				{
					Label notFoundLabel = new Label("Search Result: No record found");
					notFoundLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					VBox notFoundHbox = new VBox(notFoundLabel);
					notFoundHbox.setPadding(new Insets(30,0,0,40));
					
					HBox srchStdLabelHbox = new HBox(searchStudentLabel);
					srchStdLabelHbox.setAlignment(Pos.CENTER);
					HBox stIdSrchHbox = new HBox(10, inputStudentIdLabel, studentIdText);
					stIdSrchHbox.setAlignment(Pos.CENTER);
					HBox srchByStIdButtonHbox = new HBox(srchStButton);
					srchByStIdButtonHbox.setAlignment(Pos.CENTER);
					VBox vboxNotFound = new VBox(5, srchStdLabelHbox, stIdSrchHbox, srchByStIdButtonHbox);
					vboxNotFound.setPadding(new Insets(0,40,0,0));
					HBox hbox = new HBox(10, notFoundHbox, vboxNotFound);
										
					borderPane.setCenter(hbox);
				}
				else
				{
					Label fieldNameLabel = new Label("EnrollmentID  CourseID  Year   Semester");
					fieldNameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					dropEnButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
					cancelEnButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
																	        
			        gridPane.setPadding(new Insets(50,0,0,50));
					gridPane.setVgap(1);
					gridPane.setHgap(40);
					
					gridPane.add(fieldNameLabel, 0, 0);	
					
					for (int k = 0; k < j; k++)
					{ 
						resultFoundText[k] = new TextField();
						resultFoundText[k].setText(enIdNumFound[k] + "            " + csIdNumFound[k] + "      "
					                             + yrFound[k] + "    " + smFound[k]); 
						resultFoundText[k].setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
						gridPane.add(resultFoundText[k], 0, k+1);
					}
					
					HBox dropEnButtonHbox = new HBox(dropEnButton);
					dropEnButtonHbox.setPadding(new Insets(65,0,0,0));
					HBox cancelEnButtonHbox = new HBox(cancelEnButton);
					cancelEnButtonHbox.setPadding(new Insets(65,0,0,0));
					HBox hbox = new HBox(10, gridPane, dropEnButtonHbox, cancelEnButtonHbox);
					borderPane.setCenter(hbox);
				}
				
				gridPane.getChildren().forEach(item -> 
				{
			        item.setOnMouseClicked(new EventHandler<MouseEvent>() 
			        {
			        	String enString;
			        	TextField enStringText;
			        	Integer rowIndex;
			        	String enIdClickString = "";
			        	
			            public void handle(MouseEvent event) 
			            {
			            	if (event.getClickCount() == 1) 
			                {
			                    javafx.scene.Node source = (javafx.scene.Node) event.getSource();
			                    rowIndex = GridPane.getRowIndex(source);
			                }
			            	
			            	if (rowIndex >= 1)
			            	{
			            		enStringText = resultFoundText[rowIndex-1];
								enString = enStringText.getText();
								for (int i = 0; i < 5; i++)
								{
									enIdClickString += enString.charAt(i);
								}
								enIdClickFound.setText(enIdClickString);
			            	}
			            }
			        });
				});

				try {
					enFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

				if (studentId.length() == 5 && Integer.parseInt(studentId) > numberOfRecords)
	        	{
        			Label errorInputLabel1 = new Label("This sutdent ID does not exist: ");
        			Label errorInputLabel2 = new Label(studentId);
        			Label errorInputLabel3 = new Label(". Maximum student ID is ");
        			Label errorInputLabel4 = new Label(Integer.toString(numberOfRecords));
        			
        			errorInputLabel1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel3.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel4.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			okErrorButtonEn.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        			okErrorButtonEn.setMinWidth(60);
        			
        			HBox errorInputHbox = new HBox(errorInputLabel1, errorInputLabel2, errorInputLabel3, errorInputLabel4);
        			errorInputHbox.setAlignment(Pos.CENTER);
        			VBox errorInputVbox = new VBox(20, errorInputHbox, okErrorButtonEn);
        			errorInputVbox.setAlignment(Pos.CENTER);
    			
        			Scene sceneErr1 = new Scene(errorInputVbox, 550, 100);
        			sceneErr1.setFill(Color.CADETBLUE);
    				
    				secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr1);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    HBox srchStdLabelHbox = new HBox(searchStudentLabel);
					srchStdLabelHbox.setAlignment(Pos.CENTER);
					HBox stIdSrchHbox = new HBox(10, inputStudentIdLabel, studentIdText);
					stIdSrchHbox.setAlignment(Pos.CENTER);
					HBox srchByStIdButtonHbox = new HBox(srchStButton);
					srchByStIdButtonHbox.setAlignment(Pos.CENTER);
					VBox vboxSearch = new VBox(5, srchStdLabelHbox, stIdSrchHbox, srchByStIdButtonHbox);
				    
				    borderPane.setRight(null);
        			borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(vboxSearch);
				    }	
        		
	    		try {
	    			if (studentId.length() != 5 ) throw new InputError();
	    		} catch(InputError e) {
	    			Label errorInputLabel = new Label(e.getMessage());
	    			errorInputLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	    			okErrorButtonEn.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	    			okErrorButtonEn.setMinWidth(60);
	    			VBox errorInputVbox = new VBox(20, errorInputLabel, okErrorButtonEn);
	    			errorInputVbox.setAlignment(Pos.CENTER);
	    				    			
	    			Scene sceneErr2 = new Scene(errorInputVbox, 450, 100);
	    			sceneErr2.setFill(Color.CADETBLUE);
					
					secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr2);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    HBox srchStdLabelHbox = new HBox(searchStudentLabel);
					srchStdLabelHbox.setAlignment(Pos.CENTER);
					HBox stIdSrchHbox = new HBox(10, inputStudentIdLabel, studentIdText);
					stIdSrchHbox.setAlignment(Pos.CENTER);
					HBox srchByStIdButtonHbox = new HBox(srchStButton);
					srchByStIdButtonHbox.setAlignment(Pos.CENTER);
				    VBox vboxSearch = new VBox(5, srchStdLabelHbox, stIdSrchHbox, srchByStIdButtonHbox);
				    borderPane.setRight(null);
        			borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(vboxSearch);
	    		}
			}					
});
		
		okErrorButtonEn.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				studentIdText.setText("");
			}
		});
		
		dropEnButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
					sureButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	        		VBox vbox = new VBox(sureButton);
	        		vbox.setAlignment(Pos.CENTER);
					borderPane.setCenter(vbox);
			}
		});
		
		sureButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				String enId = enIdClickFound.getText();
				Enrollment enrollment = null;
				EnrollmentFile enFile = null;
				
				try {
					enFile = new EnrollmentFile("enrollment.dat");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					enFile.moveFilePointer(Integer.parseInt(enId)-1);
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
				try {
					enrollment = enFile.readEnrollmentInfo();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				enrollment.setEnrollmentId("");
				enrollment.setStudentId("");
				enrollment.setCourseId("");
				enrollment.setCourseName("");
				enrollment.setYear("");
				enrollment.setSemester("");
				enrollment.setGrade("");
				
				try {
					enFile.moveFilePointer(Integer.parseInt(enId) - 1);
				} catch (NumberFormatException | IOException e) {
					e.printStackTrace();
				}
				try {
					enFile.writeEnrollmentInfo(enrollment);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				borderPane.setTop(menuBar);
				borderPane.setCenter(null);
			}
		});	
		
		cancelEnButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				borderPane.setTop(menuBar);
				borderPane.setCenter(null);
				
			}
		});
	}
	
	public static void manageGrade(Stage primaryStage) throws IOException
	{
		ComboBox<String> yearComboBox = new ComboBox<>();
		ComboBox<String> semesterComboBox = new ComboBox<>();
		ComboBox<String> gradeCombo = new ComboBox<>();
		yearComboBox.getItems().setAll("2018", "2019", "2020", "2021");
		semesterComboBox.getItems().setAll("Spring", "Summer", "Fall", "Winter");
		yearComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		semesterComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		yearComboBox.setValue("2019");
		semesterComboBox.setValue("Spring");
		
		Label inputStudentIdLabel = new Label("Input Student ID");
		TextField studentIdText = new TextField();
		inputStudentIdLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		studentIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		TextField enIdClickFound = new TextField("-1");
		
		Button srchStButton = new Button("Search");
		Button editGradeButton = new Button("Edit Grade");
		Button cancelEditGradeButton = new Button("Cancel");
		Button updateGradeButton = new Button("Update Grade");
		Button okUpdateButton = new Button("OK");
		Button okErrorButtonRp = new Button("OK");
		srchStButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		editGradeButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		cancelEditGradeButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		updateGradeButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		okUpdateButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		
		GridPane gridPane = new GridPane();
		
		HBox srchStdLabelHbox = new HBox(10, inputStudentIdLabel, studentIdText, yearComboBox, semesterComboBox, srchStButton);
		srchStdLabelHbox.setAlignment(Pos.CENTER);
		borderPane.setCenter(srchStdLabelHbox);
	
		srchStButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{				
				int j = 0, k = 0, numberOfStdRecords = 0, numberOfEnRecords = 0;
				String[] enIdNumFound = null, csIdNumFound = null, csNameFound = null, yrFound = null, smFound = null, gradeFound = null;
				String stdIdInput, yearInput, semesterInput;
				StudentFile stFile = null;
				
				stdIdInput = studentIdText.getText();
				yearInput = yearComboBox.getValue();
				semesterInput = semesterComboBox.getValue();
				
				try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfStdRecords = stFile.getNumberOfRecords();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				EnrollmentFile enFile = null;
				try {
					enFile = new EnrollmentFile("enrollment.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				try {
					numberOfEnRecords = enFile.getNumberOfRecords();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Enrollment[] enrollment = null;
				enrollment = new Enrollment[numberOfEnRecords];
				enIdNumFound = new String[numberOfEnRecords];
				csIdNumFound = new String[numberOfEnRecords];
				csNameFound = new String[numberOfEnRecords];
				yrFound = new String[numberOfEnRecords];
				smFound = new String[numberOfEnRecords];
				gradeFound = new String[numberOfEnRecords];
				
				for (int i = 0; i < numberOfEnRecords; i++)
				{	try {
					enFile.moveFilePointer(i);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					try {
						enrollment[i] = enFile.readEnrollmentInfo();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						
					if (stdIdInput.equals(enrollment[i].getStudentId()) && yearInput.equals(enrollment[i].getYear())
					             			 && semesterInput.equals(enrollment[i].getSemester()) )
					{
						enIdNumFound[j] = enrollment[i].getEnrollmentId();
						csIdNumFound[j] = enrollment[i].getCourseId();
						csNameFound[j] = enrollment[i].getCourseName();
						yrFound[j] = enrollment[i].getYear();
						smFound[j] = enrollment[i].getSemester();
						gradeFound[j] = enrollment[i].getGrade();
					
						j++;
					}
				}
				
				Label srResultLabel = new Label("Search Result: (* means grade not assigned)");
				srResultLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				Label srResultHeadLabel = new Label("EnrollmentID  CourseID  CourseName       Year  Semester  Grade");
				srResultHeadLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				TextField[] resultFoundText = new TextField[j];
																        
		        gridPane.setPadding(new Insets(0,0,0,40));
				gridPane.setVgap(1);
				gridPane.setHgap(0);
				gridPane.add(srResultHeadLabel, 0, 0);
				
				HBox srResultLabelHbox = new HBox(srResultLabel);
				srResultLabelHbox.setPadding(new Insets(10,0,0,0));
				srResultLabelHbox.setAlignment(Pos.CENTER);
				
				for (k = 0; k < j; k++)
				{ 
					resultFoundText[k] = new TextField();
					resultFoundText[k].setText(enIdNumFound[k] + "           " + csIdNumFound[k] + "      "
				            + csNameFound[k] + "     " + yrFound[k] + "    " + smFound[k] + "    " + gradeFound[k]); 
					resultFoundText[k].setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					gridPane.add(resultFoundText[k], 0, k+1);
				}
				
				HBox editCancelButtonHbox = new HBox(20, editGradeButton, cancelEditGradeButton);
				editCancelButtonHbox.setAlignment(Pos.CENTER);
				editCancelButtonHbox.setPadding(new Insets(10,0,0,10));
				VBox enGradeVbox = new VBox(5, srResultLabelHbox, gridPane, editCancelButtonHbox);
				borderPane.setCenter(enGradeVbox);

				gridPane.getChildren().forEach(item -> 
				{
			        item.setOnMouseClicked(new EventHandler<MouseEvent>() 
			        {
			        	String enString;
			        	TextField enStringText;
			        	Integer rowIndex;
			        	String enIdClickString = "";
			        	
			            public void handle(MouseEvent event) 
			            {
			            	if (event.getClickCount() == 1) 
			                {
			                    javafx.scene.Node source = (javafx.scene.Node) event.getSource();
			                    Integer colIndex = GridPane.getColumnIndex(source);
			                    rowIndex = GridPane.getRowIndex(source);
			                }
			            	
			            	if (rowIndex >= 1)
			            	{
			            		enStringText = resultFoundText[rowIndex-1];
								enString = enStringText.getText();
								for (int i = 0; i < 5; i++)
								{
									enIdClickString += enString.charAt(i);
								}
								enIdClickFound.setText(enIdClickString);
			            	}
			            }
			        });
				});

				try {
					enFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				if (stdIdInput.length() == 5 && Integer.parseInt(stdIdInput) > numberOfStdRecords)
	        	{
        			Label errorInputLabel1 = new Label("This sutdent ID does not exist: ");
        			Label errorInputLabel2 = new Label(stdIdInput);
        			Label errorInputLabel3 = new Label(". Maximum student ID is ");
        			Label errorInputLabel4 = new Label(Integer.toString(numberOfStdRecords));
        			
        			errorInputLabel1.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel2.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel3.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			errorInputLabel4.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
        			okErrorButtonRp.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
        			okErrorButtonRp.setMinWidth(60);
        			
        			HBox errorInputHbox = new HBox(errorInputLabel1, errorInputLabel2, errorInputLabel3, errorInputLabel4);
        			errorInputHbox.setAlignment(Pos.CENTER);
        			VBox errorInputVbox = new VBox(20, errorInputHbox, okErrorButtonRp);
        			errorInputVbox.setAlignment(Pos.CENTER);
        			
        			borderPane.setRight(null);
        			borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			
        			Scene sceneErr1 = new Scene(errorInputVbox, 550, 100);
    				sceneErr1.setFill(Color.CADETBLUE);
    				
    				secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr1);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(srchStdLabelHbox);
				}	
        		
	    		try {
	    			if (stdIdInput.length() != 5 ) throw new InputError();
	    		} catch(InputError e) {
	    			Label errorInputLabel = new Label(e.getMessage());
	    			errorInputLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
	    			okErrorButtonRp.setStyle("-fx-font-size: 11pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0");
	    			okErrorButtonRp.setMinWidth(60);
	    			VBox errorInputVbox = new VBox(20, errorInputLabel, okErrorButtonRp);
	    			errorInputVbox.setAlignment(Pos.CENTER);
	    			
	    			borderPane.setRight(null);
        			borderPane.setBottom(null);
        			borderPane.setLeft(null);
	    			
	    			Scene sceneErr2 = new Scene(errorInputVbox, 450, 100);
	    			sceneErr2.setFill(Color.CADETBLUE);
					
					secondaryStage = new Stage(StageStyle.UTILITY);
				    secondaryStage.setScene(sceneErr2);
				    secondaryStage.setTitle("Message");

				    secondaryStage.setX(800); secondaryStage.setY(600);
				    secondaryStage.show();
				    
				    borderPane.setRight(null);
      	  		    borderPane.setBottom(null);
        			borderPane.setLeft(null);
        			borderPane.setCenter(srchStdLabelHbox);
	    		}
			}
		});
		
		okErrorButtonRp.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				studentIdText.setText("");
			}
		});
		
		editGradeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				String enId = enIdClickFound.getText();
				
				if (enId.equals("-1"))
				{	
					Label srResultLabel = new Label("Search Result: (* means grade not assigned)");
					srResultLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					Label srResultHeadLabel = new Label("EnrollmentID  CourseID  CourseName       Year  Semester  Grade");
					srResultHeadLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					HBox srResultLabelHbox = new HBox(srResultLabel);
					srResultLabelHbox.setPadding(new Insets(10,0,0,0));
					srResultLabelHbox.setAlignment(Pos.CENTER);
					
					HBox editCancelButtonHbox = new HBox(20, editGradeButton, cancelEditGradeButton);
					editCancelButtonHbox.setAlignment(Pos.CENTER);
					editCancelButtonHbox.setPadding(new Insets(10,0,0,10));
					VBox enGradeVbox = new VBox(5, srResultLabelHbox, gridPane, editCancelButtonHbox);
					borderPane.setCenter(enGradeVbox);
				}
				else 
				{
					Enrollment enrollment = null;
					EnrollmentFile enFile = null;
					try {
						enFile = new EnrollmentFile("enrollment.dat");
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					try {
						enFile.moveFilePointer(Integer.parseInt(enId) - 1);
					} catch (NumberFormatException | IOException e) {
						e.printStackTrace();
					}
					try {
						enrollment = enFile.readEnrollmentInfo();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Label enrollIdLabel = new Label("Enrollment ID");
					Label courseIdLabel = new Label("Course ID");
					Label chooseGradeLabel = new Label("Choose Grade");
					enrollIdLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					courseIdLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					chooseGradeLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					TextField enrollIdText = new TextField(enrollment.getEnrollmentId());
					TextField courseIdText = new TextField(enrollment.getCourseId());
					enrollIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
					courseIdText.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: darkgray");
					enrollIdText.setMaxWidth(70);
					courseIdText.setMaxWidth(70);
										
					gradeCombo.getItems().addAll("A", "B", "C", "D", "F");
					gradeCombo.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
					gradeCombo.setValue("A");
					
					HBox hbox = new HBox(5, enrollIdLabel, enrollIdText, courseIdLabel, courseIdText, chooseGradeLabel, gradeCombo, updateGradeButton);
					hbox.setPadding(new Insets(5,10,0,40));
					borderPane.setCenter(hbox);
					
					try {
						enFile.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});	 
		
		cancelEditGradeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				borderPane.setCenter(null);
				borderPane.setTop(menuBar);
				primaryStage.setScene(mainScene);
				primaryStage.setTitle("Student Information System");
				primaryStage.show();
			}
		});	
		
		updateGradeButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				String enId = enIdClickFound.getText(); 
								
				EnrollmentFile enFile = null;
				try {
					enFile = new EnrollmentFile("enrollment.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Enrollment[] enrollment = null;
				try {
					enrollment = new Enrollment[enFile.getNumberOfRecords()];
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				try {
					for (int i=0; i<enFile.getNumberOfRecords(); i++)
					{
						enFile.moveFilePointer(i);
						enrollment[i] = enFile.readEnrollmentInfo();
									
						if (enrollment[i].getEnrollmentId().equals(enId))
						{
							enrollment[i].setGrade(gradeCombo.getValue());
							enFile.moveFilePointer(i);
							enFile.writeEnrollmentInfo(enrollment[i]);
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}	
				
				Label okLabel = new Label("Grade has been updated.");
				okLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				VBox vbox = new VBox(10, okLabel, okUpdateButton);
				vbox.setAlignment(Pos.CENTER);
				BorderPane okBorderPane = new BorderPane();
				okBorderPane.setCenter(vbox);
				
				Scene okScene = new Scene(okBorderPane, 400,100);
				okScene.setFill(Color.CADETBLUE);
				 
			    secondaryStage = new Stage(StageStyle.UTILITY);
			    secondaryStage.setScene(okScene);
			    secondaryStage.setTitle("Message");

			    secondaryStage.setX(800); secondaryStage.setY(600);
			    secondaryStage.show();
			    
				try {
					enFile.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		
		okUpdateButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				secondaryStage.close();
				studentIdText.setText("");
				borderPane.setCenter(srchStdLabelHbox);
			}
		});
	}
	
	public static void generateReport(Stage primaryStage) throws IOException
	{
		Label inputCsIdYearLabel = new Label("Input Course Name and Year");
		Label courseLabel = new Label("--Course------------------------------");
		ComboBox<String> csNameComboBox = new ComboBox<>();
		ComboBox<String> yearComboBox = new ComboBox<>();
		
		inputCsIdYearLabel.setStyle("-fx-font-size: 9pt; -fx-font-weight: bold");
		courseLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		yearComboBox.getItems().setAll("2018", "2019", "2020", "2021");
		csNameComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		yearComboBox.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
		
		CourseFile csFile = null;
		try {
			csFile = new CourseFile("course.dat");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Course[] course = null;
		try {
			course = new Course[csFile.getNumberOfRecords()];
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			csNameComboBox.getItems().clear();
			for (int i=0; i < csFile.getNumberOfRecords(); i++)
			{
				csFile.moveFilePointer(i);
				course[i] = csFile.readCourseInfo();
				csNameComboBox.getItems().add(course[i].getCourseName());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    		
		csNameComboBox.setValue(course[1].getCourseName());
		yearComboBox.setValue("2020");
			
		Button reportSearchButton = new Button("Search");
		reportSearchButton.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold; -fx-background-color: #FFFFE0"); 
		
		VBox inputCsIdYearLabelVbox = new VBox(inputCsIdYearLabel);	
		inputCsIdYearLabelVbox.setMaxHeight(50);
		inputCsIdYearLabelVbox.setAlignment(Pos.CENTER);
		HBox courseHbox = new HBox(5, csNameComboBox, yearComboBox);
		VBox courseVbox = new VBox(courseLabel, courseHbox);
		VBox reportSearchButtonVbox = new VBox(reportSearchButton);
		reportSearchButtonVbox.setMaxHeight(50);
		reportSearchButtonVbox.setAlignment(Pos.CENTER);
		
		HBox reportHbox = new HBox(15, inputCsIdYearLabelVbox, courseVbox, reportSearchButtonVbox);
		reportHbox.setPadding(new Insets(5,0,0,60));
		borderPane.setCenter(reportHbox);
		
		reportSearchButton.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent event)
			{
				Label reportCourseLabel = new Label("Report for Course:");
				Label courseNameLabel = new Label(csNameComboBox.getValue());
				Label reportYearLabel = new Label("Year:"); 
				Label yearLabel = new Label(yearComboBox.getValue());
				Label gradeUnassignedLabel = new Label("(* : grade unassigned)");
				TextField reportHeadLabel = new TextField("studentId    studentName          semester    grade");
				
				reportCourseLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				courseNameLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				reportYearLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				yearLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				gradeUnassignedLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				reportHeadLabel.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				
				HBox hboxHead = new HBox(5, reportCourseLabel, courseNameLabel, reportYearLabel, yearLabel, gradeUnassignedLabel);
				hboxHead.setAlignment(Pos.CENTER);
				
				TextArea textArea = new TextArea();
				textArea.setPrefColumnCount(200);
				textArea.setPrefRowCount(650);
				
				String courseName, year;
				Student student;
				
				StudentFile stFile = null;
				try {
					stFile = new StudentFile("student.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				EnrollmentFile enFile = null;
				try {
					enFile = new EnrollmentFile("enrollment.dat");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				Enrollment[] enrollment = null;
				try {
					enrollment = new Enrollment[enFile.getNumberOfRecords()];
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				courseName = csNameComboBox.getValue();
				year = yearComboBox.getValue();
				
				String resultFound = reportHeadLabel.getText();
				try {
					for (int i=0; i<enFile.getNumberOfRecords(); i++)
					{
						enFile.moveFilePointer(i);
						enrollment[i] = enFile.readEnrollmentInfo();
						if (enrollment[i].getCourseName().equals(courseName) && enrollment[i].getYear().equals(year))
						{
							String studentName = "";
							for (int k=0; k<stFile.getNumberOfRecords(); k++)
							{
								stFile.moveFilePointer(k);
								student = stFile.readStudentInfo();
								if (enrollment[i].getStudentId().equals(student.getStId()))
									studentName = student.getFirstName().trim() + " " + student.getLastName();
							}
							resultFound += "\n" + enrollment[i].getStudentId() + "          " + studentName + "    " + 
					                   enrollment[i].getSemester() + "     " + enrollment[i].getGrade();
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}		
				
				textArea.setText(resultFound);
				textArea.setStyle("-fx-font-size: 10pt; -fx-font-weight: bold");
				VBox vboxFinal = new VBox(hboxHead, textArea);
				vboxFinal.setPadding(new Insets(20,0,0,0));
				borderPane.setCenter(vboxFinal);
			}
		});
	}
	
		
}		

