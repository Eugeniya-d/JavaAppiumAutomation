import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass{

    @Test
    public void testGetLocalNumber() {
        int actualNumber = this.getLocalNumber(14);
        Assert.assertTrue("Заданное число " + actualNumber + " не равно 14", actualNumber == 14);
    }

    @Test
    public void testGetClassNumber() {
        int actualClassNumber = getClassNumber();
        Assert.assertTrue("Заданное число " + actualClassNumber + " меньше или равно 45", actualClassNumber > 45);
    }

    @Test
    public void testGetClassString() {
        String actualClassSting = getGetClassString();
        String firstValue = "Hello";
        String secondValue = "hello";
        Assert.assertTrue("Заданное число " + actualClassSting + " не содержит Hello или hello",
                actualClassSting.contains(firstValue) || actualClassSting.contains(secondValue));
    }
}
