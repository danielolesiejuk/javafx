package com.starterkit.javafx.dataprovider;

import java.util.Collection;

import com.starterkit.javafx.dataprovider.data.UserProfileVO;
import com.starterkit.javafx.dataprovider.impl.DataProviderImpl;

/**
 * Provides data.
 *
 * @author Daniel
 */
public interface DataProvider {

	/**
	 * Instance of this interface.
	 */
	DataProvider INSTANCE = new DataProviderImpl();

	/**
	 * Finds users with their name containing specified login, name, surname
	 * .
	 *
	 * @param login, name, surname
	 *            strings contained in login, name, surname
	 *            
	 * @return collection of users matching the given criteria
	 */
	
	Collection<UserProfileVO> findUsers(String userId, String firstName, String lastName);
	
	void deleteUser(Long id);

	void saveUser(UserProfileVO user, int position);

}
