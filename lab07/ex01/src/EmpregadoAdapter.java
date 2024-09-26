package ex01.src;

import ex01.src.Employee;

/**
 * This class serves as an adapter for the Empregado class.
 * It extends the Employee class and provides a way to create Employee objects from Empregado objects.
 */
public class EmpregadoAdapter extends Employee {

    /**
     * Constructs a new EmpregadoAdapter with the given Empregado.
     * The name, code, and salary of the Empregado are used to create the Employee.
     * @param empregado the Empregado to be adapted
     */
    public EmpregadoAdapter(Empregado empregado) {
        super(empregado.nome() + " " + empregado.apelido(), empregado.codigo(), empregado.salario());
    }

}