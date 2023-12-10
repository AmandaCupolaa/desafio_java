package school.sptech.componentes;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.memoria.Memoria;
import school.sptech.Servidor;
import school.sptech.TimerTask.Mensagem;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Timer;

public class MemoriaController extends Componente{
    private final Looca looca = new Looca ();
    private final Memoria memoria;
    private MemoriaDAO memoriaDAO;

    public MemoriaController(String nome) {
        super(nome);
        this.memoria = looca.getMemoria();
        this.memoriaDAO = new MemoriaDAO();
    }

    @Override
    public boolean isCadastrado() {
        return memoriaDAO.verificarComponente();
    }

    @Override
    public void definirMetrica() {
        Scanner leitor = new Scanner(System.in);

        System.out.println("""
                +---------------------------------------+
                |      Defina a métrica para a RAM      |
                +---------------------------------------+
                 """);

        super.setMetrica(leitor.nextInt());

        memoriaDAO.adicionarComponente(super.getMetrica());

    }

    public void removerMemoria() {
        memoriaDAO.removerComponente();
    }

    @Override
    public void dadosComponente() {

        Timer agendador = new Timer();
        Scanner in = new Scanner(System.in);

        double memoriaUsage = memoria.getEmUso();
        double ramFormatted = (double) Math.round((memoriaUsage * 4) / 1024.0 / 1024.0 / 1024.0);

        String dataAtual = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        memoriaDAO.iniciarMonitoramento(ramFormatted, dataAtual);

        System.out.println("Para sair pressione qualquer tecla");
        String mensagemEibir = "RAM: " + ramFormatted;

        Mensagem tarefa1 = new Mensagem(mensagemEibir, 1000,5000);
        agendador.schedule(tarefa1, tarefa1.getDelay(), tarefa1.getPeriodo());

        in.nextLine();
        agendador.cancel();

    }
}
