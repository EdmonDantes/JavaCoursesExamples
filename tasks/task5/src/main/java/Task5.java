public class Task5 {
    public static void main(String[] args) {
    }

    // 1.2
    public static String doubleChar(String s) {
        String result = "";
        for (int i = 0; i < s.length(); i++) {
            result += s.charAt(i);
            result += s.charAt(i);
        }
        return result;
    }

    // 1.1
    public String sayHello(String[] name, String city, String state) {
        String result = "Hello,";
        for (int i = 0; i < name.length; i++) {
            result += " " + name[i];
        }
        result += "! Welcome to " + city + ", " + state + "!";
        return result;
    }
}
