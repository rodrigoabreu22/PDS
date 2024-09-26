package ex01.src;

import java.util.ArrayList;
import java.util.List;

public class Registos {
    // Data elements
    private ArrayList<Empregado> empregados; // Stores the employees

    public Registos() {
        empregados = new ArrayList<>();
    }
    public void insere(Empregado emp) {
    // Code to insert employee
        if (empregados.contains(emp)) {
            System.out.println("Empregado já existe");
        } else {
            empregados.add(emp);
        }
    }

    public void remove(int codigo) {
    // Code to remove employee
        for (int i=0; i<empregados.size(); i++){
            Empregado empregado = empregados.get(i);
            if (empregado.codigo()==codigo){
                empregados.remove(i);
                return;
            }
        }
        System.out.printf("O empregado com o código %d não existe.\n", codigo);
    }

    public boolean isEmpregado(int codigo) {
    // Code to find employee
        for (int i=0; i<empregados.size(); i++){
            Empregado empregado = empregados.get(i);
            if (empregado.codigo()==codigo){
                return true;
            }
        }
        return false;
    }

    public List<Empregado> listaDeEmpregados() {
    // Code to retrieve collection
        return empregados;
    }
}

