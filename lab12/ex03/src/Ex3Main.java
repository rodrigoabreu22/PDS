import java.util.List;
import java.util.Scanner;

public class Ex3Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Livro> livros = List.of(
            new Livro("Java Anti-Stress", 123, "Omodionah", 2020, new Inventario()),
            new Livro("A Guerra dos Padrões", 456, "Jorge Omel", 2020, new Inventario()),
            new Livro("A Procura da Luz", 789, "Khumatkli", 2020, new Inventario())
        );

        System.out.println("*** Biblioteca ***");
        for (int i=0; i<livros.size(); i++) {
            System.out.println(i+1 + "    " + livros.get(i));
        }
        System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela\n");

        while(sc.hasNextLine()) {
            String input = sc.nextLine();
            String[] idx = input.split(",");
            int livro;

            switch (Integer.parseInt(idx[1])) {
                case 1:
                    livro = Integer.parseInt(idx[0]) - 1;
                    livros.get(livro).regista();
                    break;
                case 2:
                    livro = Integer.parseInt(idx[0]) - 1;
                    livros.get(livro).requesita();
                    break;
                case 3:
                    livro = Integer.parseInt(idx[0]) - 1;
                    livros.get(livro).devolve();
                    break;
                case 4:
                    livro = Integer.parseInt(idx[0]) - 1;
                    livros.get(livro).reserva();
                    break;
                case 5:
                    livro = Integer.parseInt(idx[0]) - 1;
                    livros.get(livro).cancelaReserva();
                    break;
                default:
                    System.out.println("A operação não existe");
                    break;
            }
            System.out.println("*** Biblioteca ***");
            for (int i=0; i<livros.size(); i++) {
                System.out.println(i+1 + "    " + livros.get(i));
            }
            System.out.println(">> <livro>, <operação: (1)regista; (2)requisita; (3)devolve; (4)reserva; (5)cancela\n");
        }
        sc.close();
    }
}
