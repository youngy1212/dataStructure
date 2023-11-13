import java.util.*;


class Fruit  implements Comparable<Fruit> {
    private String name;
    private int score;

    public Fruit(String name, int score) {
        this.name = name;
        this.score = score;
    }
    
    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    //정렬 기준은 '점수가 낮은 순서'
    @Override 
    public int compareTo(Fruit o) {
       if(this.score < o.score){
        return -1;
       }
       return 1;
    }
    
}


public class Sorting2 {


    public static void main(String[] args) {
        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        Arrays.sort(arr); //일반 정렬

        //key값을 이용한 정렬
        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Fruit("바나나", 2));
        fruits.add(new Fruit("사과", 5));
        fruits.add(new Fruit("당근", 3));

        Collections.sort(fruits);

        for (int i = 0; i < fruits.size(); i++) {
            System.out.print("(" + fruits.get(i).getName() + "," + fruits.get(i).getScore() + ") ");
        }

    }
}
