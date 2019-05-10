package pl.awkwieicn.testing.introduction;

public class Test {

    public static void main(String[] args) {
        testCalcAdd();
    }

    private static void testCalcAdd() {
        Calc calc = new Calc();
        int result = calc.sum(2, 2);

        if (result!=4)
            throw new IllegalStateException("Wrong result!");
        else
            System.out.println("OK");
    }
}
