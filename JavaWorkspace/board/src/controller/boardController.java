package controller;

import ioutil.Ioutil;

public class boardController {
    public void run(){
        hello();

        while (true) {
            showMenu();
            
            String key = Ioutil.input("커맨드 입력 >>> ");
            if(key.equals("0")){
                break;
            }
            command(key);
        }
        bye();
    }

    private void hello(){
        Ioutil.print("===================");
        Ioutil.print("===================");
        Ioutil.print("===헛소리 게시판===");
        Ioutil.print("===================");
        Ioutil.print("===================");
    }
    private void showMenu(){
        Ioutil.print("1.게시판 접속");
        Ioutil.print("2.로그인");
        Ioutil.print("3.회원가입");
        Ioutil.print("0.게시판 종료");
        
    }

    private void bye(){
        Ioutil.print("프로그램을 종료 합니다.");
    }

    private void command(String key){
        switch (key) {
            case "1":
                Ioutil.pause();
                break;
            case "2":
                Ioutil.pause();
                break;
            default:
                Ioutil.print("잘못된 입력입니다.");
                Ioutil.pause();
                break;
        }
    }

}
