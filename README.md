# Banking_system
<b>This is a java project which is based on banking system . </b>                                                                
<b><h3>Tools used  </h3></b>
		<p>* Intellij Idea </p> 
		<p>* mysql j connector</p>                                                                                        
<p>Here we provide some class and his corresponding methods that are used to build this project</p>
<center><u><b><h4>-> Account class</h4></b></u></center>
        <p>* OpenAccount()</p>
        <p>* getAccountNumber()</p>
        <p>* generate_account_number()</p>
        <p>* account_exists()</p>
<u><b><h4>-> User class</h4></b></u>
        <p>* register()</p>
       <p> * login()</p>
       <p> * user_exist()</p>
<u><b><h4>-> accountManager class</h4></b></u>
       <p> * creditMoney()</p>
      <p>  * debitMoney()</p>
       <p> * transfer_money()</p>
      <p>  * check_balance()</p>
<u><b><h4>-> BankingApp class</h4></b></u>
       <p> * MainMenu()</p>
			 <p> * create instances</p>
			<p> * setup connection with db and load drives</p>
   <h1>Schemas</h1>
    <h3>Accounts</h3>

<table>
    <thead>
        <tr>
            <th>Field</th>
            <th>Type</th>
            <th>Null</th>
            <th>Key</th>
            <th>Default</th>
            <th>Extra</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>account_number</td>
            <td>bigint</td>
            <td>NO</td>
            <td>PRI</td>
            <td>NULL</td>
            <td></td>
        </tr>
        <tr>
            <td>full_name</td>
            <td>varchar(255)</td>
            <td>NO</td>
            <td></td>
            <td>NULL</td>
            <td></td>
        </tr>
        <tr>
            <td>email</td>
            <td>varchar(255)</td>
            <td>NO</td>
            <td>UNI</td>
            <td>NULL</td>
            <td></td>
        </tr>
        <tr>
            <td>balance</td>
            <td>decimal(10,2)</td>
            <td>NO</td>
            <td></td>
            <td>NULL</td>
            <td></td>
        </tr>
        <tr>
            <td>security_pin</td>
            <td>char(4)</td>
            <td>NO</td>
            <td></td>
            <td>NULL</td>
            <td></td>
        </tr>
    </tbody>
</table>
<h3>User </h3>
<table>
    <thead>
        <tr>
            <th>Field</th>
            <th>Type</th>
            <th>Null</th>
            <th>Key</th>
            <th>Default</th>
            <th>Extra</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>full_name</td>
            <td>varchar(255)</td>
            <td>NO</td>
            <td></td>
            <td>NULL</td>
            <td></td>
        </tr>
        <tr>
            <td>email</td>
            <td>varchar(255)</td>
            <td>NO</td>
            <td>PRI</td>
            <td>NULL</td>
            <td></td>
        </tr>
        <tr>
            <td>password</td>
            <td>varchar(255)</td>
            <td>NO</td>
            <td></td>
            <td>NULL</td>
            <td></td>
        </tr>
    </tbody>
</table>
<h2>Description</h2>
<p> In this project firstly you should register yourself with valid email and name (if email is already not  registered ) then login and gave your email id and the password that you already registered and click on create new account
and then gave your details like full name then gave intial amount and after that you should gave a security pin which is used in transaction 
after that one account number is generated and showed and you can perform many tasks like credit ,debit,transfer money and check balance etc </p>

        
