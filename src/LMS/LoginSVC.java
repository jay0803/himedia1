package LMS;

import java.sql.*;

public class LoginSVC {
    Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/lms";
            con = DriverManager.getConnection(url, "root", "1111");
            System.out.println("Connection Success!");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    //로그인
    public User login(String id, String passwd) {
        User user = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connect();
            String sql = "SELECT * FROM manager WHERE mid = ? AND mpw = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, passwd);
            rs = ps.executeQuery();

            if (rs.next()) {
                String name = rs.getString("mnm");
                user = new User(id, passwd, name);
            }
        } catch (SQLException se) {
            System.out.println("로그인 중 오류 발생");
            se.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return user;
    }

    // 회원가입 기능
    public int insertMember(String id, String passwd, String name) {
        PreparedStatement ps = null;
        int cnt = 0;

        try {
            connect();
            String sql = "INSERT INTO manager (mid, mpw, mnm, state) VALUES (?, ?, ?, 0)";
            ps = con.prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, passwd);
            ps.setString(3, name);

            cnt = ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("이미 존재하는 아이디입니다.");
        } catch (SQLException se) {
            System.out.println("회원가입 중 오류 발생");
            se.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return cnt;
    }
}
