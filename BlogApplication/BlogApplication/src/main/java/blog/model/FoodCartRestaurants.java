
package blog.model;

/**
 * Administrators is a simple, plain old java objects (POJO).
 * Well, almost (it extends {@link Persons}).
 */
public class FoodCartRestaurants extends Restaurants {
	protected boolean licensed;
	
	public FoodCartRestaurants(int restaurantId, String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			Companies company, boolean licensed) {
		super(restaurantId, name, description, menu, hours, active,
				cuisineType, street1, street2, city, state, zip,
				company);
		this.licensed = licensed;
	}
	
	public FoodCartRestaurants(int restaurantId) {
		super(restaurantId);
	}
	
	public FoodCartRestaurants(String name, String description, String menu, String hours, boolean active,
			CuisineType cuisineType, String street1, String street2, String city, String state, int zip,
			Companies company, boolean licensed) {
		super(name, description, menu, hours, active,
				cuisineType, street1, street2, city, state, zip,
				company);
		this.licensed = licensed;
	}

	public boolean isLicensed() {
		return licensed;
	}

	public void setLisenced(boolean licensed) {
		this.licensed = licensed;
	}
	
	
}
