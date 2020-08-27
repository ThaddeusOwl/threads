
import os

threads = 32
data = 1000
x = ("java runForkAlgo "+ str(data) + "x" + str(data) + " " + str(threads))
print("For " + str(threads) + " threads")
for i in range (0, 20):
	os.system(x)
