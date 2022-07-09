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
admin varchar(45)

## The app in action
[Demo](https://youtu.be/dAWCRi3Og_0).

## App download
click this link from your mobile app [click here](https://github.com/nikaloamashvili/Restaurant-Finder/blob/main/Client%20side/app-debug.apk).


