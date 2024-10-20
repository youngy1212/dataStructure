function factorial(nember) {
  if (nember == 1 || nember == 0) {
    return 1;
  }
  return nember * factorial(nember - 1);
}

console.log(factorial(5));
