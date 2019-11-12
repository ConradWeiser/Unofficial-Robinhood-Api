
# Consider this repository deprecated

Unfortunately, due to some incidents with people accessing the Robinhood APIs directly, Robinhood Financial LLC has made some drastic changes to their API. This library no longer works with the current service. 

Robinhood does not appear like they will be making their new API system public, and as such, this repository should be considered archived. I hope it was some use while it was still alive!

# Unofficial Robinhood Api (Java Wrapper)

More documentation and features to come. 

A Java wrapper providing easy access to the [Unofficial Robinhood Api](https://github.com/sanko/Robinhood).

Currently Implemented:

* Logging users in and out
* Get account information - [Click me for response contents](https://github.com/sanko/Robinhood/blob/master/Account.md#gather-list-of-accounts)
* Getting information about tickers
* Making orders
* Get holdings user is currently invested in
* More to come


## Installation

Include the latest .jar file from the [Release Page](https://github.com/ConradWeiser/Unofficial-Robinhood-Api/releases) in your system build path.

Maven/Gradle options will probably become available in the future


## Usage

[Javadocs available - Click me!](https://conradweiser.github.io/Unofficial-Robinhood-Api/)
This library is built with the intention of making extracting information from the Robinhood API as easy as possible. 

Say for the following example, we want to get our account number, and how much buying power we have available

```
//Providing a username and password automatically logs the instance into our account!
RobinhoodApi api = new RobinhoodApi("username", "password");
    
//Make the request for all of the account information
AccountElement accountData = api.getAccountData();
    
//Extract the data we want
String accountNumber = accountData.getAccountNumber();
Float buyingPower = accountData.getBuyingPower();
    
//Print to console!
System.out.println(accountNumber + " : " + String.valueOf(buyingPower));
```

### For more detailed instructions on usage, [Click me!](https://github.com/ConradWeiser/Unofficial-Robinhood-Api/blob/master/Usage.md)

## Things to do

* Include a more comprehensive guide on what data you are getting from each method (Probably within the javadocs)
* Continue implementing more of the working methods we have available
* Streamline the library framework in general. Currently things work very well, but it's not perfect
* Update this Readme so it's more pretty, and has more information. Consider this a v-0.1 as I continue to write the library

____

If you have any questions, comments or suggestions, please do throw me an Email! 
    
    
