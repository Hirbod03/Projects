#
# Driver for testing ... tests combinations of test files by calling call compute_tweets
#

from sentiment_analysis import compute_tweets

print("** Test 1")
results = compute_tweets("tweets1.txt", "key1.txt")
print(results)

print("** Test 2")
results = compute_tweets("tweets1.txt", "key2.txt")
print(results)

print("** Test 3")
results = compute_tweets("tweets2.txt", "key1.txt")
print(results)

print("** Test 4")
results = compute_tweets("tweets2.txt", "key2.txt")
print(results)
