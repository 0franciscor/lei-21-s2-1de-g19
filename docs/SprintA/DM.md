# OO Analysis #

The construction process of the domain model is based on the client specifications, especially the nouns (for _concepts_) and verbs (for _relations_) used. 

## Rationale to identify domain conceptual classes ##
To identify domain conceptual classes, start by making a list of candidate conceptual classes inspired by the list of categories suggested in the book "Applying UML and Patterns: An Introduction to Object-Oriented Analysis and Design and Iterative Development". 


### _Conceptual Class Category List_ ###

**Business Transactions**

* Test

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
* medical lab technician
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

* Company (Many labs)


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
| Administrator | creates | test type |
| Blood Test  	| is a    	| Test  |
| Category | created by | administrator |
| Company | owns | chemical laboratory |
|         | owns | clinical analysis laboratory |
|         | perfoms | test |
|         | conducts | test type |
| Covid-19 Test  	| is a    	| Test  |
| Clinical analysis laboratory | performs | test |
|   						   | performs    	| Blood Test  |
|   						   | performs    	| Covid-19 Test  |
| Clinical chemistry technologist | registers | result |
| 								  | works for | chemical laboratory |
| Lab order | brought by | client |
|           | contains | test type |
| Medical lab technician | registers | sample |
|   					 | works for | clinical analysis laboratory |
| Parameter | present under | category |
| Receptionist | works for | clinical analysis laboratory |
| 			   | registers | test |
|  			   | registers | client |
|  			   | receives | lab order |
| Result | analyzed by | specialist doctor |
| Sample | analyzed by | clinical chemistry technologist |
| 		 | is sent to | chemical laboratory |
| Specialist doctor | registers | report |
| Test | requested by | client |
|  	   | is of | test type |
|      | requests analysis of | parameter |
|      | collects | sample |

## Domain Model

![DM.svg](DM.svg)