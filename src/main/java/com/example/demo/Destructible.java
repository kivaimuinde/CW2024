package com.example.demo;

/**
 * The Destructible interface is implemented by classes whose objects can be
 * damaged and destroyed. It defines the actions of taking damage and being
 * destroyed.
 */
public interface Destructible {

	/**
	 * This method is called to inflict damage on an object implementing the
	 * Destructible interface.
	 */
	void takeDamage();

	/**
	 * This method is called to destroy an object implementing the Destructible
	 * interface, marking it as no longer functional or existing.
	 */
	void destroy();
}
