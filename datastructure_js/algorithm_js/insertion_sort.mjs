function InsertionSort(arr) {
  for (let i = 1; i < arr.length; i++) {
    let insertionSData = arr[i];
    let j;
    for (j = i - 1; j >= 0; j--) {
      if (arr[j] > insertionSData) {
        arr[j + 1] = arr[j];
      } else {
        break;
      }
    }
    arr[j + 1] = insertionSData;
  }
}

let arr = [4, 1, 5, 3, 6, 2];
console.log("==== 정렬 후 ====");
InsertionSort(arr);
console.log(arr);
