# Seyed Hirbod Hosseini

# Function to remove spaces
def removeSpaces(arg):
    arg = str(arg)
    res = ""
    for character in arg:
        if not character == " ":
            res += character
    return res


# Making class
class Country:
    # Constructor
    def __init__(self, name, pop="", area="", continent=""):
        self._name = name
        self._population = pop
        self._area = area
        self._continent = continent

    # Returns name of country
    def getName(self):
        return removeSpaces(self._name)

    # Returns population of country
    def getPopulation(self):
        return self._population

    # Returns the area of the country
    def getArea(self):
        return self._area

    # Returns the continent of the country
    def getContinent(self):
        return self._continent

    # Sets the population of the country to the argument passed
    def setPopulation(self, pop):
        self._population = pop

    # Sets the area of the country to the argument passed
    def setArea(self, area):
        self._area = area

    # Sets the continent of the country to the argument passed
    def setContinent(self, continent):
        self._continent = continent

    # Sorting function
    def __lt__(self, other):
        return self._name < other._name

    # Returns print statement
    def __repr__(self):
        return str(self._name) + " (pop: " + str(self._population) + ", size: " + str(self._area) + ") in " + self._continent
