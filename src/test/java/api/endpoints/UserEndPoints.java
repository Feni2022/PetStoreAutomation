package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

// Created for CRUD METHODS(create,read,update,delete requests the user API) IMPLEMENTATION

public class UserEndPoints {
	
	public static Response createUser(User payload) //User is pojo class and require payload parameter
	{ 
	Response response = given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(payload)
		
		.when()
		  .post(Routes.post_url); // it will return some response
	
	    return response;
	
	}
	
	public static Response readUser(String userName) // require userName parameter
	{ 
	Response response = given()
		               .pathParam("username", userName)
		
		.when()
		  .get(Routes.get_url); 
	
	    return response;
	
	}
	
	public static Response updateUser(String userName,User payload) // require both 
	{ 
	Response response = given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .pathParam("username",userName)
		  .body(payload)
		
		.when()
		  .put(Routes.update_url);
	
	    return response;
	
	}
	
	
	public static Response deleteUser(String userName) // require userName parameter
	{ 
	Response response = given()
		               .pathParam("username", userName)
		
		.when()
		  .delete(Routes.delete_url);
	
	    return response;
	
	}
	
	
	
	
	

}



