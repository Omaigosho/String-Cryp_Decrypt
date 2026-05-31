def multMatrix(matrix, vector):
    resVector = []
    cofactor = 0
    for ls in matrix:
        cofactor = 0
        for x in range(len(ls)):
            cofactor = cofactor + (ls[x] * vector[x])
        resVector.append(cofactor % 29)

    return resVector


def det(matrix):
    determinant = (matrix[0][0] * matrix[1][1]) - ((matrix[0][1] * matrix[1][0]))
    return determinant % 29


def inverse(determinant):
    inv = 0
    for i in range(1, 29):
        if (determinant * i) % 29 == 1:
            inv = i
    return inv


def matInverse(matrix):
    a, b = matrix[0][0], matrix[0][1]
    c, d = matrix[1][0], matrix[1][1]

    adj = [[d, -b], [-c, a]]
    detInv = inverse(det(matrix))
    for x in range(len(adj)):
        for y in range(len(adj[x])):
            adj[x][y] = (adj[x][y] * detInv) % 29
    return adj
