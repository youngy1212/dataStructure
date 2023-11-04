import java.util.ArrayList;
import java.util.HashMap;

public class RoutesSumCount {

    class Tree{
        class Node{ 
            int data;
            Node left;
            Node right;
            Node(int data){
                this.data = data;
            }
        }
        Node root;
        Tree(int size){
            root = makeBST(0,size-1);
        }
        Node makeBST(int start, int end){
            if(start > end) return null;
            int mid = (start+end)/2;
            Node node = new Node(mid);
            node.left = makeBST(start,mid-1);
            node.right = makeBST(mid+1, end);
            return node;
        }

        //검색할 합산 값을 받으면 재귀함수 호출
        int countRathWithSum(int targetSum){
            return countRathWithSum(root, targetSum);
        }
        int countRathWithSum(Node root, int targetSum) {
            if(root == null) return 0;
            //현재노드를 기점으로 경우의 개수를 세는 함수 호출
            int pathFromRoot = countPathsWithSumFromNode(root, targetSum,0);
            //왼오 노드들도 호출
            int pathOnLeft = countRathWithSum(root.left, targetSum);
            int pathOnRight = countRathWithSum(root.right, targetSum);
            return pathFromRoot + pathOnLeft + pathOnRight;
        }
        //특정 노드를 시작점으로 경로의 개수를 찾는 함수 
        private int countPathsWithSumFromNode(Node node, int targetSum, int currSum) {
            //시작노드, 합산, 현재돌면서 합산 
            if(node == null) return 0; //트리 끝 도착
            currSum += node.data;
            int totalPaths = 0;
            if(currSum == targetSum) {
                totalPaths++; //경로갯수 증가
            }
            totalPaths += countPathsWithSumFromNode(node.left, targetSum, currSum);
            totalPaths += countPathsWithSumFromNode(node.right, targetSum, currSum);

            return totalPaths;
        }

        //방법 2 배열을 이용
        int countPathsWithSum2(int targetSum){
            ArrayList<Integer> array = new ArrayList<Integer>();
            return countPathsWithSum2(root,targetSum,array);
        }
        int countPathsWithSum2(Node root, int targetSum, ArrayList<Integer> array) {
            if(root == null) return 0; 
            int totalPaths = 0; //총경로의 갯수
            //기존 배열방에 현재 노드의 갯수를 더하고, 시작점 더하기
            addValue(array, root.data);
            totalPaths = countPaths(array, targetSum);
            totalPaths += countPathsWithSum2(root.left, targetSum, array);
            totalPaths += countPathsWithSum2(root.right, targetSum, array);
            romoveLast(array); //반환하기전 배열방 삭제 
            
            return totalPaths;
        }
      
        void addValue(ArrayList<Integer> array, int value) {
            for(int i =0; i<array.size(); i++){
                array.set(i, array.get(i)+value);
            } //배열에 값 더해주기
            array.add(value);//새로운 시작점 추가
        }
        
        //해당 값이 있는지 검색
        int countPaths(ArrayList<Integer> array, int targetSum) {
            int totalPaths = 0;
            for(int i =0; i<array.size(); i++){
                if(array.get(i) == targetSum) totalPaths++;
            }
            return totalPaths;
        }

        //마지막 노드를 지우고 지운 노드 값만큼 배열방의 값을 빼는 함수
        void romoveLast(ArrayList<Integer> array) {
            int value = array.remove(array.size()-1);
            for(int i =0; i<array.size(); i++){
                array.set(i, array.get(i) -value);
            }
        }

        //방법 3 
        int countPathsWithSum3(int targetSum){
            HashMap<Integer, Integer> hashTable = new HashMap<Integer, Integer>();
            hashTable.put(0, 1); //노드의 값을 키로 사용 
            return countPathsWithSum3(root, targetSum,0 ,hashTable);
        }

        //검사할 노드, 현재 합산값, 지금 합산값(임시저장), 받은경로 개수 리턴
        int countPathsWithSum3(Node node, int targetSum, int currSum,
                HashMap<Integer, Integer> hashTable) {
            if(node == null) return 0;

            currSum += node.data; //현제 노드의 데이터를 현재까지 값이 합산 
            int sum = currSum - targetSum; //현재 합산값에서 값만큼 뒤로갔을때 
            int totalPaths = hashTable.getOrDefault(sum, 0); //이에 해당하는 키가 있는지 검색
            incrementHashTable(hashTable,currSum,1);// 해쉬맵에 현재 합산값을 1로 추가하 
            totalPaths += countPathsWithSum3(node.left, targetSum, currSum, hashTable); //경로의 갯수를 받아옴
            totalPaths += countPathsWithSum3(node.right, targetSum, currSum, hashTable);
            incrementHashTable(hashTable, currSum, -1); //해시맵에서 해당 데이터는 삭제
            return totalPaths; //부모에 반환 
        }
        //hash테이블을 업데이트 해주는 함수 
        void incrementHashTable(HashMap<Integer, Integer> hashTable,int key, int val){
            int newCount = hashTable.getOrDefault(key, 0)+val; //해당 값이 있는지 체크
            if(newCount == 0){ 
                hashTable.remove(key);
            }else{
                hashTable.put(key, newCount); //있다면 
            }
            
            
        }
        
    }


    public static void main(String[] args) {

        RoutesSumCount routesSumCount = new RoutesSumCount();
        RoutesSumCount.Tree t = routesSumCount.new Tree(10);

        System.out.println(t.countPathsWithSum3(3));
    }


    


}
