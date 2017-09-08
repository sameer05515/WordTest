package com.p.wd.test;



import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.p.wd.test.Test;


//import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
	
	//private static final String PERSON_COLLECTION = "wordscollection";

	public static void main(String[] args) {

		try {
			// For XML
			ApplicationContext ctx = new GenericXmlApplicationContext("spring.xml");

			// For Annotation
//		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringMongoConfig.class);
			MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
			
			List<Wordi> words= Test.getAllWords();
			
			System.out.println(words.size());
			
			for(Wordi w:words){
				
				System.out.println(w);
				
				ObjectMapper mapper = new ObjectMapper();
				StringWriter stringEmp = new StringWriter();
				mapper.writeValue(stringEmp, w);
				
				w.setId(null);
//			mongoOperation.insert(wt,PERSON_COLLECTION);
				mongoOperation.insert(w);
			}
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		User user = new User("mkyong", "password123");
//
//		// save
//		mongoOperation.save(user);
//
//		// now user object got the created id.
//		System.out.println("1. user : " + user);
//
//		// query to search user
//		Query searchUserQuery = new Query(Criteria.where("username").is("mkyong"));
//
//		// find the saved user again.
//		User savedUser = mongoOperation.findOne(searchUserQuery, User.class);
//		System.out.println("2. find - savedUser : " + savedUser);
//
//		// update password
//		mongoOperation.updateFirst(searchUserQuery, Update.update("password", "new password"),
//				User.class);
//
//		// find the updated user object
//		User updatedUser = mongoOperation.findOne(
//				new Query(Criteria.where("username").is("mkyong")), User.class);
//
//		System.out.println("3. updatedUser : " + updatedUser);
//
//		// delete
//		//mongoOperation.remove(searchUserQuery, User.class);
//
//		// List, it should be empty now.
//		List<User> listUser = mongoOperation.findAll(User.class);
//		System.out.println("4. Number of user = " + listUser.size());
//		System.out.println("4. list of users = " + listUser);

	}

}