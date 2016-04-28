## SQL injection example: Java with MySQL

Subtitle: how not to write a login screen

To set up: start MySQL server running, then run the following commands in the console to create a passwords database, passwords table, and add some test data.

```
create database passwords;

use passwords;

create table passwords (username varchar(30), login varchar(30), password varchar(30));

insert into passwords values ('Abby Admin', 'admin', 'kittens');
insert into passwords values ('Ben SysAdmin', 'ben', 'octopus');
insert into passwords values ('Carl ComputerTech', 'carl', 'mouse');
insert into passwords values ('Deb Developer', 'deb', 'zebra');
```


Edit the database username and password in PasswordDatabase.java to appropriate values for your system, and then run the code.

Try logging in with one of the valid passwords, and also try with an invalid password. You should see an appropriate success or failure message.

OK, now try this for the login and password.

```
login = 'whatever' and password ' or '1'='1
```
Who are you logged in as?

Now, try some of the other attacks from the .NET/SQLServer version of this - they should all work here.
https://github.com/minneapolis-edu/vb-sql-injection

* Can you create yourself a new account?
* Can you delete someone else's account?
* Can you change someone's password? 
* Can you discover someone's password? 

Consider this could be a login form on a website that anyone could access. This is a huge problem. 

OK, so how to fix? One very useful preventative measure is parameterized queries. Try replacing the SQL statement at PasswordDatabase:44 with a parameterized query, and then try the evil SQL again. It shouldn't work. This is why you should always user parameterized queries, and doubly always when user input is involved!
