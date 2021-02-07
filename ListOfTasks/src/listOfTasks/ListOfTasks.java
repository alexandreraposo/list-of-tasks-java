package listOfTasks;

import java.util.Scanner;

public class ListOfTasks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner teclado = new Scanner(System.in);

		//----  MANDATORY CODE  ----\\
		int tamMax=100;

		String [] tarefa = new String[tamMax];
		boolean [] temPrazo = new boolean[tamMax];
		boolean [] foiFeita = new boolean[tamMax];
		int [][] data = new int[tamMax][3];

		int nTarefas=0;
		//----  MANDATORY CODE  ----\\
		

		int dataD = 0;
		int dataM = 0;
		int dataA = 0;
		int ultimaFeita = 0;

		boolean eBissexto = false; // Irá servir para validar a data

		int [] idTarefas = new int [nTarefas]; // criação de um array que contém o número de posições que o nTarefas tem

		String [] dataSplit = new String [2];

		char opcaoMenu = 0;

		do {
			System.out.println("******************");
			System.out.println("*  (V)isualizar  *");
			System.out.println("*  (M)arcar      *");
			System.out.println("*  (E)ditar      *");
			System.out.println("*                *");
			System.out.println("*  (S)air        *");
			System.out.println("******************");

			System.out.print("Intoduza uma opção: ");
			opcaoMenu = teclado.next().charAt(0);
			teclado.nextLine();
			switch(opcaoMenu) {
			case 'v':
			case 'V':
				char opcaoVisualizar = 0;
				String dataIntroduzida = "";

				do {
					System.out.println("********************************");
					System.out.println("*  Visualizar (t)odas          *");
					System.out.println("*  Visualizar (d)ia d          *");
					System.out.println("*  Visualizar (a)té dia d      *");
					System.out.println("*  Visualizar (p)or fazer      *");
					System.out.println("*  Visualizar (f)eitas         *");
					System.out.println("*  Visualizar por pa(l)avra p  *");
					System.out.println("*                              *");
					System.out.println("*  (V)oltar                    *");
					System.out.println("********************************");

					System.out.print("Intoduza uma opção: ");
					opcaoVisualizar = teclado.next().charAt(0);
					teclado.nextLine();

					switch(opcaoVisualizar) {
					case 't':
					case 'T':
						System.out.println();

						// imprime todas as tarefas
						if (nTarefas != 0) {
							System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
							for (int i = 0; i < nTarefas; i++) {
								System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
								if (!temPrazo[i]) {
									System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
								} else {
									System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
								}
								System.out.println();
							}
						} else {
							System.out.println("Não existem tarefas para mostrar!");
						}

						System.out.println();
						break;
					case 'd':
					case 'D':
						System.out.println();

						System.out.print("Introduza o dia que pretende visualizar (dd/mm/aaaa): ");
						dataIntroduzida = teclado.nextLine();

						dataSplit = dataIntroduzida.split("/");
						dataD = Integer.parseInt(dataSplit[0]);
						dataM = Integer.parseInt(dataSplit[1]);
						dataA = Integer.parseInt(dataSplit[2]);

						idTarefas = new int [nTarefas];

						boolean existeDia = false;

						// este ciclo percorre o nTarefas e sempre que a data introduzida for igual à data existente no array data e cada posição no array temPrazo for true,
						// guarda o número da tarefa na posição do array idTarefas e coloca a variável existeDia a true
						for (int i = 0; i < nTarefas; i++) {
							if (dataD == data[i][0] && dataM == data[i][1] && dataA == data[i][2]) { 
								idTarefas[i] = i + 1;
								existeDia = true;
							} 
						}

						// se a variável existeDia for true e as posições do array idTarefas forem diferentes de 0 imprime as tarefas
						if (existeDia) {
							System.out.println("\n\t Tarefa \t\t Data \t\t Feita"); 
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									System.out.println();
								}
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa planeada para esse dia!");
						}

						System.out.println();
						break;
					case 'a':
					case 'A':
						System.out.println();

						System.out.print("Introduza o dia até que pretende visualizar (dd/mm/aaaa): ");
						dataIntroduzida = teclado.nextLine();

						dataSplit = dataIntroduzida.split("/");
						dataD = Integer.parseInt(dataSplit[0]);
						dataM = Integer.parseInt(dataSplit[1]);
						dataA = Integer.parseInt(dataSplit[2]);


						idTarefas = new int [nTarefas];
						boolean existeAteDia = false;

						// este ciclo percorre o nTarefas e verifica se a data introduzida é maior ou igual à data existente no array data,
						// caso seja verdade, guarda o número da tarefa na posição do array idTarefas e coloca a variável existeAteDia a true
						for (int i = 0; i < nTarefas; i++) {
							if (dataA > data[i][2] && temPrazo[i] || dataA == data[i][2] && dataM > data[i][1] && temPrazo[i] || dataA == data[i][2] && dataM == data[i][1] && dataD >= data[i][0] && temPrazo[i]) {
								idTarefas[i] = i + 1;
								existeAteDia = true;
							}
						}

						// se a variável existeAteDia for true e as posições do array idTarefas forem diferentes de 0 imprime, as tarefas até ao dia introduzido
						if (existeAteDia) {
							System.out.println("\n\t Tarefa \t\t Data \t\t Feita"); 
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0 && temPrazo[i]) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();
								}
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa planeada até esse dia!");
						}

						System.out.println();
						break;
					case 'p':
					case 'P':

						idTarefas = new int [nTarefas];
						boolean existePorFazer = false;

						// este ciclo percorre o nTarefas e verifica se cada tarefa do array foiFeita é false,
						// caso seja false, guarda o número da tarefa na posição do array idTarefas e coloca a variável existePorFazer a true
						for (int i = 0; i < nTarefas; i++) {
							if (!foiFeita[i]) {
								idTarefas[i] = i + 1;
								existePorFazer = true;
							} 
						}

						// se a variável existePorFazer for true e as posições do array idTarefas forem diferentes de 0 e as posições do array temPrazo forem true,
						// então imprime as tarefas que estão por fazer
						if (existePorFazer) {
							System.out.println();
							System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();
								}
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa por fazer!");
						}

						System.out.println();
						break;
					case 'f':
					case 'F':

						idTarefas = new int [nTarefas];
						boolean estaFeita = false;

						// este ciclo percorre o nTarefas e verifica se cada tarefa do array foiFeita é true,
						// caso seja true, guarda o número da tarefa na posição do array idTarefas e coloca a variável estaFeita a true
						for (int i = 0; i < nTarefas; i++) {
							if (foiFeita[i]) {
								idTarefas[i] = i + 1;
								estaFeita = true;
							} 
						}

						// se a variável estaFeita for true e as posições do array idTarefas forem diferentes de 0, então imprime as tarefas que estão feitas
						if (estaFeita) {
							System.out.println("\n\t Tarefa \t\t Data \t\t Feita"); 
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();
								}
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa feita!");
						}

						System.out.println();
						break;
					case 'l':
					case 'L':

						System.out.print("\nIntroduza a palavra que pretende procurar: ");
						String palavraIntroduzida = teclado.next();
						teclado.nextLine();

						idTarefas = new int [nTarefas];
						boolean encontrouPalavra = false;

						// este ciclo percorre o nTarefas e verifica se a palavra introduzida corresponde á que se encontra no array tarefa
						// caso seja true, guarda o número da tarefa na posição do array idTarefas e coloca a variável encontrouPalavra a true
						for (int i = 0; i < nTarefas; i++) {
							if (tarefa[i].indexOf(palavraIntroduzida) != -1) {
								idTarefas[i] = i + 1;
								encontrouPalavra = true;
							}
						}

						// se a variável encontrouPalavra for true e as posições do array idTarefas forem diferentes de 0, então imprime todas as tarefas que tenham essa palavra na descrição
						if (encontrouPalavra) {
							System.out.println("\n\t Tarefa \t\t Data \t\t Feita");
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();
								}
							}
						} else {
							System.out.println("\nNão existe nenhuma palavra com esse nome!");
						}

						System.out.println();
						break;
					case 'v':
					case 'V':
						break;
					default:
						System.out.println("Opção Inválida!");
					}
				} while (opcaoVisualizar != 'v' && opcaoVisualizar != 'V');
				break;
			case 'm':
			case 'M':
				char opcaoMarcar = 0;
				do {
					System.out.println("************************************");
					System.out.println("*  Marcar como (f)eita por número  *");
					System.out.println("*  Marcar como feita por (t)exto   *");
					System.out.println("*  (D)esmarcar última feita        *");
					System.out.println("*  Marcar todas (n)o dia d         *");
					System.out.println("*                                  *");
					System.out.println("*  (V)oltar                        *");
					System.out.println("************************************");

					System.out.print("Intoduza uma opção: ");
					opcaoMarcar = teclado.next().charAt(0);
					teclado.nextLine();

					switch(opcaoMarcar) {
					case 'f':
					case 'F':
						System.out.println();

						int marcarTarefa = 0;

						// validação ao introduzir o número da tarefa
						do {
							System.out.print("Introduza o número da tarefa (> 0): ");
							marcarTarefa = teclado.nextInt();
							teclado.nextLine();
						} while (marcarTarefa <= 0);

						// caso o número da tarefa introduzido no array foiFeita seja false, coloca-o a true
						if (marcarTarefa <= nTarefas) {
							if (!foiFeita[marcarTarefa - 1]) {
								foiFeita[marcarTarefa - 1] = true;
								
								// imprime a tarefa marcada como feita
								if (foiFeita[marcarTarefa - 1]) {
									System.out.println();
									System.out.println("\t Tarefa \t\t Data \t\t Feita");
									System.out.printf("%d: \t %-20s", marcarTarefa, tarefa[marcarTarefa - 1]);
									if (!temPrazo[marcarTarefa - 1]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[marcarTarefa - 1] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[marcarTarefa - 1][0], data[marcarTarefa - 1][1], data[marcarTarefa - 1][2], foiFeita[marcarTarefa - 1] ? "X" : "");
									}

									ultimaFeita = marcarTarefa; // guarda a última tarefa feita na variável ultimaFeita

									System.out.println();
								}
							} else {
								System.out.printf("\nA tarefa %d já se encontra marcada como feita!\n", marcarTarefa);
								System.out.println();
								break;
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa com esse número!");
						}

						System.out.println();
						break;
					case 't':
					case 'T':
						System.out.println();

						System.out.print("Introduza o nome da tarefa: ");
						String tarefaIntroduzida = teclado.nextLine();

						idTarefas = new int [nTarefas];
						boolean existeTarefa = false;

						// este ciclo percorre o nTarefas e verifica se o texto introduzido corresponde ao que se encontra no array tarefa
						// e se é false no array foiFeita. Caso se verifique, guarda o número da tarefa na posição do array idTarefas e coloca a variável existeTarefa a true
						for (int i = 0; i < nTarefas; i++) {
							if (tarefa[i].indexOf(tarefaIntroduzida) != -1 && !foiFeita[i]) {
								idTarefas[i] = i + 1;
								existeTarefa = true;
							}
						}

						// se a variável existeTarefa for true verifica se a tarefa na posição foiFeita também é true, se for é porque a tarefa já se encontra
						// marcada, senão coloca-a a true e imprime a tarefa marcada como feita
						if (existeTarefa) {
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0) {
									foiFeita[i] = true;
									System.out.println("\n\t Tarefa \t\t Data \t\t Feita");
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}

									ultimaFeita = idTarefas[i]; // guarda a última tarefa feita na variável ultimaFeita

									System.out.println();
									break;
								}	
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa com essa descriçao para ser marcada!");
						}

						System.out.println();
						break;
					case 'd':
					case 'D':

						idTarefas = new int [nTarefas];

						// verifica se a variavel ultimaFeita é diferente que 0, se for percorre o nTarefas e incrementa ao array idTarefas o número e a posição que tem a 
						// variavel ultimaFeita. Depois verifica se no array idTarefas existe alguma posição diferente de 0 e caso seja coloca a tarefa no array FoiFeita a false e por fim imprime
						if (ultimaFeita != 0) {
							System.out.println("\n\t Tarefa \t\t Data \t\t Feita");
							for (int i = 0; i < nTarefas; i++) {
								idTarefas[ultimaFeita - 1] = ultimaFeita;
								if (idTarefas[i] != 0) {
									foiFeita[i] = false;
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();

								}
							}
							ultimaFeita = 0; // reseta a variável
						} else {
							System.out.println("\nNão existe nenhuma última tarefa marcada como feita para ser desmarcada!");
						}


						System.out.println();
						break;
					case 'n':
					case 'N':
						System.out.println();

						System.out.print("Introduza o dia que pretende marcar (dd/mm/aaaa): ");
						dataIntroduzida = teclado.nextLine();

						dataSplit = dataIntroduzida.split("/");
						dataD = Integer.parseInt(dataSplit[0]);
						dataM = Integer.parseInt(dataSplit[1]);
						dataA = Integer.parseInt(dataSplit[2]);

						idTarefas = new int [nTarefas];
						boolean estaMarcada = false;

						// este ciclo percorre o nTarefas e verifica se a data introduzida é igual aos valores existentes no array data, se as tarefas no array foiFeitas,
						// têm o valor false e têm o valor true no array temPrazo, guarando o número da tarefa na posição do array idTarefas e coloca a variável estaMarcada a true
						for (int i = 0; i < nTarefas; i++) {
							if (dataD == data[i][0] && dataM == data[i][1] && dataA == data[i][2] && !foiFeita[i]) {
								idTarefas[i] = i + 1;
								estaMarcada = true;
							} 
						}

						// caso a variável estaMarcada seja true, percorre o nTarefas e caso as posições do array idTarefas sejam diferentes de 0 coloca as tarefas a true e imprime
						if (estaMarcada) {                        
							System.out.println("\n\t Tarefa \t\t Data \t\t Feita");
							for (int i = 0; i < nTarefas; i++) {
								if (idTarefas[i] != 0) {
									foiFeita[i] = true;
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}

									ultimaFeita = idTarefas[i]; // guarda a última tarefa feita na variável ultimaFeita

									System.out.println();                                    
								}
							}
						} else {
							System.out.println("\nNão existe nenhuma tarefa para ser marcada nesse dia!");
						}

						System.out.println();
						break;
					case 'v':
					case 'V':
						break;
					default:
						System.out.println("Opção Inválida!");	
					}
				} while (opcaoMarcar != 'v' && opcaoMarcar != 'V');
				break;
			case 'e':
			case 'E':
				char opcaoAddDelete = 0;
				int posicaoTarefa = 0;
				char prazo = 0;
				String novaData = "";
				String novaDescricao = "";
				boolean listaCheia = false;
				do {
					System.out.println("*************************************");
					System.out.println("*  (A)dicionar tarefa               *");
					System.out.println("*  Adicionar (t)arefa na posição n  *");
					System.out.println("*  Apagar tarefa na (p)osição n     *");
					System.out.println("*  Apagar (f)eitas                  *");
					System.out.println("*  (E)ditar tarefa                  *");
					System.out.println("*                                   *");
					System.out.println("*  (V)oltar                         *");
					System.out.println("*************************************");

					System.out.print("Intoduza uma opção: ");
					opcaoAddDelete = teclado.next().charAt(0);
					teclado.nextLine();

					switch(opcaoAddDelete) {
					case 'a':
					case 'A':
						System.out.println();

						prazo = 0;
						listaCheia = false;
						eBissexto = false;

						// verifica se a lista já está cheia 
						if (nTarefas >= tamMax) {
							listaCheia = true;
						}

						if (listaCheia) {
							System.out.println("Não é possivel adicionar mais tarefas porque a lista encontra-se cheia!\n");
							break;
						} else {

							nTarefas += 1; // atualiza o nTarefas

							System.out.print("Introduza o nome de uma nova tarefa: ");
							novaDescricao = teclado.nextLine();

							// validação ao introduzir se quer ou não um prazo
							do {
								System.out.println();
								System.out.print("Deseja adicionar um prazo? (S/N): ");
								prazo = teclado.nextLine().charAt(0);
							} while (prazo != 's' && prazo != 'S' && prazo != 'n' && prazo != 'N');

							// coloca o array temPrazo a true e guarda a data introduzida no array data
							if (prazo == 's' || prazo == 'S') {
								System.out.println();
								System.out.print("Introduza o prazo que pretende (dd/mm/aaaa): ");
								novaData = teclado.nextLine();

								dataSplit = novaData.split("/");
								dataD = Integer.parseInt(dataSplit[0]);
								dataM = Integer.parseInt(dataSplit[1]);
								dataA = Integer.parseInt(dataSplit[2]);
							}

							// Verifica se o ano introduzido é bissexto
							if((dataA % 4 == 0 && dataA % 100 != 0) || (dataA % 400 == 0)) {
								eBissexto = true;
							} else {
								eBissexto = false;
							}

							// Validação da data
							if((eBissexto && dataM == 2 && dataD > 29) || (!eBissexto && dataM == 2 && dataD > 28) || (dataM == 1 && dataD > 31) || (dataM == 3 && dataD > 31)
									|| (dataM == 4 && dataD > 30) || (dataM == 5 && dataD > 31) || (dataM == 6 && dataD > 30) || (dataM == 7 && dataD > 31) || (dataM == 8 && dataD > 31)
									|| (dataM == 9 && dataD > 30) || (dataM == 10 && dataD > 31) || (dataM == 11 && dataD > 30) || (dataM == 12 && dataD > 31) || (dataM > 12)) {
								System.out.println("\nA data que introduziu é inválida!");
								nTarefas -= 1; // atualiza o nTarefas
							} else {
								// adiciona a tarefa no final da lista
								if (prazo == 's' || prazo == 'S') {
									tarefa[nTarefas - 1] = novaDescricao;
									temPrazo[nTarefas - 1] = true;
									foiFeita[nTarefas - 1] = false;
									data[nTarefas - 1][0] = dataD;
									data[nTarefas - 1][1] = dataM;
									data[nTarefas - 1][2] = dataA;
								} else {
									tarefa[nTarefas - 1] = novaDescricao;
									temPrazo[nTarefas - 1] = false;
									foiFeita[nTarefas - 1] = false;
									data[nTarefas - 1][0] = 0;
									data[nTarefas - 1][1] = 0;
									data[nTarefas - 1][2] = 0;
								}

								// imprime as tarefas existentes e a nova adicionada
								System.out.println();
								System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
								for (int i = 0; i < nTarefas; i++) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();
								}
							}
						}

						System.out.println();
						break;
					case 't':
					case 'T':
						System.out.println();

						posicaoTarefa = 0;
						prazo = 0;
						listaCheia = false;
						eBissexto = false;

						// verifica se a lista já está cheia 
						if (nTarefas >= tamMax) {
							listaCheia = true;
						}

						if (listaCheia) {
							System.out.println("Não é possivel adicionar mais tarefas porque a lista encontra-se cheia!\n");
							break;
						} else {
							// pede para introduzir a posição sendo que tem de ser > 0
							do {
								System.out.print("Introduza a posiçao pretendida para a nova tarefa (> 0): ");
								posicaoTarefa = teclado.nextInt();
								teclado.nextLine();
							} while (posicaoTarefa <= 0);

							nTarefas += 1; // atualiza o nTarefas

							// verifica se a posição introduzida é maior que nTarefas + 1
							System.out.println();
							if (posicaoTarefa - 1 > nTarefas - 1) {
								System.out.println("Não é possivel adicionar nenhuma tarefa nessa posição!");
								System.out.println();
								nTarefas -= 1; // atualiza o nTarefas
								break;
							} 		

							System.out.print("Introduza o nome de uma nova tarefa: ");
							novaDescricao = teclado.nextLine();

							do {
								System.out.println();
								System.out.print("Deseja adicionar um prazo? (S/N): ");
								prazo = teclado.nextLine().charAt(0);
							} while (prazo != 's' && prazo != 'S' && prazo != 'n' && prazo != 'N');

							// verifica se o utilizador quer ter um prazo, coloca o array temPrazo a true e pede para introduzir uma data
							if (prazo == 's' || prazo == 'S') {
								System.out.println();
								System.out.print("Introduza o prazo que pretende (dd/mm/aaaa): ");
								novaData = teclado.nextLine();

								dataSplit = novaData.split("/");
								dataD = Integer.parseInt(dataSplit[0]);
								dataM = Integer.parseInt(dataSplit[1]);
								dataA = Integer.parseInt(dataSplit[2]);
							}

							// Verifica se o ano introduzido é bissexto
							if((dataA % 4 == 0 && dataA % 100 != 0) || (dataA % 400 == 0)) {
								eBissexto = true;
							} else {
								eBissexto = false;
							}

							// Validação da data
							if((eBissexto && dataM == 2 && dataD > 29) || (!eBissexto && dataM == 2 && dataD > 28) || (dataM == 1 && dataD > 31) || (dataM == 3 && dataD > 31)
									|| (dataM == 4 && dataD > 30) || (dataM == 5 && dataD > 31) || (dataM == 6 && dataD > 30) || (dataM == 7 && dataD > 31) || (dataM == 8 && dataD > 31)
									|| (dataM == 9 && dataD > 30) || (dataM == 10 && dataD > 31) || (dataM == 11 && dataD > 30) || (dataM == 12 && dataD > 31) || (dataM > 12)) {
								System.out.println("\nA data que introduziu é inválida!");
								nTarefas -= 1; // atualiza o nTarefas
							} else {
								// decrementa uma posição em cada array até chegar à posição introduzida pelo utilizador (faz um shift para a "direita")
								for (int i = nTarefas - 1; i > posicaoTarefa - 1; i--) {
									tarefa[i] = tarefa[i - 1]; 
									temPrazo[i] = temPrazo[i - 1]; 
									foiFeita[i] = foiFeita[i - 1];
									data[i][0] = data[i - 1][0]; 
									data[i][1] = data[i - 1][1];
									data[i][2] = data[i - 1][2];
								}

								// guarda a nova tarefa na posição introduzida
								if (prazo == 's' || prazo == 'S') {
									tarefa[posicaoTarefa - 1] = novaDescricao;
									temPrazo[posicaoTarefa - 1] = true;
									foiFeita[posicaoTarefa - 1] = false;
									data[posicaoTarefa - 1][0] = dataD;
									data[posicaoTarefa - 1][1] = dataM;
									data[posicaoTarefa - 1][2] = dataA;
								} else {
									tarefa[posicaoTarefa - 1] = novaDescricao;
									temPrazo[posicaoTarefa - 1] = false;
									foiFeita[posicaoTarefa - 1] = false;
									data[posicaoTarefa - 1][0] = 0;
									data[posicaoTarefa - 1][1] = 0;
									data[posicaoTarefa - 1][2] = 0;
								}

								// imprime
								System.out.println();
								System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
								for (int i = 0; i < nTarefas; i++) {
									System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
									if (!temPrazo[i]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
									}
									System.out.println();
								}
							}
						}

						System.out.println();
						break;
					case 'p':
					case 'P':
						System.out.println();

						posicaoTarefa = 0;

						// pede para introduzir a posição sendo que tem de ser > 0
						do {
							System.out.print("Introduza a posiçao pretendida para remover uma tarefa (> 0): ");
							posicaoTarefa = teclado.nextInt();
							teclado.nextLine();
						} while (posicaoTarefa <= 0);

						// verifica se a posição introduzida é maior que nTarefas
						System.out.println();
						if (posicaoTarefa > nTarefas) {
							System.out.println("Não é possivel remover nenhuma tarefa nessa posição!");
							System.out.println();
							break;
						} 

						nTarefas -= 1; // atualiza o nTarefas

						if (nTarefas == 0) {
							System.out.println("Última tarefa apagada com sucesso!");
							System.out.println();
							break;
						} else {
							// incrementa uma posição em cada array desde a posição introduzida pelo utilizador até chegar ao nTarefas (faz um shift para a "esquerda")
							for (int i = posicaoTarefa - 1; i < nTarefas; i++) {
								tarefa[i] = tarefa[i + 1];
								temPrazo[i] = temPrazo[i + 1]; 
								foiFeita[i] = foiFeita[i + 1];
								data[i][0] = data[i + 1][0]; 
								data[i][1] = data[i + 1][1];
								data[i][2] = data[i + 1][2];
							}
						}

						// imprime
						System.out.println("\t Tarefa \t\t Data \t\t Feita"); 
						for (int i = 0; i < nTarefas; i++) {
							System.out.printf("%d: \t %-20s", i + 1, tarefa[i]);
							if (!temPrazo[i]) {
								System.out.printf("\t Sem Prazo \t %s", foiFeita[i] ? "X" : "");
							} else {
								System.out.printf("\t %02d/%02d/%d \t %s", data[i][0], data[i][1], data[i][2], foiFeita[i] ? "X" : "");
							}
							System.out.println();
						}

						System.out.println();
						break;
					case 'f':
					case 'F':
						System.out.println();

						idTarefas = new int [nTarefas];
						boolean todasApagadas = false;
						int quantTarefasFeitas = 0; // contador da quantidade de tarefas feitas

						// este ciclo percorre o nTarefas, em que drecrementa desde o número que está em nTarefas até chegar à posição 0
						// verifica se em cada posição no array foiFeita é true e caso seja true guarda a posição no array idTarefas
						// por fim percorrre novamente o nTarefas - 1, começa na primeira posição do array idTarefas e incrementa, atualizando assim a lista (remove os duplicados)
						for (int i = nTarefas - 1; i >= 0; i--) {
							if (foiFeita[i]) {
								idTarefas[i] = i;
								quantTarefasFeitas ++;
								todasApagadas = true;
								for (int k = idTarefas[i]; k < nTarefas - 1; k++) {
									tarefa[k] = tarefa[k + 1];
									temPrazo[k] = temPrazo[k + 1];
									foiFeita[k] = foiFeita[k + 1];
									data[k][0] = data[k + 1][0];
									data[k][1] = data[k + 1][1];
									data[k][2] = data[k + 1][2];
								}
							} 
						}

						nTarefas -= quantTarefasFeitas; // atualiza o nTarefas

						// imprime
						if (todasApagadas) {
							System.out.println("Tarefas feitas apagadas com sucesso!");
						} else {
							System.out.println("Não existe nenhuma tarefa feita para ser apagada!");
						}

						System.out.println(); 
						break;
					case 'e':
					case 'E':
						char opcaoEditar = 0;
						do {
							System.out.println("******************************");
							System.out.println("*  (E)ditar texto            *");
							System.out.println("*  (R)emover/adicionar data  *");
							System.out.println("*                            *");
							System.out.println("*  (V)oltar                  *");
							System.out.println("******************************");

							System.out.print("Intoduza uma opção: ");
							opcaoEditar = teclado.next().charAt(0);
							teclado.nextLine();

							switch(opcaoEditar) {
							case 'e':
							case 'E':
								System.out.println();

								int numeroTarefa = 0;
								// validação ao introduzir o número da tarefa
								do {
									System.out.print("Introduza o número da tarefa (> 0): ");
									numeroTarefa = teclado.nextInt();
									teclado.nextLine();
								} while (numeroTarefa <= 0);

								// Pede ao utilizador uma nova descrição caso a tarefa exista, substitui a descrição e no final imprime a tarefa atualizada
								if (numeroTarefa <= nTarefas) {
									System.out.println();
									System.out.print("Introduza uma nova descrição para substituir a atual: ");
									novaDescricao = teclado.nextLine();

									tarefa[numeroTarefa - 1] = novaDescricao;
									System.out.println();

									System.out.println("\t Tarefa \t\t Data \t\t Feita");
									System.out.printf("%d: \t %-20s", numeroTarefa, tarefa[numeroTarefa - 1]);
									if (!temPrazo[numeroTarefa - 1]) {
										System.out.printf("\t Sem Prazo \t %s", foiFeita[numeroTarefa - 1] ? "X" : "");
									} else {
										System.out.printf("\t %02d/%02d/%d \t %s", data[numeroTarefa - 1][0], data[numeroTarefa - 1][1], data[numeroTarefa - 1][2], foiFeita[numeroTarefa - 1] ? "X" : "");
									}

									System.out.println();


								} else {
									System.out.println();
									System.out.println("Não existe nenhuma tarefa com esse número!");
								}

								System.out.println();
								break;
							case 'r':
							case 'R':
								System.out.println();

								numeroTarefa = 0;
								eBissexto = false;

								// validação ao introduzir o número da tarefa
								do {
									System.out.print("Introduza o número da tarefa (> 0): ");
									numeroTarefa = teclado.nextInt();
									teclado.nextLine();
								} while (numeroTarefa <= 0);

								// verifica se o número da tarefa introduzido existe
								System.out.println();
								if (numeroTarefa > nTarefas) {
									System.out.println("Não existe nenhuma tarefa com esse número!");
									System.out.println();
									break;
								}

								// se o número da tarefa introduzido tiver no array temPrazo com o valor false, coloca-o a true,
								// pede ao utilizador para introduzir uma data e imprime a tarefa atualizada no ecrã
								if (!temPrazo[numeroTarefa - 1]) {
									System.out.print("Introduza o prazo que pretende (dd/mm/aaaa): ");
									novaData = teclado.nextLine();
									System.out.println();

									dataSplit = novaData.split("/");
									dataD = Integer.parseInt(dataSplit[0]);
									dataM = Integer.parseInt(dataSplit[1]);
									dataA = Integer.parseInt(dataSplit[2]);

									// Verifica se o ano introduzido é bissexto
									if((dataA % 4 == 0 && dataA % 100 != 0) || (dataA % 400 == 0)) {
										eBissexto = true;
									} else {
										eBissexto = false;
									}

									// Validação da data
									if((eBissexto && dataM == 2 && dataD > 29) || (!eBissexto && dataM == 2 && dataD > 28) || (dataM == 1 && dataD > 31) || (dataM == 3 && dataD > 31)
											|| (dataM == 4 && dataD > 30) || (dataM == 5 && dataD > 31) || (dataM == 6 && dataD > 30) || (dataM == 7 && dataD > 31) || (dataM == 8 && dataD > 31)
											|| (dataM == 9 && dataD > 30) || (dataM == 10 && dataD > 31) || (dataM == 11 && dataD > 30) || (dataM == 12 && dataD > 31) || (dataM > 12)) {
										System.out.println("A data que introduziu é inválida!");
									} else {
										temPrazo[numeroTarefa - 1] = true;
										data[numeroTarefa - 1][0] = dataD;
										data[numeroTarefa - 1][1] = dataM;
										data[numeroTarefa - 1][2] = dataA;

										System.out.println("\t Tarefa \t\t Data \t\t Feita");
										System.out.printf("%d: \t %-20s", numeroTarefa, tarefa[numeroTarefa - 1]);
										if (!temPrazo[numeroTarefa - 1]) {
											System.out.printf("\t Sem Prazo \t %s", foiFeita[numeroTarefa - 1] ? "X" : "");
										} else {
											System.out.printf("\t %02d/%02d/%d \t %s", data[numeroTarefa - 1][0], data[numeroTarefa - 1][1], data[numeroTarefa - 1][2], foiFeita[numeroTarefa - 1] ? "X" : "");
										}

										System.out.println();
									}
								} else {
									// caso a tarefa já tenha uma prazo é colocado no array temPrazo o valor false
									temPrazo[numeroTarefa - 1] = false;
									data[numeroTarefa - 1][0] = 0;
									data[numeroTarefa - 1][1] = 0;
									data[numeroTarefa - 1][2] = 0;
									System.out.println("Data limite removida com sucesso!");
								}

								System.out.println();
								break;
							case 'v':
							case 'V':
								break;
							default:
								System.out.println("Opção Inválida");
							}
						} while (opcaoEditar != 'v' && opcaoEditar != 'V');
						break;
					case 'v':
					case 'V':
						break;
					default:
						System.out.println("Opção Inválida!");
					}
				} while (opcaoAddDelete != 'v' && opcaoAddDelete != 'V');
				break;
			case 's':
			case 'S':
				System.out.println();
				System.out.println("Terminou o programa!");
				break;
			default:
				System.out.println("Opção Inválida!");
			}
		} while(opcaoMenu != 's' && opcaoMenu != 'S');

		teclado.close();
	}

}
