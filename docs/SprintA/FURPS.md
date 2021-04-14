# Supplementary Specification (FURPS+)

## Functionality

_Specifies functionalities that:_

- _are common across several US/UC;_
- _are not related to US/UC, namely: Audit, Reporting and Security._

  * Security
    * “All those who wish to use the application must be authenticated...”
    * "In case of a new client, the receptionist registers the client in the application."
    * "...the receptionist needs the client’s citizen card number, National Healthcare Service (NHS) number,birth date, sex, Tax Identification number (TIF), phone number, e-mail and name." (encriptação)
    * "... a password holding seven alphanumeric characters, including three capital letters and two digits."
    * "Each test is characterized by an internal code..." (????)
  * Auditing
  * Authentication
    * "All those who wish to use the application must be authenticated with a password..."
  * Communication
    * "...and identifying each sample with a barcode that is automatically generated using an external API."
    * "...with all the information demanded by the NHS and should send them to the NHS using their API."
    * "... the client receives a notification alerting that the results are already available in the central application..."
    * "The client receives the notification by SMS and e-mail." ( UTILIZAR UMA API SMS ?)
    * "...the application uses an external module that is responsible for doing an automatic validation using test reference values."
  * Licensing
  * Localisation
    * "The application must support the English language only." 
  * Online help
  * Persistence
    * "The application should use object serialization to ensure data persistence between two runs of the application."
  * Printing
    * "...informing that he/she must access the application to view those results." (notification)
  * Reporting
    * "...specialist doctor who makes a diagnosis and writes a report that afterwards will be delivered to the client."
    * "...the report becomes available in the system and must be validated by the laboratory coordinator."
    * "The company is also required to generate daily (automatic) reports..."
  * Scheduling
    * "The company is also required to generate daily (automatic) reports..."
  * Transaction management
  * Workflow
    * preciso de descrever o fluxo das análises



## Usability 

_Evaluates the user interface. It has several subcategories,
among them: error prevention; interface aesthetics and design; help and
documentation; consistency and standards._

  * Accessibility
    * "...the application must allow ordering the clients by TIF and by name."
  * Aesthetics
  * Consistency



## Reliability
_Refers to the integrity, compliance and interoperability of the software. The requirements to be considered are: frequency and severity of failure, possibility of recovery, possibility of prediction, accuracy, average time between failures._

  * Accuracy
  * Availability
  * Recoverability



## Performance
_Evaluates the performance requirements of the software, namely: response time, start-up time, recovery time, memory consumption, CPU usage, load capacity and application availability._

  * Recovery time
  * Response time
  * Shutdown time
  * Start-up time
  * Throughput




## Supportability
_The supportability requirements gathers several characteristics, such as:
testability, adaptability, maintainability, compatibility,
configurability, installability, scalability and more._ 

  * Auditing 
  * Adaptability
  * Compatibility
  * Configurability
    * "The ordering algorithm to be used by the application must be defined through a configuration file."
    * "The algorithm to be used by the application must be defined through a configuration file." (benchmark)
  * Installability
  * Localisation
  * Maintainability
  * Scalability
  * Testability
    * "The development team must implement unit tests for all methods except methods that implement Input/Output operations."
    * "The unit tests should be implemented using the JUnit 4 framework."
    * The JaCoCo plugin should be used to generate the coverage report."




## +

### Design Constraints

_Specifies or constraints the system design process. Examples may include: programming languages, software process, mandatory standards/patterns, use of development tools, class library, etc._

  * "The application must be developed in Java language using the IntelliJ IDE or Netbeans implementation design."
  * "...adopt recognized coding standards..."
  * "...use Javadoc to generate useful documentation for Java code."
  * "All the images/figures produced during the software development process should be recorded in SVG format."





### Implementation Constraints

_Specifies or constraints the code or construction of a system such
such as: mandatory standards/patterns, implementation languages,
database integrity, resource limits, operating system._

  *  "...adopt recognized coding standards..."
  * "...use Javadoc to generate useful documentation for Java code."
  * "...the application should implement a brute-force algorithm (an algorithm which examines each subsequence)..."
  * "Only the specialist doctor is allowed to access all client data." (código em que só o specialist doctor pode ter acesso)




### Interface Constraints
_Specifies or constraints the features inherent to the interaction of the
system being developed with other external systems._

  * "The application graphical interface is to be developed in JavaFX 11."




### Physical Constraints

_Specifies a limitation or physical requirement regarding the hardware used to house the system, as for example: material, shape, size or weight._
