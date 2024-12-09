import java.util.*;

public class AlgoritmoGenetico {
    private List<Individuo> populacao;
    private static final int POPULACAO_INICIAL = 200;
    private static final int MAX_GERACOES = 100000;
    private static final double TAXA_MUTACAO = 0.1;

    public AlgoritmoGenetico() {
        this.populacao = new ArrayList<>();
        inicializarPopulacao();
    }

    private void inicializarPopulacao() {
        Random rand = new Random();
        for (int i = 0; i < POPULACAO_INICIAL; i++) {
            Horario horario = new Horario();
            int[][] horarioMat = horario.getHorario();
            for (int t = 0; t < Horario.NUM_TURMAS; t++) {
                for (int h = 0; h < Horario.NUM_HORARIOS; h++) {
                    horarioMat[t][h * 2] = rand.nextInt(5); // Sábado
                    horarioMat[t][h * 2 + 1] = rand.nextInt(5); // Domingo
                }
            }
            Individuo individuo = new Individuo(horario);
            populacao.add(individuo);
        }
    }

    public void executarAlgoritmo() {
        for (int geracao = 0; geracao < MAX_GERACOES; geracao++) {
            Map<Individuo, Integer> aptidoes = avaliarPopulacao();

            List<Individuo> novaPopulacao = new ArrayList<>();
            for (int i = 0; i < POPULACAO_INICIAL / 2; i++) {
                Individuo pai1 = selecionarIndividuo(aptidoes);
                Individuo pai2 = selecionarIndividuo(aptidoes);

                // Cruzamento
                Individuo filho1 = cruzamento(pai1, pai2);
                Individuo filho2 = cruzamento(pai2, pai1);

                // Mutação
                mutacao(filho1);
                mutacao(filho2);

                novaPopulacao.add(filho1);
                novaPopulacao.add(filho2);
            }

            populacao = novaPopulacao;
        }
    }

    private Map<Individuo, Integer> avaliarPopulacao() {
        Map<Individuo, Integer> aptidoes = new HashMap<>();
        for (Individuo individuo : populacao) {
            aptidoes.put(individuo, individuo.calcularAptidao());
        }
        return aptidoes;
    }

    private Individuo selecionarIndividuo(Map<Individuo, Integer> aptidoes) {
        Random rand = new Random();
        List<Individuo> individuos = new ArrayList<>(aptidoes.keySet());
        return individuos.get(rand.nextInt(individuos.size()));
    }

    private Individuo cruzamento(Individuo pai1, Individuo pai2) {
        Random rand = new Random();
        Horario filhoHorario = new Horario();
        int[][] filhoHorarioMat = filhoHorario.getHorario();

        for (int t = 0; t < Horario.NUM_TURMAS; t++) {
            for (int h = 0; h < Horario.NUM_HORARIOS; h++) {
                filhoHorarioMat[t][h * 2] = rand.nextBoolean() ? pai1.getHorario().getHorario()[t][h * 2] : pai2.getHorario().getHorario()[t][h * 2];
                filhoHorarioMat[t][h * 2 + 1] = rand.nextBoolean() ? pai1.getHorario().getHorario()[t][h * 2 + 1] : pai2.getHorario().getHorario()[t][h * 2 + 1];
            }
        }

        return new Individuo(filhoHorario);
    }

    private void mutacao(Individuo individuo) {
        Random rand = new Random();
        if (rand.nextDouble() < TAXA_MUTACAO) {
            int t = rand.nextInt(Horario.NUM_TURMAS);
            int h = rand.nextInt(Horario.NUM_HORARIOS);
            individuo.getHorario().getHorario()[t][h * 2] = rand.nextInt(5); // Sábado
            individuo.getHorario().getHorario()[t][h * 2 + 1] = rand.nextInt(5); // Domingo
        }
    }

    public Individuo encontrarMelhorIndividuo() {
        return populacao.stream()
                .max(Comparator.comparing(Individuo::getAptidao))
                .orElse(null);
    }
}
