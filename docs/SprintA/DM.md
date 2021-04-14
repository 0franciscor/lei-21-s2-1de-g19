# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Test Diagnosis

---

**Transaction Line Itemss**

* Sample

---

**Product/Service related to a Transaction or Transaction Line Item**

*  Covid-19 test 
*  Blood test

---


**Transaction Records**

*  Report
*  Lab order
*  Result

---


**Roles of People or Organizations**

* Client
* Administrator
* Clinical chemistry technologist
* Laboratory coordinator
* MLT
* Receptionist
* Specialist doctor

---


**Places**

*  Application
*  Clinical Analysis Laboratory
*  Chemical Laboratory 

---

**Noteworthy Events**

* Chemical Analysis

---


**Physical Objects**

* Swab

---


**Descriptions of Things**

*  Type of Test (TestType)
*  Category


---


**Catalogs**

*  

---


**Containers**

*  Parameters

---


**Elements of Containers**

*  PLT
*  RBC
*  WBC

---


**Organizations**

*  Many Labs

---

**Other External/Collaborating Systems**

*  External module
*  Internal code
*  Internal process


---


**Records of finance, work, contracts, legal matters**

* 

---


**Financial Instruments**

*  

---


**Documents mentioned/used to perform some work/**

* 
---



###**Rationale to identify associations between conceptual classes**###

An association is a relationship between instances of objects that indicates a relevant connection and that is worth of remembering, or it is derivable from the List of Common Associations: 

+ **_A_** is physically or logically part of **_B_**
+ **_A_** is physically or logically contained in/on **_B_**
+ **_A_** is a description for **_B_**
+ **_A_** known/logged/recorded/reported/captured in **_B_**
+ **_A_** uses or manages or owns **_B_**
+ **_A_** is related with a transaction (item) of **_B_**
+ etc.



| Concept (A) 		|  Association   	|  Concept (B) |
|----------	   		|:-------------:		|------:       |
| Many Labs  	| performs    		| Tests  |
|   			| conducts    		| Test Type  |
| Category  	| created by    	| Administrator  |
| Test  		| requested by    	| Client  |
|   			| is a    			| Type of Test  |
|   			| collects    		| Sample  |
|   			| requests analysis of    	| Parameter  |
| Company  		| owns    	| Chemical Laboratory  |
|   			| owns   	| Clinical Analysis Laboratory  |
| Clinical Analysis Laboratory  	| performs    	| Blood Test  |
|   			| performs    	| Covid-19 Test  |
| Blood Test  	| is a    	| Test  |
| Covid-19 Test  	| is a    	| Test  |
|               |               |       |
|               |               |       |
|               |               |       |
| Specialist doctor | registers | report |
| Result | analyzed by | specialist doctor |
| Clinical chemistry technologist | registers | result |
| 								  | works for | chemical laboratory |
| Sample | analyzed by | clinical chemistry technologist |
| 		 | is sent to | chemical laboratory |
| Medical lab technician | registers | sample |
|   					 | works for | clinical analysis laboratory |
| Company | owns | chemical laboratory |
|         | owns | clinical analysis laboratory |
|         | perfoms | test |
|         | conducts | test type |
| Clinical analysis laboratory | performs | test |
| Receptionist | works for | clinical analysis laboratory |
| 			   | registers | test |
|  			   | registers | client |
|  			   | receives | lab order |
| Test | requested by | client |
|  	   | is of | test type |
|      | requests analysis of | parameter |
|      | collects | sample |
| Lab order | brought by | client |
|           | contains | test type |
| Parameter | present under | category |
| Category | created by | administrator |
| Administrator | creates | test type |



## Domain Model

![DM.svg](DM.svg)