package LMS;

import java.util.Scanner;

public class ManagerMenu {
    public static void showManagerMenu(Scanner scanner){
        LoginSVC loginSVC = new LoginSVC();

        while (true) {
            System.out.println("\n[관리자 메뉴]");
            System.out.println("1. 관리자 로그인");
            System.out.println("2. 관리자 가입");
            System.out.println("3. 홈으로");
            System.out.print("선택: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("관리자 로그인 메뉴");
                    System.out.print("아이디 입력 >> ");
                    String loginId = scanner.nextLine();
                    System.out.print("비밀번호 입력 >> ");
                    String loginPw = scanner.nextLine();

                    User user = loginSVC.login(loginId, loginPw);
                    if (user == null) {
                        System.out.println("로그인 실패! 아이디 또는 비밀번호를 확인하세요.");
                    } else {
                        System.out.println("로그인 성공!");
                        System.out.println(user);
                    }
                    break;

                case "2":
                    System.out.println("관리자 가입 메뉴");
                    System.out.print("아이디 입력 >> ");
                    String id = scanner.nextLine();
                    System.out.print("비밀번호 입력 >> ");
                    String pw = scanner.nextLine();
                    System.out.print("이름 입력 >> ");
                    String name = scanner.nextLine();

                    int result = loginSVC.insertMember(id, pw, name);
                    if (result > 0) {
                        System.out.println("회원가입 성공!");
                    } else {
                        System.out.println("회원가입 실패. 다시 시도해주세요.");
                    }
                    break;

                case "3":
                    return;

                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.\n");
            }
        }
    }
}
