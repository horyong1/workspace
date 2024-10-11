package controller;


import ioutil.Ioutil;
import service.Service;

public class Controller {
    private Service service = new Service();
    public void run(){
        hello();
        while (true) {
            showMenu();
            String key = Ioutil.input("커멘드 입력 >>>>> ");

            if (key.equals("0")) {
                break;
            }

            selectMenu(key);
        }
        bye();

    }

    private void hello(){
        System.out.println("학생관리 프로그램 ");
        System.out.println("");
    }

    private void showMenu(){
        System.out.println("1.학생 정보 입력");
        System.out.println("2.학생 정보 출력");
        System.out.println("3.학생 정보 검색");
        System.out.println("4.학생 정보 삭제");
        System.out.println("5.학생 평균 점수");
        System.out.println("0.프로그램 종료");
    }

    private void selectMenu(String key){
        switch (key) {
            case "1":
                service.addStudent();
                Ioutil.pause();
                break;
            case "2":
                service.findAllStudent();
                Ioutil.pause();
                break;
            case "3":
                service.findByNameStudent();
                Ioutil.pause();
                break;
            case "4":
                service.removeStudent();
                Ioutil.pause();
                break;
            case "5":
                service.avgScore();
                Ioutil.pause();
                break;
        
            default:
                System.out.println("잘못된 입력입니다.....");
                Ioutil.pause();
                break;
        }
    }

    private void bye(){
        System.out.println("프로그램을 종료합니다.");
    }
}
