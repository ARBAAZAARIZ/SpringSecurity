Nexus Bank Document
Setting up Backend
1.Open Spring Initializer and add required dependencies like
•	Spring Web
•	Spring Devtools
•	Spring Jpa
•	MySQL Driver
•	Validation
•	Mail Sender
•	Lombok
•	Spring Security
 
2. Create Database 
 
3. Add Details of database into application.properties (Spring Boot Project)

4. Discussing about services with security and without security
•	Without Security

/contact – This Service should accept the details from the Contact Us page int the UI and save to the DB.

/notices – This Service should send the notice details from DB to the ‘NOTICES’ page in UI.

•	With Security
/myAccount - This Service should send the account details of the logged in user from the DB to UI.
/myBalance – This service should send the balance and transaction details of the logged in user from the DB to UI.
/myLoans - This Service should send the loan details of the logged in user from the DB to UI.
/myCards – This Service should send the card details of the logged in user from DB to the UI.

5. Creating necessary packages  


Now in controller make a class names as AccountController and in that class make one API method 
Now run the application we can see that default login page popped up that because default spring security provides basic authentication 



So to access our API enter user name – user and password provided by farmwork in console 
UserName – user
Password - 
 After entering the credentials, we can access our Api  
If we want our username and password in this basic default spring security then configure it into application.property




Currently, Spring Security is securing all of my APIs, but I need to customize its behaviour to meet my specific business requirements.

To do this customization, we first need to understand the code within Spring Security that is responsible for this default behaviour. By examining the default configurations within the Spring Security framework, we can identify how to make the necessary adjustments.
Open a class which is SpringSecurityWebConfiguration in this class look for a method which is defaultSecurityFilterChain in this method we can see that there is a piece of code which is responsible for securing all api’s inside our application. 





So now we get that if we want to give customize security to Api’s we have to customize or override this method.
defaultSecurityFilterChain is responsible for managing the security checks on all incoming HTTP requests. Specifically, it ensures that:
•  Authentication: Checks if the user has provided valid credentials (such as username and password).
•  Authorization: Verifies if the authenticated user has the necessary permissions to access the requested resource.
•  Session Management: Ensures that the user's session is properly managed and tracks the authentication status.
•  Other Security Aspects: Handles additional security concerns like CSRF protection, request validation, and logging.
Now we we will customize the spring security with our requirements
Create a package named configuration and, in that package, create class named ProjectSecurityconfig.
So we have seen in SpringSecurityWebCofiguration that defaultSecurityFilterChain is responsible for handling for securing the http requests, so we have to override this method in our security configuration class which is SpringSecurityConfiguration.
So override that method and don’t write in that method we will discuss first about method structure keyword.
SecurityFilterChain - defaultSecurityFilterChain method returns an object of type SecurityFilterChain, which contains the rules and configurations that define how incoming HTTP requests are processed and secured. These rules include which APIs can be accessed by authenticated users, what kind of authentication mechanisms are used, and other security-related settings.
Now we will Learn about permitAll() , denyAll() , requestMatcher() , authenticated() methods.
permitAll() - The permitAll() method in Spring Security lets anyone access a specific URL or resource without any restrictions. This means that any user can access the resource, whether they are logged in or not.


denyAll() - The denyAll() method in Spring Security blocks access to a specific URL or resource completely. When you use this method, no one can access the resource, whether they are logged in or not, authenticated or not. 



requestMatcher() - To secure custom APIs as per your requirements, you can use the requestMatcher() method to specify which API endpoints need to be authenticated. By doing so, you ensure that only authenticated users can access certain parts of your application.
Here's how you can secure custom APIs using requestMatcher() and the authenticated() method.


authenticated() - The authenticated() method in Spring Security is used to enforce that only authenticated users can access certain API endpoints. By applying this method, you ensure that users must log in before they can access the specified resources.
UsernamePasswordAuthenticationFilter class : 
•	Role: This filter is responsible for processing authentication requests. It typically handles form-based login (when the user submits a username and password via a login form).
•	Process:
1.	Extraction: It extracts the username and password from the login request.
2.	Authentication: It creates an Authentication token with the extracted credentials and passes it to the AuthenticationManager for authentication.
3.	Success/Failure Handling: Based on the result from the AuthenticationManager, it either forwards the user to a success page or redirects them back to the login page with an error message.
BasicAuthenticationFilter class
•	Role: This filter is responsible for handling HTTP Basic Authentication. It is not used for form-based login but for situations where the client sends credentials encoded in the HTTP headers.
•	Process:
1.	Extraction: It extracts the username and password from the Authorization HTTP header.
2.	Authentication: It creates an Authentication token and passes it to the AuthenticationManager to verify the credentials.
3.	Security Context: If the credentials are valid, it updates the SecurityContextHolder with the authenticated user's details.








Configuring users using InMemoryUserDetailsManager
Storing credentials like usernames and passwords directly in the application.properties file is not a viable solution for large enterprise applications.
Why Not Store Credentials in application.properties?
Security Risks:
•	Storing sensitive information like usernames and passwords in plain text within application.properties poses significant security risks. If the file is compromised, all credentials are exposed.
Scalability Issues:
•	For enterprise applications, you typically need to manage multiple users with different roles and permissions. Storing credentials for a large number of users in application.properties is impractical and unmanageable.
Better Approaches
Database Storage:
o	Use a database to store user credentials. This allows for dynamic management of users, easy updates, and scalability. Each user can have a unique username and password, and you can enforce strong password policies and encryption.
There is one Interface in Spring Security Interface which is UserDetailsService Interface in Spring Security plays a crucial role in fetching user-related information. Specifically, it loads user-specific data during the authentication process. This interface has a single method called loadUserByUsername, which is used to retrieve user details based on the username provided.
Role of UserDetailsService
•	Fetching User Data: It retrieves user details (such as username, password, and authorities) from a data source, typically a database.
•	Integration with Authentication: It integrates with the authentication process by providing necessary user information for verification.
The UserDetailsService interface is essential for fetching user-related data during the authentication process. By implementing this interface, you can integrate Spring Security with your application's user data source, ensuring that users are properly authenticated and authorized.


Create one Method in ProjectSecurityConfig with datatype USerDetailsService and method name userDeatilsService, this method will return the bean so don’t forgot to apply @Bean annotation
In this class Thetre is one class present named as User. So with the help of User class create username and password using withUsername() and password() method and with this methids use authorities() to give authorities to the user and then use build method to ensuring that what we have written should return user object.
 Note- build method returns USerDetails object which represent user .
Now create a UserDetails variable to catch the user details so this is how full user should looks like
UserDetails user =User.withUsername(“user”).password(“{noop}12345”)
.authorities(“read”).build();
now i have to return the InMemoryUserDetailsManager(user,admin); 
  UserDetailsService: This method returns an instance of UserDetailsService and is marked with @Bean so that it is managed by the Spring container.
 UserDetails Instances: Two users are created with withUsername(), password(), and authorities() methods, and finally, build() to create UserDetails objects.
•	user: Username "user", password "12345", authority "read".
•	admin: Username "admin", password "admin123", authorities "read" and "write".
  InMemoryUserDetailsManager: This is instantiated with the created UserDetails objects and returned by the userDetailsService method.

Now We will Understand about InMemoryUserDetailsManager
What is InMemoryUserDetailsManager?
•	Role: InMemoryUserDetailsManager is an implementation of the UserDetailsService interface that stores user details in memory. It is useful for development and testing purposes where you don't need a persistent user store.
•	Usage: It is ideal for scenarios where you have a small number of users and do not require the complexity of a database or external identity provider.
•	Methods: It provides methods for managing user accounts, including adding, removing, and updating users, all stored in memory.


How it Works:
•	In-Memory Storage: User details are stored in the application memory, making it easy to manage users during runtime.
•	User Management: You can manage users through the InMemoryUserDetailsManager methods. For example, you can add, remove, or retrieve user details as needed.
•	Authentication: During authentication, Spring Security uses the InMemoryUserDetailsManager to load user details based on the username provided.
In summary, InMemoryUserDetailsManager is a convenient way to manage user details in memory for development and testing. It simplifies user authentication by storing user details within the application without needing a database.
 
Now we are using plain text for password which is not good so we have to use password encoder and use BCrypt Hash code 
Use like this 










To make sure users create strong passwords and keep them safe, we can use a tool provided by Spring Security called CompromisedPasswordChecker. This tool checks if passwords have been exposed in any known data breaches, helping to prevent weak passwords like "12345" or "admin@123" from being used.
We can create a method named compromisedPasswordChecker that returns a CompromisedPasswordChecker instance. In this method, we will use the HaveIBeenPwnedApiPasswordChecker class, which checks passwords against a large database of compromised passwords. This Checking password feature was introduced in Spring Security version 6.3.
in this HaveIBeenPwnedApiPasswordChecker this class api which is used to chjeck the password and return if the password is leaked or not.
 
Now we have used the password 12345 and 54321, with this password if we run our program and try to access api by loging in this is what we will get.


  
To Prevent this we have to use strong password, for strong password we can use special characters like @,#,$ etc.

So I am using Nexus@1234 for user and Nexus#4321 for admin and let’s we if our proram let us access the api or not!
TA DA!! 
    we can access tha API’s.




Deep Dive into of UserDetailsService & UserDetailsManager Interfaces
Diagram Title: USER MANAGEMENT IMPORTANT CLASSES & INTERFACES





The diagram illustrates the relationships between different interfaces and classes used in user management, specifically in the context of Spring Security.
UserDetailsService (Interface)
Purpose: This is the core interface responsible for loading user-specific data.
Method:
loadUserByUsername(String username): This method is used to load user details based on the provided username.
UserDetailsManager (Interface)
Purpose: This is an extension of the UserDetailsService and provides additional functionalities to create, update, and delete users.
Methods:
createUser(UserDetails user): Creates a new user.
updateUser(UserDetails user): Updates an existing user.
deleteUser(String username): Deletes a user based on the username.
changePassword(String oldPwd, String newPwd): Changes the user's password from the old one to the new one.
userExists(String username): Checks if a user exists based on the username.
3. Sample Implementation Classes Provided by the Spring Security Team
These are concrete classes that implement the interfaces mentioned above:
InMemoryUserDetailsManager: Manages users stored in memory.
JdbcUserDetailsManager: Manages users stored in a database -using JDBC.
LdapUserDetailsManager: Manages users stored in an LDAP directory.
4. UserDetails (Interface)
Purpose: This interface provides the core user information used by all the interfaces and classes mentioned above.
Implementation: Various classes can implement this interface to represent user details like username, password, and authorities.
Summary:
The diagram provides an overview of how user management is handled in Spring Security using interfaces and their sample implementations. The UserDetailsService interface is the foundation for loading user data, while the UserDetailsManager interface extends it by adding methods for managing users. The sample implementation classes (InMemoryUserDetailsManager, JdbcUserDetailsManager, LdapUserDetailsManager) provide concrete ways to handle user management in different contexts. The UserDetails interface encapsulates the core user information required by these interfaces and classes. 
Deep Dive of UserDetails & Authentication interfaces
Diagram Title: USER MANAGEMENT IMPORTANT CLASSES & INTERFACES
 
The diagram illustrates the relationships between different interfaces and classes used in user management within Spring Security, focusing on how user login details are managed.
Key Components:
Left Side: Principal and Authentication
Principal (Interface):
Represents the currently authenticated user.
Authentication (Interface):
Represents the authentication token, containing the user's authentication information.
Connected to UsernamePasswordAuthenticationToken (Class): This is a concrete implementation that represents an authentication request using username and password.
UsernamePasswordAuthenticationToken (Class):
Methods:
getName(): Returns the name of the authenticated user.
getPrincipal(): Returns the principal (usually the UserDetails object).
getAuthorities(): Returns the authorities (roles/permissions) granted to the user.
getCredentials(): Returns the credentials (password).
getDetails(): Returns additional details about the authentication request.
isAuthenticated(): Indicates whether the user is authenticated.
setAuthenticated(): Sets the authentication status.
eraseCredentials(): Erases the credentials for security purposes.
Note: "Authentication is the return type in all the scenarios where we are trying to determine if the authentication is successful or not. Like inside the AuthenticationProvider & AuthenticationManager."
Right Side: UserDetails and User
UserDetails (Interface):
Represents the core user information used by Spring Security.
User (Class):
A concrete implementation that represents a user.
Methods:
getPassword(): Returns the user's password.
getUsername(): Returns the user's username.
getAuthorities(): Returns the authorities granted to the user.
isAccountNonExpired(): Indicates whether the user's account has expired.
isAccountNonLocked(): Indicates whether the user's account is locked.
isEnabled(): Indicates whether the user is enabled.
isCredentialsNonExpired(): Indicates whether the user's credentials (password) are expired.
eraseCredentials(): Erases the credentials for security purposes.
Note: "UserDetails is the return type in all the scenarios where we try to load the user info from the storage systems. Like inside the UserDetailsService & UserDetailsManager."
Handwritten Annotations and Questions:
One question asks, "Why do we have 2 separate ways to store login user details?" This refers to the distinction between storing user details in UserDetails and the authentication state in Authentication.
Summary:
The diagram explains the flow of user authentication in Spring Security:
UserDetails interface is used for loading user information from storage (like databases).
Authentication interface is used for representing the user's authentication state.
Classes like UsernamePasswordAuthenticationToken and User implement these interfaces to provide concrete mechanisms for authentication and user data management.


No we Gonna Use Database to store user and fetch username and password from database to authenticate.
Download and install Docker as we gonna upload image of database which is MySQL in docker. 
After installing check for version in command prompt (docker version)
To install MySQL database inside my local system with the help of docker then run this command ( docker run -p 3307:3307 –-name springsecurity     )
docker run means we are trying to run a container .
-p means we are trying to give port mapping.
--name containername we are trying to give a name to my container.
-e means environment variable, environment variable is like username and password or database name 
So to give password use this command (-e MYSQL_ROOT_PASSWORD=root)
To give database name run this (-e MYSQL_DATABASE=nexusbank) 
-d means means I want to run this command in detached mode and I don’t want to see any logs
After -d give docker image name lik this (-d mysql)

This I how full command looks like 
Docker run -p 3307:3307 –-name springsecurity -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=nexusbank -d mysql

Now download SQLELECTRON-> it’s a friendly interface to help us to interact with different databases easily.
Think of it as a friendly interface that allows you to connect to databases, run SQL queries, and manage your data without needing to be a database expert. It's not a database system itself, but a helpful bridge between you and your databases.
In summary:
•	SQLECTRON is a SQL client.
•	It lets you connect to various types of databases.
•	You can run SQL queries and scripts.
•	You can browse and manage database tables and data.
•	It's user-friendly and available for multiple platforms.
After installing add connection giave name to the connection and test the connection and save it.
Now in nexus databsse create user and authorities table take script from SpringSecurity4 resources.sql.scripts.sql and then add some users and authories in the table. 
Now make changes to configuration file 


 
And now add some users into database using some dbms and check Api’s what you have made with the stored users in our case this what uers looks like.
 
 
Now we gonna make our own database and give information to project that use this database and authenticate from this table.
Make Customer class and add nessary arrangemnt
 
 
Now create a class in config file named NexusBankUSerDetailsService which will implements UserDetailsService and give implementation to loadUserByUsername.
 









And now create a api which is to register the user, so create a controller class which will handle the registration,








And now test the api and login with your credentials 
So I gont an error which no path foud error,so go to ProjectSecurityConfig and add the url path to requestMatcher and do permit all, and then post the credentials.
Again I have got an error says 401,403 error which is forbidden which tells that I am not authorized this is because spring security by default it protects all put,patch or delete http requestion so we have to disable the CSRF in defaultSecurityFiletrChain shown bellow 
 
Now we can register the new user






Successfully Registered the user 
Now check if I can login from this or not 


                                                                                              


                                                                     Hooray we have successfully loged.



Now We Gonna understand what is Encoding, Encryption and Hashing. Which one is suitable and why Encoding and Decoding , Encryption and Decryption is not suitable for password management.
So there are three type of way to protect password is Encoding,Encryption and Hashing
 
1.Encoding->
What It Is:
•	Technical Side: Encoding is the process of converting data from one format to another so that it can be easily used by different systems. It’s not meant to secure data; it’s just about making data compatible.
•	Real-World Example: Imagine you have a text message that you want to send via email, but the email system only accepts text in a specific format. You convert the message to Base64 (an encoding scheme) so that it can be transmitted properly. The recipient can easily decode it back into the original message because the method (Base64) is public knowledge.
Why It’s Not Suitable for Password Management:
•	Not Secure: Because encoding is reversible by design, if someone intercepts an encoded password (or if it is stored somewhere), they can decode it easily. It does not protect the information from unauthorized access.
 
2.Encryption->
What It Is:
•	Technical Side: Encryption transforms plain data (plaintext) into an unreadable format (ciphertext) using an algorithm and a key. The process is reversible if you have the correct decryption key.
•	Real-World Example: Think about sending a secret letter in a locked box. You lock the box (encrypt the message) using a key, and only someone with the matching key can open the box (decrypt it) and read the message.
Why It’s Not Suitable for Password Management:
•	Reversible with a Key: Even though encryption hides the original data, it is reversible if someone obtains the decryption key. In password storage, you don’t want anyone—even someone with administrative access—to be able to recover the original password.
•	Key Management Challenges: Securely handling and storing encryption keys is complex. If the keys are compromised, all encrypted passwords become vulnerable.
So there are two ways to encrypt the data, SYMMETRIC ENCRYPTION AND ASYMMETRIC ENCRYPTION 



SYMMETRIC ENCRYPTION
 
Definition:
•	Symmetric encryption is a type of encryption where the same key is used for both encryption and decryption.
Key Characteristics:
1.	Single Key Usage:
o	Explanation: One key is used for both converting plaintext to ciphertext (encryption) and converting ciphertext back to plaintext (decryption).
o	Example: AES (Advanced Encryption Standard).
o	Note: The key must be kept secret and shared securely between parties.
2.	Efficiency:
o	Explanation: Symmetric encryption algorithms are generally faster and less computationally intensive than asymmetric encryption algorithms.
o	Example: Encrypting large amounts of data, such as files or disks, can be done quickly with AES.
3.	Common Algorithms:
o	AES: Known for strong security and efficiency.
o	DES: An older algorithm, less secure due to shorter key length.
o	3DES: An extension of DES, offering improved security by applying DES three times with different keys.
4.	Security:
o	Explanation: The security of symmetric encryption relies on keeping the key secret. If the key is compromised, the encrypted data is also compromised.
o	Example: Securely transmitting the key through a different channel, such as a secure courier or an encrypted email.
5.	Applications:
o	Explanation: Symmetric encryption is commonly used for encrypting data at rest and in transit.
o	Example:
o	Data at Rest: Encrypting files or entire disks.
o	Data in Transit: Secure communication protocols like SSL/TLS for HTTPS.
6.	Real-World Example:
o	Scenario: You want to store sensitive files on your computer.
o	Solution: Use a symmetric encryption algorithm like AES to encrypt the files with a secret key. Only you (and anyone you share the key with) can decrypt and access the files.
ASYMMETRIC ENCRYPTION  
Definition:
•	Asymmetric encryption, also known as public-key encryption, uses a pair of keys—a public key and a private key—for encryption and decryption.
Key Characteristics:
1.	Two-Key Pair:
o	Explanation: Asymmetric encryption involves a pair of keys—a public key (used for encryption) and a private key (used for decryption).
o	Example: RSA (Rivest-Shamir-Adleman).
o	Note: The public key is shared openly, while the private key is kept secret.
2.	Security:
o	Explanation: The security of asymmetric encryption relies on the computational difficulty of deriving the private key from the public key. Even if the public key is known, the data remains secure as long as the private key is kept secret.
o	Example: Using RSA, it is computationally infeasible to derive the private key from the public key due to the complexity of the underlying mathematical problems.
3.	Common Algorithms:
o	 RSA: Widely used for secure data transmission.
o	 ECC: Provides strong security with shorter key lengths, making it efficient.
4.	Applications:
o	Explanation: Asymmetric encryption is commonly used for secure key exchange, digital signatures, and secure email.
•	Example:
o	Secure Key Exchange: Exchanging symmetric keys securely over an insecure channel.
o	Digital Signatures: Verifying the authenticity and integrity of a message or document.
o	Secure Email: Encrypting emails with the recipient’s public key.
5.	Efficiency:
o	Explanation: Asymmetric encryption is generally slower and more computationally intensive than symmetric encryption, making it less suitable for encrypting large amounts of data.
o	Example: Using asymmetric encryption for key exchange and symmetric encryption for the actual data encryption.
6.	Real-World Example:
o	Scenario: You want to send a confidential email to a colleague.
o	Solution: Encrypt the email using your colleague's public key (asymmetric encryption). Your colleague can then decrypt the email using their private key. You can also sign the email with your private key to verify your identity.		
Summary for Password Management:
•	Symmetric Encryption: Not suitable due to the need to securely share and manage the same key for encryption and decryption.
•	Asymmetric Encryption: Not suitable due to the complexity and the need to manage key pairs.
•	Hashing: The best method for storing passwords securely, as it is a one-way process that does not require key management and cannot be reversed. 

HASHING 

 	









Definition: Hashing is the process of converting any input data (like a password or file) into a fixed-size string of characters, called a hash, using a mathematical function known as a hash function.
Key Characteristics:
1.	One-way function: Hashing is irreversible; you cannot derive the original input from the hash.
2.	Deterministic: The same input will always produce the same hash using the same algorithm.
3.	Fixed-size output: Regardless of the size of the input, the hash will always have a fixed length.
4.	Avalanche effect: A small change in the input produces a completely different hash.
How Does Hashing Work?
1.	Input: Any data, such as a password, string, or file, is given as the input.
2.	Hash Function: A hash function is applied to the input. It performs mathematical operations to generate a fixed-size hash value.
3.	Output (Hash): The result is a hash value, which is a string of seemingly random characters.
Example:
•	Input: password123
•	Hash (using SHA-256): ef92b778bafe771e89245b89ecbc493d198b7f1522b8c7286f32d0b62b9d73ca
Even a small change in the input produces a drastically different hash:
•	Input: password124
•	Hash: 78c8f7200b292ad504a46bfc3a9d6d1b3a99d4cd0cb024ada54aa1dc59f4cbce
Common Hashing Algorithms
1.	SHA (Secure Hash Algorithm):
•	Examples: SHA-1, SHA-256, SHA-512.
•	SHA-256 is one of the most widely used and secure hashing algorithms.
2.	MD5 (Message Digest Algorithm):
•	Produces a 128-bit hash.
•	Not recommended for secure applications due to vulnerabilities (e.g., collisions).
3.	bcrypt:
•	Specifically designed for hashing passwords.
•	Introduces salt and is computationally expensive to slow down brute-force attacks.
4.	Argon2:
•	Modern and highly secure algorithm designed for password hashing.
•	Winner of the Password Hashing Competition (PHC).
Salting
•	What is Salting?: Adding random data (called a "salt") to the input before hashing to ensure that identical inputs produce different hashes.
•	Why is it Important?:
1.	Protects against rainbow table attacks (precomputed hashes of common passwords).
2.	Ensures each user has a unique hash even if they use the same password.
Example:
•	Input: password123
•	Salt: random123
•	Combined Input: password123random123
•	Hash: 9bf31c7ff062936a96d3c8bd1f8f2ff3
Even if another user has the same password, a different salt will result in a different hash.
Applications of Hashing
1.	Password Storage:
o	Store only hashed passwords (not plaintext passwords) in the database.
o	Example: A website hashes your password upon signup. During login, it hashes the entered password and compares it with the stored hash.
2.	Data Integrity:
o	Verifying that a file or message hasn’t been tampered with.
o	Example: File checksum (e.g., using MD5 or SHA-256) during downloads.
3.	Digital Signatures:
o	Hashes are used to create digital signatures to ensure authenticity and integrity.
4.	Cryptographic Applications:
o	Hashing plays a role in blockchain technology, where it secures data in blocks.
Strengths of Hashing
1.	One-way Property:
o	Hashing ensures the original data cannot be retrieved, providing excellent security for sensitive data like passwords.
2.	Fixed Output:
o	No matter how large the input, the hash size is fixed, which makes it suitable for indexing, checksum verification, and cryptographic operations.
3.	Efficiency:
o	Hash functions are designed to be fast and lightweight.
1. Collisions
•	Explanation: A collision occurs when two different inputs produce the same hash value. While strong hashing algorithms (like SHA-256) minimize the chances of collisions, older or weaker algorithms (like MD5 or SHA-1) are more susceptible to this issue.
•	Why It’s a Problem: If a collision occurs, an attacker might exploit it to bypass authentication systems by creating a different input (password) that matches the stored hash.
•	Example:
o	Input 1: password123
o	Input 2: mypassword!
o	Weak Hash Algorithm Output: Both inputs result in the same hash value.
o	If the hashing algorithm is vulnerable to collisions, an attacker could use "mypassword!" to log in as a user whose password was "password123".
2. Brute-Force Attacks
•	Explanation: Hashing itself cannot prevent brute-force attacks, where an attacker tries numerous inputs until they find one that matches the hash.
•	Why It’s a Problem: If the password is weak (e.g., "12345"), even with hashing, an attacker can quickly guess it by hashing common passwords or using precomputed hash lists.
•	Example:
o	A hacker uses a dictionary of common passwords and hashes them using the same algorithm as your system.
o	If "12345" is hashed to ef92b778bafe771e89245b89ecbc493d, the hacker simply compares this to the stored hash and finds a match.
Not Suitable for Reversible Data
•	Explanation: Hashing is a one-way process. If you need to retrieve the original data (e.g., in encryption/decryption scenarios), hashing is not suitable.
•	Why It’s a Problem: Data stored in hashed form cannot be decrypted for reuse—it can only be verified by hashing the same input again.
•	Example:
o	Storing credit card numbers securely requires encryption (not hashing) because you may need to retrieve the original credit card number for processing payments. With hashing, retrieving the original number is impossible.
 









Understanding about Authentication Provider
 
An Authentication Provider is a key component in authentication systems, especially in frameworks like Spring Security. It is responsible for validating user credentials and determining whether a user is authenticated.
What is an Authentication Provider?
•	Definition: An Authentication Provider is a class or component that processes authentication requests. It checks the provided credentials (like username and password) and returns an authenticated user object if the credentials are valid.
•	Role: It acts as a bridge between the authentication mechanism (e.g., login form) and the user data source (e.g., database, LDAP, or external service).
How It Works
1.	Authentication Request:
o	A user submits their credentials (e.g., username and password) through a login form.
2.	Authentication Provider:
o	The Authentication Provider receives the request and validates the credentials against a data source (e.g., database, LDAP, or an external API).

3.	Authentication Object:
o	If the credentials are valid, the Authentication Provider returns an Authentication object containing user details and granted authorities (roles/permissions).
o	If the credentials are invalid, it throws an AuthenticationException.

Key Methods in Authentication Provider
1.	authenticate(Authentication authentication):
o	Validates the credentials provided in the Authentication object.
o	Returns a fully authenticated Authentication object if successful.
o	Throws an exception if authentication fails.
2.	supports(Class<?> authentication):
o	Checks if the Authentication Provider can handle the type of authentication request (e.g., username-password, token-based).
Types of Authentication Providers
1.	Built-in Providers in Spring Security:
o	DaoAuthenticationProvider: Validates credentials against a database using a UserDetailsService.
o	LdapAuthenticationProvider: Authenticates users against an LDAP directory.
o	JwtAuthenticationProvider: Validates JSON Web Tokens (JWTs).
o	OAuth2AuthenticationProvider: Handles OAuth2-based authentication.
2.	Custom Authentication Provider:
o	You can create your own provider by implementing the AuthenticationProvider interface. This is useful for integrating with third-party systems or custom authentication mechanisms.
Why we should consider creating our own AuthenticationProvider->
Creating a custom AuthenticationProvider can be a valuable approach in many scenarios where built-in authentication mechanisms don't fully meet the specific requirements of your application. Below, I'll explain why you might consider building your own AuthenticationProvider with detailed points:


1. Custom Authentication Logic
•	Why? Sometimes, the authentication logic required by your application is too unique or complex for the standard providers, such as DaoAuthenticationProvider (which works with a database) or LdapAuthenticationProvider (for LDAP).
•	Example: You need to authenticate users using a third-party system, such as a REST API or a custom token-based service, which is not covered by the built-in providers.
2. Support for Multiple Authentication Mechanisms
•	Why? If your application allows users to log in using multiple mechanisms, such as username/password, API keys, or one-time tokens, a custom AuthenticationProvider can provide the flexibility to implement and support these methods.
•	Example:
o	One provider might validate usernames and passwords from a database.
o	Another custom provider might validate one-time tokens sent via email or SMS.
o	Your custom provider could integrate these seamlessly.
3. Integrating External Authentication Systems
•	Why? When your application relies on an external system for authentication, such as Single Sign-On (SSO), OAuth2 providers, or custom external APIs, you need a way to bridge the gap between your application and the external service.
•	Example: A custom AuthenticationProvider could call a remote API to validate user credentials and fetch roles/permissions.
4. Adding Domain-Specific Validation
•	Why? Some applications require domain-specific validation that goes beyond simple username and password checks. For example, you may need to check business logic, such as whether a user's account is approved by an administrator or if they belong to a particular department.
•	Example: Allowing only employees from a specific team to log in, based on a database check.

5. Enhancing Security
•	Why? If you need advanced security features, such as multi-factor authentication (MFA) or biometric authentication, you might need to create a custom provider to implement the additional steps required for validation.
•	Example: After validating the username and password, the custom provider could also verify a one-time code sent to the user's mobile device.
6. Tailored Error Handling
•	Why? Built-in providers often have generic error messages for failed authentication attempts, which might not provide the level of detail your application needs.
•	Example: A custom provider can differentiate between:
o	A disabled account.
o	An expired password.
o	Invalid credentials.
o	And return meaningful error messages or responses to the user.
7. Flexibility and Maintainability
•	Why? Custom providers give you control over how authentication is handled, making the system more adaptable to future changes or requirements.
•	Example: As your application evolves, you can easily extend the custom provider to include new rules or integrate additional data sources without significant changes to the entire authentication system.
8. Working Alongside Built-in Providers
•	Why? Spring Security allows you to use multiple AuthenticationProvider instances in a chain. By adding a custom provider, you can complement the built-in ones without replacing them entirely.
•	Example: Use the built-in DaoAuthenticationProvider for regular users and a custom provider for admin authentication using a different database.




Making Our Own Custom Authentication provider
When building a custom AuthenticationProvider, it allows us to define custom authentication logic tailored to the needs of our application. Here's a step-by-step guide:
Steps for Building a Custom Authentication Provider

1. Create the Class
•	The class should have a meaningful name (e.g., ProjectNameUsernamePwdAuthenticationProvider).
•	This class must implement the AuthenticationProvider interface from Spring Security.
2. Implement the Methods
The AuthenticationProvider interface has two methods that must be implemented:
1.	Authentication authenticate(Authentication authentication)
o	Purpose: This method is responsible for authenticating the user.
o	Input:
	An Authentication object containing the user’s credentials (e.g., username and password).
o	Output:
	If authentication succeeds, it returns a fully authenticated Authentication object.
	If authentication fails, it should throw an exception (e.g., BadCredentialsException).
2.	boolean supports(Class<?> authentication)
•	Purpose: This method specifies the type of authentication token that this provider can handle.
•	Implementation:
o	Typically, we check if the authentication class is of type UsernamePasswordAuthenticationToken:
return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
3. Write the Logic in authenticate()
Inside the authenticate() method:
•	Extract the credentials (e.g., username and password) from the Authentication object.
•	Validate these credentials (e.g., load user details and compare the password).
•	If credentials are valid:
o	Return a new UsernamePasswordAuthenticationToken with the user’s details and granted authorities (roles/permissions).
•	If credentials are invalid:
o	Throw an exception such as BadCredentialsException.
Example shown in below picture
 

4.Implement the supports() Method
•	What It Does: It checks whether this AuthenticationProvider can handle a particular type of Authentication object.


5.Add the @Component Annotation
•	Annotating the class with @Component ensures that Spring will scan and register this custom AuthenticationProvider as a bean.
•	This tells Spring Security to include your custom provider in the authentication process.
 
6. Verify Your API
•	After implementing the custom provider, ensure that your application is running properly.
•	Use tools like Postman or a browser to test the authentication flow and verify that the custom logic is applied.
Key Points for Notes
1.	Why Create a Custom AuthenticationProvider?
o	To handle custom logic (e.g., third-party authentication or specific validation logic).
o	To extend or replace the default authentication mechanisms provided by Spring Security.
2.	Steps to Implement a Custom Provider:
o	Create a class that implements the AuthenticationProvider interface.
o	Implement the authenticate() method to validate user credentials.
o	Implement the supports() method to specify the type of authentication supported.
o	Annotate the class with @Component for Spring to recognize it.
3.	Remember:
o	Use UsernamePasswordAuthenticationToken if handling username-password authentication.
o	Use a UserDetailsService to fetch user details securely.
o	Protect passwords by encoding and matching them with a PasswordEncoder.


























	


upper picture is to change the environment variavble



































Session Timeout & invalid session configuration
Configet this in application.properties ( server.servlet.session.timeout=${SESSION_TIMEOUT:2m} ) 
Concurrent session control configuration – this means that to control tthat how many sessions a user can have like if he login from 1 browser then if he is trying to login from another browser then his last session will be invalid ahn he can continue his latest login session, so to control this we have to configure this in project security file.
 
Let’s see , first try to login from microsoft browser we can see thst we successfylly login then try to login from brave browser we can see that we have successfully login but then go back to Microsoft and refresh or try to access another url it will thro error like this 
 
Now if we want to prevent login if a user is already login from another browser, then we have to invoke this method ( maxSessionsPreventsLogin(true) )
Now login from edge browser and then try to login from brave browser you will not be able to login from brave, you will get this






This is how full thing looks like 
 








Listing Authentication Events
Authentication Events
Authentication events in Spring Security allow you to listen for and react to login activities, such as successful authentication or authentication failures. These events are triggered during the authentication process.








Authentication Success Event
•	Method: onSuccess(AuthenticationSuccessEvent successEvent)
•	Triggered When:
o	A user logs in successfully (valid username/password).
•	What Happens:
o	The method logs the username of the user who successfully logged in using:
successEvent.getAuthentication().getName(); 
•	Example Log Output:
o	If the user "johnDoe" logs in successfully, the log would look like:
Login successful for user : johnDoe 
Authentication Failure Event
•	Method: onFail(AbstractAuthenticationFailureEvent failureEvent)
•	Triggered When:
o	A user login fails (invalid username/password or any other authentication failure).
•	What Happens:
o	Logs the username and the reason for failure using:
	failureEvent.getAuthentication().getName() to get the username.
	failureEvent.getException().getMessage() to log the error message (e.g., "Bad credentials").
•	Example Log Output:
o	If the user "johnDoe" attempts to log in with an incorrect password, the log might look like:
Login failed for user : johnDoe due to : Bad credentials 
Why Use Authentication Events?
1.	Logging and Monitoring:
o	Track successful and failed login attempts for security audits.
2.	Security Analytics:
o	Identify suspicious activity, such as repeated failed login attempts.
3.	Custom Actions:
o	Perform additional actions on login events, such as sending notifications, locking accounts after too many failed attempts, or recording attempts in a database.
Custom Form login configuration
 
 





Now Create React project open a folder we have to create react project in command line and then run this command to make new react project.
npm create vite@latest   this is new command 
npm  create-react-app project-name   this command is not recommended its deprecated 
after running this command(npm create vite@latest  ) it will ask project name I am giving nexusbank-app
then it give option to choose framework which one react,angular or more so choose react, then it will ask what language you are gonna build the project so I am building react project using JavaScript.
And then your project is made but its half completed now we have to install npm so run command this (npm install ) now our project is ready now just run thisw command to se weather our project is running or not (npm run dev) and now we can access our project from browser by defult it will run on some other port number we an use this command to choose our port number (npm run dev -- --port=3000)  TADA HoooRrayy we setup the project.









Now clear the folder delete app.css and index.css and remove anessary code from main.jsx and app.jsx
                                                This is how our project structure looks like. 

			Now install required dependencies 
			1 . React Router DOM 
			command ->( npm install react-router-dom)
•				Purpose: Handles routing in a React application.
Why It's Needed: If your app has multiple pages or views (e.g., Login, Dashboard,                     Profile), you need React Router to navigate between them without reloading the page.

2 . Axios
•	Command ->( npm install axios)
Purpose: A library for making HTTP requests to your backend (e.g., Spring Security APIs).
•	Why It's Needed: Simplifies API calls like GET, POST, PUT, DELETE, etc., and handles responses asynchronously.
•	Key Features:
o	Allows custom configurations for headers (e.g., setting Authorization tokens).
o	Supports interceptors for pre- and post-processing requests.
o	Handles errors gracefully.
•	Example Usage:
import axios from 'axios'; axios.get('https://api.example.com/data') .then(response => console.log(respons

4.	Style Components
Command-->( npm install styled-components)
Purpose: Enables writing CSS-in-JS, meaning styles are written directly in your JavaScript code.
Why It's Needed: Helps you create styled React components without needing separate CSS files. You can scope styles to specific components easily.
Key Features:
o	Dynamically styles components based on props.
o	Ensures no style conflicts between components.
Example Usage:
import styled from 'styled-components'; const Button = styled.button` background-color: blue; color: white; padding: 10px; border: 
5.	React hook form 
Command ( npm install react-hook-form)
•	Purpose: A library for handling forms in React, making it easier to manage state, validation, and submission.
•	Why It's Needed: Simplifies form handling by reducing boilerplate code. It provides built-in validation and is highly performant.
•	Key Features:
o	Uses hooks (e.g., useForm) for form state management.
o	Offers flexible validation rules.
o	Works well with custom input fields.
•	Example Usage:
import { useForm } from 'react-hook-form'; function App() { const { register, handleSubmit, errors } = useForm(); const onSubmit = data
6.	ESLint and Prettier:
For code linting and formatting:
npm install -D eslint prettier 
7.	Farmer Motion
	Framer Motion: A powerful library for animations in React.
	Install it if animations are needed:
Command (npm install framer-motion )
o	Not necessary unless animations are required.
8.	Font Awesome
Command ( npm install @fortawesome/react-fontawesome @fortawesome/free-solid-svg-icons @fortawesome/fontawesome-svg-core)

9.	Install BootStrap
Command  (npm install bootstrap)

10.	Js cookies
In React, you can use:
1.	js-cookie (compatible with JavaScript or TypeScript):
command  npm install js-cookie 
Now create component folder in that create context folder in that foldef create ThemeContext.jsx file and create a global variable which is accessible to all component 








Now in main.jsx file wrap app component in ThemeContext component 






Now create a navbar component should look like this  
Then create a Register Component should look like this 






                                                                                                                                                    This is how I handle the form data 
Now we will understand about CROS.














CORS (Cross-Origin Resource Sharing) in Spring is a mechanism that allows your web application to safely request resources (like APIs) from a different domain. By default, browsers block such cross-origin requests for security reasons, but CORS lets you control these permissions.
How CORS Works in Spring Boot
Spring Boot provides multiple ways to enable CORS for your APIs:
1. Using @CrossOrigin in Controllers (Simplest Approach)
You can enable CORS directly in a specific controller or method using @CrossOrigin:
@CrossOrigin(origins = "http://localhost:5173")  // Allow requests from frontend running on port 5173
@RestController
@RequestMapping("/api")
public class UserController {
This allows requests only from http://localhost:5173.
•	You can also allow multiple origins: 
•	@CrossOrigin(origins = {"http://localhost:5173", "http://example.com"})


________________________________________
2. Global CORS Configuration (WebMvcConfigurer)
If you need CORS enabled for all controllers, use a global configuration:

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allow all endpoints
                .allowedOrigins("http://localhost:5173")  // Allow frontend requests
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Allow specific HTTP methods
                .allowedHeaders("*");  // Allow all headers
    }
}
•	addMapping("/**"): Applies CORS to all endpoints.
•	allowedOrigins("http://localhost:5173"): Only permits requests from this frontend.
•	allowedMethods("GET", "POST", "PUT", "DELETE"): Controls which HTTP methods are allowed.
________________________________________
3. Configuring CORS in Spring Security
 
________________________________________
When Do You Need CORS?
•	If your frontend (React, Angular, Vue) is running on a different port/domain than your Spring Boot backend.
•	When making API calls from the browser, but receiving a CORS policy error.
________________________________________
Common Errors & Fixes
Error	Possible Fix
CORS policy blocked request	Add @CrossOrigin in the controller or enable global CORS
Preflight request failed	Ensure Spring Security allows CORS
403 Forbidden	Check if CSRF protection is enabled, disable if needed
________________________________________
With this setup, your Spring Boot application can allow safe communication between different domains while maintaining security.

Now We will Understand about CSRF attack
 

CSRF (Cross-Site Request Forgery) is a type of web security attack where an attacker tricks a user into performing unintended actions on a web application in which they are authenticated.
🔹 How CSRF Works
1️.A user logs into a trusted website (e.g., their bank).
2️.Without logging out, the user visits a malicious website controlled by an attacker.
3️.The malicious site sends a hidden request to the trusted site using the user's credentials (since they are still logged in).
4️.The trusted site processes the request, thinking it's genuine—allowing actions like money transfers or changing account settings without the user’s consent.
🔹 Why Is It Dangerous?
•	Attackers can change user settings, perform transactions, delete data, or even steal information.
•	CSRF exploits the trust that a web application has in an authenticated user.
•	Victims usually don’t realize they were attacked until damage is done.

🔹 How to Prevent CSRF?
✅ Use CSRF Tokens → Generate a unique token for each request and verify it.
✅ Enforce SameSite Cookies → Prevent third-party sites from making requests.
✅ Check Referrer Header → Validate that requests originate from trusted sources.
✅ Use User Authentication Best Practices → Implement strong security measures.

To extract the XSRF-TOKEN at frontend
function getCsrfToken() {
  const csrfCookie = document.cookie
    .split("; ")
    .find(row => row.startsWith("XSRF-TOKEN="));
  return csrfCookie ? csrfCookie.split("=")[1] : null;
}
Sure! This function extracts the CSRF token (XSRF-TOKEN) from the cookies stored in the user's browser. Let's break it down step by step:
🔹 Step-by-Step Explanation
1️.document.cookie
This retrieves all cookies stored by the browser as a single string, where multiple cookies are separated by ;.
Example of document.cookie:
"SESSION_ID=abc123; XSRF-TOKEN=xyz789; theme=dark"
2️.split("; ")
This splits the cookie string into an array of individual cookies:
["SESSION_ID=abc123", "XSRF-TOKEN=xyz789", "theme=dark"]
3️.find(row => row.startsWith("XSRF-TOKEN="))
This searches for the CSRF token in the array. It looks for a cookie that starts with "XSRF-TOKEN=", returning:
"XSRF-TOKEN=xyz789"
4️.split("=")[1]
Now, it extracts the actual token value from "XSRF-TOKEN=xyz789" by splitting at "=", resulting in:
["XSRF-TOKEN", "xyz789"]
✅ The [1] selects "xyz789", which is the CSRF token value.
5️.eturn csrfCookie ? csrfCookie.split("=")[1] : null;
•	If the cookie exists, return the extracted token.
•	If not found, return null.
________________________________________
🔹 Final Output Example
✅ If XSRF-TOKEN is present in cookies:
getCsrfToken();  // Output: "xyz789"
✅ If XSRF-TOKEN is missing:
getCsrfToken();  // Output: null
________________________________________
🔹 Why Is This Useful?
✔ Allows your React app to fetch the CSRF token automatically.
✔ Prevents CSRF attacks by ensuring the token is sent in requests.
✔ Necessary for interacting with Spring Boot's CSRF protection.

Learning about difference between Authentication and Authorization
 
Authentication vs Authorization
Definitions:
•	Authentication: Verifying the identity of a user/system (e.g., confirming "who you are").
•	Authorization: Granting or restricting access rights to resources (e.g., defining "what you can do").
Real-World Example:
Imagine a company office:
•	Authentication: Showing your ID card to the security guard to prove you’re an employee.
•	Authorization: Being allowed into the "Finance Department" room only if you’re part of the finance team.
•	Authentication = Login (proving identity).
•	Authorization = Access Control (granting permissions)
 
Summary
•	Authentication is handled via authentication providers, login mechanisms, and token validation.
•	Authorization is controlled through role-based access control (RBAC), method-level security annotations, and permission-based checks.


