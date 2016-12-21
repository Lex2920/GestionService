/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.gestion.departement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;


public class ServerLauncher {
	
	
	
	public static void main(String[] args) {
		//ApplicationContext context = new ClassPathXmlApplicationContext("server-beans.xml");
		
		 AnnotationConfigApplicationContext ctx =
			     new AnnotationConfigApplicationContext();
		 
		 ctx.register(SpringConfiguration.class);
		 ctx.refresh();
		 		
		System.out.println("Waiting for requests");
	}
}

