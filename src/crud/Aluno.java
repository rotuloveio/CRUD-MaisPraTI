package crud;

import java.util.Date;

/**
 *
 * @author Rômulo
 */
public class Aluno extends Pessoa {
    private float grade;

    public Aluno(String name) {
        super(name);
    }
    
    public Aluno(String name, String tel, Date birth, Date create, Date update, float grade) {
        super(name, tel, birth, create, update);
        this.grade = grade;
    }
    
    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }
    
}
