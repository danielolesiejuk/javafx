package com.starterkit.javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.starterkit.javafx.model.UserProfileEdit;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
public class UserProfileEditController {

	private static final Logger LOG = Logger.getLogger(UserProfileEditController.class);

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
	@FXML
	private TextField emailField;
	@FXML
	private TextField passwordField;
	@FXML
	private TextArea aboutMeArea;
	@FXML
	private TextArea mottoArea;

	
	@FXML
	private Button saveButton;
	@FXML
	private Button cancelButton;

	private final DataProvider dataProvider = DataProvider.INSTANCE;


	private final UserProfileEdit model = new UserProfileEdit();
	
	private UserProfileVO updatedUser = new UserProfileVO();
	int userIndex;

	/**
	 * The JavaFX runtime instantiates this controller.
	 * <p>
	 * The @FXML annotated fields are not yet initialized at this point.
	 * </p>
	 */
	public UserProfileEditController() {
		LOG.debug("Constructor: userIdField = " + userIdField);
		LOG.debug("Constructor: firstNameField = " + firstNameField);
		LOG.debug("Constructor: lastNameField = " + lastNameField);
		LOG.debug("Constructor: emailField = " + emailField);
		LOG.debug("Constructor: passwordField = " + passwordField);
		LOG.debug("Constructor: aboutMeArea = " + aboutMeArea);
		LOG.debug("Constructor: mottoArea = " + mottoArea);
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
	public void initialize() {
		LOG.debug("initialize(): userIdField = " + userIdField);
		LOG.debug("initialize(): firstNameField = " + firstNameField);
		LOG.debug("initialize(): lastNameField = " + lastNameField);
		LOG.debug("initialize(): emailField = " + emailField);
		LOG.debug("initialize(): passwordField = " + passwordField);
		LOG.debug("initialize(): aboutMeArea = " + aboutMeArea);
		LOG.debug("initialize(): mottoArea = " + mottoArea);
		/*
		 * Bind controls properties to model properties.
		 */

		userIdField.textProperty().bind(model.userIdProperty());
		firstNameField.textProperty().bindBidirectional(model.firstNameProperty());
		lastNameField.textProperty().bindBidirectional(model.lastNameProperty());
		emailField.textProperty().bindBidirectional(model.emailProperty());
		passwordField.textProperty().bindBidirectional(model.passwordProperty());
		aboutMeArea.textProperty().bindBidirectional(model.aboutMeProperty());
		mottoArea.textProperty().bindBidirectional(model.mottoProperty());
	
	}
	
	public void init(UserProfileVO user, int position) {
		LOG.debug("initialize(): userIdField = " + user.getLogin());
		LOG.debug("initialize(): firstNameField = " + user.getName());
		LOG.debug("initialize(): lastNameField = " + user.getSurname());
		LOG.debug("initialize(): emailField = " + user.getEmail());
		LOG.debug("initialize(): passwordField = " + user.getPassword());
		LOG.debug("initialize(): aboutMeArea = " + user.getAboutMe());
		LOG.debug("initialize(): mottoArea = " + user.getLifeMotto());
		
		
		model.idProperty().setValue(user.getId());
		model.userIdProperty().setValue(user.getLogin());
		model.firstNameProperty().setValue(user.getName());
		model.lastNameProperty().setValue(user.getSurname());
		model.emailProperty().setValue(user.getEmail());
		model.passwordProperty().setValue(user.getPassword());
		model.aboutMeProperty().setValue(user.getAboutMe());
		model.mottoProperty().setValue(user.getLifeMotto());
		
		userIndex = position;
		
	}
		
	/**
	 * The JavaFX runtime calls this method when the <b>Search</b> button is
	 * clicked.
	 *
	 * @param event
	 *            {@link ActionEvent} holding information about this event
	 */
	@FXML
	private void saveButtonAction(ActionEvent event) {
		LOG.debug("'Save' button clicked");

		saveButtonAction();
		
	}

	/**
	 * This implementation is correct.
	 * <p>
	 * The {@link DataProvider#findPersons(String, SexVO)} call is executed in a
	 * background thread.
	 * </p>
	 */
	
	private void saveButtonAction() {
		
		updatedUser.setId(model.getId());
		updatedUser.setLogin(model.getUserId());
		updatedUser.setName(model.getFirstName());
		updatedUser.setSurname(model.getLastName());
		updatedUser.setEmail(model.getEmail());
		updatedUser.setPassword(model.getPassword());
		updatedUser.setAboutMe(model.getAboutMe());
		updatedUser.setLifeMotto(model.getMotto());
				
		dataProvider.saveUser(updatedUser, userIndex);
				
		Stage stage = (Stage) saveButton.getScene().getWindow();
	    stage.close();
	}

	@FXML
	private void cancelButtonAction(ActionEvent event) {
		LOG.debug("'Cancel' button clicked");

		// cancelButtonAction();
		Stage stage = (Stage) cancelButton.getScene().getWindow();
	    stage.close();
		
	}
	
	

}
