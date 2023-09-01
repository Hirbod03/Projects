
# Importing country class
from country import *


# class header
class CountryCatalogue:

    # Constructor
    def __init__(self, countryFile):
        # Reading in file
        file = open(countryFile, "r")

        # Reading header line
        self._head = file.readline().strip()

        # Initializing list of Countries
        self._countryCat = []

        # Looping through file line by line
        for line in file:
            # Stripping line of \n
            line = line.strip()
            # Removing spaces
            line = removeSpaces(line)
            # Splitting line into a list
            line = line.split("|")
            # Creating country object from values
            cObj = Country(line[0], line[2], line[3], line[1])
            # Adding country to catalogue
            self._countryCat.append(cObj)

        self._countryCat = sorted(self._countryCat)
        # Closing file
        file.close()

    # function header
    def findCountry(self, country):
        # looping through items inside catalogue
        for c in self._countryCat:
            # comparing names to find a match
            if removeSpaces(c.getName()) == removeSpaces(country.getName()):
                return c
        return None

    # function header
    def addCountry(self, countryName, pop, area, cont):
        # Creating new country object for comparison
        newCountry = Country(countryName, pop, area, cont)
        # looping through items inside catalogue
        for country in self._countryCat:
            # comparing names to find a match
            if country.getName() == newCountry.getName():
                return False
        # Adding country to catalogue
        self._countryCat.append(newCountry)
        return True

    def printCountryCatalogue(self):
        # Printing countries one by one
        for country in self._countryCat:
            print(country)

    def saveCountryCatalogue(self, fileName):
        # Sorting list alphabetically
        sortedList = sorted(self._countryCat)
        # Writing out to file
        file = open(fileName, "w")
        # Accumulator
        a = 0
        # Writing original header to line
        file.write(self._head + '\n')
        # Looping through items inside catalogue list
        for country in sortedList:
            # Writing attributes to file
            file.write(str(country.getName()) + "|" + str(country.getContinent()) + "|" + str(
                country.getPopulation()) + "|" + str(country.getArea()) + "\n")
            # Accumulation
            a += 1
        # Checking if total countries in output are equal to total countries inside orignal catalogue
        if a == len(self._countryCat):
            return a
        return -1

    def setPopulationOfCountry(self, c, p):
        # Checking for valid argument
        if p != "" or p != 0:
            # Looping through items inside catalogue
            for country in self._countryCat:
                # Checking for a match
                if country.getName() == c.getName():
                    country.setPopulation(p)

    def setAreaOfCountry(self, c, a):
        # Checking for inlaid input
        if a != "" or a != 0:
            # Looping through countries inside catalogue
            for country in self._countryCat:
                # Checking for a match
                if country.getName() == c.getName():
                    country.setArea(a)

    def setContinentOfCountry(self, c, con):
        # Checking for invalid input
        if con != "":
            # Looping through countries inside catalogue
            for country in self._countryCat:
                # Checking for a match
                if country.getName() == c.getName():
                    country.setContinent(con)
