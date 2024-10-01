package controller;

import service.Service;

import ioutil.Ioutil;

public class Controller {
   private Service service = new Service();
   
    public void run(){
        hello();
        while (true) {
           showMenu();
           String key = commandValue();
           if(commandEquals(key)){
                break;
           }
           switchCase(key);

        }
        bye();
    }
    
    private void hello(){
        Ioutil.print("================");
        Ioutil.print("학생관리 프로그램");
        Ioutil.print("================");

    }

    private void showMenu(){
        Ioutil.print("1.학생정보입력");
        Ioutil.print("2.학생정보입력");
        Ioutil.print("3.학생정보입력");
        Ioutil.print("4.학생정보입력");
        Ioutil.print("5.학생정보입력");
        Ioutil.print("0.학생정보입력");
    }

    private void bye(){
        Ioutil.print("프로그램을 종료합니다. 감사합니다.");
    }

    private void switchCase(String value){
        switch (value) {
            case "1":
                service.addStudent();
                break;
            case "2":
                service.showStudent();
                break;
            case "3":
                
                break;
            case "4":
                
                break;
            
            case "5":
                
                break;
            
            default:
                Ioutil.print("잘못된 입력 입니다.");
                Ioutil.pause();
                break;
        }

    }

    private String commandValue(){
        return Ioutil.input("커맨드 입력 >>> "); 
    }

    private boolean commandEquals(String value){
        return value.equals("0");
    }



}