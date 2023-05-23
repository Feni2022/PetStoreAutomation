package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created for CRUD METHODS(create,read,update,delete requests the user API) IMPLEMENTATION

public class UserEndPoints2 {
	 
	// created for getting URL'S from properties file
	 static ResourceBundle getURL()
	{
		ResourceBundle routes = ResourceBundle.getBundle("routes"); // Load properties file
		return routes;
	}
	
	
	public static Response createUser(User payload) //User is pojo class and require payload parameter
	{ 
		String post_url=getURL().getString("post_url");
		
		
	Response response = given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(payload)
		
		.when()
		  .post(post_url); // it will return some response
	
	    return response;
	
	}
	
	public static Response readUser(String userName) // require userName parameter
	{ 
		String get_url=getURL().getString("get_url");
		
		
		
	Response response = given()
		               .pathParam("username", userName)
		
		.when()
		  .get(get_url); 
	
	    return response;
	
	}
	
	public static Response updateUser(String userName,User payload) // require both 
	{ 
		
		String update_url = getURL().getString("update_url");
		
	Response response = given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .pathParam("username",userName)
		  .body(payload)
		
		.when()
		  .put(update_url);
	
	    return response;
	
	}
	
	
	public static Response deleteUser(String userName) // require userName parameter
	{ 
		String delete_url=getURL().getString("delete_url");
		
		
		
	Response response = given()
		               .pathParam("username", userName)
		
		.when()
		  .delete(delete_url);
	
	    return response;
	
	}
	
	
	
	
	

}



