package crud;

import java.io.IOException;
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

    final static int CREATE = 1;
    final static int READ = 2;
    final static int UPDATE = 3;
    final static int DELETE = 4;
    final static int EXIT = 5;

    static ArrayList<Aluno> alunos = new ArrayList<>();
    static ArrayList<Pessoa> pessoas = new ArrayList<>();

    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        Iterator<Aluno> itrAluno;
        Iterator<Pessoa> itrPessoa;

        boolean found;

        Scanner sc1 = new Scanner(System.in);
        int option = 0;
        while (option != EXIT) {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                Runtime.getRuntime().exec("clear"); 
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            System.out.println("CRUD SIMPLES DO ROMULO RAMOS PEREIRA");
            System.out.println("Selecione uma opcao:");
            System.out.println("[1] Cadastrar uma pessoa/aluno");
            System.out.println("[2] Listar pessoas/alunos cadastrados");
            System.out.println("[3] Atualizar dados de uma pessoa/aluno");
            System.out.println("[4] Deletar uma pessoa/aluno");
            System.out.println("[5] Sair");
            System.out.print("Sua opcao: ");

            option = 0;
            try {
                option = Integer.parseInt(sc1.nextLine());
            } catch (NumberFormatException e) {
            }

            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (IOException | InterruptedException e) {
                Runtime.getRuntime().exec("clear"); 
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }

            switch (option) {
                case CREATE -> {
                    System.out.println("CADASTRAR UMA PESSOA/ALUNO");
                    String name = "";
                    do {
                        System.out.print("Digite o nome: ");
                        name = sc1.nextLine();
                    } while (name.isEmpty());
                    String tel = "";
                    do {
                        System.out.print("Digite o telefone (somente digitos): ");
                        tel = sc1.nextLine();
                    } while (tel.isEmpty());
                    Date birth = new Date();
                    Date now = new Date();
                    while (true) {
                        try {
                            System.out.print("Digite a data de nascimento [dd/mm/aa]: ");
                            String sbirth = sc1.nextLine();
                            birth = birthFormatter.parse(sbirth);
                            break;
                        } catch (ParseException e) {
                        }
                    }
                    String nota = "";
                    while (!("S".equals(nota) || "N".equals(nota))) {
                        System.out.print("Deseja informar nota final [S/N]: ");
                        nota = sc1.nextLine();
                    }
                    if ("S".equals(nota)) {
                        float grade = -1;
                        while (grade == -1) {
                            try {
                                System.out.print("Nota final: ");
                                grade = Float.parseFloat(sc1.nextLine());
                            } catch (NumberFormatException e) {
                            }
                        }
                        Aluno tempAluno = new Aluno(name, tel, birth, now, now, grade);
                        alunos.add(tempAluno);
                    } else {
                        Pessoa tempPessoa = new Pessoa(name, tel, birth, now, now);
                        pessoas.add(tempPessoa);
                    }
                }

                case READ -> {
                    System.out.println("LISTAR PESSOAS/ALUNOS CADASTRADOS");
                    list();
                }
                case UPDATE -> {
                    System.out.println("ATUALIZAR DADOS DE UMA PESSOA/ALUNO");
                    list();
                    found = false;
                    int id = 0;
                    while (true) {
                        try {
                            System.out.print("Digite o ID da pessoa/aluno a atualizar [0 p/ cancelar]: ");
                            id = Integer.parseInt(sc1.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                        }
                    }
                    itrAluno = alunos.iterator();
                    Date now;
                    boolean updated = false;
                    while (itrAluno.hasNext()) {
                        Aluno tempAluno = itrAluno.next();
                        if (id == tempAluno.getId()) {
                            found = true;
                            System.out.print("Digite o novo nome [deixe em branco para nao alterar]: ");
                            String newName = sc1.nextLine();
                            if (!(newName.isEmpty())) {
                                tempAluno.setName(newName);
                                updated = true;
                            }
                            System.out.print("Digite o novo telefone (somente digitos) [deixe em branco para nao alterar]: ");
                            String newTel = sc1.nextLine();
                            if (!(newTel.isEmpty())) {
                                tempAluno.setTel(newTel);
                                updated = true;
                            }
                            Date newBirth = new Date();
                            while (true) {
                                try {
                                    System.out.print("Digite a nova data de nascimento [dd/mm/aa] [deixe em branco para nao alterar]: ");
                                    String newSbirth = sc1.nextLine();
                                    if (newSbirth.isEmpty()) {
                                        break;
                                    }
                                    newBirth = birthFormatter.parse(newSbirth);
                                    tempAluno.setBirth(newBirth);
                                    updated = true;
                                    break;
                                } catch (ParseException e) {
                                }
                            }
                            now = new Date();
                            float newGrade = -1;
                            while (newGrade == -1) {
                                try {
                                    System.out.print("Digite a nova nota final [deixe em branco para nao alterar]: ");
                                    String sgrade = sc1.nextLine();
                                    if (sgrade.isEmpty()) {
                                        break;
                                    }
                                    newGrade = Float.parseFloat(sgrade);
                                    tempAluno.setGrade(newGrade);
                                    updated = true;
                                } catch (NumberFormatException e) {
                                }
                            }
                            if (updated) {
                                System.out.println(tempAluno.getName() + " atualizado com sucesso.");
                                tempAluno.setUpdate(now);
                            } else {
                                System.out.println("Nenhuma alteracao feita.");
                            }
                        }
                    }
                    itrPessoa = pessoas.iterator();
                    while (itrPessoa.hasNext()) {
                        Pessoa tempPessoa = itrPessoa.next();
                        if (id == tempPessoa.getId()) {
                            found = true;
                            System.out.print("Digite o novo nome [deixe em branco para nao alterar]: ");
                            String newName = sc1.nextLine();
                            if (!(newName.isEmpty())) {
                                tempPessoa.setName(newName);
                                updated = true;
                            }
                            System.out.print("Digite o novo telefone (somente digitos) [deixe em branco para nao alterar]: ");
                            String newTel = sc1.nextLine();
                            if (!(newTel.isEmpty())) {
                                tempPessoa.setTel(newTel);
                                updated = true;
                            }
                            Date newBirth = new Date();
                            while (true) {
                                try {
                                    System.out.print("Digite a nova data de nascimento [dd/mm/aa] [deixe em branco para nao alterar]: ");
                                    String newSbirth = sc1.nextLine();
                                    if (newSbirth.isEmpty()) {
                                        break;
                                    }
                                    newBirth = birthFormatter.parse(newSbirth);
                                    tempPessoa.setBirth(newBirth);
                                    updated = true;
                                    break;
                                } catch (ParseException e) {
                                }
                            }
                            now = new Date();
                            if (updated) {
                                System.out.println(tempPessoa.getName() + " atualizado com sucesso.");
                                tempPessoa.setUpdate(now);
                            } else {
                                System.out.println("Nenhuma alteracao feita.");
                            }
                        }
                    }
                    if (!found) {
                        System.out.println("Pessoa/aluno inexistente.");
                    }
                }
                case DELETE -> {
                    System.out.println("DELETAR UMA PESSOA/ALUNO");
                    list();
                    found = false;
                    int id = 0;
                    while (true) {
                        try {
                            System.out.print("Digite o ID da pessoa/aluno a remover [0 p/ cancelar]: ");
                            id = Integer.parseInt(sc1.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                        }
                    }
                    itrAluno = alunos.iterator();
                    while (itrAluno.hasNext()) {
                        Aluno tempAluno = itrAluno.next();
                        if (id == tempAluno.getId()) {
                            itrAluno.remove();
                            found = true;
                        }
                    }
                    itrPessoa = pessoas.iterator();
                    while (itrPessoa.hasNext()) {
                        Pessoa tempPessoa = itrPessoa.next();
                        if (id == tempPessoa.getId()) {
                            itrPessoa.remove();
                            found = true;
                        }
                    }
                    if (found) {
                        System.out.println("Registro " + id + " removido com sucesso.");
                    } else {
                        System.out.println("Pessoa/aluno inexistente.");
                    }
                }
            }
            if (option == EXIT) {
                System.out.println("Obrigado por usar o CRUD.");
            }
            if (option >= CREATE && option <= DELETE) {
                System.out.print("Pressione enter para continuar.");
                sc1.nextLine();
            }
        }
    }

    public static void list() {
        System.out.format("%3s", "ID");
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

    public static void printAluno(Aluno aluno) {
        System.out.format("%3s", aluno.getId());
        System.out.format("%20s", aluno.getName());
        System.out.format("%15s", aluno.getTel());
        System.out.format("%15s", birthFormatter.format(aluno.getBirth()));
        System.out.format("%20s", dateFormatter.format(aluno.getCreate()));
        System.out.format("%20s", dateFormatter.format(aluno.getUpdate()));
        System.out.format("%10.1f", aluno.getGrade());
        System.out.println();
    }

    public static void printPessoa(Pessoa pessoa) {
        System.out.format("%3s", pessoa.getId());
        System.out.format("%20s", pessoa.getName());
        System.out.format("%15s", pessoa.getTel());
        System.out.format("%15s", birthFormatter.format(pessoa.getBirth()));
        System.out.format("%20s", dateFormatter.format(pessoa.getCreate()));
        System.out.format("%20s", dateFormatter.format(pessoa.getUpdate()));
        System.out.println();
    }
}
