package com.royalbattle;

import utils.SettingsConst;

public class GameChar {

	private final int id;
	private String name;
	private String password;
	private int health;
	private int damage;
	private int rating;
	
	public GameChar(int id, String name, String password, int health, int damage, int rating) {
		this.id = id;
		this.setName(name);
		this.setPassword(password);
		this.setHealth(health);
		this.setDamage(damage);
		this.setRating(rating);
	}
	
	public GameChar(int id, String name, String password) {
		this(id, name, password, SettingsConst.DEFAULT_HEALTH, SettingsConst.DEFAULT_DAMAGE, 0);
	}

	/**
	 * Getters and setters
	 */
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	/**
	 * End of getters and setters
	 */
}
