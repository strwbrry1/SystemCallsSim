import java.util.ArrayList;
import java.util.List;

public class Kernel {
    private final static List<SysCall> sysCallTable = new ArrayList<>();

    public static void printSystemCalls() {
        for (SysCall sysCall : sysCallTable) {
            System.out.println(sysCall);
        }
    }

    public static void registerProcess() {
        sysCallTable.add(new SysCall(0, "write", new String[] {"hello, world!", "13"}));
        sysCallTable.add(new SysCall(1, "read", new String[] {"some string"}));
        sysCallTable.add(new SysCall(2, "execute", new String[] {"1"}));
        sysCallTable.add(new SysCall(3, "create_file", new String[] {"filename"}));
        sysCallTable.add(new SysCall(4, "get_time", new String[] {}));
    }

    public static void executeCall(int id) throws Exception {
        SysCall sc = new SysCall(999, "err_proc", new String[] {});
        for (int i = 0; i < sysCallTable.size(); i++) {
            if (sysCallTable.get(i).getId() == id) {
                sc = sysCallTable.get(i);
                break;
            }
        }
        if (sc.getId() == 999) {
            System.err.println("Системный вызов по данному идентификатору не обнаружен");
            return;
        }

        System.out.printf("Выполняется системный вызов [%d]\n", id);
        switch (id) {
            case 0:
                if (sc.getArgs().length < 2) {
                    throw new Exception("Недостаточно аргументов!");
                }
                write(sc.getArgs()[0], sc.getArgs()[1]);
                break;
            case 1:
                if (sc.getArgs()[0] == null) {
                    throw new Exception("Неверные аргументы!");
                }
                read(sc.getArgs()[0]);
                break;
            case 2:
                if (sc.getArgs()[0] == null) {
                    throw new Exception("Недостаточно аргументов!");
                }
                execute(sc.getArgs()[0]);
                break;
            case 3:
                if (sc.getArgs()[0] == null) {
                    throw new Exception("Недостаточно аргументов!");
                }
                create_file(sc.getArgs()[0]);
                break;
            case 4:
                if (sc.getArgs().length > 0) {
                    throw new Exception("Лишние аргументы в вызове!");
                }
                get_time();
                break;
            default:
                System.err.println("Ошибка системного вызова!");
                break;
        }
    }

    private static void write(String str, String len) {

        for (int i = 0; i < Integer.parseInt(len); i++) {
            System.out.print(str.charAt(i));
        }
    }

    private static String read(String str) {
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            res += str.charAt(i);
        }
        return res;
    }

    private static void execute(String pID) {
        System.out.printf("Process [%d] executed\n", Integer.parseInt(pID));
    }

    private static void create_file(String filename) {
        System.out.printf("File [%s] created\n", filename);
    }

    private static void get_time() {
        System.out.println(System.currentTimeMillis());
    }
}
