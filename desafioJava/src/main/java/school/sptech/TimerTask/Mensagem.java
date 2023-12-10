package school.sptech.TimerTask;

import java.util.TimerTask;

public class Mensagem extends TimerTask {

    private String mensagem;
    private  int delay;
    private int periodo;

    public Mensagem(String mensagem, int delay, int periodo) {
        this.mensagem = mensagem;
        this.delay = delay;
        this.periodo = periodo;
    }

    @Override
    public void run() {
        System.out.println(mensagem);
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getDelay() {
        return delay;
    }

    public int getPeriodo() {
        return periodo;
    }
}
