# movies
Demo project for an internship

Setup postgre sql database before running.


**Intellij http request example (no tests yet):**
// Inventory side
###
POST http://localhost:8080/api/inventory
Content-Type: application/json

{
  "title": "The wolf of Wall Street",
  "releaseYear":2013, "releaseMonth":12, "releaseDay": 17,
  "category": "Biographical crime black comedy",
  "actors": "Leonardo DiCaprio, Jonah Hill, Margot Robbie, Matthew McConaughey",
  "description": "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government."
}

###
GET http://localhost:8080/api/inventory

###
GET http://localhost:8080/api/inventory/category

###
GET http://localhost:8080/api/inventory/popularity

###
GET http://localhost:8080/api/inventory/rented

###
PATCH http://localhost:8080/api/inventory/1
Content-Type: application/json

{
  "title": "New title",
  "releaseYear": 2010
}

###
DELETE http://localhost:8080/api/inventory/1


// Checkout side
###
POST http://localhost:8080/api/checkout/new

###
GET http://localhost:8080/api/checkout/1

###
POST http://localhost:8080/api/checkout/1
Content-Type: application/json

{
  "movieId": 2,
  "weeks": 5
}
