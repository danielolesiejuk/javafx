package com.starterkit.javafx.model;

import java.util.ArrayList;
import java.util.List;

import com.starterkit.javafx.dataprovider.data.UserProfileVO;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;

/**
 * Data displayed on the person search screen.
 *
 * @author Daniel
 */
public class UserProfileSearch {

	private final StringProperty login = new SimpleStringProperty();
	private final StringProperty name = new SimpleStringProperty();
	private final StringProperty surname = new SimpleStringProperty();
	private final ListProperty<UserProfileVO> result = new SimpleListProperty<>(
			FXCollections.observableList(new ArrayList<>()));

	
	public final StringProperty userIdProperty() {
		return this.login;
	}
	
	public final String getUserId() {
		return this.userIdProperty().get();
	}
	
	public final void setUserId(final String login) {
		this.userIdProperty().set(login);
	}
	
	public final StringProperty firstNameProperty() {
		return this.name;
	}
	
	public final String getFirstName() {
		return this.firstNameProperty().get();
	}
	
	public final void setFirstName(final String name) {
		this.firstNameProperty().set(name);
	}
	
	public final StringProperty lastNameProperty() {
		return this.surname;
	}
	
	public final String getLastName() {
		return this.lastNameProperty().get();
	}
	
	public final void setLastName(final String surname) {
		this.lastNameProperty().set(surname);
	}
	
	public final List<UserProfileVO> getResult() {
		return result.get();
	}

	public final void setResult(List<UserProfileVO> value) {
		result.setAll(value);
	}

	public ListProperty<UserProfileVO> resultProperty() {
		return result;
	}

	@Override
	public String toString() {
		return "UserProfileSearch [login=" + login + ", name=" + name + ", surname=" + surname + "]";
	}

}
