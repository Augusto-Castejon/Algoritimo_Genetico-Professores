public class main {
    public static void main(String[] args) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico();
        ag.executarAlgoritmo();

        Individuo melhorIndividuo = ag.encontrarMelhorIndividuo();
        melhorIndividuo.getHorario().imprimirHorario();
    }
}
