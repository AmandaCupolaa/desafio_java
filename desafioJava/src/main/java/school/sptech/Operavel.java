package school.sptech;

public interface Operavel {
     boolean verificarComponente();
     void adicionarComponente(double metrica);
     void removerComponente();
     void iniciarMonitoramento(double registro, String dataHora);
}
