package school.sptech.componentes;

import school.sptech.Monitoravel;

public abstract class Componente implements Monitoravel {

    private String nome;

    private Double limiteMaximo;

    private String Medida;

    public Componente(String nome, Double limiteMaximo, String medida) {
        this.nome = nome;
        this.limiteMaximo = limiteMaximo;
        Medida = medida;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getLimiteMaximo() {
        return limiteMaximo;
    }

    public void setLimiteMaximo(Double limiteMaximo) {
        this.limiteMaximo = limiteMaximo;
    }

    public String getMedida() {
        return Medida;
    }

    public void setMedida(String medida) {
        Medida = medida;
    }

    @Override
    public String toString() {
        return "Componente{" +
                "nome='" + nome + '\'' +
                ", limiteMaximo=" + limiteMaximo +
                ", Medida='" + Medida + '\'' +
                '}';
    }
}
