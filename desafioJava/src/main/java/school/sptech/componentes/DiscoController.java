package school.sptech.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.discos.Volume;
import school.sptech.TimerTask.Mensagem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;

public class DiscoController extends Componente{
    private final Looca looca = new Looca ();
    private final DiscoGrupo grupoDiscos;
    private DiscoDAO discoDAO;

    public DiscoController (String nome) {
        super(nome);
        this.grupoDiscos = looca.getGrupoDeDiscos();
        this.discoDAO = new DiscoDAO();
    }

    @Override
    public boolean isCadastrado() {
        return discoDAO.verificarComponente();
    }

    @Override
    public void definirMetrica() {
        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                +-----------------------------------------+
                |      Defina a métrica para o Disco      |
                +-----------------------------------------+
                 """);

        super.setMetrica(leitor.nextInt());

        discoDAO.adicionarComponente(super.getMetrica());

    }

    public void removerDisco() {
        discoDAO.removerComponente();
    }

    @Override
    public void dadosComponente() {

        Timer agendador = new Timer();
        Scanner in = new Scanner(System.in);

        List<Volume> volumes = grupoDiscos.getVolumes();

        double discoUsage = volumes.get(0).getDisponivel();

        double discoFormmatted = (double) Math.round(((discoUsage * 7.5) / 1024.0 / 1024.0 / 1024.0) / 10);

        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        discoDAO.iniciarMonitoramento(discoFormmatted, dataAtual);

        System.out.println("Para sair pressione qualquer tecla");
        String mensagemEibir = "Disco: " + discoFormmatted;

        Mensagem tarefa1 = new Mensagem(mensagemEibir, 1000,5000);
        agendador.schedule(tarefa1, tarefa1.getDelay(), tarefa1.getPeriodo());

        in.nextLine();
        agendador.cancel();

    }
}
