function leftShifts(arr,x)
{
    for(let i = 0; i < x ; i++)
    {
        leftShift(arr);
    }
}
function leftShift(arr)
{
    let temp = arr[0];
    for(let i = 0; i < arr.length - 1 ; i++)
    {
        arr[i] = arr[i+1];
    }
    arr[arr.length-1] = temp;
}

let masterArray = [7, 8, 9, 4, 5, 6, 17, 18, 19];

console.log(masterArray);

leftShifts(masterArray, 4);

console.log(masterArray);
