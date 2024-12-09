public class Horario {
    private int[][] horario;
    static final int NUM_TURMAS = 3;
    static final int NUM_HORARIOS = 5;
    private static final int NUM_DIAS = 2; // Sábado e Domingo

    public Horario() {
        this.horario = new int[NUM_TURMAS][NUM_HORARIOS * NUM_DIAS]; // 2 dias * 5 horários
    }

    public Horario(int[][] horario) {
        this.horario = horario;
    }

    public int[][] getHorario() {
        return horario;
    }

    public void setHorario(int[][] horario) {
        this.horario = horario;
    }

    public void imprimirHorario() {
            System.out.println("Horários para Sábado e Domingo:");
            
            // Para Sábado
            System.out.println("Sábado:");
            for (int i = 0; i < 5; i++) {  // para os 5 horários
                System.out.print("Horário " + (i + 1) + ": ");
                for (int t = 0; t < 3; t++) {  // para as 3 turmas
                    System.out.print("Turma " + (t + 1) + ": " + horario[t][i * 2] + "  ");
                }
                System.out.println();
            }
        
            // Para Domingo
            System.out.println("\nDomingo:");
            for (int i = 0; i < 5; i++) {
                System.out.print("Horário " + (i + 1) + ": ");
                for (int t = 0; t < 3; t++) {
                    System.out.print("Turma " + (t + 1) + ": " + horario[t][i * 2 + 1] + "  ");
                }
                System.out.println();
            }
        }
    
}
