## About the app:
this a android app that helps users to find a restaurant in their location by price filter.

## Frontend:
the Frontend was write on **Android studio** in **Kotlin** languege , i worked with **FireBase** for **User Authentication** and used Travel Advisor **API** for data
acquisition about the restaurants in **JSON** format ,in addition i used LazyColum from **Jetpack** unit and **Material Design Components**.
to run the code on **Android studio** you need to few steps:

1.get api key from [rapidapi](https://rapidapi.com/apidojo/api/travel-advisor/) and past in Repository class.

2.past your local machine ip in RetrieveFeedTask and RetrieveFeedTaskForRestaurant.

## Backend:
the TCP server was write on **Eclipse** in **Java** languege , the server conected to **MySql** database.
to run the server you need to create on the MySQl two tabeles:

Table1: restaurant
Columns:
idrestaurant int AI PK 
name varchar(45) 
raw_ranking varchar(45) 
address varchar(45) 
photo varchar(100) 
userEmail varchar(45)

Table2: users
Columns:
idusers int AI PK 
name varchar(45) 
phone varchar(45) 
age varchar(45) 
address varchar(45) 
mail varchar(45) 
admin varchar(45) // 
To make a user an administrator you need to change the value in this column (admin) to one, in the existing version this can only be done directly through the MySQL DB.
When a user is defined as a manager and he marks a restaurant as a favorite then all users will see it in their favorites lists (this is creating a restaurant advertisement).

## The App Flow
![image](https://user-images.githubusercontent.com/76586954/178093031-17c9f6a1-3338-4b06-b7b7-eb7966fded4f.png)
