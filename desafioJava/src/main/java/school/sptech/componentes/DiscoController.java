package school.sptech.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class DiscoController extends Componente{
    private final Looca looca = new Looca ();
    private final DiscoGrupo grupoDiscos;
    private DiscoDAO discoDAO;

    public DiscoController (String nome) {
        super(nome);
        this.grupoDiscos = looca.getGrupoDeDiscos();
        this.discoDAO = new DiscoDAO();
    }

    public boolean verificarDisco() {
        return discoDAO.verificarComponente();
    }

    public void metricaDisco() {
        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                +-------------------------------------------------+
                |      Defina a métrica para esse componente      |
                +-------------------------------------------------+
                 """);

        super.setMetrica(leitor.nextInt());

        discoDAO.adicionarComponente(super.getMetrica());

    }

    public void removerDisco() {
        discoDAO.removerComponente();
    }

    public void monitorarDisco() {
        List<Volume> volumes = grupoDiscos.getVolumes();

        double discoUsage = volumes.get(0).getDisponivel();

        String discoFormmatted = new DecimalFormat("#.##").format((discoUsage * 7.5) / 1024.0 / 1024.0 / 1024.0).replace(",", ".");
        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        // mudar tipo pra double, não String - todo
//        discoDAO.iniciarMonitoramento(discoFormmatted, dataAtual);

    }
}
