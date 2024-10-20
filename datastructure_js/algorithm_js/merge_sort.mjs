function MergeSort(arr, leftIndex, rightIndex) {
  if (leftIndex < rightIndex) {
    let midIndex = parseInt((leftIndex + rightIndex) / 2);
    MergeSort(arr, leftIndex, midIndex);
    MergeSort(arr, midIndex + 1, rightIndex);
    Merge(arr, leftIndex, midIndex, rightIndex);
  }
}

function Merge(arr, leftIndex, midIndex, rightIndex) {
  let leftAreaIndex = leftIndex;
  let rightAreaIndex = midIndex + 1;

  let temArr = [];
  temArr.length = rightIndex + 1;
  temArr.fill(0, 0, rightIndex + 1);

  let temArrIndex = leftIndex;
  while (leftAreaIndex <= midIndex && rightAreaIndex <= rightIndex) {
    if (arr[leftAreaIndex] <= arr[rightAreaIndex]) {
      temArr[temArrIndex] = arr[leftAreaIndex++];
    } else {
      temArr[temArrIndex] = arr[rightAreaIndex++];
    }
    temArrIndex++;
  }

  if (leftAreaIndex > midIndex) {
    for (let i = rightAreaIndex; i <= rightIndex; i++) {
      temArr[temArrIndex++] = arr[i];
    }
  } else {
    for (let i = leftAreaIndex; i <= midIndex; i++) {
      temArr[temArrIndex++] = arr[i];
    }
  }

  for (let i = leftIndex; i <= rightIndex; i++) {
    arr[i] = temArr[i];
  }
}

let arr = [3, 4, 5, 1, 7, 9, 2];
console.log(arr);

MergeSort(arr, 0, arr.length - 1);
console.log("==== 정렬 후 ====");
console.log(arr);
