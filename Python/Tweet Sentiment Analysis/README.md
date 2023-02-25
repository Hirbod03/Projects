## Tweet Sentiment Analysis
This is a project for the CS1026 course, which involves performing sentiment analysis on Twitter data to determine the "happiest" timezone in the continental United States. The project consists of two Python scripts: sentiment_analysis.py and main.py.

### sentiment_analysis.py
This Python module contains a function that performs sentiment analysis on Twitter data. The function analyzes each individual tweet to determine a "happiness score" for the tweet, which is found by looking for certain keywords in the tweet and totaling their "sentiment values". The "sentiment values" range from 1 to 10.

The function separates a tweet into words based on white space and removes any punctuation from the beginning or end of the word. It also converts the word into lower case letters. If a word matches any of the sentiment keywords, the function adds the score of that sentiment keyword to a total for the tweet. If the tweet has at least one matched keyword, it is called a "keyword tweet".

The function counts the number of tweets and the number of keyword tweets for each timezone (Eastern, Central, Mountain, Pacific). The "happiness score" for a timezone is the sum of the happiness scores for all the keyword tweets in the region divided by the number of keyword tweets in that region.

### main.py
This Python script uses the sentiment_analysis.py module to analyze Twitter data. The script reads tweets from the tweets.txt file and keywords from the keywords.txt file. It then calls the sentiment analysis function to determine the "happiest" timezone in the continental United States.

### Files
The repo includes the following files:

sentiment_analysis.py: A Python module that contains a function to perform sentiment analysis on Twitter data.
main.py: A Python script that uses the sentiment_analysis.py module to analyze Twitter data.
tweets.txt: A text file containing the tweets to be analyzed.
keywords.txt: A text file containing the sentiment keywords and their corresponding scores.
README.md: This file, which provides an overview of the project.

### Usage
To use this project, simply run the main.py script. The script will read tweets from the tweets.txt file and keywords from the keywords.txt file. It will then call the sentiment analysis function to determine the "happiest" timezone in the continental United States.

### Author
This project was created by Hirbod Hosseini for the CS1026 course at Western University in fall of 2021.
