public class SysCall {
    private final int id;
    private final String name;
    private final String[] args;

    public SysCall(int id, String name, String[] args) {
        this.id = id;
        this.name = name;
        this.args = args;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String[] getArgs() {
        return this.args;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[").append(id).append("] ");
        str.append(name).append(": ");
        for (int i = 0; i < args.length; i++) {
            str.append(args[i]);
            str.append(" ");
        }
        return str.toString();
    }
}
