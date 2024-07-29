package com.soumya.main;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.soumya.main.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;



@SpringBootApplication
public class SpringBootWebfluxApplication {

	public static void main(String[] args) {
		
		ApplicationContext context = SpringApplication.run(SpringBootWebfluxApplication.class, args);
		
//		Student s = context.getBean(Student.class);
//		
//		s.setName("Ram");
//		s.setRoll(26);
//		
//		Mono<Student> m1 = Mono.just(s);
//		
//		Mono<String> m2 = m1.map( student -> student.getName());
//		
//		System.err.println(Thread.currentThread().getName());
//		m2.subscribe(data -> {
//		
//			System.err.println(Thread.currentThread().getName());
//
//			System.err.println("called mono");
//			System.err.println(data);
//		});
//		
//		Mono<String[]> m4=Mono.just(new String[] {
//				 						"Hello","Hii","Bye"
//									});
//		
//		System.err.println(Thread.currentThread().getName());
//
//		m4.subscribe(strArr -> {
//			
//			System.err.println(Thread.currentThread().getName());
//
//			List<String> list = List.of(strArr);
//			
//			System.err.println("called subscriber");
//			
//			list.forEach(System.err::println);
//			
//		});
//		
//		Flux<String> flux =Flux.just("hii","how","are","you");
//		
//		System.err.println(Thread.currentThread().getName());
//
//		
//		flux.subscribe(obj -> {
//			
//			System.err.println(Thread.currentThread().getName());
//
//			System.err.println("called flux 1");
//			System.err.println(obj.toUpperCase());
//			});
//		
//		System.err.println("------------------------");
//		
//		System.err.println(Thread.currentThread().getName()+"-------------");

		
//		Mono<String> firstName = Mono.just("Soumyajit ");
//		Flux<String> lastName =  Flux.just("Banerjee");
//		
//		Flux<String> fullName = firstName.
//								concatWith(lastName).
//								delayElements(Duration.ofMillis(6000)).log();
//		
//		fullName.subscribe(data ->{
//			
//			System.err.println(Thread.currentThread().getName());
//
//			System.err.println("called flux 2");
//			System.err.println(data);
//			
//		});
//		
//		
//		 
//		
//		for(int i=1;i<=10;i++) {
//			
//			System.err.println(Thread.currentThread().getName()+" finished execution...");
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
//		System.err.println("Main terminated");
//		
//		
//		List<String> l =List.of("hii","hello"); 
//		
//        Flux<String> f =Flux.fromIterable(l);
//        
//        Flux<Integer> ff=f.map(str -> str.length());
//        ff.subscribe(i -> System.err.println(i));
//        
//        f.subscribe(str -> System.out.println(str));
//        
//        Flux<Integer> fInt = ff.filter(Int -> Int > 3);
//        
//        fInt.subscribe(System.out::println);
		
		
		  Flux<String> names = Flux.just(new String[] {
				  
				  "Ram","Shyam","Roshni","Priya","Soumyajit",
				  "Komal","Nayan","Rashid","Fatima","Nakul",
				  "Prithvi","Lokesh","Rohit","Kareena","Janhvi"
		  });
		  
		  
//		  Flux<Employee> employees = names.map( name -> 
//		             		  Employee.builder().
//		             		  id(UUID.randomUUID().toString()).
//		             		  name(name).
//		             		  build()
//		            ).log();
		  
		  
		  Flux<Employee> employees = names.map(Test::getEmployee).log();
		  
		  employees.subscribe(System.out::println);
		
		 // employees.flatMap( emp -> Mono.just(emp.getName()));
		  
		  System.out.println("--------------------------------------");
		  
        Flux<String> capNames = employees.transform( flux ->  {
        	 
        	     Flux<String> myFlux = null;

        	     myFlux = flux.map( e -> {
        	    	 
        	    	 return e.getName().toUpperCase();
        	    	 
        	     });
        	     
        	     return myFlux;
         })	;	
        
        capNames.subscribe(System.out::println);
		
	}

	class Test{
		
		static Employee getEmployee(String name) {
			
			return Employee.builder().
           		  id(UUID.randomUUID().toString()).
           		  name(name).
           		  build();
		}
	}
	

}
