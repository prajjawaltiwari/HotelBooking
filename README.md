## REST API Collateral Management Serivce ,using the spring,hibernate,H2 database and maven.

### Prerequisities
JDK :- 1.7  
Maven:-3.0 or later  
Tomcat:- 1.7 or later

### API Documentation.
To test the REST API , login to Collateral Risk Assessment portal url(http://localhost:8081/login),Enter Valid Credentials and Select Save Collateral<br>

1. Get Collateral Details on the basis of loanid and cutomerid <br>
URL :- localhost:8082/getCollaterals/{loanId}/{customerId}<br>
provide the loanid and customerid , which will return response of all the collateral details on the basis of loanid and customerid.<br>

2. Get Collateral ID on the basis of Loanid<br>
URL :- localhost:8082/getCollateralId/{loanId}<br>
provide the loanid , which will return collateralid on the basis of loanid.<br>

3. Save Real Estate details <br>
URL :- localhost:8082/saveRealEstate<br>
method :- post<br>
After hitting this api , and adding details through portal , values are saved in h2 database. <br>

4. Save Cash Deposit details  <br>
URL :- localhost:8082/saveCashDeposits<br>
method :- post<br>
After hitting this api , and adding details through portal , values are saved in h2 database. <br>



## Automated test cases will be added using the Junit and mockito.
Unit tests can be executed using the Unit-tests feature branch.