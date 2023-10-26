import java.util.LinkedList;

enum AnimalType{
    DOG, CAT
}

abstract class Animal{
    AnimalType type;
    String name;
    int order;
    Animal(AnimalType type, String name){
        this.type = type;
        this.name = name;
    }

    void setOrder(int order){
        this.order = order;
    }

    int getOrder(){
        return order;
    }

    String info(){
        return order+") type:"+type+",name "+name;
    }
}

class Dog extends Animal{ //개의 타입넘김
    Dog(String name){
        super(AnimalType.DOG, name);
    }
}

class Cat extends Animal{ 
    Cat(String name){
        super(AnimalType.CAT, name);
    }
}

//동물분양
class AnimalShelter{
    LinkedList<Dog> dogs = new LinkedList<Dog>();
    LinkedList<Cat> cats = new LinkedList<Cat>();
    int order; //동물 순서
    AnimalShelter(){
        order = 1;//1부터 시작
    }

    //동물이 들어오면
    void enqueue(Animal animal){
        animal.setOrder(order); //번호 부여
        order++;
        if(animal.type == AnimalType.DOG){ 
            dogs.addLast((Dog) animal); //개큐 마지막에 추가
        }else if(animal.type == AnimalType.CAT){
            cats.addLast((Cat) animal);
        }
    }

    //개를 분양받고자 함
    Animal dequeueDog(){
        return dogs.poll(); //첫번째 데이터 넘기기
    }

    //고양이를 분양
    Animal dequeueCat(){
        return cats.poll();
    }

    //고르지 않음
    Animal dequeue(){

        if(dogs.size()==0 && cats.size() ==0){
            return null;
        }else if(dogs.size() == 0){
            return cats.poll();
        }else if(cats.size() == 0){
            return dogs.poll();
        }

        //둘다 있는 경우
        Animal dog = dogs.peek();
        Animal cat = cats.peek();
        
        //순서비교
        if(cat.order < dog.order){
            return cats.poll();
        }else{
            return dogs.poll();
        }

    }

}


public class LinkedListQueue {
    public static void main(String[] args) {
        
        Dog d1 = new Dog("puppy");
        Dog d2 = new Dog("cdsed2");
        Dog d3 = new Dog("dsfdd3");
        Cat c1 = new Cat("sdfsdc1");
        Cat c2 = new Cat("csdfsc2");
        Cat c3 = new Cat("cdssc3");

        AnimalShelter as = new AnimalShelter();
        as.enqueue(d1);
        as.enqueue(c1);
        as.enqueue(d2);
        as.enqueue(c2);
        as.enqueue(d3);
        as.enqueue(c3);

        System.out.println(as.dequeueCat().info());
        System.out.println(as.dequeueDog().info());
        System.out.println(as.dequeue().info());
        System.out.println(as.dequeue().info());
        System.out.println(as.dequeue().info());
    }
}
