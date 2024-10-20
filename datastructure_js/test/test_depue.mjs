import { Deque } from "./Deque.mjs";

let depue = new Deque();

console.log(`isEmpty: ${depue.isEmpty}`);
depue.addFirst(1);
depue.addFirst(2);
depue.addFirst(3);
depue.addFirst(4);
depue.addFirst(5);
console.log(`isEmpty: ${depue.isEmpty}`);
console.log("\n");

console.log("==== removeFirst ====");
depue.removeFirst();
depue.printAll();
depue.removeFirst();
depue.printAll();
depue.removeFirst();
depue.printAll();
depue.removeFirst();
depue.printAll();
depue.removeFirst();
depue.printAll();
console.log(`isEmpty: ${depue.isEmpty}`);

console.log("==== addList  ====");
depue.addLast(1);
depue.addLast(2);
depue.addLast(3);
depue.addLast(4);
depue.addLast(5);
depue.printAll();
console.log(`isEmpty: ${depue.isEmpty}`);
console.log("\n");
console.log("==== removeLast ====");
depue.removeLast();
depue.printAll();
depue.removeLast();
depue.printAll();
depue.removeLast();
depue.printAll();
depue.removeLast();
depue.printAll();
depue.removeLast();
depue.printAll();
console.log(`isEmpty: ${depue.isEmpty}`);
