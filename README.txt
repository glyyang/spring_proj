Instruction:

Requirement:

Apache Tomcat, Java 8, MySQL 8, JRI;



Application setup:

1. download Apache Tomcat 8 Webserver from https://tomcat.apache.org/download-80.cgi, choose 32-bit/64-bit Windows Service Installer;
2. download Java 8 from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html, choose Java SE Development Kit 8u181, Windows x64, and click Accept License Agreement;
3. follow the procedure specified by the installer and download JDK 1.8, then start Tomcat installer;
4. follow the procedure until when asked about Java Runtime, open the folder where JSK 1.8 is installed, then open jre folder, and copy the path of jre folder as Java Runtime path;
5. finish rest of steps for installing and setup Tomcat (note: you must set user name and password for your Tomcat server in order to use Tomcat GUI, simplest way is to set it during installation time through installer);
6. download R 3.5.1 from https://cran.r-project.org/bin/windows/base/, open first link on upper left corner of page;
7. after download finishes, run the installer and follow the instructions specified by installer;
8. after downloading R, go to https://www.rstudio.com/products/rstudio/download/ and download RStudio Desktop version, set R location to where R 3.5.1 is installed;
9. open RStudion, and through script run 
		packages.install("reshape");
		packages.install("ggplot2");
		packages.install("rJava");
10. open My Computer root directory, the right click and open property;
11. click Change Settings, click Advanced, then click Environment Variables;
12. add following System Variables:
	R_HOME: path to your R 3.5.1 root folder;
	JRE_HOME: path to your JRE root folder (one level inside your installed JDK root folder);
	JAVA_HOME: path to your JDK root folder;
13. add following paths in path variables in under System Variables:
	path to your jri root folder, it should be inside rJava folder that was installed through RStudio script (usually inside R library root folder inside User Document folder);
	path to your R.dll parent folder, it should be the inside the bin folder in R 3.5.1 root folder, depends on the type of operating system;
14. download MySQL 8 from https://dev.mysql.com/downloads/installer/, use second installer, then follow instruction to set admin user name and password as well as download both 32x and 64x versions of MySQL servers;
15. export application as a WAR file, and through Tomcat GUI (localhost:8080 by default or whatever that was set during Tomcat installation), click manage applications, and deploy the war file onto Tomcat;
16. go into the tomcat root folder, open conf, then open server.xml, add in following line within Host tag:
		<Context docBase="spring_proj" path="/controller"/>
	then add following attribute to Host tag:
		deployOnStartup="true"
17. go into Services, find Apache Tomcat, and set it to startup automatically;
18. now migrate database. Simplest way is export all database table from old database as cvs files, then create a new schema in new database called g128 and import all cvs files by child-parent dependency order into created schema;
19. once everything is done, restart Tomcat Server, and go to localhost:8080/controller/index (depends on which port tomcat is using) to start using application;



Aplication run:

index page: gives a menu for access to all the other pages, it is the supposed homepage of the application;
manual update page: gives functionality of manually fetching orders information from Amazon, the user have to specify the time periods in which the orders are made;
order searching page: gives functionality to view basic information of most recent 1000 orders. The information viewed include order fulfillment channel, order id, order purchase date, etc.
adding and checking order mapping page: enable user to check database inventory-item-to-order-item mapping, it will show both mapped and unmapped items, and allow user to add or update mappings;
checking order items by property: let user to check all order items according to item description. The description would be matched with mapping data; in other words, it will only show items that are mapped as unmapped items would be considered property-undefined;
show report page: present user with statistical report of items in interest. User can put in different constraints and criteria in the form at top of page and get a summarial view on the statistical growth/drop of their items in insterest; the report includes the statistical comparisons of target items sale at given time against various other times such as a day ago, a month ago, a year ago, etc., the raise and drop of the sale would also be shown in the report as well as distribution of different items during target sale constraint;
analysis page: currently the page with display a distribution graph per user input with a curvature regression to aid user analysis on data distribution patterns. User have to put in different constraint parameters such as purchase time range and item title. If all inputs are blank, the application would graph the distribution of all orders since 2014/01/01.



Amazon MWS consumption format:

done through JAX-B, unmarshal XML data directly into object or marshal Java POJO/bean into XML tags; the XML format can be found on Amazon MWS Java SDK page;
