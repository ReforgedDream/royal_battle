package com.royalbattle;

import exceptions.UsernameOutOfBoundsException;
import utils.Credentials;
import utils.SettingsConst;

public class GameChar {

	private final int id;
	private Credentials credentials;
	private int health;
	private int damage;
	private int rating;
	
	public GameChar(int id, String name, int passwordHash, int health, int damage, int rating) 
			throws UsernameOutOfBoundsException {
		
		try {
			this.setCredentials(new Credentials(name, passwordHash));
		} catch (UsernameOutOfBoundsException e) {
			throw new UsernameOutOfBoundsException();
		}
		
		this.id = id;
		this.setHealth(health);
		this.setDamage(damage);
		this.setRating(rating);
	}
	
	public GameChar(int id, String name, int passwordHash) throws UsernameOutOfBoundsException {
		this(id, name, passwordHash, SettingsConst.DEFAULT_HEALTH, SettingsConst.DEFAULT_DAMAGE, 0);
	}
	
	/**
	 * This is to conveniently create a new default character with given credentials
	 * ID would be auto-assigned in database
	 */
	public GameChar(Credentials credentials) {
		this.id = 0;
		this.setCredentials(credentials);
		this.setHealth(SettingsConst.DEFAULT_HEALTH);
		this.setDamage(SettingsConst.DEFAULT_DAMAGE);
		this.setRating(0);
	}

	/**
	 * Getters and setters
	 */
	public int getId() {
		return id;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}
	
	/**
	 * End of getters and setters
	 */
	
	@Override
	public String toString() {
		return "Name: " + this.getCredentials().getUsername() + ", " +
				"Rating: " + Integer.toString(this.getRating()) + ", " +
				"Health: " + Integer.toString(this.getHealth()) + ", " +
				"Damage: " + Integer.toString(this.getDamage());
		
	}
}
