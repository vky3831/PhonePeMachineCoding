# PhonePe Mini Wallet

Design a prepaid mini wallet for PhonePe through which users can send and receive money among themselves and can win exciting offers. The user can load money into the wallet via. various sources like CC, DC, UPI, etc, and perform easy transactions.

### Requirements

* Users need to register on PhonePe to use this wallet.
* The minimum amount of transaction will be always greater the 0
* The user must have a sufficient balance in his wallet while doing the transaction.
* The user can fetch their wallet balance
* The user can sort transactions history (eg. based on date, amount, etc)
* The user can filter transactions history (eg. based on user etc)
* The transactions will be eligible for exciting offers if they meet their respective criteria.

### Capabilities
 
* Below are various functions that should be supported with the necessary parameters passed. 
* These are just suggestions, the signatures can be altered as long as the functionalities are provided.
  * registerUser - register user to use wallet
  * topUpWallet() - add money to the wallet
  * fetchBalance() - fetch balance available in the wallet
  * sendMoney() - send money to the user, all the eligible offers will be applicable for transaction
  * getTransactions(filter, sorter) - fetches transactions history based on filter and sorting criteria

### Offers
#### Following are example offers supported by the wallet

* The first transaction amounting greater than 100 will be eligible for 10% cashback
* If the wallet balance of both the sender and receiver becomes equal after the transaction then 5% cashback.
### Guidelines

* You should store the data in memory using a language-specific data structure.
* Implement clear separation between your data layers and service layers.
* Simple and basic functions are expected as entry point - no marks for providing fancy restful API or framework implementation
* Because this is a machine coding round, heavy focus would be given on data modeling, code quality etc, candidate should not focus too much time on algo which compromises with implementation time
### Expectations:

* Your code should cover all the mandatory functionalities explained above.
* The code should be extendable and scalable to incorporate future extensions.
* Your code should be executable and clean.
* Your code should be properly refactored, and exceptions should be gracefully handled.
* Appropriate errors should be displayed on the console
* Code should be extendable to support multiple offers , filters and sorters
### How will you be evaluated?

* Code Should be working
* Code readability and testability
* Separation Of Concerns
* Abstraction
* Object-Oriented concepts
* Exception handling
* Language proficiency.
* Scalability
* Test Coverage (Bonus Points)