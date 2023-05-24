import java.util.Stack;

public class Pilha {
    public static void main(String[] args) {
        Stack pilha = new Stack();
        pilha.push("&"); //símbolo inicial (Z0)
        int posicao = 0;
        Estados estado = Estados.INICIAL;
        char simbolo;

        String entrada = "abbaXabba"; //L = {wXwr  | w pertence a {a|b}* e wr é w invertido}
        while (posicao <= entrada.length()) {
            if (posicao != entrada.length()) {
                simbolo = entrada.charAt(posicao);
            } else {
                simbolo = 1; //esse if else serve pra eliminar o fator do símbolo quando a pilha fica vazia, sem ele o
                //código não roda a última vez por que o charAt dá erro de Out of Bounds
            }
            if (pilha.isEmpty()) {
                System.out.println("Pilha vazia"); // A pilha está vazia, não há transição possível
                break;
            }
            String topo = pilha.peek().toString();
            char topoPilha = topo.charAt(0);
            System.out.println(pilha.toString());
            if (estado.equals(Estados.INICIAL)) { //empilhamento, estado q
                if (topoPilha == '&' && simbolo == 'a') {
                    pilha.push('X');
                    posicao++;
                } else if (topoPilha == '&' && simbolo == 'b') {
                    pilha.push('Y');
                    posicao++;
                } else if (topoPilha == 'X' && simbolo == 'b') {
                    pilha.push('Y');
                    posicao++;
                } else if (topoPilha == 'X' && simbolo == 'a') {
                    pilha.push('X');
                    posicao++;
                } else if (topoPilha == 'Y' && simbolo == 'b') {
                    pilha.push('Y');
                    posicao++;
                } else if (topoPilha == 'Y' && simbolo == 'a') {
                    pilha.push('X');
                    posicao++;
                } else if (simbolo == 'X') {
                    posicao++;
                    estado = Estados.DESEMPILHAR;
                }
            } else if (estado.equals(Estados.DESEMPILHAR)) { //desempilhamento, estado p
                if (topoPilha == 'X' && simbolo == 'a') {
                    pilha.pop();
                    posicao++;
                } else if (topoPilha == 'Y' && simbolo == 'b') {
                    pilha.pop();
                    posicao++;
                } else if (topoPilha == '&') {
                    estado = Estados.FINAL;
                    System.out.println("foi pro estado final");
                } else {
                    System.out.println("erro");
                    pilha.removeAllElements();
                }
            } else { //estado final, f
                System.out.println("Finalizou");
                posicao++; //quebra o loop
            }
        }
    }
}
