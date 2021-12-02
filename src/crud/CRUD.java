package crud;

import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Rômulo
 */
public class CRUD {

    static SimpleDateFormat birthFormatter = new SimpleDateFormat("dd/MM/yy");
    static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");

    public static void main(String[] args) throws ParseException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Pessoa> pessoas = new ArrayList<>();

        Iterator<Aluno> itrAluno;
        Iterator<Pessoa> itrPessoa;

        boolean found;

        Scanner sc1 = new Scanner(System.in);
        int option = 0;
        while (option != 5) {
            System.out.println("\nCRUD simples do Romulo Ramos Pereira");
            System.out.println("Selecione uma opcao:");
            System.out.println("[1] Cadastrar uma pessoa/aluno");
            System.out.println("[2] Listar pessoas/alunos cadastrados");
            System.out.println("[3] Atualizar dados de uma pessoa/aluno");
            System.out.println("[4] Deletar uma pessoa/aluno");
            System.out.println("[5] Sair");
            System.out.print("Sua opcao: ");

            try {
                option = Integer.parseInt(sc1.nextLine());
            } catch (Exception e) {}

            switch (option) {
                case 1 -> {
                    System.out.print("Digite o nome: ");
                    String name = sc1.nextLine();
                    System.out.print("Digite o telefone (somente dígitos): ");
                    String tel = sc1.nextLine();
                    System.out.print("Digite a data de nascimento [dd/mm/aa]: ");
                    String sbirth = sc1.nextLine();
                    Date birth = birthFormatter.parse(sbirth);
                    Date now = new Date();
                    System.out.print("Deseja informar nota final [S/N]: ");
                    String nota = sc1.nextLine();
                    if ("S".equals(nota)) {
                        
                        float grade = -1;
                        while (grade == -1) {
                            try {
                                System.out.print("Nota final: ");
                                grade = Float.parseFloat(sc1.nextLine());
                            } catch (Exception e) {}
                        }
                        Aluno tempAluno = new Aluno(name, tel, birth, now, now, grade);
                        alunos.add(tempAluno);
                    } else {
                        Pessoa tempPessoa = new Pessoa(name, tel, birth, now, now);
                        pessoas.add(tempPessoa);
                    }
                }

                case 2 -> {
                    System.out.format("%20s", "NOME");
                    System.out.format("%15s", "TELEFONE");
                    System.out.format("%15s", "NASCIMENTO");
                    System.out.format("%20s", "CRIADO EM");
                    System.out.format("%20s", "ATUALIZADO EM");
                    System.out.format("%10s", "NOTA");
                    System.out.println();
                    alunos.forEach((aluno) -> printAluno(aluno));
                    pessoas.forEach((pessoa) -> printPessoa(pessoa));
                }
                case 3 -> {
                    found = false;
                    System.out.print("Digite o nome da pessoa/aluno a atualizar: ");
                    String name = sc1.nextLine();
                    itrAluno = alunos.iterator();
                    Date now;
                    while (itrAluno.hasNext()) {
                        Aluno tempAluno = itrAluno.next();
                        if (name.equals(tempAluno.getName())) {
                            found = true;
                            System.out.print("Digite o novo nome: ");
                            String newName = sc1.nextLine();
                            System.out.print("Digite o novo telefone (somente digitos): ");
                            String newTel = sc1.nextLine();
                            System.out.print("Digite a nova data de nascimento [dd/mm/aa]: ");
                            String newSbirth = sc1.nextLine();
                            Date newBirth = birthFormatter.parse(newSbirth);
                            now = new Date();
                            System.out.print("Digite a nova nota final: ");
                            float newGrade = Float.parseFloat(sc1.nextLine());
                            tempAluno.setName(newName);
                            tempAluno.setTel(newTel);
                            tempAluno.setBirth(newBirth);
                            tempAluno.setUpdate(now);
                            tempAluno.setGrade(newGrade);
                        }
                        itrPessoa = pessoas.iterator();
                        while (itrPessoa.hasNext()) {
                            Pessoa tempPessoa = itrPessoa.next();
                            if (name.equals(tempPessoa.getName())) {
                                found = true;
                                System.out.print("Digite o novo nome: ");
                                String newName = sc1.nextLine();
                                System.out.print("Digite o novo telefone (somente digitos): ");
                                String newTel = sc1.nextLine();
                                System.out.print("Digite a nova data de nascimento [dd/mm/aa]: ");
                                String newSbirth = sc1.nextLine();
                                Date newBirth = birthFormatter.parse(newSbirth);
                                now = new Date();
                                tempPessoa.setName(newName);
                                tempPessoa.setTel(newTel);
                                tempPessoa.setBirth(newBirth);
                                tempPessoa.setUpdate(now);
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Pessoa/aluno inexistente.");
                    }
                }
                case 4 -> {
                    found = false;
                    System.out.print("Digite o nome da pessoa/aluno a remover: ");
                    String name = sc1.nextLine();
                    itrAluno = alunos.iterator();
                    while (itrAluno.hasNext()) {
                        Aluno tempAluno = itrAluno.next();
                        if (name.equals(tempAluno.getName())) {
                            itrAluno.remove();
                            found = true;
                        }
                    }
                    itrPessoa = pessoas.iterator();
                    while (itrPessoa.hasNext()) {
                        Pessoa tempPessoa = itrPessoa.next();
                        if (name.equals(tempPessoa.getName())) {
                            itrPessoa.remove();
                            found = true;
                        }
                    }
                    if (found) {
                        System.out.println(name + " removido com sucesso.");
                    } else {
                        System.out.println("Pessoa/aluno inexistente.");
                    }
                }
            }
        }
    }

    public static void printAluno(Aluno aluno) {
        System.out.format("%20s", aluno.getName());
        System.out.format("%15s", aluno.getTel());
        System.out.format("%15s", birthFormatter.format(aluno.getBirth()));
        System.out.format("%20s", dateFormatter.format(aluno.getCreate()));
        System.out.format("%20s", dateFormatter.format(aluno.getUpdate()));
        System.out.format("%10.1f", aluno.getGrade());
        System.out.println();
    }

    public static void printPessoa(Pessoa pessoa) {
        System.out.format("%20s", pessoa.getName());
        System.out.format("%15s", pessoa.getTel());
        System.out.format("%15s", birthFormatter.format(pessoa.getBirth()));
        System.out.format("%20s", dateFormatter.format(pessoa.getCreate()));
        System.out.format("%20s", dateFormatter.format(pessoa.getUpdate()));
        System.out.println();
    }
}
