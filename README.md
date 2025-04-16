# springboot-authentication-user-cru

---

## 🔐 Authentication Flow

- Users register using `/auth/register`
- Login via `/auth/login` to receive a JWT
- Send the JWT in headers:  
  `Authorization: Bearer <your_token_here>`
- Protected endpoints can now be accessed

---

## 📦 Sample Endpoints

| Method | Endpoint              | Description                |
|--------|-----------------------|----------------------------|
| POST   | `/auth/register`      | Register a new user        |
| POST   | `/auth/login`         | Authenticate and get token |
| GET    | `/users`              | List all users             |
| GET    | `/users/{id}`         | Get user by ID             |
| PUT    | `/users/{id}`         | Update user                |
| DELETE | `/users/{id}`         | Delete user                |

---

## 🧪 Running the Project

1. Clone the repository  
   `git clone https://github.com/yourusername/springboot-authentication-user-crud.git`

2. Navigate into the folder  
   `cd springboot-authentication-user-crud`

3. Build the project  
   `mvn clean install`

4. Run the app  
   `./mvnw spring-boot:run` or run from your IDE

---

## 💡 What I Learned

- Building secure REST APIs with Spring Security
- JWT token generation and validation
- Clean architecture: separation of concerns
- Using DTOs to avoid exposing sensitive data
- How to write testable, scalable service layers

---

## 📫 Contact Me

I'm currently looking for Java backend opportunities.  
Feel free to reach out if you're hiring or want to collaborate!

**Email:** [csmadlopha@gmail.com](mailto:csmadlopha@gmail.com)  
**LinkedIn:** [linkedin.com/in/celenkosini-s-nyawo-83368278](https://www.linkedin.com/in/celenkosini-s-nyawo-83368278)

---

## 📃 License

This project is open source and free to use under the MIT license.
