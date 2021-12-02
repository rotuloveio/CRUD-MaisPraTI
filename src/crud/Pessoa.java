package crud;

import java.util.Date;

/**
 *
 * @author Rômulo
 */
public class Pessoa {
    private String name;
    private String tel;
    private Date birth;
    private Date create;
    private Date update;
    private int id;

    static int ID = 0;
    
    public Pessoa(String name) {
        this.name = name;
    }
    
    public Pessoa(String name, String tel, Date birth, Date create, Date update) {
        this.name = name;
        this.tel = tel;
        this.birth = birth;
        this.create = create;
        this.update = update;
        this.id = ++ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public Date getUpdate() {
        return update;
    }

    public void setUpdate(Date update) {
        this.update = update;
    }
    
}
