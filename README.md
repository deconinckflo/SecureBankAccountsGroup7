# SecureBankAccountsGroup7

## Question 1 : Explain how you ensure user is the right one starting the app

We added a login system to ensure user is Destini Dickens, we added the user in the SQL lite database, we hashed his password in the database.
Every time the user starts the app, he must provide his lastname and firstname and the right password in order to connect.

## Question 2 : How do you securely save user's data on the phone ?

We store user's data in a SQLlite database (which is located in the app private files.

## Question 3 : How did you hide the API url ?

We used enigma plugin to encrypt strings in the source code. If the attacker try to decompile our apk, he will not be able to retrieve the api url.

## Question 4 : Screenshots of the app

**Login screen :**

![LOGIN ACTIVITY](/images/1.PNG)

**User's accounts page :**

![ACCOUNTS ACTIVITY](/images/2.PNG)

**Menu :**
![MENU](/images/3.PNG)

**Make a transfer page :**

![TRANSFER PAGE](/images/4.PNG)
![MAKING TRANSFER](/images/5.PNG)
![TRANSFER DONE](/images/6.PNG)

