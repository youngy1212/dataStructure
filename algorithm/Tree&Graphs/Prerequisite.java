import java.util.HashMap;
import java.util.LinkedList;

public class Prerequisite {

    class Project{
        private String name; 
        private LinkedList<Project> dependencies; //선행될 
        private boolean marked;//결과를 담았는지 알려줄 플래그
        public Project(String name){
            this.name = name;
            this.marked = false;
            this.dependencies = new LinkedList<Project>();
        }
        
        //의존관계 추가해 줄 함수
        public void addDenpendecy(Project project){
            if(!dependencies.contains(project)){
                dependencies.add(project);
            } //프로젝트를 받아서 LinkedList 추가
        }

        //멤버변수를 private 선언해서 관계를 가져올 get 함수 선언
        public LinkedList<Project> getDependencies(){
            return this.dependencies;
        }
        public String getName(){
            return this.name;
        }
        public boolean getMarked(){
            return this.marked;
        }
        public void setMarked(boolean marked){
            this.marked = marked;
        }

    }

    class ProjectManager{ //프로젝트를 관리할 클래스
        private HashMap<String, Project> projects;
        public ProjectManager(String[] names, String[][]dependencies){
            //project배열과 의존도 2차원배열을 받음
            buildProject(names); //프로젝트를 만들고
            addDenpendencies(dependencies); //의존도를 추가
        }
        public void buildProject(String[] names) {
            projects = new HashMap<String, Project>(); //객체생성
            for(int i =0; i<names.length; i++){ //이름과 프로젝트 객체를 map에 저장
                projects.put(names[i], new Project(names[i]));
            }
        }
        
        //의존도를 추가하는 함수
        public void addDenpendencies(String[][] dependencies) {
            for(String[] dependency : dependencies){
                Project before = findProject(dependency[0]);//먼저
                Project after = findProject(dependency[1]); //나중
                after.addDenpendecy(before); //먼저처릴할껄 나중안에 의존으로 저장
            }
        }

        private int index;
        //의존성에 입각해 프로젝트 순서 정하기
        public Project[] buildOrder(){
            initMakingFlages(); //마킹플레그 초기화
            Project[] ordered = new Project[this.projects.size()];//결과값을 저장할 배열
            index = 0; //저장할 배열방 시작 0
            for(Project project : this.projects.values()){
                buildOrder(project,ordered); //재귀호출
            }
            return ordered;
        }

        public void buildOrder(Project project, Project[] ordered) {
            //먼저 처리되어야 할 함수가 있는지 확인!
            if(!project.getDependencies().isEmpty()){
                for(Project p : project.getDependencies()){
                    buildOrder(p, ordered); //있다면 재호출
                }
            }
            //모든 의존이 다 처리되었다면
            if(project.getMarked()  == false){ //결과배열에 추가되었나?
                project.setMarked(true);//처리안되엇다면
                ordered[index] = project;
                index++;
            }

        }

        private void initMakingFlages() { //순서를 정하기전 flasges 세팅
            for(Project project : this.projects.values()){
                project.setMarked(false);
            }
        }

        //이름으로 프로젝트 node를 찾는함수
        public Project findProject(String name) { 
            return projects.get(name);
        }
      

    }



    public static void main(String[] args) {
        Prerequisite prerequisite = new Prerequisite();
        String[] projects = {"a", "b","c", "d","e", "f","g"};
        String[][] dependencies = {{"f","a"}, {"f","b"},{"f","c"},
    {"b","a"},{"c","a"},{"a","e"},{"b","e"},{"d","g"}};

        ProjectManager pm = prerequisite.new ProjectManager(projects, dependencies);
        Project[] ps = pm.buildOrder();
        for(Project p : ps){
            if(p!=null){
                System.out.print(p.getName()+" ");
            }
        }
    }
    
}