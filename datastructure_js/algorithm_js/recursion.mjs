function myFunction(nember) {
  if (nember > 10) return;
  console.log(nember);
  myFunction(nember + 1);
}

myFunction(1);
