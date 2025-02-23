public class Main {
    public static void main(String[] args) {
        try {
            Kernel.registerProcess();
            Kernel.printSystemCalls();
            Kernel.executeCall(0);
            Kernel.executeCall(1);
            Kernel.executeCall(2);
            Kernel.executeCall(3);
            Kernel.executeCall(4);
            Kernel.executeCall(5);
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}