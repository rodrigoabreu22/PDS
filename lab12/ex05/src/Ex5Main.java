public class Ex5Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Ex5Main <path> [recursive]");
            return;
        }

        String path = args[0];
        boolean recursive = args.length > 1 && args[1].equals("recursive");

        DirectoryVisitor visitor = new DirectoryVisitor(path, recursive);

        visitor.calculate();

        System.out.println("Done!");
    }
}
