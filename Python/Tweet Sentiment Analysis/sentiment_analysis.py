# Returns the ratio of two given arguments
def ratio(numerator, denominator):
    # Checks if argument is 0
    if denominator == 0:
        return 0
    else:
        # Returns ratio
        return numerator / denominator


# Returns a region based on long and lat values
def geo_locator(coordinates):
    # Lat-Long points
    p1 = (49.189787, -67.444574)
    p2 = (24.660845, -67.444574)
    p3 = (49.189787, -87.518395)
    p5 = (49.189787, -101.998892)
    p7 = (49.189787, -115.236428)
    p9 = (49.189787, -125.242264)
    latitude = float(coordinates[0])
    longitude = float(coordinates[1])
    # Sets latitude domain
    latRange = p1[0] >= latitude >= p2[0]
    # Checks if longitude and latitude fit in their respective domains
    if p1[1] >= longitude > p3[1] and latRange:
        return "east"
    if p3[1] >= longitude > p5[1] and latRange:
        return "central"
    if p5[1] >= longitude > p7[1] and latRange:
        return "mountain"
    if p7[1] >= longitude >= p9[1] and latRange:
        return "pacific"


def remove_punctuation(array):
    # Constant of punctuation characters for comparison
    PUNCTUATION = "!\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~"
    # Loops through list
    for i in range(0, len(array)):
        # Loops through word character by character
        for letter in array[i]:
            # Checking to see if character is at beginning or end of word
            if array[i].index(letter) == 0 or array[i].index(letter) == len(array[i]) - 1:
                # Checking for punctuation character
                if letter in PUNCTUATION:
                    # Replacing punctuation characters with nothing
                    word = array[i].replace(letter, "", 1)
                    array[i] = word
    return array


# Goes through string char by char deleting chars that are not alpha
def remove_data(sentence):
    # Loops through chars in string
    for letter in sentence:
        # If char is not a char from the alphabet
        if not letter.isalpha():
            # Removes that occurrence of char in string
            sentence = sentence.replace(letter, "", 1)
        else:
            # Returns modified string when loop reaches alpha character
            return sentence
    return sentence


# Computes happy value of a tweet
def happy_value_computer(line, dictionary):
    # variables for happy value and counting total key words
    hapValue = 0
    keywordsCounter = 0
    # loops through index of the list of words of the tweet
    for i in range(0, len(line)):
        # checks for a keyword
        if line[i] in dictionary:
            # increments keyword counter and happy value
            keywordsCounter += 1
            hapValue += dictionary[line[i]]
    # returns the ratio of total happy values to num of keywords
    return ratio(hapValue, keywordsCounter)


# Computes tweets
def compute_tweets(t, k):
    # Checking for existence of passed files
    try:
        # Reading in files
        tFile = open(t, "r", encoding="utf-8")
        kFile = open(k, "r", encoding="utf-8")
    except FileNotFoundError:
        # Returns empty list if file names are invalid
        return []
    # Empty dictionary for keywords and their values
    keyGlossary = {}
    # Int counters for total tweets, total keyword tweets and total happy value for each region
    eastTweets = 0
    eastKeyTweets = 0
    eastHapValueTotal = 0
    centTweets = 0
    centKeyTweets = 0
    centHapValueTotal = 0
    mountTweets = 0
    mountKeyTweets = 0
    mountHapValueTotal = 0
    pacificTweets = 0
    pacificKeyTweets = 0
    pacificHapValueTotal = 0
    # Looping through keyword file and sorting words with their value
    for line in kFile:
        line = line.strip().lower()
        line = line.split(",")
        keyGlossary[line[0].lower()] = int(line[1])
    # Looping through to read line by line
    for line in tFile:
        # Strips \n from lines in file
        line = line.strip()

        # Index values for square brackets in order to locate coordinates
        i1 = line.index("[")
        i2 = line.index("]")

        # Drawing coordinates substring and storing. Adding 1 to index 1 to exclude square bracket at start of line
        latLong = line[i1 + 1: i2]
        tZone = latLong.split(", ")

        # Removing coordinate values and unneeded info from line while making all characters in the line lowercase
        line = remove_data(line.lower())
        # Putting strings that are separated by a single white space in a list
        line = line.split(" ")
        # Removing punctuation from each string of list
        line = remove_punctuation(line)

        # Determining location
        if geo_locator(tZone) == "east":
            happyValue = happy_value_computer(line, keyGlossary)

            # Checks if tweet contained keyword(s)
            if happyValue > 0:
                eastTweets += 1
                eastKeyTweets += 1
                eastHapValueTotal += happyValue

            # If tweet has no keyword(s) it's still counted towards total tweets of region
            else:
                eastTweets += 1
        if geo_locator(tZone) == "central":
            happyValue = happy_value_computer(line, keyGlossary)

            # Checks if tweet contained keyword(s)
            if happyValue > 0:
                centTweets += 1
                centKeyTweets += 1
                centHapValueTotal += happyValue

            # If tweet has no keyword(s) it's still counted towards total tweets of region
            else:
                centTweets += 1
        if geo_locator(tZone) == "mountain":
            happyValue = happy_value_computer(line, keyGlossary)

            # Checks if tweet contained keyword(s)
            if happyValue > 0:
                mountTweets += 1
                mountKeyTweets += 1
                mountHapValueTotal += happyValue

            # If tweet has no keyword(s) it's still counted towards total tweets of region
            else:
                mountTweets += 1
        if geo_locator(tZone) == "pacific":
            happyValue = happy_value_computer(line, keyGlossary)

            # Checks if tweet contained keyword(s)
            if happyValue > 0:
                pacificTweets += 1
                pacificKeyTweets += 1
                pacificHapValueTotal += happyValue

            # If tweet has no keyword(s) it's still counted towards total tweets of region
            else:
                pacificTweets += 1

    # Calculating average happy value for each region
    eastAvg = ratio(eastHapValueTotal, eastKeyTweets)
    centralAvg = ratio(centHapValueTotal, centKeyTweets)
    mountainAvg = ratio(mountHapValueTotal, mountKeyTweets)
    pacificAvg = ratio(pacificHapValueTotal, pacificKeyTweets)

    # Empty list to be returned
    dis = []

    # Assigning values to tuples
    east = (eastAvg, eastKeyTweets, eastTweets)
    central = (centralAvg, centKeyTweets, centTweets)
    mountain = (mountainAvg, mountKeyTweets, mountTweets)
    pacific = (pacificAvg, pacificKeyTweets, pacificTweets)

    # Appending tuples in order
    dis.append(east)
    dis.append(central)
    dis.append(mountain)
    dis.append(pacific)

    # Closing files
    tFile.close()
    kFile.close()

    # Returning list of tuples
    return dis
