/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyuser;
import java.util.Vector;
/**
 *
 * @author trann
 */
public class QLUser {
    private User user[];
    private int n;
    private int MAXN;
    
    public QLUser(int MAXN){
        this.MAXN = MAXN;
        user = new User[MAXN];
        n = 0;
    } 
    
    public void themUser(User u){
        user[n]=u;
        n++;
    }
    
    public void themUser(String t,String mk,String dd,int q ){
        user[n]=new User(t, mk, dd, q);
        n++;
    }
    
    public Vector timKiem(String ten){
        Vector rows=new Vector();
        for(int i=0; i<n;i++)
        if(user[i].laUser(ten))
        rows.add(user[i].hienThiRow());
    return rows;
    }
    
    public Vector timKiem(int quyen){
        Vector rows=new Vector();
        for(int i=0;i<n;i++)
        {
            if(user[i].laUser(quyen))
                rows.add(user[i].hienThiRow());
            }
        return rows;
    }
}
