# Seyed Hirbod Hosseini

# Importing Catalogue class
from catalogue import *
# Importing path function from os package
from os import path


# Function that checks for valid name
def checkName(name):
    # Empty string case
    if name == "":
        return False
    # Stripping spaces
    name = removeSpaces(name)
    # Checking for first letter to be uppercase
    if not name[0].isupper():
        return False
    # Going through string letter by letter
    for i in range(1, len(name)):
        if not name[i].isalpha() and name[i] != "_":
            return False
    return True


def getInput(output):
    while True:
        # Input prompt
        c = input("Do you wanna quit? 'Y' (yes) or 'N'(no)?: ").lower()
        # Checking for input
        if c != 'n':
            return None
        # Input prompt
        newFileName = input("Pass new file name: ")
        # Checking for valid input
        if path.exists(newFileName):
            return newFileName


# Function that returns int value for comparison
def returnNum(num):
    # Empty string case
    if num == "":
        return 0
    # Converting argument to a string and removing spaces
    num = removeSpaces(num)
    # Empty string to be returned
    res = ""
    # Looping through argument digit by digit
    for i in num:
        if i.isnumeric():
            res += i
    # Returning original numeric argument without commas
    return int(res)


# Function that checks for commas in nums
def checkNumForm(num):
    # Removing spaces and converting to string
    num = removeSpaces(num)
    # Digit placement accumulator
    a = 0
    # Looping through argument from right to left
    for i in range(len(num) - 1, -1, -1):
        # Accumulation
        a += 1
        # Checking for every fourth character from the right to be a comma
        if a % 4 == 0 and num[i] != ',':
            return False
        # Checking if characters are non-numeric
        if not num[i].isnumeric() and a % 4 != 0:
            return False
    return True


# Function header
def processUpdates(cntryFileName, updateFileName, badUpdateFile):
    # Writing out to output.txt
    output = open("output.txt", "w")
    # Checking for valid input
    if not path.exists(cntryFileName):
        cntryFileName = getInput(output)
        # Checking for user input to quit
        if cntryFileName is None:
            output.write("Update Unsuccessful\n")
            output.close()
            return False, None
    if not path.exists(updateFileName):
        updateFileName = getInput(output)
        if updateFileName is None:
            output.write("Update Unsuccessful\n")
            output.close()
            return False, None
    # Sorting input data
    catalog = CountryCatalogue(cntryFileName)
    # Reading in update file
    updates = open(updateFileName, "r")
    # Writing out for file for invalid updates
    badUpdates = open(badUpdateFile, "w")
    # Loops through update file line by line
    for line in updates:
        # Strips \n
        line = line.strip()
        # Removing spaces and storing inside a new variable
        newLine = removeSpaces(line)
        # Checking for empty line
        if newLine == "" or newLine == " ":
            continue
        # Checks if line only contains name of country
        if ';' not in newLine and checkName(newLine):
            catalog.addCountry(newLine, 0, 0, "")
            continue
        # Splits update data into a list
        updata = newLine.split(';')
        # Checks for valid name
        if not checkName(updata[0]):
            badUpdates.write(newLine + "\n")
            continue
        # New country object for comparison
        cntry = Country(updata[0], "", "", "")
        # Checks if country already exists in catalogue
        obj = catalog.findCountry(cntry)
        # Value and Counter variables
        pCount = 0
        pValue = ""
        aCount = 0
        aValue = ""
        cCount = 0
        cValue = ""
        # Invalid Update Identifier flag
        flag = False
        # Loops through list of update data
        for i in range(1, len(updata)):
            update = updata[i]
            # Checks for empty value
            if update != "":
                # Checks for update value identifiers
                if update[0:2] == 'P=':
                    pCount += 1
                    pValue = update[2:]
                elif update[0:2] == 'A=':
                    aCount += 1
                    aValue = update[2:]
                elif update[0:2] == 'C=':
                    cCount += 1
                    cValue = update[2:]
                # Checking for invalid value identifier and missing equal sign
                else:
                    flag = True
        # Set of valid continent values for comparison
        continents = {'Africa', 'Antarctica', "Arctic", 'Asia', 'Europe', 'North_America', 'South_America'}
        # Check for valid form of updates
        if not checkNumForm(pValue) or not checkNumForm(aValue) or (
                removeSpaces(cValue) not in continents and cValue != "") or (
                pCount > 1 or aCount > 1 or cCount > 1) or flag:
            badUpdates.write(line + "\n")
            continue
        # If country is not in the catalogue
        if obj is None:
            # Adds country to catalogue
            catalog.addCountry(updata[0].strip(" "), pValue, aValue, cValue)
        # If country is already in the catalogue
        else:
            # Checking if updates are meaningful
            if returnNum(pValue) != returnNum(obj.getPopulation()) and returnNum(pValue) > 0:
                catalog.setPopulationOfCountry(obj, pValue)
            if returnNum(aValue) != returnNum(obj.getArea()) and returnNum(aValue) > 0:
                catalog.setAreaOfCountry(obj, aValue)
            catalog.setContinentOfCountry(obj, cValue)
    # Saving updates to updated country file
    catalog.saveCountryCatalogue("output.txt")
    # Closing files
    updates.close()
    output.close()
    badUpdates.close()
    # Return statement
    return True, catalog
