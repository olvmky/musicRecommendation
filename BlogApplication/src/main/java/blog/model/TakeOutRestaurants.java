package blog.model;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class TakeOutRestaurants extends Restaurants {
	protected int maxWaitTime;
	
	public TakeOutRestaurants(int restaurantId, String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			Companies company, int maxWaitTime) {
		super(restaurantId, name, description, menu, hours, active,
				cuisineType, street1, street2, city, state, zip,
				company);
		this.maxWaitTime = maxWaitTime;
	}
	
	public TakeOutRestaurants(int restaurantId) {
		super(restaurantId);
	}
	
	public TakeOutRestaurants(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			Companies company, int maxWaitTime) {
		super(name, description, menu, hours, active,
				cuisineType, street1, street2, city, state, zip,
				company);
		this.maxWaitTime = maxWaitTime;
	}

	public int getMaxWaitTime() {
		return maxWaitTime;
	}

	public void setMaxWaitTime(int maxWaitTime) {
		this.maxWaitTime = maxWaitTime;
	}

	
	
}
