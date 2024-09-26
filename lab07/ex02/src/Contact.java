package ex02.src;

public class Contact {
    private String nome;
    private Integer numero;

    private Contact(String nome, Integer numero) {
        this.nome = nome;
        this.numero = numero;
    }

    public String getName() {
        return nome;
    }

    public void setName(String nome) {
        this.nome = nome;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public static Contact createContact(String nome, Integer numero) {
        return new Contact(nome, numero);
    }

    @Override
    public String toString() {
        return nome + " " + numero;
    }
}
