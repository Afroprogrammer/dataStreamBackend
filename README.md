## DataStream API

---

### Software Requirements

1. Java 11
2. Postgres

---

### Instructions
1. Clone the repository
2. Use application-{your_name} as the active profile when running the application
3. Configure the database name, username and  password in your profile
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/databae_name_here
    username: username_here
    password: password_here
```
4. Configure your paystack and biller credentials in your profile
```yaml
services:
  paystack:
    apiKey: key_here

  datastream:
    apiKey: key_here
```

##### NB: you can override any default configuration in the application.yml profile in your own profile

--- 

