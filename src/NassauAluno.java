import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;




public class NassauAluno {
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		ArrayList<Aluno> alunos = new ArrayList<Aluno>();
		
        
		alunos = MenuPrincipal(in, alunos);
		
		in.close();
		
	}
	
	public static class Aluno {
		
		private String nome; 
		private int periodo;
		private String[] disciplinas;
		
		public Aluno(String _nome, int _periodo, String[] _disciplinas){
            this.nome = _nome;
            this.periodo = _periodo;
            this.disciplinas = _disciplinas;
        }
		
		public void setNome(String _nome){
            this.nome = _nome;
        }

        public String getNome(){
            return this.nome;
        }
        
        public int getPeriodo(){
        	return this.periodo;
        }
        
        public String[] getDisciplinas(){
        	return this.disciplinas;
        }
	}
	
	public static Aluno AdicionarAluno(Scanner in){

        String nomeCompleto;
        int periodo;
        String[] disciplinas;

        nomeCompleto = ColocarNome(in);
        System.out.println("\nNome salvo: " + nomeCompleto);

        periodo = SelecionarPeriodo(in);
        System.out.println("\n* Estudante " + nomeCompleto + " do " + periodo + "º Período salvo com sucesso! *\n");
        
        disciplinas = DefinirDisciplinas(periodo);

        return new Aluno(nomeCompleto, periodo, disciplinas);
 
        }
	
	public static String ColocarNome(Scanner in)
    {
        String nome = null;
        String sobrenome;
        String nomeCompleto = null;
        //in.useDelimiter("\n");


        System.out.println("\nDigite seu nome: ");
        nome = in.next();
        nome = nome.substring(0,1).toUpperCase() + nome.substring(1,nome.length()).toLowerCase();

        System.out.println("\nDigite seu sobrenome: ");
        sobrenome = in.next();
        sobrenome = sobrenome.substring(0,1).toUpperCase() + sobrenome.substring(1,sobrenome.length()).toLowerCase();

        nomeCompleto = ConfirmarNome(in, nome, sobrenome);

        return nomeCompleto;
    }
	
	public static String ConfirmarNome(Scanner in, String nome, String sobrenome){

        String confirmacaoNome;
        String nomeCompleto = null;

        System.out.println("\nNome escolhido: " + nome + " "+ sobrenome);
        System.out.println("Confirmar nome? Digite S ou N.");

        confirmacaoNome = in.next().toUpperCase();

       if(confirmacaoNome.equals("S")){
           return nome + " " + sobrenome;
        } else if(confirmacaoNome.equals("N")){
           nomeCompleto = ColocarNome(in);
           return nomeCompleto;
        } else {
            System.out.println("Entrada incorreta");
            nomeCompleto = ConfirmarNome(in, nome, sobrenome);
        }
       return nomeCompleto;
    }

public static int SelecionarPeriodo(Scanner in){

    int numeroPeriodo = 0;

    System.out.println("\nPertence ao período 1, 2 ou 3?");

    try {
    	numeroPeriodo = in.nextInt();
    } catch (InputMismatchException e){
        System.out.println("Digite um número!");
        in.next();
        numeroPeriodo = SelecionarPeriodo(in);
        return numeroPeriodo;
    }

    switch (numeroPeriodo){
        case 1:
        	numeroPeriodo = 1;
            break;
        case 2:
        	numeroPeriodo = 2;
            break;
        case 3:
        	numeroPeriodo = 3;
            break;
        default:
            System.out.println("Escolheu incorretamente.");
            numeroPeriodo = SelecionarPeriodo(in);
            break;
    }
    return numeroPeriodo;
}

public static String[] DefinirDisciplinas(int periodo) {
	
	Disciplinas disciplina = new Disciplinas();
	

	
	if (periodo == 1) {
		
		return disciplina.disciplinasPeriodo1;
		
	} else if (periodo == 2) {
		
		return disciplina.disciplinasPeriodo2;
		
	} else {
		
		return disciplina.disciplinasPeriodo3;
		
	}
}



public static ArrayList<Aluno> MenuPrincipal(Scanner in, ArrayList<Aluno> _alunos){
    System.out.println("O que deseja fazer?\n" +
            "1: Calcular Médias\n2: Adicionar um aluno \n3: Excluir um aluno\n4: Alterar nome de um aluno\n");


    int opcaoMenu = 0;
    ArrayList<Aluno> alunos = _alunos;
    Aluno aluno = null;


    try {
        opcaoMenu = in.nextInt();
       
    } catch (InputMismatchException e){
        System.out.println("\nDigite um número!\n");
        in.next();
        return MenuPrincipal(in, alunos);
    }

    switch (opcaoMenu){
        case 1:
        	CalcularMedias(in, alunos);
            break;
        case 2:
        	alunos.add(aluno = AdicionarAluno(in));
        	MenuPrincipal(in, alunos);
            break;
        case 3:
            ExcluirAluno(alunos, in);
            break;
        case 4:
        	AlterarEstudante(alunos, in);
            break;
        default:
            System.out.println("\nDigite um número válido no menu!\n");
            MenuPrincipal(in, alunos);
            break;
    }

    return alunos;
}

public static void ExcluirAluno(ArrayList<Aluno> alunos, Scanner in){

    int selecaoPersonagem = 0;
    String confirmarExclusao = null;

    if(alunos.size() > 0){
        System.out.println("\nEscolha qual estudante deseja excluir:");

        for(int i = 0; i < alunos.size(); i++){
            System.out.println((i+1)+": "+ alunos.get(i).getNome());
        }

        try {
            selecaoPersonagem = in.nextInt();
        } catch (InputMismatchException e){
            System.out.println("\nDigite um número!\n");
            in.next();
            ExcluirAluno(alunos, in);
        }

        if(selecaoPersonagem > alunos.size() || selecaoPersonagem <= 0){
            System.out.println("\nEntrada incorreta.\n");
            ExcluirAluno(alunos, in);
        }

        confirmarExclusao = ConfirmarExclusaoAluno(in, alunos.get(selecaoPersonagem - 1).getNome());

        if(confirmarExclusao.equals("S")){

            System.out.println("\nEstudante "+alunos.get(selecaoPersonagem - 1).getNome()+" excluído.");
            alunos.remove(selecaoPersonagem - 1);

        } else if (confirmarExclusao.equals("N")){
            System.out.println("\nEstudante não foi excluído.\n");
        }

        MenuPrincipal(in, alunos);

    } else {
        System.out.println("\nNão há estudantes para excluir.\n");
        MenuPrincipal(in, alunos);
    }
}

public static String ConfirmarExclusaoAluno(Scanner in, String nomeAluno){

    String confirmacaoExclusao = null;

    System.out.println("\nConfirmar exclusão de " + nomeAluno + "? Digite S ou N.");
    confirmacaoExclusao = in.next().toUpperCase();

    if(confirmacaoExclusao.equals("S")){
        return "S";
    } else if (confirmacaoExclusao.equals("N")) {
        return "N";
    } else {
        System.out.println("Entrada incorreta.");
        confirmacaoExclusao = ConfirmarExclusaoAluno(in, nomeAluno);
    }
    return confirmacaoExclusao;
}


public static void AlterarEstudante(ArrayList<Aluno> alunos, Scanner in){

    int selecaoPersonagem = 0;
    String nomeAntigo;
    String novoNome;

    if(alunos.size() > 0){

        System.out.println("\nEscolha qual estudante deseja alterar:");

        for(int i = 0; i < alunos.size(); i++){
            System.out.println((i+1)+": "+ alunos.get(i).getNome());
        }

        try {
            selecaoPersonagem = in.nextInt();
        } catch (InputMismatchException e){
            System.out.println("Digite um número!");
            in.next();
            AlterarEstudante(alunos, in);
        }

        if(selecaoPersonagem > alunos.size() || selecaoPersonagem <= 0){
            System.out.println("Entrada incorreta.");
            AlterarEstudante(alunos, in);
        }

        nomeAntigo = alunos.get(selecaoPersonagem - 1).getNome();
        novoNome = ColocarNome(in);

        alunos.get(selecaoPersonagem - 1).setNome(novoNome);

        System.out.println("\n" + nomeAntigo + " alterou o nome para " + novoNome);

        MenuPrincipal(in, alunos);

    } else {
        System.out.println("\nNão há estudantes para alterar.\n");
        MenuPrincipal(in, alunos);
    }
}

public static void CalcularMedias(Scanner in, ArrayList<Aluno> alunos) {
	
	if(alunos.size() > 0)
	{
	
		Disciplinas disc = new Disciplinas();
		
		String[] nomesDisciplinas = disc.getDisciplinas();
		int quantAlunosDisciplina, quantAlunosReprovados, quantAlunosFinal, quantAlunosAprovados;
		float nota1 = 0;
		float nota2 = 0;
		float mediaAluno;
		double mediaGeralDisciplina, maiorMediaDisciplina, menorMediaDisciplina, mediaGeralTurma;
		
		Aluno ultimoAluno = null;
		
		int qntDisciplinas = 0;
	
		mediaGeralTurma = 0;

		
		for(int i = 0; i < nomesDisciplinas.length; i++) {
			
			quantAlunosDisciplina = 0;
			quantAlunosReprovados = 0;
			quantAlunosFinal = 0;
			quantAlunosAprovados = 0;
			mediaGeralDisciplina = 0;
			maiorMediaDisciplina = 0;
			menorMediaDisciplina = 0;
			
			
			int contador = 1;
			
			
			for(Aluno a: alunos) {
				
				
				for(String d : a.disciplinas) {
					
					if(nomesDisciplinas[i] == d) {
						
						
						if(ultimoAluno != null) {
						
						
							if(a.periodo != ultimoAluno.periodo) {
								
								mediaGeralTurma /= qntDisciplinas;
							
								System.out.println("#### RESUMO DA TURMA ####");
								System.out.println("TURMA: " + ultimoAluno.periodo + "º período");
								System.out.println("QTD DE DISCIPLINAS: " + ultimoAluno.disciplinas.length);
								System.out.println("MÉDIA GERAL DA TURMA: " + mediaGeralTurma + "\n");
								
								mediaGeralTurma = 0;
								qntDisciplinas = 0;
							}
						}
						
						System.out.println("\n@@@@ Disciplina " + nomesDisciplinas[i] + " @@@@");
						
						System.out.println("@@@@ " + a.periodo + "º Período\n");
						
						System.out.println("#### Estudante: " + a.nome);
						quantAlunosDisciplina++;
						
						
						
						nota1 = RecebeNota(in, a.nome, nomesDisciplinas[i], 1);
						
						nota2 = RecebeNota(in, a.nome, nomesDisciplinas[i], 2);
						
						
						mediaAluno = (nota1 + nota2)/2;
						
						System.out.println("\nMédia de " + a.nome + ": " + mediaAluno);
						
						mediaGeralDisciplina += mediaAluno;
						
						
						if(contador == 1) {
							
							maiorMediaDisciplina = mediaAluno;
							menorMediaDisciplina = mediaAluno;
	
						}
						
						
						if(mediaAluno > maiorMediaDisciplina) {
							
							maiorMediaDisciplina = mediaAluno;
						}
						
						if(mediaAluno < menorMediaDisciplina) {
							
							menorMediaDisciplina = mediaAluno;
						}
						
						
						if(mediaAluno < 4) {
							
							System.out.println("Reprovou!");
							quantAlunosReprovados++;
							
						} else if(mediaAluno < 7) {
							
							System.out.println("Vai pra final!");
							quantAlunosFinal++;
							
						} else {
							
							System.out.println("Aprovade!");
							quantAlunosAprovados++;
						}
						
						ultimoAluno = a;
						 
						contador++;
					}
					
				}
			}
			
			if(quantAlunosDisciplina != 0) {
			
				mediaGeralDisciplina /= quantAlunosDisciplina;
				
				System.out.println("\n#### RESUMO DA DISCIPLINA ####");
				System.out.println("DISCIPLINA: " + nomesDisciplinas[i]);
				System.out.println("QTD TOTAL DE ALUNOS: " + quantAlunosDisciplina);
				System.out.println("MÉDIA GERAL DA DISCIPLINA: " + mediaGeralDisciplina);
				System.out.println("MAIOR MÉDIA DA DISCIPLINA: " + maiorMediaDisciplina);
				System.out.println("MENOR MÉDIA DA DISCIPLINA: " + menorMediaDisciplina);
				System.out.println("QTD ALUNOS REPROVADOS: " + quantAlunosReprovados);
				System.out.println("QTD ALUNOS NA FINAL: " + quantAlunosFinal);
				System.out.println("QTD ALUNOS APROVADOS: " + quantAlunosAprovados + "\n");
				
				mediaGeralTurma += mediaGeralDisciplina;
			
				qntDisciplinas++;
			}
			
			
			
			
			
			if(i == nomesDisciplinas.length - 1) {
				
				mediaGeralTurma /= qntDisciplinas;
			
				System.out.println("#### RESUMO DA TURMA ####");
				System.out.println("TURMA: " + ultimoAluno.periodo + "º período");
				System.out.println("QTD DE DISCIPLINAS: " + ultimoAluno.disciplinas.length);
				System.out.println("MÉDIA GERAL DA TURMA: " + mediaGeralTurma + "\n");
			}
			
		}
		
	
	}
	
	else {
		System.out.println("\nNão há estudantes para calcular notas.\n");
        MenuPrincipal(in, alunos);
	}
	
}

public static float RecebeNota(Scanner in, String nome, String nomeDisciplina, int numeroNota) {
	
	float nota = 0;
	
	System.out.println("\nQual a nota " + numeroNota +" de " + nome + " na disciplina " + nomeDisciplina + "?\n");
	
	try {
		nota = in.nextFloat();
       
    } catch (InputMismatchException e){
        System.out.println("\nDigite um número!\n");
        in.next();
        nota = RecebeNota(in, nome, nomeDisciplina, numeroNota);
    }
	
	if(nota > 10) {
		System.out.println("\nNota inválida!\n");
		nota = RecebeNota(in, nome, nomeDisciplina, numeroNota);
	}
	
	return nota;
}


}


  class Disciplinas {
	
	public String[] disciplinasPeriodo1 = {"Lógica de Programação", "Cultura de Jogos Digitais", "Artes para Jogos Digitais"};
	public String[] disciplinasPeriodo2 = {"Oficina de Jogos Analógicos", "Legislação e Ética", "Aplicações para Jogos Digitais"};
	public String[] disciplinasPeriodo3 = {"Projeto Integralizador", "Programação de Jogos"};
	public String[] disciplinasTodas = {"Lógica de Programação", "Cultura de Jogos Digitais", "Artes para Jogos Digitais", "Oficina de Jogos Analógicos", "Legislação e Ética", "Aplicações para Jogos Digitais", "Projeto Integralizador", "Programação de Jogos"};
	
	public Disciplinas() {}
	
	public String[] getDisciplinas() {
		return this.disciplinasTodas;
	}
}







