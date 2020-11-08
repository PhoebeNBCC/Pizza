/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DataBean.ConnectionDL;
import DataBean.EmpDL;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author phoeb
 */
@Named(value = "empBackingBean")
@ViewScoped
public class EmpBackingBean implements Serializable{
    @Inject
    private Employee e;
    @Inject
    private EmpDL bean;

    public EmpBackingBean() {
    }
    

    public Employee getE() {
        return e;
    }
    public void setE(Employee e) {
        this.e = e;
    }
    public String validate(){
        if(bean.isValidate(e)){
            //return "Admin.jsp?faces-redirect=true";
            return "Admin.xhtml";
        }
        else{
            return "";
        }
    }
}
