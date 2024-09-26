package ex01.src;

// Petiscos
class Empregado {
    private String nome;
    private String apelido;
    private int codigo;
    private double salario;

    public Empregado(String nome, String apelido, int codigo, double salario) {
        this.nome = nome;
        this.apelido = apelido;
        this.codigo = codigo;
        this.salario = salario;
    }

    public String apelido() {
        return apelido;
    }

    public String nome() {
        return nome;
    }

    public int codigo() {
        return codigo;
    }

    public double salario() {
        return salario;
    }

    public String toString() {
        return String.format("%-10s | %-10s | %-6d | %-10.2f", nome, apelido, codigo, salario);
    }
}
