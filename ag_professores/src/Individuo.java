import java.util.HashSet;
import java.util.Set;

public class Individuo {
    private Horario horario;
    private int aptidao;

    public Individuo() {
        this.horario = new Horario();
        this.aptidao = 0;
    }

    public Individuo(Horario horario) {
        this.horario = horario;
        this.aptidao = calcularAptidao();
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public int getAptidao() {
        return aptidao;
    }

    public void setAptidao(int aptidao) {
        this.aptidao = aptidao;
    }

    public int calcularAptidao() {
        int aptidao = 0;
        int[][] matriz = horario.getHorario();

        // Bônus para aulas consecutivas da mesma matéria
        for (int t = 0; t < Horario.NUM_TURMAS; t++) {
            for (int h = 0; h < Horario.NUM_HORARIOS - 1; h++) {
                if (matriz[t][h * 2] == matriz[t][(h + 1) * 2] && matriz[t][h * 2] != 0) {
                    aptidao += 20;
                }
            }
        }

        // Penalização para choques de horários
        for (int h = 0; h < Horario.NUM_HORARIOS; h++) {
            Set<Integer> materiasNoHorario = new HashSet<>();
            for (int t = 0; t < Horario.NUM_TURMAS; t++) {
                if (matriz[t][h * 2] != 0 && !materiasNoHorario.add(matriz[t][h * 2])) {
                    aptidao -= 30;
                }
                if (matriz[t][h * 2 + 1] != 0 && !materiasNoHorario.add(matriz[t][h * 2 + 1])) {
                    aptidao -= 30;
                }
            }
        }

        return aptidao;
    }
}
