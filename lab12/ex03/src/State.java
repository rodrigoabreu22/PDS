public interface State {
    public boolean reserva(Livro l);
    public boolean devolve(Livro l);
    public boolean regista(Livro l);
    public boolean cancelaReserva(Livro l);
    public boolean requesita(Livro l);
}