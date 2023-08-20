# GymCrowdPredict

## Introduction
GymCrowdPredict is a Java program that helps users find the best time to attend a gym based on historical attendance data. It reads data from a CSV file containing gym attendance records and provides insights about the optimal time to visit the gym.
GymCrowdPredict analyzes historical gym attendance data to provide users with information on the best times to visit the gym. The program processes the data and offers three main functionalities:

#### Finding the absolute best time to attend the gym.
#### Finding the best time to attend the gym with a given minimum attendance.
#### Finding the best time to attend the gym on a specific day of the week.
The program reads data from a CSV file named sampleData.csv, performs analysis, and presents the results to the user.

## Getting Started

To use the GymCrowdPredictor program, follow these steps:

Clone or download the repository to your local machine.
Ensure you have Java installed on your system.
Locate the sampleData.csv file and populate it with gym attendance records according to the supplied format.
## Usage

Open a terminal window and navigate to the directory containing the program files.
Compile the program using the command:
sh
Copy code
javac Predictor.java
Run the program using the command:
sh
Copy code
java Predictor
Follow the on-screen menu prompts to choose one of the available functionalities.
Input required data as prompted by the program.
Review the output to find the best time to attend the gym based on your selected criteria.
To exit the program, enter 0 when prompted.
## Functionality

### 1. Finding the Absolute Best Time to Attend the Gym
This option finds the absolute best time to attend the gym based on historical attendance data. It considers the time with the lowest attendance count and provides the relevant information.

### 2. Finding the Best Time to Attend the Gym with a Minimum Attendance
This option allows you to input a minimum attendance count. The program then identifies the best time to attend the gym while meeting the minimum attendance requirement. If no suitable time is found, the program informs you that no such time exists.

### 3. Finding the Best Time to Attend the Gym on a Certain Day
This option lets you input a specific day of the week. The program then identifies the best time to attend the gym on that day, taking into account the minimum attendance requirement.

## Contributing

Contributions to the GymCrowdPredictor project are welcome. If you find any issues or have suggestions for improvements, please feel free to open an issue or create a pull request in this repository.
