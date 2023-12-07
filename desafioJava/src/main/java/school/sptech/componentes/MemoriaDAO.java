package school.sptech.componentes;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Operavel;
import school.sptech.bancoDeDados.Conexao;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MemoriaDAO implements Operavel {
    Conexao conexao;
    JdbcTemplate con;

    public MemoriaDAO() {
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
    }

    @Override
    public boolean verificarComponente() {
        boolean existeMemoria = false;

        List<Integer> idMemorias = con.queryForList("SELECT idComponente FROM Componente WHERE nomeComponente = 'Memória RAM'", Integer.class);

        if (!idMemorias.isEmpty()) {
            existeMemoria = true;
        }

        return existeMemoria;
    }

    @Override
    public void adicionarComponente(double metrica) {

        con.update("INSERT INTO Componente (nomeComponente, unidadeMedida, metricaComponente) VALUES (?,?, ?)", "Memória RAM", "%", metrica);

        System.out.println("Memória adicionada com sucesso!!!");

    }

    @Override
    public void removerComponente() {
        int count = con.queryForObject("SELECT COUNT(*) FROM Componente WHERE nomeComponente = 'Memória RAM'", Integer.class);

        if (count == 0) {
            System.out.println("Nenhum componente Memória encontrado.");
        } else {
            con.update("DELETE FROM Componente WHERE nomeComponente = 'Memória RAM'");
            System.out.println("Memória removida com sucesso.");

        }
    }

    @Override
    public void iniciarMonitoramento(double registro, String dataHora) {

        Integer id = con.queryForObject("SELECT idComponente FROM Componente WHERE nomeComponente = 'Memória'", Integer.class);
        con.update("INSERT INTO Registro (registro, dataHora,fkComponente) VALUES (?,?,?)", registro, dataHora, id);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}