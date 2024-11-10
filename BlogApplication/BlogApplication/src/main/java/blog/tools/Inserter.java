// Jingtao Han
package blog.tools;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import blog.dal.*;
import blog.model.*;

public class Inserter {

    public static void main(String[] args) throws SQLException {
        // DAO instances
        UsersDao usersDao = UsersDao.getInstance();
        CreditCardsDao creditCardsDao = CreditCardsDao.getInstance();
        CompaniesDao companiesDao = CompaniesDao.getInstance();
        RestaurantsDao restaurantsDao = RestaurantsDao.getInstance();
        SitDownRestaurantsDao sitDownRestaurantsDao = SitDownRestaurantsDao.getInstance();
        TakeOutRestaurantsDao takeOutRestaurantsDao = TakeOutRestaurantsDao.getInstance();
        FoodCartRestaurantsDao foodCartRestaurantsDao = FoodCartRestaurantsDao.getInstance();
        ReviewsDao reviewsDao = ReviewsDao.getInstance();
        RecommendationsDao recommendationsDao = RecommendationsDao.getInstance();
        ReservationsDao reservationsDao = ReservationsDao.getInstance();

        // Test Users
        Users user1 = new Users("user123", "password1", "First", "User", "user1@example.com", "1234567890");
        user1 = usersDao.create(user1);
        System.out.format("Created User: u:%s f:%s l:%s \n", user1.getUserName(), user1.getFirstName(), user1.getLastName());

        Users fetchedUser = usersDao.getUserByUserName("user123");
        System.out.format("Fetched User: u:%s f:%s l:%s \n", fetchedUser.getUserName(), fetchedUser.getFirstName(), fetchedUser.getLastName());

        // Test CreditCards
        CreditCards creditCard1 = new CreditCards(1234567890123456L, new Date(System.currentTimeMillis() + 365*24*60*60*1000L), user1);
        creditCard1 = creditCardsDao.create(creditCard1);
        System.out.format("Created Credit Card for User: %s\n", creditCard1.getUser().getUserName());

        CreditCards fetchedCard = creditCardsDao.getCreditCardByCardNumber(1234567890123456L);
        System.out.format("Fetched Credit Card for User: %s\n", fetchedCard.getUser().getUserName());

        List<CreditCards> userCards = creditCardsDao.getCreditCardsByUserName("user123");
        System.out.format("User %s has %d credit cards.\n", "user123", userCards.size());

        creditCard1 = creditCardsDao.updateExpiration(creditCard1, new Date(System.currentTimeMillis() + 2*365*24*60*60*1000L));
        System.out.format("Updated Expiration Date for Card: %d\n", creditCard1.getCardNumber());

        creditCardsDao.delete(creditCard1);
        System.out.println("Deleted Credit Card for User: " + creditCard1.getUser().getUserName());

        // Test Companies
        Companies company1 = new Companies("Company A", "About Company A");
        company1 = companiesDao.create(company1);
        System.out.format("Created Company: %s\n", company1.getCompanyName());

        Companies fetchedCompany = companiesDao.getCompanyByCompanyName("Company A");
        System.out.format("Fetched Company: %s\n", fetchedCompany.getCompanyName());

        companiesDao.updateAbout(company1, "New About Company A");
        System.out.format("Updated Company Info: %s\n", company1.getCompanyName());


        // Test Restaurants
        Restaurants restaurant1 = new Restaurants(
        	    "Restaurant A",              // Name
        	    "A cozy place serving African cuisine.", // Description
        	    "Menu A",                    // Menu
        	    "Mon-Fri: 8am - 10pm, Sat-Sun: 9am - 11pm", // Hours
        	    true,                        // Active
        	    Restaurants.CuisineType.AFRICAN, // CuisineType
        	    "123 Main St",              // Street1
        	    "Suite 200",                // Street2 (optional)
        	    "Seattle",                  // City
        	    "WA",                       // State
        	    98101,                      // Zip
        	    fetchedCompany // CompanyName (assuming fetchedCompany is not null)
        	);        
        restaurant1 = restaurantsDao.create(restaurant1);
        System.out.format("Created Restaurant: %s\n", restaurant1.getName());

        Restaurants fetchedRestaurant = restaurantsDao.getRestaurantById(restaurant1.getRestaurantId());
        System.out.format("Fetched Restaurant: %s\n", fetchedRestaurant.getName());

        List<Restaurants> africanRestaurants = restaurantsDao.getRestaurantsByCuisine(Restaurants.CuisineType.AFRICAN);
        System.out.format("Found %d African Restaurants.\n", africanRestaurants.size());
        
        List<Restaurants> restaurants = restaurantsDao.getRestaurantsByCompanyName("Company A");
        System.out.format("Found %d Restaurants for Company A.\n", restaurants.size());

        // Test SitDownRestaurants
        SitDownRestaurants sitDownRestaurant1 = new SitDownRestaurants(
        	    "Taste of Asia",            // Name
        	    "Authentic Asian cuisine with a modern twist.", // Description
        	    "Menu B",                  // Menu
        	    "Mon-Sun: 10am - 10pm",    // Hours
        	    true,                      // Active
        	    Restaurants.CuisineType.ASIAN, // CuisineType
        	    "456 Elm St",              // Street1
        	    null,                      // Street2 (optional)
        	    "San Francisco",           // City
        	    "CA",                      // State
        	    94105,                    // Zip
        	    fetchedCompany, // CompanyName
        	    200
        	);
        sitDownRestaurant1 = sitDownRestaurantsDao.create(sitDownRestaurant1);
        System.out.format("Created SitDown Restaurant: %s\n", sitDownRestaurant1.getName());

        SitDownRestaurants fetchedSitDownRestaurant = sitDownRestaurantsDao.getSitDownRestaurantById(sitDownRestaurant1.getRestaurantId());
        System.out.format("Fetched SitDown Restaurant: %s\n", fetchedSitDownRestaurant.getName());

        List<SitDownRestaurants> sitDownRestaurants = sitDownRestaurantsDao.getSitDownRestaurantsByCompanyName("Company A");
        System.out.format("Found %d SitDown Restaurants for Company A.\n", sitDownRestaurants.size());

        // Test TakeOutRestaurants
        TakeOutRestaurants takeOutRestaurant1 = new TakeOutRestaurants(
        	    "Italian Bistro",          // Name
        	    "A warm Italian bistro serving classic dishes.", // Description
        	    "Menu C",                  // Menu
        	    "Tue-Sun: 5pm - 11pm",     // Hours
        	    true,                      // Active
        	    Restaurants.CuisineType.EUROPEAN, // CuisineType
        	    "789 Pine St",             // Street1
        	    "Apt 3",                  // Street2 (optional)
        	    "New York",                // City
        	    "NY",                      // State
        	    10001,                    // Zip
        	    fetchedCompany, // CompanyName
        	    60
        	);
        takeOutRestaurant1 = takeOutRestaurantsDao.create(takeOutRestaurant1);
        System.out.format("Created TakeOut Restaurant: %s\n", takeOutRestaurant1.getName());

        TakeOutRestaurants fetchedTakeOutRestaurant = takeOutRestaurantsDao.getTakeOutRestaurantById(takeOutRestaurant1.getRestaurantId());
        System.out.format("Fetched TakeOut Restaurant: %s\n", fetchedTakeOutRestaurant.getName());

        List<TakeOutRestaurants> takeOutRestaurants = takeOutRestaurantsDao.getTakeOutRestaurantsByCompanyName("Company A");
        System.out.format("Found %d TakeOut Restaurants for Company A.\n", takeOutRestaurants.size());

        takeOutRestaurantsDao.delete(takeOutRestaurant1);
        System.out.println("Deleted TakeOut Restaurant: " + takeOutRestaurant1.getName());

        // Test FoodCartRestaurants
        FoodCartRestaurants foodCartRestaurant1 = new FoodCartRestaurants(
        	    "Fiesta Mexicana",         // Name
        	    "Vibrant Mexican restaurant with a festive atmosphere.", // Description
        	    "Menu D",                  // Menu
        	    "Mon-Sat: 11am - 10pm",    // Hours
        	    true,                      // Active
        	    Restaurants.CuisineType.HISPANIC, // CuisineType
        	    "321 Oak St",              // Street1
        	    "Building 2",             // Street2 (optional)
        	    "Miami",                   // City
        	    "FL",                      // State
        	    33101,                    // Zip
        	    fetchedCompany, // CompanyName
        	    true
        	);
        
        foodCartRestaurant1 = foodCartRestaurantsDao.create(foodCartRestaurant1);
        System.out.format("Created FoodCart Restaurant: %s\n", foodCartRestaurant1.getName());

        FoodCartRestaurants fetchedFoodCartRestaurant = foodCartRestaurantsDao.getFoodCartRestaurantById(foodCartRestaurant1.getRestaurantId());
        System.out.format("Fetched FoodCart Restaurant: %s\n", fetchedFoodCartRestaurant.getName());

        List<FoodCartRestaurants> foodCartRestaurants = foodCartRestaurantsDao.getFoodCartRestaurantsByCompanyName("Company A");
        System.out.format("Found %d FoodCart Restaurants for Company A.\n", foodCartRestaurants.size());

        foodCartRestaurantsDao.delete(foodCartRestaurant1);
        System.out.println("Deleted FoodCart Restaurant: " + foodCartRestaurant1.getName());
        
        // Test Reviews
        Reviews review1 = new Reviews(new Date(System.currentTimeMillis()), "This restaurant is great!", 5, user1, restaurant1);
        review1 = reviewsDao.create(review1);
        System.out.format("Created Review: %s\n", review1.getContent());

        Reviews fetchedReview = reviewsDao.getReviewById(review1.getReviewId());
        System.out.format("Fetched Review: %s\n", fetchedReview.getContent());

        List<Reviews> userReviews = reviewsDao.getReviewsByUserName(user1.getUserName());
        System.out.format("User %s has %d reviews.\n", user1.getUserName(), userReviews.size());

        List<Reviews> restaurantReviews = reviewsDao.getReviewsByRestaurantId(1);
        System.out.format("Found %d reviews for Restaurant ID 1.\n", restaurantReviews.size());

        reviewsDao.delete(review1);
        System.out.println("Deleted Review: " + review1.getContent());

        // Test Recommendations
        Recommendations recommendation1 = new Recommendations(user1, restaurant1);
        recommendation1 = recommendationsDao.create(recommendation1);
        System.out.format("Created Recommendation: %s\n", recommendation1.getRecommendationId());

        Recommendations fetchedRecommendation = recommendationsDao.getRecommendationById(recommendation1.getRecommendationId());
        System.out.format("Fetched Recommendation: %s\n", fetchedRecommendation.getRecommendationId());

        List<Recommendations> userRecommendations = recommendationsDao.getRecommendationsByUserName(user1.getUserName());
        System.out.format("User %s has %d recommendations.\n", user1.getUserName(), userRecommendations.size());

        List<Recommendations> restaurantRecommendations = recommendationsDao.getRecommendationsByRestaurantId(1);
        System.out.format("Found %d recommendations for Restaurant ID 1.\n", restaurantRecommendations.size());

        recommendationsDao.delete(recommendation1);
        System.out.println("Deleted Recommendation: " + recommendation1.getRecommendationId());

        // Test Reservations
        Reservations reservation1 = new Reservations(new Date(System.currentTimeMillis() + 1000000), new Date(System.currentTimeMillis() + 2000000), 3, user1, sitDownRestaurant1);
        reservation1 = reservationsDao.create(reservation1);
        System.out.format("Created Reservation for User: %s\n", reservation1.getUser().getUserName());

        Reservations fetchedReservation = reservationsDao.getReservationById(reservation1.getReservationId());
        System.out.format("Fetched Reservation for User: %s\n", fetchedReservation.getUser().getUserName());

        List<Reservations> userReservations = reservationsDao.getReservationsByUserName(user1.getUserName());
        System.out.format("User %s has %d reservations.\n", user1.getUserName(), userReservations.size());

        List<Reservations> sitDownReservations = reservationsDao.getReservationsBySitDownRestaurantId(1);
        System.out.format("Found %d reservations for SitDown Restaurant ID 1.\n", sitDownReservations.size());

        reservationsDao.delete(reservation1);
        System.out.println("Deleted Reservation for User: " + reservation1.getUser().getUserName());

        // Clean up
        sitDownRestaurantsDao.delete(sitDownRestaurant1);
        System.out.println("Deleted SitDown Restaurant: " + sitDownRestaurant1.getName());
        
        restaurantsDao.delete(restaurant1);
        System.out.println("Deleted Restaurant: " + restaurant1.getName());
        
        usersDao.delete(user1);
        System.out.println("Deleted User: " + user1.getUserName());
        
        companiesDao.delete(company1);
        System.out.println("Deleted Company: " + company1.getCompanyName());
    }
}