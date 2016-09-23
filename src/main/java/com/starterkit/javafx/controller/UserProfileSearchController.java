package com.starterkit.javafx.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.starterkit.javafx.model.UserProfileSearch;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller for the person search screen.
 * <p>
 * The JavaFX runtime will inject corresponding objects in the @FXML annotated
 * fields. The @FXML annotated methods will be called by JavaFX runtime at
 * specific points in time.
 * </p>
 *
 * @author Daniel
 */
public class UserProfileSearchController {

	private static final Logger LOG = Logger.getLogger(UserProfileSearchController.class);

	/**
	 * Resource bundle loaded with this controller. JavaFX injects a resource
	 * bundle specified in {@link FXMLLoader#load(URL, ResourceBundle)} call.
	 * <p>
	 * NOTE: The variable name must be {@code resources}.
	 * </p>
	 */
	@FXML
	private ResourceBundle resources;

	/**
	 * URL of the loaded FXML file. JavaFX injects an URL specified in
	 * {@link FXMLLoader#load(URL, ResourceBundle)} call.
	 * <p>
	 * NOTE: The variable name must be {@code location}.
	 * </p>
	 */
	@FXML
	private URL location;

	/**
	 * JavaFX injects an object defined in FXML with the same "fx:id" as the
	 * variable name.
	 */
	@FXML
	private TextField userIdField;
	@FXML
	private TextField firstNameField;
	@FXML
	private TextField lastNameField;

	/*
	@FXML
	private ComboBox<Sex> sexField;
	*/
	
	@FXML
	private Button searchButton;
	@FXML
	private Button deleteButton;
	@FXML
	private Button editButton;

	@FXML
	private TableView<UserProfileVO> resultTable;

	@FXML
	private TableColumn<UserProfileVO, String> userIdColumn;
	@FXML
	private TableColumn<UserProfileVO, String> firstNameColumn;
	@FXML
	private TableColumn<UserProfileVO, String> lastNameColumn;

	private final DataProvider dataProvider = DataProvider.INSTANCE;


	private final UserProfileSearch model = new UserProfileSearch();

	/**
	 * The JavaFX runtime instantiates this controller.
	 * <p>
	 * The @FXML annotated fields are not yet initialized at this point.
	 * </p>
	 */
	public UserProfileSearchController() {
		LOG.debug("Constructor: userIdField = " + userIdField);
		LOG.debug("Constructor: firstNameField = " + firstNameField);
		LOG.debug("Constructor: lastNameField = " + lastNameField);
	}

	/**
	 * The JavaFX runtime calls this method after loading the FXML file.
	 * <p>
	 * The @FXML annotated fields are initialized at this point.
	 * </p>
	 * <p>
	 * NOTE: The method name must be {@code initialize}.
	 * </p>
	 */
	@FXML
	private void initialize() {
		LOG.debug("initialize(): userIdField = " + userIdField);
		LOG.debug("initialize(): firstNameField = " + firstNameField);
		LOG.debug("initialize(): lastNameField = " + lastNameField);

		initializeResultTable();

		/*
		 * Bind controls properties to model properties.
		 */
		userIdField.textProperty().bindBidirectional(model.userIdProperty());
		firstNameField.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.lastNameProperty());
		resultTable.itemsProperty().bind(model.resultProperty());

		/*
		 * Make the Search button inactive when the Name field is empty.
		 */
		searchButton.disableProperty().bind(userIdField.textProperty().isEmpty().and(firstNameField.textProperty().isEmpty().and(lastNameField.textProperty().isEmpty())));
		deleteButton.disableProperty().bind(resultTable.getSelectionModel().selectedItemProperty().isNull());
		editButton.disableProperty().bind(resultTable.getSelectionModel().selectedItemProperty().isNull());
	}
	private void initializeResultTable() {
		/*
		 * Define what properties of PersonVO will be displayed in different
		 * columns.
		 */
		userIdColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLogin()));
		firstNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getName()));
		lastNameColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getSurname()));

		/*
		 * Show specific text for an empty table. This can also be done in FXML.
		 */
		resultTable.setPlaceholder(new Label(resources.getString("table.emptyText")));

		/*
		 * When table's row gets selected say given person's name.
		 */
		resultTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserProfileVO>() {

			@Override
			public void changed(ObservableValue<? extends UserProfileVO> observable, UserProfileVO oldValue, UserProfileVO newValue) {
				LOG.debug(newValue + " selected");

				if (newValue != null) {
					Task<Void> backgroundTask = new Task<Void>() {

						@Override
						protected Void call() throws Exception {
							return null;
						}

						@Override
						protected void failed() {
							LOG.error("Could not open Edit Window: ", getException());
						}
					};
					new Thread(backgroundTask).start();
				}
 
			}
		});
	}

	/**
	 * The JavaFX runtime calls this method when the <b>Search</b> button is
	 * clicked.
	 *
	 * @param event
	 *            {@link ActionEvent} holding information about this event
	 */
	@FXML
	public void searchButtonAction(ActionEvent event) {
		LOG.debug("'Search' button clicked");

		searchButtonAction();
	}

	/**
	 * This implementation is correct.
	 * <p>
	 * The {@link DataProvider#findPersons(String, SexVO)} call is executed in a
	 * background thread.
	 * </p>
	 */
	private void searchButtonAction() {
		/*
		 * Use task to execute the potentially long running call in background
		 * (separate thread), so that the JavaFX Application Thread is not
		 * blocked.
		 */
		Task<Collection<UserProfileVO>> backgroundTask = new Task<Collection<UserProfileVO>>() {

			/**
			 * This method will be executed in a background thread.
			 */
			@Override
			protected Collection<UserProfileVO> call() throws Exception {
				LOG.debug("call() called");

				/*
				 * Get the data.
				 */
				Collection<UserProfileVO> result = dataProvider.findUsers( //
						model.getUserId(),
						model.getFirstName(),
						model.getLastName()); //
				LOG.debug("model : " + model);
				/*
				 * Value returned from this method is stored as a result of task
				 * execution.
				 */
				return result;
			}

			/**
			 * This method will be executed in the JavaFX Application Thread
			 * when the task finishes.
			 */
			@Override
			protected void succeeded() {
				LOG.debug("succeeded() called");

				/*
				 * Get result of the task execution.
				 */
				Collection<UserProfileVO> result = getValue();

				/*
				 * Copy the result to model.
				 */
				model.setResult(new ArrayList<UserProfileVO>(result));
				LOG.debug("result  :" + result);
				/*
				 * Reset sorting in the result table.
				 */
				resultTable.getSortOrder().clear();
			}
		};

		/*
		 * Start the background task. In real life projects some framework
		 * manages background tasks. You should never create a thread on your
		 * own.
		 */
		new Thread(backgroundTask).start();
	}

	@FXML
	private void deleteButtonAction(ActionEvent event) {
		LOG.debug("'Delete' button clicked");

		
		UserProfileVO user = resultTable.getSelectionModel().getSelectedItem();
		dataProvider.deleteUser(user.getId());
		searchButtonAction();
	}
	
	@FXML
	private void editButtonAction(ActionEvent event) throws Exception {
		LOG.debug("'Edit' button clicked");
		Stage secondaryStage = new Stage();
		
		UserProfileVO user = resultTable.getSelectionModel().getSelectedItem();
		
		int position = resultTable.getSelectionModel().getSelectedIndex();
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/starterkit/javafx/view/userProfile-edit.fxml"),
				ResourceBundle.getBundle("com/starterkit/javafx/bundle/base"));
		Parent root1 = (Parent) fxmlLoader.load();
       
        UserProfileEditController mainController = fxmlLoader.<UserProfileEditController> getController();
        mainController.init(user, position); 
		
				
		
		/*
		 * Set the default locale based on the '--lang' startup argument.
		 */

		secondaryStage.setTitle("StarterKit-JavaFX");
		
		/*
		 * Load screen from FXML file with specific language bundle (derived
		 * from default locale).
		 */
		//Parent tree = FXMLLoader.load(getClass().getResource("/com/starterkit/javafx/view/userProfile-edit.fxml"), //
		//		ResourceBundle.getBundle("com/starterkit/javafx/bundle/base"));

		Scene scene = new Scene(root1);

		/*
		 * Set the style sheet(s) for application.
		 */
		scene.getStylesheets().add(getClass().getResource("/com/starterkit/javafx/css/standard.css").toExternalForm());

		secondaryStage.setScene(scene);

		secondaryStage.show();
		
		secondaryStage.setOnHiding(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				searchButtonAction();
				
			}
		});
		
	}

}
