public class Disponivel implements State {
    @Override
    public boolean reserva(Livro l) {
        return true;
    }

    @Override
    public boolean devolve(Livro l) {
        return false;
    }

    @Override
    public boolean regista(Livro l) {
        return false;
    }

    @Override
    public boolean cancelaReserva(Livro l) {
        return false;
    }

    @Override
    public boolean requesita(Livro l) {
        return true;
    }

    public String toString() {
        return "[Disponivel]";
    }
}
