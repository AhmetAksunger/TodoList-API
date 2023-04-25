# TodoList-API
<br/>
<p align="center">
  <a href="https://github.com/AhmetAksunger/TodoList-API">
    <img src="https://imgtr.ee/images/2023/04/24/8KEvb.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">TodoList API</h3>

  <p align="center">
    <a href="https://github.com/AhmetAksunger/TodoList-API"><strong>Explore the docs »</strong></a>
    <br/>
    <br/>
    .
    <a href="https://github.com/AhmetAksunger/TodoList-API/issues">Report Bug</a>
    .
    <a href="https://github.com/AhmetAksunger/TodoList-API/issues">Request Feature</a>
  </p>
</p>

![Downloads](https://img.shields.io/github/downloads/AhmetAksunger/TodoList-API/total) ![Forks](https://img.shields.io/github/forks/AhmetAksunger/TodoList-API?style=social) ![Issues](https://img.shields.io/github/issues/AhmetAksunger/TodoList-API) 

## Table Of Contents

* [About the Project](#about-the-project)
* [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Installation](#installation)
* [Usage](#usage)
* [SQL Template](#sql-template)
* [Contributing](#contributing)
* [License](#license)

## About The Project

![Screen Shot](https://imgtr.ee/images/2023/04/24/8KDNq.png)

This is a Todo List API created with Java Spring. It allows users to create an account and log in. Only authorized users can use the API to manage their Todo Lists and Todo List Items. The API is connected to a Postgre database, which includes five tables: Users, Roles, User Role Junctions, Todo Lists, and Todo Items.

The API contains several endpoints that allow users to manage their todo lists and items, such as creating, updating, and deleting them. These endpoints are restricted based on the user's authorization level, ensuring that only the user can manage their own todo lists and items. If the input is invalid, the API will handle it appropriately using a strong error handling mechanism.

## Built With

Java Spring & PostgreSQL
**Dependencies**
- Spring JPA Data
- Spring WEB
- Spring Security
- Spring DevTools
- Spring Validation
- Lombok
- OAuth2
- Model Mapper
- PostgreSQL Driver

## Getting Started

To use this API, you'll need to have Java and Spring installed on your computer. You'll also need to clone this repository and set up a Postgre database. Then fill the application.properties based on your database


### Installation

application.properties can be found in the following directory
``src/main/resources/application.properties``

**application.properties**
```application.properties
server.port=8000
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.hibernate.show-sql=false
spring.datasource.url=jdbc:postgresql://localhost:5432/todo
spring.datasource.username=postgres
spring.datasource.password=12345
spring.jpa.properties.javax.persistence.validation.mode = none
```
Change "todo" to your database name


``
spring.datasource.url=jdbc:postgresql://localhost:5432/todo
`` 


Fill the username and password fields based on your PostgreSQL application.


``
spring.datasource.username=postgres
spring.datasource.password=12345
``


Also you can change the server port.


``
server.port=8000
``

## Usage

To use this API, you'll need to make requests to the various endpoints using a tool like Postman or curl. The Admin account is added to the database as soon as the application runs.
``username: "admin"``
``password: password``

Here are the examples of the types of requests you can make:

#### User Registration
```http
POST /auth/register
Content-Type: application/json
```
```json
{
    "username": "johndoe",
    "password": "password123",
}
```
#### User Login
```http
POST /auth/login
Content-Type: application/json
```
```json
{
    "username": "johndoe",
    "password": "password123",
}
```
#### User Profile
```http
POST /user/profile
Content-Type: application/json
Authorization: Bearer <token>
```
**``Example Response``**
```json
{
    "username": "ahmet",
    "authorities": [
        {
            "roleId": 2,
            "authority": "USER"
        }
    ]
}
```
#### User Update
```http
POST /user/profile/update
Content-Type: application/json
Authorization: Bearer <token>
```
```json
{

    "username":"johndoe",
    "password":"password123",
    "accountNonLocked":"false"

}
```
#### Create TodoList
```http
POST /user/create/todolist
Content-Type: application/json
Authorization: Bearer <token>
```
```json
{
"name": "John Doe's Yoga Practice Todo List"
}
```
#### Delete TodoList
```http
POST /user/delete/todolist/{id}
Content-Type: application/json
Authorization: Bearer <token>
```
#### Update TodoList
```http
POST /user/update/todolist
Content-Type: application/json
Authorization: Bearer <token>
```
```json
{

    "id":"2",
    "name": "Updated TodoListName"

}
```
#### Create TodoItem
```http
POST /user/create/todoitem
Content-Type: application/json
Authorization: Bearer <token>
```
```json
{
"name": "Yoga class at 6 PM",
"description": "Go to yoga class at 6pm at Mecidiyeköy",
"completed": false,
"todoListId": 1
}
```
#### Update TodoItem
```http
POST /user/update/todoitem
Content-Type: application/json
Authorization: Bearer <token>
```
```json
{

    "id":1,
    "name": "Yoga class at 4 PM",
    "description": "Yoga class was moved to 4 pm, don't forget to attend",
    "completed": false

}
```
#### Delete TodoList
```http
POST /user/delete/todoitem/{id}
Content-Type: application/json
Authorization: Bearer <token>
```
#### User Get TodoLists
```http
POST /user/get/todolists
Content-Type: application/json
Authorization: Bearer <token>
```
**``Example Response``**
```json
[
    {
        "id": 23,
        "name": "Ahmet's Yoga Practice Todo List"
    },
    {
        "id": 24,
        "name": "Ahmet's Shopping TODO List"
    }
]
```
#### User Get TodoLists & Items
```http
POST /user/get/todolistsanditems
Content-Type: application/json
Authorization: Bearer <token>
```
**``Example Response``**
```json
[
    {
        "name": "Buy bunch of onions and 3 bread",
        "description": "This is all we can eat these days in Turkey ;)",
        "completed": false,
        "todoListId": 24,
        "todoListName": "Ahmet's Shopping TODO List"
    },
    {
        "name": "Buy Yoga Equipments",
        "description": "Buy Yoga equipments for the class at 4pm",
        "completed": false,
        "todoListId": 23,
        "todoListName": "Ahmet's Yoga Practice Todo List"
    },
    {
        "name": "Yoga class at 6 PM",
        "description": "Join yoga class at the local gym",
        "completed": false,
        "todoListId": 23,
        "todoListName": "Ahmet's Yoga Practice Todo List"
    }
]
```
#### Admin Get TodoLists & Items
```http
POST /admin/get/todolistsanditems
Content-Type: application/json
Authorization: Bearer <token>
```
**``Example Response``**
```json
[
    {
        "name": "1000 Pushups",
        "description": "1000 push ups in 10 days",
        "completed": false,
        "todoListName": "John's Gym Todo List",
        "todoListId": 21,
        "username": "john"
    },
    {
        "name": "Lose 10 kgs",
        "description": "Lose 10 kgs in 5 months",
        "completed": false,
        "todoListName": "John's Gym Todo List",
        "todoListId": 21,
        "username": "john"
    },
    {
        "name": "Run 4kms",
        "description": "Run 4 kms each day",
        "completed": false,
        "todoListName": "John's Gym Todo List",
        "todoListId": 21,
        "username": "john"
    },
    {
        "name": "Boxing Practice",
        "description": "Attend boxing practice every Tuesday and Thursday",
        "completed": false,
        "todoListName": "John's Boxing Todo List",
        "todoListId": 22,
        "username": "john"
    },
    {
        "name": "Yoga class at 6 PM",
        "description": "Join yoga class at the local gym",
        "completed": false,
        "todoListName": "Ahmet's Yoga Practice Todo List",
        "todoListId": 23,
        "username": "ahmet"
    },
    {
        "name": "Buy boxing gloves",
        "description": "Purchase a new pair of boxing gloves from Decathlon",
        "completed": false,
        "todoListName": "John's Boxing Todo List",
        "todoListId": 22,
        "username": "john"
    },
    {
        "name": "Buy bunch of onions and 3 bread",
        "description": "This is all we can eat these days in Turkey ;)",
        "completed": false,
        "todoListName": "Ahmet's Shopping TODO List",
        "todoListId": 24,
        "username": "ahmet"
    },
    {
        "name": "Buy Yoga Equipments",
        "description": "Buy Yoga equipments for the class at 4pm",
        "completed": false,
        "todoListName": "Ahmet's Yoga Practice Todo List",
        "todoListId": 23,
        "username": "ahmet"
    }
]
```
## SQL Template

![sqltemplate](https://user-images.githubusercontent.com/116587797/234143448-f9d221ae-8bb5-47e1-872c-19bf5fbe2f78.png)

## Contributing

Contributions are what make the open source community such an amazing place to be learn, inspire, and create. Any contributions you make are **greatly appreciated**.
* If you have suggestions for adding or removing projects, feel free to [open an issue](https://github.com/AhmetAksunger/TodoList-API/issues/new) to discuss it, or directly create a pull request after you edit the *README.md* file with necessary changes.
* Please make sure you check your spelling and grammar.
* Create individual PR for each suggestion.
* Please also read through the [Code Of Conduct](https://github.com/AhmetAksunger/TodoList-API/blob/main/CODE_OF_CONDUCT.md) before posting your first idea as well.

### Creating A Pull Request

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

Distributed under the MIT License. See [LICENSE](https://github.com/AhmetAksunger/TodoList-API/blob/main/LICENSE.md) for more information.

## Acknowledgements

* [Unknown Koder](https://github.com/unknownkoder/spring-security-login-system) - *Used his Authentication tutorial and system.*
* [Shaan Khan](https://github.com/ShaanCoding/) - *Built ReadME Template*
