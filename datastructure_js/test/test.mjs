import { Node, LinkedList } from "./LinkedList.mjs";

let node1 = new Node(1);
let node2 = new Node(2);
let node3 = new Node(3);

node1.next = node2;
node2.next = node3;

console.log(node1.data);
console.log(node1.next.data);
console.log(node1.next.next.data);

let list = new LinkedList();
console.log("==== insertAt() ====");

list.insertAt(0, 0);
list.insertAt(1, 1);
list.insertAt(2, 2);
list.insertAt(3, 3);
list.insertAt(4, 4);
list.printAll();

console.log("==== insertAt() 종료 ====");
list.clear();
list.printAll();

console.log("==== insertLast() ====");
list.insertLast(0);
list.insertLast(1);
list.insertLast(3);
list.printAll();

console.log("==== deleteAt() ====");
list.deleteAt(0);
list.printAll();
list.deleteAt(1);
list.printAll();

console.log("==== deletLast() ====");
list.insertLast(5);
list.deleteLast();
list.deleteLast();
list.printAll();

console.log("==== getNodeAt() ====");
list.insertLast(1);
list.insertLast(2);
list.insertLast(3);
list.insertLast(4);
list.insertLast(5);

console.log(list.getNodeAt(2));
