import java.util.EmptyStackException;

class FullStackException extends Exception{

    public FullStackException(){
        super();
    }
    public FullStackException (String msg){
        super(msg);
    }
}

//방법1: 고정길이

class FixedMultiStacks{
    private int numOfStacks = 3; //총 스택
    private int stackSize; //스택별 크기
    private int[] values; //내용
    private int[] sizes; //각 스택에 사이즈 공간

    //생성자 
    public FixedMultiStacks(int stackSize){
        this.stackSize = stackSize;
        this.sizes = new int[numOfStacks]; //사이즈를 저장할 배열 생성
        this.values = new int[numOfStacks * stackSize]; //스택 * 사이즈로 실제 데이터 저장공간

    }

    public boolean isEmpty(int stackNum){ 
        return sizes[stackNum] == 0; //스택번호의 방이 비었니?
    }

    //다 찼니? 번호와, 처음 스택 사이즈를 비교
    public boolean isFull(int stackNum){
        return sizes[stackNum] == stackSize;
    }

    //스택 맨위 
    public int getTopIndex(int stackNum){
        int offset = stackSize * stackNum; //첫 방번호 
        int size = sizes[stackNum]; //데이터가 얼마나 찼는지

        //두개를 합치면 번호가 나옴
        return offset + size -1;
    }

    //몇번 스택에 push할지
    public void push(int stackNum, int data) throws FullStackException{ 
        if(isFull(stackNum)){
            throw new FullStackException();
        }

        //마지막에 추가
        values[getTopIndex(stackNum)+1] = data;
        sizes[stackNum]++; //사이즈도 늘려줘야함.
    }

    //가져와서 지우기
    public int pop(int stackNum){
        if(isEmpty(stackNum)){
             throw new EmptyStackException();
        }
        int top = getTopIndex(stackNum);
        int data = values[top]; //방가져옴
        values[top] =0; //비워주기
        sizes[stackNum]--;
        return data;

    }

    public int peek(int stackNum){
        if(isEmpty(stackNum)){
            throw new EmptyStackException();
        }
        return values[getTopIndex(stackNum)];
    }

}

//방법2 : 유동길이 

class MultiStacks { 

    public class StackInfo{ //데이터를 재사용이 용이하게
        public int start, dataSize, stackSize; //시작위치, 데이터크기, 스택총사이즈
        public StackInfo(int start, int stackSize){
            this.start = start;
            this.stackSize = stackSize;
            this.dataSize = 0;
        }

        //임의 배열방 번호가, 해당 스택영역 안에 있는 배열방 번호인지 확인
        public boolean isWithinStack(int index){ 
            
            if(index <0 || index >= values.length){ //0보다작거나, 배열 전체크기보다 크다면 비교할 필요없음. false
                return false;
            }

            //스택의 숫자가 시작부터 끝까지의 사이인지! 체크 
            int virtualIndex = index < start? index + values.length :
                index; //index가 앞으로 넘어가서 start보다 작아질 수있음. 그렇기 떄문에 start 보다 작으면 배열 크기를 더해서 비교
            int end = start + stackSize;
            return start <= virtualIndex &&  virtualIndex <end;    
        }

        //스택의 맨 마지막 방번호
        public int getLastStackIndex(){
            return adjustIndex(start+stackSize-1); //0부터 시작하니 -1
        }

         //스택의 데이터의 마지막 방 번호
        public int getLastDataIndex(){
            return adjustIndex(start+dataSize-1); 
        }
        
        //새로운 데이터를 추가하고자 할때 데이터 방번호를 가져오자
        public int getNewDataIndex(){
            return adjustIndex(getLastDataIndex()+1);
        }

        public boolean isFull(){
            return dataSize == stackSize;
        }

        public boolean isEmpty(){
            return dataSize == 0;
        }
    }

    //내부변수 선언
    private StackInfo[] info; //스택정보 저장
    private int[] values; //데이터가 들어갈배열
    
    public MultiStacks(int numOfStacks, int dafaultSize){
        info = new StackInfo[numOfStacks]; //저장할 배열방의 크기
        for(int i =0; i<numOfStacks; i++){ //스택 갯수만큼
            info[i] = new StackInfo(dafaultSize*i, dafaultSize);
            //각 스택방의 정보 초기화
        }

        values = new int[numOfStacks*dafaultSize];
    }

    private void expand(int stackNum){ //자리가 없을 때 shift
        int nextStack = (stackNum +1) % info.length;
        shift(nextStack);
        info[stackNum].stackSize++;
    }
    private void shift(int stackNum){
        StackInfo stack = info[stackNum];//스택정보 가져오기
        if(stack.dataSize >= stack.stackSize){ //다 찼는지 확인
            //다 찼다면 다시 재귀함수
            int nextStack = (stackNum +1)%info.length;
            shift(nextStack); 
            //다음 스택을 찾고 
            stack.stackSize++;
        }
        int index = stack.getLastStackIndex();//그스택의 마지막 가져오기
        //자기 앞의 데이터를 자기에게로 미룸. 
        while(stack.isWithinStack(index)){
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        } //맨앞 데이터도 초기화
        values[stack.start] = 0;
        stack.start = nextIndex(stack.start);
        stack.stackSize--;
    }

    //전체 배열에 얼마나 쌓였는지 알아보기
    public int numberOfElements(){
        int totalDataSize = 0;
        for(StackInfo sd : info){
            totalDataSize += sd.dataSize;
        }
        return totalDataSize;
    }
    //다 찼는지 확인
    public boolean allStacksAreFull(){
        return numberOfElements() == values.length;
    }
    //배열 크기를 벗어나는 가상인덱스를 다시계산해서 실제 index를 알려주는 함수
    public int adjustIndex(int index){
        int max = values.length;
        return ((index % max)+max) % max;
    }

    //인덱스 
    private int nextIndex(int index){
        return adjustIndex(index+1);
    }

    private int previousIndex(int index){
        return adjustIndex(index -1);
    }

    //스택 기능들 구현!
    public void push(int stackNum,int value) throws  FullStackException{
        
        if(allStacksAreFull()){
            throw new FullStackException();
        }

        //스택정보를 가져와서
        StackInfo stack = info[stackNum];
        //스택이 다 찼는지 체크
        if(stack.isFull()){
            expand(stackNum); //자리가없다. 자리를 확장하는 함수 호출
        }

        values[stack.getNewDataIndex()] = value;
        stack.dataSize++; //사이즈가 늘었다 알려주기

    }

    public int pop(int stackNum) throws Exception{
        StackInfo stack = info[stackNum];
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        //맨마지막 정보 가져오기
        int last = stack.getLastDataIndex();
        int value = values[last]; 

        //가져온 데이터 삭제
        stack.dataSize--;
        return value;

    }

    public int peek(int stackNum){
        StackInfo stack = info[stackNum];
        if(stack.isEmpty()){
            throw new EmptyStackException();
        }
        return values[stack.getLastDataIndex()];
    }

}


public class MultiStack {
    public static void main(String[] args) {

        //FixedMultiStacks ms = new FixedMultiStacks(5);

        MultiStacks ms = new MultiStacks(3,5);


        try {
            ms.push(0, 1);
            ms.push(0, 2);
            ms.push(0, 3);
            ms.push(0, 4);
            ms.push(0, 5);
            ms.push(0, 6);
            ms.push(0, 7);
            ms.push(0, 8);
            ms.push(0, 9);
            ms.push(1, 11);
            ms.push(1,12);
            ms.push(1, 13);
            ms.push(1, 14);
            ms.push(1, 15);

            
        } catch (FullStackException e) {
            System.out.println("It's full");
        }

        //스택에 데이터 가져오기
        try {
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.peek(0));
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.pop(0));
           
            System.out.println("Stack #0"+ms.pop(0));
            System.out.println("Stack #0"+ms.pop(0));

        } catch (Exception e) {
            System.out.println("It;s empty");
        }

    }
}