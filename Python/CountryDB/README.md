## Overview
This repository contains a Python program for updating a country catalog using a master file and an update file. The program is designed to handle various update scenarios and provides functionality to manage a catalog of country information.

## Program Details
This program was developed as part of a programming assignment for CS1026, involving the following tasks:

Implementing a Country class to represent individual countries with name, population, area, and continent attributes.
Implementing a CountryCatalogue class to manage a collection of countries, including loading data from a file and performing various operations.
Creating a Python module, processUpdates.py, to process updates from an update file, validate the updates, and generate an output file with the updated catalog.

## Usage
To use this program, follow these steps:

Ensure you have Python installed on your system.
Clone this repository to your local machine.
Place your country data file (e.g., data.txt) and an update file (e.g., updates.txt) in the repository directory.
Open a terminal or command prompt and navigate to the repository directory.
Run the processUpdates.py script, providing the country data file, update file, and a file for invalid updates as command-line arguments. For example:
shell
Copy code
python processUpdates.py data.txt updates.txt badupdates.txt
Replace data.txt, updates.txt, and badupdates.txt with your file names.
The program will process the updates, update the catalog, and generate an output file named output.txt.
File Descriptions
country.py: Contains the Country class definition.
catalogue.py: Contains the CountryCatalogue class definition.
processUpdates.py: Implements the update processing logic as described in the assignment.
main.py: Provides a simple interface to call the processUpdates function and test the program with provided data files.
Testing
You can test the program using the provided data files (data.txt, data2.txt, data3.txt) and update files (upd.txt, upd2.txt, upd3.txt). Ensure that your data files follow the specified format.

Acknowledgments
This program was developed as part of the CS1026 assignment at UWO in the 2021-2022 academic school year.
