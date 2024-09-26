public class Livro {
    private String titulo;
    private int ISBN;
    private State estado;
    private String autor;
    private int ano;

    public Livro(String titulo, int ISBN, String autor, int ano, State estado) {
        this.titulo = titulo;
        this.ISBN = ISBN;
        this.autor = autor;
        this.ano = ano;
        this.estado = estado;
    }

    public void regista() {
        if (this.estado.regista(this)) {
            this.estado = new Disponivel();
        } else {
            System.out.println("Operação não disponível");
        }
    }

    public void requesita() {
        if (this.estado.requesita(this)) {
            this.estado = new Emprestado();
        } else {
            System.out.println("Operação não disponível");
        }
    }

    public void devolve() {
        if (this.estado.devolve(this)) {
            this.estado = new Disponivel();
        } else {
            System.out.println("Operação não disponível");
        }
    }

    public void reserva() {
        if (this.estado.reserva(this)) {
            this.estado = new Reservado();
        } else {
            System.out.println("Operação não disponível");
        }
    }

    public void cancelaReserva() {
        if (this.estado.cancelaReserva(this)) {
            this.estado = new Disponivel();
        } else {
            System.out.println("Operação não disponível");
        }
    }

    public String toString() {
        return this.titulo + "  " + this.autor + "  " + this.estado;
    }
}
