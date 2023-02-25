# Importing function from module
from sentiment_analysis import compute_tweets

# Taking names of files as input
tweetsFile = input("Please enter name of the tweets containing file (include file type extension): ")
keyFile = input("Please enter name of the keywords containing file (include file type extension): ")

# Storing list from computing function
res = compute_tweets(tweetsFile, keyFile)

# Checking for empty list
if res:
    # Assigning variables to tuples
    east = res[0]
    central = res[1]
    mountain = res[2]
    pacific = res[3]

    # Printing formatted data from tuples
    print("Eastern Region | Happiness Score: %.3f, %d keyword tweets and %d total tweets." % (east[0], east[1], east[2]))
    print("Central Region | Happiness Score: %.3f, %d keyword tweets and %d tweets." % (central[0], central[1], central[2]))
    print("Mountain Region | Happiness Score: %.3f, %d keyword tweets and %d total tweets." % (mountain[0], mountain[1], mountain[2]))
    print("Pacific Region | Happiness Score: %.3f, %d keyword tweets and %d total tweets." % (pacific[0], pacific[1], pacific[2]))
else:
    print("File(s) not found")
