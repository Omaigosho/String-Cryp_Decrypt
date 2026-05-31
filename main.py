from utils import matrixLogic as ML
from utils import alphabet as ab


def cypher(text, base, n=2):
    segmentedText = ab.segment(text, n)
    cypherText = []
    for item in segmentedText:
        cypherVector = ML.multMatrix(base, item)
        cypherText.append(cypherVector)
    return ab.restructureText(cypherText)


def decypher(text, base, n=2):
    segmentedText = ab.segment(text, n)
    matInv = ML.matInverse(base)
    deCypherText = []
    for item in segmentedText:
        cypherVector = ML.multMatrix(matInv, item)
        deCypherText.append(cypherVector)
    return ab.restructureText(deCypherText)


def main():

    pass


main()
