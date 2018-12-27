import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D


file = open('10K3D_existHyperPlane.txt', mode='r', encoding='ascii')
num_of_points = 10000

fig = plt.figure()
ax = Axes3D(fig)
ax.set_xlabel("x-label", color='r')
ax.set_ylabel("y-label", color='g')
ax.set_zlabel("z-label", color='b')

a = 5.070341874079443
b = 0.7659070753278203
c = 6.4009453663384885
d = -5.0

X = np.arange(-10,10,1)
Y = np.arange(-10,10,1)
X,Y = np.meshgrid(X,Y)
Z = (-a/c)*X+(-b/c)*Y+(-d/c)

hyperplane = ax.plot_surface(X,Y,Z,color = 'lightgray')



file.readline()
for i in range(int(num_of_points / 2)):
    # read lines, these are positive points
    fp = file.readline()
    sample = fp.split( )
    sample = list(map(float, sample))
    ax.scatter(sample[0], sample[1], sample[2], c='r')
mean = [-1, -1, -1]

for i in range(num_of_points - int(num_of_points / 2)):
    # read lines, these are negative points
    fp=file.readline()
    sample = fp.split( )
    sample = list(map(float, sample))
    ax.scatter(sample[0], sample[1], sample[2], c='b')


file.close()
plt.show()

#need to draw the hyperplane with the points
# the hyperplane is [5.070341874079443, 0.7659070753278203, 6.4009453663384885, -5.0].[x,y,z,1]T = 0

# Hyperplane found: [5.070341874079443, 0.7659070753278203, 6.4009453663384885, -5.0] with margin larger than 0.2685622300876068 in 4d space.
# After verification, this hyper plane is true for all test data!
#  And the real margin is 0.3141815283326213
