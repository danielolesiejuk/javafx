package com.starterkit.javafx.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Data displayed on the person edit screen.
 *
 * @author Daniel
 */
public class UserProfileEdit {

	private final LongProperty id = new SimpleLongProperty();
	private final StringProperty login = new SimpleStringProperty();
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty surname = new SimpleStringProperty();
	private final StringProperty email = new SimpleStringProperty();
	private final StringProperty password = new SimpleStringProperty();
	private final StringProperty aboutMe = new SimpleStringProperty();
	private final StringProperty lifeMotto = new SimpleStringProperty();
	
	// REV: nazwy metod poiwnny byc zgodne z nazwami pol
	public final StringProperty userIdProperty() {
		return this.login;
	}
	
	public final String getUserId() {
		return this.userIdProperty().get();
	}
	
	public final void setUserId(final String userId) {
		this.userIdProperty().set(userId);
	}
	
	public final StringProperty firstNameProperty() {
		return this.name;
	}
	
	public final String getFirstName() {
		return this.firstNameProperty().get();
	}
	
	public final void setFirstName(final String firstName) {
		this.firstNameProperty().set(firstName);
	}
	
	public final StringProperty lastNameProperty() {
		return this.surname;
	}
	
	public final String getLastName() {
		return this.lastNameProperty().get();
	}
	
	public final void setLastName(final String lastName) {
		this.lastNameProperty().set(lastName);
	}
	
	public final StringProperty emailProperty() {
		return this.email;
	}
	
	public final String getEmail() {
		return this.emailProperty().get();
	}
	
	public final void setEmail(final String email) {
		this.emailProperty().set(email);
	}
	
	public final StringProperty passwordProperty() {
		return this.password;
	}
	
	public final String getPassword() {
		return this.passwordProperty().get();
	}
	
	public final void setPassword(final String password) {
		this.passwordProperty().set(password);
	}
	
	public final StringProperty aboutMeProperty() {
		return this.aboutMe;
	}
	
	public final String getAboutMe() {
		return this.aboutMeProperty().get();
	}
	
	public final void setAboutMe(final String aboutMe) {
		this.aboutMeProperty().set(aboutMe);
	}
	
	public final StringProperty mottoProperty() {
		return this.lifeMotto;
	}
	
	public final String getMotto() {
		return this.mottoProperty().get();
	}
	
	public final void setMotto(final String motto) {
		this.mottoProperty().set(motto);
	}

	public final LongProperty idProperty() {
		return this.id;
	}
	

	public final long getId() {
		return this.idProperty().get();
	}
	

	public final void setId(final long id) {
		this.idProperty().set(id);
	}

	@Override
	public String toString() {
		return "UserProfileEdit [id=" + id + ", login=" + login + ", name=" + name + ", surname=" + surname + ", email="
				+ email + ", password=" + password + ", aboutMe=" + aboutMe + ", lifeMotto=" + lifeMotto + "]";
	}
	

	
	
}
