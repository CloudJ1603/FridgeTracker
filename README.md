# Fridge Tracker

## Introduction
I propose to design a fridge tracker application which helps users keep track of the remaining days 
of the food they put in the fridge.

### ***What will the application do?***

Users can add food of type vegetables, fruit, meat or leftover 
to the fridge tracker with user specified remaining days. 
The application would automatically order these food items in terms of the
remaining days in descending order, that being said, the food item that will
expire soon will be listed on the top. The application will also outline
the items which have been expired in red. Moreover, user can discard the food 
items that have already expired (with zero remaining days and highlighted in red). 

To simulate the real-life circumstance, user can choose to "forward to the next day", which 
decrement the remaining days of all food items in the fridge tracker by one.

### ***Who will use it?***

The target users would be people who are tired of conventional 
fridge experience, including but not limited to throwing 
rotted ingredients, clearing up smelling leftover, and totally forgetting 
the things purchased. Basically, everyone who has a fridge, and want to 
have a more manageable way of using it, would be the target user of 
this application.


### ***Why is this project of interest to you?***

As a student with huge workloads and tight deadlines all year round. 
It has been a headache for me to maintain a healthy and manageable life-style. 
The management of items in fridge is the most annoying one 
among all housekeeping things. 

## User Stories
In the context of a fridge tracker application:
## Phase 0, 1
- As a user, I want to be able to ***add*** food items to the fridge tracker.
- As a user, I want the fridge track can automatically ***sort*** the items I added with respect to the 
    remaining days in descending order.
- As a user, I want to be able to ***remove*** food items from the fridge tracker.
- As a user, I want to be able to see all the food items in a ***table view***.
- As a user, I want to be able to ***discard*** the expired food from the fridge tracker.
- As a user, I want the fridge tracker can ***simulate*** the passing of days, and change the remaining days
   of the food items accordingly. 
## Phase 2
- As a user, I want to be able to save my fridge tracker's data to file
- As a user, I want to be able to load my fridge tracker's data from file
