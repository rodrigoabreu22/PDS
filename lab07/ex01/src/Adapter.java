package ex01.src;

public interface Adapter {
    public boolean addEmployee (Empregado empregado);
    public void removeEmployee(int codigo);
    public boolean isEmployee (int codigo);
    public void listAllEmployees();
}