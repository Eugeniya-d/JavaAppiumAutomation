public class MainClass {
    private int classNumber = 20;
    private String getClassString = "Hello, world";

    public int getLocalNumber(int number) {
        return number;
    }

    public int getClassNumber() {
        return this.classNumber;
    }

    public String getGetClassString() {
        return this.getClassString;
    }
}
