# Team Feedbackathon – Progress Blog

# Ideas
<strong> Customer Feedback Management System </strong> <em>(chosen idea)</em>
Automate the process of categorize customer feedback by taking feedback from different sources and analyse them by creating criteria in terms of the sentiment of the feedback and matching keywords to categorize the feedback for quicker response.

<strong>Expense Management System</strong>
Take receipts as a picture from a phone or a document and extract the text to automatically retrieve and fill in the parameters like store name, location, date, amount and category.

<em><strong>Origins</strong></em>
The idea was initially formulated by Michael and his involvement the project he works on within Accenture. Within his project, the client has to deal with a great deal of customer feedback, so that they can ultimately review this feedback and provide a better service over all. With customer feedback being an integral part of improving a business, the intention of the application, using HP’s IdolOnDemand APIs, is to automate the process of organising feedback from customers and organising by problems that may be most frequent so that problems can be arranged in order of most important, and addressed easily by the business.

<em><strong>Fundamentals</strong></em>
Using the Sentiment Analysis API, the application can pull in a lot of data (from reviews, for example) and analyse this data in order to work out the ‘sentiment’, whether this be positive, negative or neutral. Within the UI, this will be arranged into a Pie Chart and Word Cloud and each topic will be sorted by a colour in the pie chart based on the sentiment (green for positive etc) and the Word Cloud will provide information on the most frequently used words. The Sentiment Analysis API can also analyse the data in order to decide the topic of the feedback received. Due to the fact some topics may be spelled differently (for example, ‘website’, ‘site’, ‘web site’), the Entity Extraction API will be used in order to pull these similarities and compile the data together to acquire a more accurate reading of the topics.

<strong>Extras</strong>

There are a number of extra features which would be desirable to feature in the application in order to make it more useful to the business and make life easier. 
- We would like to make use of the Language Detection API to make this a global product, in order to interpret the language of the feedback received and then passed into the Sentiment Analysis. The Language Detection would enable the business to direct the feedback to the relevant department (should it be a multi-national organisation) and then, with this language information, passed into Sentiment Analysis, which can analyse a number of languages as long as this is specified.
- Speech Recognition would also be desirable, as this would be able to take any feedback provided in person (over the phone or in an interview, for example), converted into text and then passed into the Sentiment Analysis to understand whether this feedback was positive or negative and addressed/categorised accordingly.
- The Text From Image Recognition API could be used in order to extract text from letter, and in turn, parsed into the Sentiment Analysis in order to provide sentiment information.

<strong>Plan</strong>


<strong>Day 1:</strong>

Brainstorming – 1 hour
Planning and Design – 2 hours
Development – Rest of the day

<strong>Day 2:</strong>

Development – 9am to lunch
Integration – 1 hour
Testing – 2 hours
Documentation and Blog Creation – 1 hour

5 words to describe our product

-	Opinion-service
-	Dynamic
-	Analytics
-	Categorisation
-	Consensus 

<strong>Business purpose</strong>

Customer service/people’s opinions about a given topic is an integral role in every business and in some cases such as Amazon is their main business plan. A huge amount of data is gathered, it would immense amount of time to manually sense of this. This data needs to be viewed and actioned upon in real time. Our solution is operating upon a large repository of comments/reviews then getting snapshot of feelings of the customer and categorise and filter upon topic. The JSON that is generated from the API can allow us to create a graphs and charts to depict the data.

We have generated our own scripts to mine the data as well as calling additional API’s to mine twitter. These data sources are only the beginning. We have the initial text and can operate, generate a sentiment analysis and can be categorised to be distributed to individual teams based on topic. This provides routing for the correct person/team to deal with the customer’s feedback.

Another aim would be to add on API’s such as the speech recognition converts mp3 files into text. Written reviews form some of the customer feedback, we thought that recordings generated from the phone calls with the customer which can generate a before and after picture to give a quantifiable element to customer satisfaction.
Business intelligence is vital is today’s competitive world. Before launching a product it could become useful to have instant feedback from multiple sources. Sources which could include; twitter, Facebook and other social media. 

With our application we can find out what people care about!



Progression
Tools
Gradle for build, Git repository, Spring MVC server component with Angularjs and bootstrap for the UI components

<strong>Link to the Web App</strong>
https://feedbackathon.herokuapp.com/

<strong>Walkthrough</strong>
https://youtu.be/vzfkMDTIMPg

