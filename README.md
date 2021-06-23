# Awesome weather

Awesome weather is an awesome application that shows current, daily, and hourly weather in different cities.

The major part of the app was written as a test task in a limited time (~5 days).

**WARNING!!** The used API has its problems. Almost the same weather for all the days in city, invalid search results ***are not client problems***.

## Features
- Display current, daily, and hourly weather in the multiple selected cities.  
- Search cities.  
- Remove selected cities.
- Offline access to selected cities.

     <img src="https://user-images.githubusercontent.com/32322394/123056776-ea928180-d40f-11eb-8579-93d4aa5eac1a.png" width="144" height="320">
    
   <img src="https://user-images.githubusercontent.com/32322394/123065237-a86d3e00-d417-11eb-87a7-99282f61b089.png" width="144" height="320">

## Architecture
The app follows Google recommended architecture approach (MVVM) and Clean Architecture principles.
Application divided into 4 modules by layers:
- app: Presentation layer specific code
- common: contains util classes used in other modules
- domain: use cases, business logic
- data: encapsulates stuff related to local and remote data

## Used libraries and tools
- Android Architecture Components
- Navigation component
- Koin
- Room
- Retrofit
- Kotlin Coroutines
- Timber
- Leak Canary
