# a3-journalapp-marshalharman
a3-journalapp-marshalharman created by GitHub Classroom


## a) DETAILS
Project Name: Journal App

Name: Harman Marshal Singh

BITS ID: 2018B4A70843G

Email: f20180843@goa.bits-pilani.ac.in


## b) APP DESCRIPTION
### Journal App
A basic journal app which allows user to record their day to day activities. Each entry in the journal includes title, date, start and end times.
### Bugs:
This app does not checks if end time is greater than start time or not. Technically, end time should be after start time but a user can set otherwise.

## c) TASKS DESCRIPTION
### Task1: Implementing the Navigation
* Added args for navigating to entryDetailsFragment.
* Added modify entry action from entrylistFragment to entryDetailsFragment.
* Created InfoFragment and added action from entryListFragment.
* For navigation to time/date picker and infoFragment navigation was done by id whereas for navigating to entryDetailsFragment safe args were used.

### Task2: Modify the database
* Added new columns for date, startTime, endTime as strings in Journal Entry and implemented the getters and setters.
* Added delete query in the Dao and delete function in the Repository for deleting an entry.
* Added a function for getting all the entries as a list for testing in the DAO.

### Task3: 
