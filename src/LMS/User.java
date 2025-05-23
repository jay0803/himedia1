package LMS;

public class User {
        private String id;
        private String password;
        private String name;

        public User(String id, String password, String name) {
            this.id = id;
            this.password = password;
            this.name = name;
        }

        // Getter
        public String getId() { return id; }
        public String getPassword() { return password; }
        public String getName() { return name; }

        @Override
        public String toString() {
            return "ID: " + id + ", 이름: " + name;
        }
    }
