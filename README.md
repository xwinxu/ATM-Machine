# ATM Machine Phase 2 | Group 0320
`To clone our repo: https://markus.teach.cs.toronto.edu/git/csc207-2019-01/group_0320`
## Project Setup Instructions
1. Open Intellij. Check out from version control -> 
                Create project from existing sources ->
                Java 1.8 and click continue until the end.
2. Project root location: phase2/src/ATM/
3. We write to files in our program, so ensure project structure and files available are like so upon cloning:
```
+ phase2 
|    ATM.mp3
|    21BankAccounts.mp3
|    design.pdf
|    TODO.txt
|    README.md
+-- src
|      +-- ATM
|           +-- controllers
|               +-- admin
|                   <All admin GUI controllers/FXMLs>
|               +-- resources
|                   <All mp3 files>
|               +-- teller
|                   <All teller GUI controllers/FXMLs>
|               +-- user
|                   <All user GUI controllers/FXMLs>
|               MainATM
|           +-- main
|               +-- accounts
|                   <All account classes>
|               +-- clients
|                   <All client accounts>
|               +-- UIOptions
|                   <UI and UIOption classes>
|               +-- utilities
|                   <All utility classes>
|               ATMMachine
+-- test
```

## Running the Program
Run MainATM as an entry point into the ATM machine. 
Our GUI will pop up and you can create new users by clicking on `CREATE` and continue on from there.

## Program Functionality
#### Initial start:
1. To login as a BankManager, enter `manager` as username and `pass` as password
2. To login as a user, create a new account with username and password
> Note: must approve a new user as a BankManager. Logout then log back in as BankManager
to approve the request for a new account by selecting `setup new user`.
Then it will be added to our list of currently active accounts upon approval.


#### 4 new text files created upon start:
1. cash_inventory.txt: the current amount of cash in our ATM (CURRENCY_TYPE:QUANTITY)
2. config.txt: the current date
3. alerts.txt: when anything in the cash inventory is below 20, we write an alert
4. mapList.txt: serialized BankManager containing all of our bank accounts and classes

#### Logged in as existing user:
1. Choose from one of the options to make transactions on the ATM
> If a transaction option is selected, the ATM's state is updated in real time
2. If at any time you'd like to logout, enter "LOGOUT" to be brought back to the main menu

#### Logged in as bank manager user:
1. Choose from options relevant to ATM configuration (i.e. initiate system date upon start 
/ restock cash into the machine / undo a given user's most recent transaction, etc)
2. If at any time you'd like to logout, click "LOGOUT" to be brought back to the main menu
> Once logged out, the ATM's state will update in real time for the next user

##### Additional notes:
* We have controllers and main folders. The controllers are for our GUI and main is for our commandline interface. Program can be run from either, the complexity and functionalities are the same.
