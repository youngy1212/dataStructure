
class ArrayList {

    private Object[] data; //배열에 들어갈 데이터
    private int size;
    private int index; //다음데이터 위치
    
    public ArrayList(){
        this.size = 1;
        this.data = new Object[this.size];
        this.index = 0;
    }
    //배열방 데이터 추가
    public void add(Object obj){

        //다찼는지 체크 
        if(this.index == this.size -1){
            doubling(); //더블링 실행
        }
        data[this.index] = obj; //배열방 맨 끝 추가
        this.index++;
    }
    private void doubling() {
        this.size = this.size*2;
        Object[] newData = new Object[this.size];
        for(int i =0; i<data.length; i++){
            newData[i] = data[i];
        }
        this.data = newData;
    }
    //인덱스 번호를 가지고 데이터를 가져오는 get함수
    public Object get(int i) throws Exception{
        //인덱스 이상인 경우 exception
        if(i > this.index -1){
            throw new Exception("ArrayIndexOutOfBound");
        }else if(i <0){
            throw new Exception("Negative Value");
        }
        return this.data[i];
    }
    //삭제하는 함수
    public void remove(int i) throws Exception{
        if(i > this.index -1){
            throw new Exception("ArrayIndexOutOfBound");
        }else if(i <0){
            throw new Exception("Negative Value");
        }
        //한칸씩 앞으로 당겨서 빈자리 매꾸기
        for(int x = i; x<this.data.length-1; x++){
            data[x] = data[x+1];
        }
        this.index--;
    }

    
}


public class ArrayListJava {
    public static void main(String[] args) throws Exception {
        ArrayList al = new ArrayList();
        al.add("0");
        al.add("1");
        al.add("2");
        al.add("3");
        al.add("4");
        al.add("5");
        al.add("6");
        al.add("7");
        al.add("8");
        System.out.println(al.get(5));
        al.remove(5);
        System.out.println(al.get(5));
    }
}
