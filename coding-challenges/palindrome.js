function palindrome(word, starter)
{
    let words = [word[starter]];
    for(let i = starter; i <= word.length ; i++)
    {
        let newWord = word.substring(starter,i);
        if(superCheck(newWord))
        {
            words.push(newWord);
        }
    }
    let maxWord = word[starter];
    for(let i = 0; i < words.length ; i++)
    {
        if(words[i].length > maxWord.length)
        {
            maxWord = words[i];
        }
    }
    return maxWord;
}
function startAndEnd(word)
{
    return word[0] == word[word.length-1];
}
function superCheck(word)
{
    let checker = new String(word);
    while(true)
    {
        if(!(startAndEnd(checker)))
        {
            return false;
        }
        checker = checker.substring(1,checker.length-1);
        if(checker == "" || checker.length == 1)
        {
            return true;
        }
    }
}
let masterWord = "address";
let allWords = [];
for(let i = 0; i < masterWord.length ; i++)
{
    allWords.push(palindrome(masterWord,i));
}
finalWord = masterWord[0];
for(let i = 0; i < allWords.length ; i++)
{
    if(allWords[i].length > finalWord.length)
    {
        finalWord = allWords[i];
    }
}
console.log(finalWord);