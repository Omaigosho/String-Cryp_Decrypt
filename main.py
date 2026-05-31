from itertools import zip_longest


def dictionaries():
    numToChar = {
        0: "A",
        1: "B",
        2: "C",
        3: "D",
        4: "E",
        5: "F",
        6: "G",
        7: "H",
        8: "I",
        9: "J",
        10: "K",
        11: "L",
        12: "M",
        13: "N",
        14: "O",
        15: "P",
        16: "Q",
        17: "R",
        18: "S",
        19: "T",
        20: "U",
        21: "V",
        22: "W",
        23: "X",
        24: "Y",
        25: "Z",
        26: ".",
        27: ",",
        28: " ",
    }

    charToNums = {}
    for num, char in numToChar.items():
        charToNums[char] = num

    return numToChar, charToNums


def segment(text, n=2):
    text = text.upper()
    nums, chars = dictionaries()
    newText = ""
    for char in text:
        if char in chars:
            newText = newText + char
    textNums = []

    for x in range(len(newText)):
        idxChar = chars[newText[x]]
        textNums.append(idxChar)

    segmentedList = []
    for bt in zip_longest(*[iter(textNums)] * n, fillvalue=28):
        segmentedList.append(list(bt))
    return segmentedList


def restructureText(list):
    nums, chars = dictionaries()
    charsNew = []
    for ls in list:
        for num in ls:
            charsNew.append(nums[num])
    return "".join(charsNew)


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


def main():
    A = [[2, 5], [1, 3]]
    A_inv = matInverse(A)

    # La matriz inversa esperada en Z_29:
    # adj = [[3, -5], [-1, 2]]
    # -5 % 29 = 24, -1 % 29 = 28
    # Como det=1 y detInv=1, no hay multiplicación extra
    assert A_inv == [[3, 24], [28, 2]], f"Fallo: {A_inv}"
    pass


main()
