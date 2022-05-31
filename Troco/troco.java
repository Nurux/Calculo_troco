import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;

//Como queria que o user informa-se os valores de cedulas/moedas optei por criar um Tipo ArrayList primeiro para depois coloca-lo
//em um array padrão de double

class troco {
    public static void main(String[] args) {
        //Seto um locale para poder usar . em números decimais
        Locale.setDefault(Locale.US);

        //Crio o meu ArrrayList pois quero um array sem tamanho definido agora no inico do codigo 
        ArrayList<Double> nota = new ArrayList<>();

        double valor = 0;
        boolean resp = true;
        char resp_user = ' ';
        int i = 0;

        //Instancio o meu Scanner para fazer a leitura 
        Scanner ler = new Scanner(System.in);

        //Usuário informa as notas do caixa
        while(resp != false){
            System.out.println("Digite a nota disponivel no caixa: ");
            nota.add(ler.nextDouble());

            System.out.println("Deseja inserir uma nova nota (Y/N)?");
            resp_user = ler.next().charAt(0);

            resp_user = Character.toLowerCase(resp_user);

            if (resp_user == 'y'){
                i++;
            }

            if (resp_user == 'n'){
                resp = false;
            }

        }

        //Usuario informa valor do troco
        System.out.println("Digite o valor do troco: ");
        valor = ler.nextFloat();

        //Crio um novo array double para pegar os valores do meu Arraylist
        double [] notas = new double[nota.size()];
        for(int t =0; t<notas.length; t++){
            notas[t] = nota.get(t);
        }

        //Chamo o metodo ordenar para ordenar minha lista de double
        double [] notas_ordenadas = ordenar(notas);
        
        //Chamo o metodo calcular para verficar quantas notas serão gastadas
        int quant_notas = calcular(valor, notas_ordenadas);

        //Informo o resultado ao usuário
        System.out.println("Quantidade de notas/moedas e: " + quant_notas);
        
    }

    //Retorna um array double ordenado
    public static double[] ordenar(double [] notas){
        double valor = 0;

        for(int i = 0; i < notas.length - 1; i++){
            for(int t = i + 1; t < notas.length; t++){
                if(notas[i] < notas[t]){
                    valor = notas[i];

                    notas[i] = notas[t];
                    notas[t] = valor;
                }
            }
        }
        return notas;
    }

    //Retorna a quantidade de notas a serem usadas no troco
    public static int calcular(Double valor, double [] notas_ordenadas) {
        int i = 0;
        int quant = 0;
        int qtd = 0;

        while(valor > 0.01){
            if(valor >= notas_ordenadas[i]){
                quant = (int) (valor / notas_ordenadas[i]);
                qtd += quant;

                if(notas_ordenadas[i] >= 1.0){
                    System.out.println(quant + " Notas de " + notas_ordenadas[i]);
                }else {
                    System.out.println(quant + " Moedas de " + notas_ordenadas[i]);
                }
                
                valor = valor - (quant * notas_ordenadas[i]);
                System.out.println("- Restante: " + valor);
            }
            i++;
            quant = 0;
        }

        return qtd;
    }

}