import numpy as np
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D

mean = [1, 1, 1]
cov = [[1, 0, 0], [0, 1, 0], [0, 0, 1]]
num_of_points = 10000

file = open('data.txt', mode='w', encoding='ascii')
file.writelines(str(num_of_points) + ' 3\n')

fig = plt.figure()
ax = Axes3D(fig)

for i in range(int(num_of_points / 2)):
    sample = np.random.multivariate_normal(mean=mean, cov=cov)
    # plt.scatter(sample[0], sample[1], c='r')
    file.writelines(str(sample[0]) + ' ' + str(sample[1]) + ' ' + str(sample[2]) + ' 1\n')
    ax.scatter(sample[0], sample[1], sample[2], c='r')
mean = [-1, -1, -1]

for i in range(num_of_points - int(num_of_points / 2)):
    sample = np.random.multivariate_normal(mean=mean, cov=cov)
    # plt.scatter(sample[0], sample[1], c='b')
    file.writelines(str(sample[0]) + ' ' + str(sample[1]) + ' ' + str(sample[2]) + ' 0\n')
    ax.scatter(sample[0], sample[1], sample[2], c='b')

file.close()
plt.show()
