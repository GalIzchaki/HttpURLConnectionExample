import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//Building REST services with Spring - https://spring.io/guides/tutorials/rest/
//How to create a REST API using Java Spring Boot - https://www.geeksforgeeks.org/how-to-create-a-rest-api-using-java-spring-boot/



public class HttpURLConnectionExample {

// Using GET method:
	public static void main(String[] args) throws IOException {
		URL obj = new URL("https://gen.demo.maryoku.net/campaigns/generate");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		
		System.out.println("GET Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response);
		} else {
			System.out.println("GET request did not work.");
		}
	}

// Using POST method:
	public static void main2(String[] args) throws IOException {
		URL obj = new URL("https://gen.demo.maryoku.net/campaigns/generate");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("content-type", "application/json");

		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write("{\"concept_name\": \"Hakuna Matata\", \"concept_description\": \"fun party at the jungle\", \"campaign_tone\": \"friendly\", \"campaign_type\": 3}".getBytes());
		os.flush();
		os.close();

		int responseCode = con.getResponseCode();
		
		System.out.println("Post Response Code :: " + responseCode);

		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
			
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			System.out.println(response);
		} else {
			System.out.println("POST request did not work.");
		}
	}


	// package payroll;
	// import java.util.Objects;
	// import javax.persistence.Entity;
	// import javax.persistence.GeneratedValue;
	// import javax.persistence.Id;

// 	@Entity // is a JPA annotation to make this object ready for storage in a JPA-based data store.
// 	class Employee {

// 	private @Id @GeneratedValue Long id;
// 	private String name;
// 	private String role; //role,name,id are attributes of our Employee domain object. 
// 	//id is marked with more JPA annotations to indicate it’s the primary key and automatically populated by the JPA provider.

// 	Employee() {}

// 	Employee(String name, String role) {

// 		this.name = name;
// 		this.role = role;
// 	}

// 	public Long getId() {
// 		return this.id;
// 	}

// 	public String getName() {
// 		return this.name;
// 	}

// 	public String getRole() {
// 		return this.role;
// 	}

// 	public void setId(Long id) {
// 		this.id = id;
// 	}

// 	public void setName(String name) {
// 		this.name = name;
// 	}

// 	public void setRole(String role) {
// 		this.role = role;
// 	}

// 	@Override
// 	public boolean equals(Object o) {

// 		if (this == o)
// 		return true;
// 		if (!(o instanceof Employee))
// 		return false;
// 		Employee employee = (Employee) o;
// 		return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
// 			&& Objects.equals(this.role, employee.role);
// 	}

// 	@Override
// 	public int hashCode() {
// 		return Objects.hash(this.id, this.name, this.role);
// 	}

// 	@Override
// 	public String toString() {
// 		return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
// 	}
// }
// //this is only defines an Employee in our system


// 	//package payroll;
// 	//import org.springframework.data.jpa.repository.JpaRepository;
// 	interface EmployeeRepository extends JpaRepository<Employee, Long> { }


// 	//This tells Spring Boot to help out, wherever possible.
// 	//package payroll;
// 	//import org.springframework.boot.SpringApplication;
// 	//import org.springframework.boot.autoconfigure.SpringBootApplication;
// 	@SpringBootApplication
// 	public class PayrollApplication {

// 		public static void main(String... args) {
// 			SpringApplication.run(PayrollApplication.class, args);
// 		}
// 	}


// 	//he following class will get loaded automatically by Spring:
// 	//package payroll;
// 	// import org.slf4j.Logger;
// 	// import org.slf4j.LoggerFactory;
// 	// import org.springframework.boot.CommandLineRunner;
// 	// import org.springframework.context.annotation.Bean;
// 	// import org.springframework.context.annotation.Configuration;
// 	@Configuration
// 	class LoadDatabase {
// 		private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
// 		@Bean
// 		CommandLineRunner initDatabase(EmployeeRepository repository) {
// 			return args -> {
// 			log.info("Preloading " + repository.save(new Employee("Bilbo Baggins", "burglar")));
// 			log.info("Preloading " + repository.save(new Employee("Frodo Baggins", "thief")));
// 			};
// 		}
// 	}

// 	//To wrap your repository with a web layer, you must turn to Spring MVC. Thanks to Spring Boot, there is little in infrastructure to code. Instead, we can focus on actions:
// 	// package payroll;
// 	// import java.util.List;
// 	// import org.springframework.web.bind.annotation.DeleteMapping;
// 	// import org.springframework.web.bind.annotation.GetMapping;
// 	// import org.springframework.web.bind.annotation.PathVariable;
// 	// import org.springframework.web.bind.annotation.PostMapping;
// 	// import org.springframework.web.bind.annotation.PutMapping;
// 	// import org.springframework.web.bind.annotation.RequestBody;
// 	// import org.springframework.web.bind.annotation.RestController;

// 	@RestController
// 	class EmployeeController {

// 	private final EmployeeRepository repository;

// 	EmployeeController(EmployeeRepository repository) {
// 		this.repository = repository;
// 	}


// 	// Aggregate root
// 	// tag::get-aggregate-root[]
// 	@GetMapping("/employees")
// 	List<Employee> all() {
// 		return repository.findAll();
// 	}
// 	// end::get-aggregate-root[]

// 	@PostMapping("/employees")
// 	Employee newEmployee(@RequestBody Employee newEmployee) {
// 		return repository.save(newEmployee);
// 	}

// 	// Single item
	
// 	@GetMapping("/employees/{id}")
// 	Employee one(@PathVariable Long id) {
		
// 		return repository.findById(id)
// 		.orElseThrow(() -> new EmployeeNotFoundException(id));
// 	}

// 	@PutMapping("/employees/{id}")
// 	Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		
// 		return repository.findById(id)
// 		.map(employee -> {
// 			employee.setName(newEmployee.getName());
// 			employee.setRole(newEmployee.getRole());
// 			return repository.save(employee);
// 		})
// 		.orElseGet(() -> {
// 			newEmployee.setId(id);
// 			return repository.save(newEmployee);
// 		});
// 	}

// 	@DeleteMapping("/employees/{id}")
// 	void deleteEmployee(@PathVariable Long id) {
// 		repository.deleteById(id);
// 	}
// 	}


// //
// class EmployeeNotFoundException extends RuntimeException {

// 	EmployeeNotFoundException(Long id) {
// 	  super("Could not find employee " + id);
// 	}
//   }

//   //
// 	@ControllerAdvice
// 	class EmployeeNotFoundAdvice {

// 	@ResponseBody
// 	@ExceptionHandler(EmployeeNotFoundException.class)
// 	@ResponseStatus(HttpStatus.NOT_FOUND)
// 	String employeeNotFoundHandler(EmployeeNotFoundException ex) {
// 		return ex.getMessage();
//   }
// }





}