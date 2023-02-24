# library imports
import string
import random
import hashlib

# dictionary to store urls and their shortened versions
urls = {}

# random url generator
def urlGenerator():
    # storing all possible letters
    letters = string.ascii_lowercase
    # infinite loop
    while 1:
        # random length 6 string generation
        newURL = ''.join((random.choice(letters)) for i in range(6))
        # checking for duplicate urls
        if newURL not in urls.values():
            return newURL

# url hash function
def hash(url):
    # takes the original url, encodes it in bytes and creates an MD5 hash of bytes
    return hashlib.md5(url.encode()).hexdigest()

# shortener function
def shortener(url):
    # obtaining dictionary key
    hashedURL = hash(url)
    # checking for duplicacy
    if hashedURL in urls:
        return urls[hashedURL]
    else:
        shortURL = urlGenerator()
        urls[hashedURL] = shortURL
        return shortURL

# main method
while True:
    # input prompt and storage
    print("Enter a URL to shorten (or 'q' to quit):")
    url = input()
    # input checking
    if url == 'q':
        break
    shortened_url = shortener(url=url)
    print(f"Shortened URL: https://short.com/{shortened_url}")
