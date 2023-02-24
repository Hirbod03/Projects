# library imports
import random # used to generate random numbers
import string # used to generate all possible characters

# input prompt and storage
passLength = int(input("Enter desired password length: "))

# storing all possible characters in a string
allChars = string.ascii_letters + string.digits + string.punctuation

# generating random string
password = ''.join(random.choice(allChars) for i in range(passLength))

# returning
print("Your password is:", password)