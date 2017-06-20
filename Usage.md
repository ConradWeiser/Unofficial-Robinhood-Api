##### So, you're a Java developer. That means you must like Object Oriented things! Well, so do I!
---

**Introduction**

If you're considering using this wrapper, It's probably safe to assume that you're looking to build a system using the Robinhood Investing Service in some way or another. This program was designed with the idea of supplimenting and giving the functionality to hook onto all of the Robinhood services without doing it yourself. 

At first glance in the javadocs, it's easy to be overwhelmed by how many classes are available in this module, although in design, you only need to worry about one. Enclosed within this Wiki page should be enough information to implement this wrapper with ease, so you can worry about the rest of the program that you're writing.

A big thank you to @sanko for making a [Robinhood API Cheat Sheet](https://github.com/sanko/Robinhood) which aided in creating this wrapper immensely.

---

### **Installation**
Coming soon

---

### **Using the API Wrapper**
Usage is as simple as importing the ***RobinhoodApi*** class, and using it as an object. After importing, there are two options you can use to instantiate the class.

The following method creates a Robinhood Api object which is not logged into any perticular account. Note that you will NOT be able to use any API call which requires you to be logged in. Refer the charts in the sections below to find out what you can, and cannot use when it's created like this. You can however run a method enclosed in this object to log the instance into a user.

```RobinhoodApi api = new RobinhoodApi()```

The other way which you can instantiate the wrapper, is by supplying a Username and Password in string format. This automatically runs the login command and stores the authentication token in it's configuration.

```RobinhoodApi api = new RobinhoodApi("username", "password")```

---



### **Authentication**

Currently, the wrapper does NOT support accounts with Multifactor Authentication enabled on their account. This may be a feature to be added later however.

Command | Function | Returns | Description | Requires Logged In User
---- | ------- | -------- | ------- | -----
Login | logUserIn("username", "password") | Request Status (Put link here)| Logs a user into the instanced object. | <center>:heavy_multiplication_x:</center>
Logout | logUserOut | Request Status | Logs a user out of the instanced class, and deletes the stored authentication token | <center>:heavy_check_mark:</center>
Retrieve Authentication Token | getAccountAuthToken() | String | Retrieves the Account Token which is used to run requests for the currently logged in user. | <center>:heavy_check_mark:</center>



