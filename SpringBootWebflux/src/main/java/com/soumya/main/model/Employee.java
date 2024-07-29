package com.soumya.main.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	private String id;
	
	private String name;
	
   public int getLength() {
	   
	   return this.name.length();
   }
   
   public static int getLength1(Employee e) {
	   
	   return e.name.length();
   }

}

