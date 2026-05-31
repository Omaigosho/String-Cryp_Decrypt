from itertools import zip_longest
def dictionaries():
    numToChar = {0:"A",1:"B",2:"C",3:"D",4:"E",5:"F",6:"G",7:"H",8:"I",9:"J",10:"K",11:"L",12:"M",13:"N",14:"O",15:"P",
    16:"Q",17:"R",18:"S",19:"T",20:"U",21:"V",22:"W",23:"X",24:"Y",25:"Z", 26: ".", 27: ",", 28: " "}
    
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
    for bt in zip_longest(*[iter(textNums)]*n, fillvalue=28):
        segmentedList.append(list(bt))
    return segmentedList

def restructureText(list):
    nums, chars = dictionaries()
    charsNew = []
    for l in list:
        for num in l:
            charsNew.append(nums[num])
    return "".join(charsNew)

def main():
    print(restructureText(segment("HOLAH")))
    pass

main()