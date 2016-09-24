package com.starterkit.javafx.dataprovider.impl;

import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.starterkit.javafx.dataprovider.DataProvider;
import com.starterkit.javafx.dataprovider.data.UserProfileVO;

/**
 * Provides data. Data is stored locally in this object. Additionally a call
 * delay is simulated.
 *
 * @author Daniel
 */
public class DataProviderImpl implements DataProvider {

	private static final Logger LOG = Logger.getLogger(DataProviderImpl.class);

	/**
	 * Delay (in ms) for method calls.
	 */
	private static final long CALL_DELAY = 3000;

	// REV: zakomentowany kod
	//private Collection<UserProfileVO> users = new ArrayList<>();

	public DataProviderImpl() {
		/*
		users.add(new UserProfileVO("login01","Imie01","Nazwisko01"));
		users.add(new UserProfileVO("login02","Imie02","Nazwisko02"));
		users.add(new UserProfileVO("login03","Imie03","Nazwisko03"));
		users.add(new UserProfileVO("login04","Imie04","Nazwisko04"));
		users.add(new UserProfileVO("login05","Imie05","Nazwisko05"));
		users.add(new UserProfileVO("login06","Imie06","Nazwisko06"));
		users.add(new UserProfileVO("login07","Imie07","Nazwisko07"));
		users.add(new UserProfileVO("login08","Imie08","Nazwisko08"));
		users.add(new UserProfileVO("login09","Imie09","Nazwisko09"));
		*/
	}

	@Override
	public Collection<UserProfileVO> findUsers(String login, String name, String surname) {
		LOG.debug("Entering findUsers()");

		RestTemplate restTemplate = new RestTemplate();
		
		// REV: adres powinien byc pobrany z konfiguracji
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8090/user/search");
		 
		// REV: nie trzeba porownywac do false
        if (login != null && login.isEmpty() == false) {
            builder.queryParam("login", login);
        }
        if (name != null && name.isEmpty() == false) {
            builder.queryParam("name", name);
        }
        if (surname != null && surname.isEmpty() == false) {
            builder.queryParam("surname", surname);
        }
        
        ResponseEntity<List<UserProfileVO>> response =
                restTemplate.exchange(builder.build().encode().toUri(),
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<UserProfileVO>>(){});
        List<UserProfileVO> users = response.getBody();
		
        
		/*
		 * Simulate a call delay.
		 */
		try {
			// REV: po co?
			Thread.sleep(CALL_DELAY);
		} catch (InterruptedException e) {
			throw new RuntimeException("Thread interrupted", e);
		}
/*
		Collection<UserProfileVO> result = users.stream().filter(u -> //
		((userId == null || userId.isEmpty()) || (userId != null && !userId.isEmpty() && u.getUserId().contains(userId))) //
				&& //
		((firstName == null || firstName.isEmpty()) || (firstName != null && !firstName.isEmpty() && u.getFirstName().contains(firstName))) //
				&& //
		((lastName == null || lastName.isEmpty()) || (lastName != null && !lastName.isEmpty() && u.getLastName().contains(lastName))) //
		).collect(Collectors.toList());
*/
		LOG.debug("Leaving findUsers()");
		return users;
		//return result;
	}

	@Override
	public void deleteUser(Long id) {
		LOG.debug("Entering deleteUser()");

		/*
		 * Simulate a call delay.
		 */
		try {
			// REV: j.w.
			Thread.sleep(CALL_DELAY);
		} catch (InterruptedException e) {
			throw new RuntimeException("Thread interrupted", e);
		}
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		// REV: j.w.
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8090/user/" + id);
		
		restTemplate.delete(builder.build().encode().toUri());
		
		 //users.remove(user);

		LOG.debug("Leaving deleteUser()");
		
		
	}
	
		
	@Override
	public void saveUser(UserProfileVO user, int position) {
		LOG.debug("Entering editUser()");

		/*
		 * Simulate a call delay.
		 */
		try {
			// REV: j.w.
			Thread.sleep(CALL_DELAY);
		} catch (InterruptedException e) {
			throw new RuntimeException("Thread interrupted", e);
		}
		
		
		//((ArrayList<UserProfileVO>) users).set(position, user);
		
		RestTemplate restTemplate = new RestTemplate();
		
		// REV: j.w.
		restTemplate.put("http://localhost:8090/user/", user);
		
		LOG.debug("Leaving editUser()");
		
		// return user;
	}
	
}
