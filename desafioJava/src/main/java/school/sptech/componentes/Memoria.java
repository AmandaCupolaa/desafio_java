package school.sptech.componentes;

import org.springframework.jdbc.core.JdbcTemplate;
import school.sptech.Looca;
import school.sptech.bancoDeDados.Conexao;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Memoria extends Componente {
    Conexao conexao;
    JdbcTemplate con;
    private final Looca looca = new Looca ();
    private final com.github.britooo.looca.api.group.memoria.Memoria memoria;

    public Memoria (String nome, Double limiteMaximo, String medida) {
        super (nome, limiteMaximo, medida);
        this.conexao = new Conexao();
        this.con = conexao.getConexaoDoBanco();
        this.memoria = looca.getMemoria ();
    }

    @Override
    public void iniciarMonitoramento() {
        double memoriaUsage = memoria.getEmUso();

        String ramFormatted = new DecimalFormat("#.##").format((memoriaUsage * 4) / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        System.out.println("Memória: " + ramFormatted + " %");

        Integer id = con.queryForObject("SELECT idComponente FROM Componente WHERE nomeComponente = 'Memoria'", Integer.class);
        con.update("INSERT INTO Registro (registro, dateDado,fkComponente) VALUES (?,?,?)",ramFormatted, dataAtual, id);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
