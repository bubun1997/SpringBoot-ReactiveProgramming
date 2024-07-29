package com.soumya.main;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.soumya.main.model.Employee;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple4;

@SpringBootTest
class SpringBootWebfluxApplicationTests {

	@Test
	void contextLoads() {
		
		Flux<Employee> f1 = Flux.just(new Employee[] {
				
				Employee.builder().id(UUID.randomUUID().toString()).name("ABC").build(),
				Employee.builder().id(UUID.randomUUID().toString()).name("DEFJKL").build(),
				Employee.builder().id(UUID.randomUUID().toString()).name("MNO").build(),


				
		});
		
		Function<Employee, Integer> fun = Employee::getLength;
		
		Flux<? extends Integer> f2 = f1.map(fun);
		
		Flux<Employee> f3 = Flux.concat(Flux.just(new Employee(UUID.randomUUID().toString(),"Soumyajit"),
													 new Employee(UUID.randomUUID().toString(),"Ram")
													 ),f1
				                        );
		
		f3.subscribe(e -> System.err.println(e.getId()+" : "+e.getName()));
		
		System.err.println("---------------------------");
		
		Flux<Tuple4<Employee,Integer, Employee, String>> f4 = Flux.zip(f1, f2, f3, Flux.just("Hello","hii","bye","good bye"));
		
		f4.subscribe(tup -> {
			
			System.err.println(tup);
			
			Employee e1 = tup.getT1(),e2 = tup.getT3();
			Integer i = tup.getT2();
			String s = tup.getT4();
			
			System.err.println(e1);
			System.err.println(e2);
			System.err.println(i);
			System.err.println(s);
			
			System.err.println("***************************");
			
			

		});
		
		BiFunction<Employee, Integer, String> bIf = (e, nameLength) -> e.getName()+" : "+nameLength; 
		
        List<Employee> listOfEmployees = List.<Employee>of(
        		
													Employee.
													builder().
													id(UUID.randomUUID().toString()).
													name("Soumyajit").
													build(),
													
													Employee.
													builder().
													id(UUID.randomUUID().toString()).
													name("Roshni").
													build(),
													
													Employee.
													builder().
													id(UUID.randomUUID().toString()).
													name("Akhil").
													build()

        		
        										);	
        
        
        
           listOfEmployees.forEach(em -> {
        	   
        	   String result = bIf.apply(em, em.getName().length());
        	   
        	   System.err.println(result);
        	   
           
           });
	}

	
}
