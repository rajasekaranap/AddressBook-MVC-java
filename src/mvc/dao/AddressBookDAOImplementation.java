/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.dao;

import mvc.models.Person;
import mvc.util.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import mvc.views.DetailViewPanel;
import mvc.views.NameListPanel;

/**
 *
 * @author Admin
 */
public class AddressBookDAOImplementation implements AddressBookDAO{
   // DetailViewPanel detailview;
    NameListPanel list;
    Connection conn;
    Statement st;
    public AddressBookDAOImplementation ()
    {
        conn= DBConnection.getInstance().getConnect();
        //getAllNames();
        
    }
    
    @Override
    public void addPerson(Person person) 
    {   
       try
       {
           st = conn.createStatement();
           System.out.println("in DAOImplement addPerson");
           // Statement st = conn.createStatement();
           String qry;
           qry = "insert into AddressBook values ('"+person.getName()+"','"+person.getMob()+"','"+person.getEmail()+"')";
           st.executeUpdate(qry);
           //System.out.println("back to caller");
        }
            
       catch(Exception e)
       {
           e.printStackTrace();
       }
           
    }
    
     public void removePerson(String name)
     {
         try
         {
           st = conn.createStatement();
           String qry="delete from AddressBook where name='"+name+"'";
           st.executeUpdate(qry);
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
     }
    
    @Override
     public void updatePerson(Person person,String name)
     {
         try
       {
           st = conn.createStatement();
           System.out.println("in DAOImplement addPerson");
           // Statement st = conn.createStatement();
            String qry="update AddressBook set name='"+person.getName()+"',mob='"+person.getMob()+"',email='"+person.getEmail()+"' where name='"+name+"'";
           //qry = "insert into AddressBook values ('"+person.getName()+"','"+person.getMob()+"','"+person.getEmail()+"')";
           st.executeUpdate(qry);
           System.out.println("in Update person to be created");
        }
            
       catch(Exception e)
       {
           e.printStackTrace();
       }
     }
    
    /**
     *to display all names to select
     */
    @Override
    public DefaultListModel getAllNames(NameListPanel namePanel)
    {
             DefaultListModel<String> dlm = new DefaultListModel<String>();
            try {
         //conn = DBConnection.getInstance().getConnect();
            Statement st = conn.createStatement();
            //String countqry = "select COUNT(*) from AddressBook";
           /* ResultSet rc = st.executeQuery(countqry);
            System.out.println(rc.getInt(1));
            System.out.println(st.executeQuery(countqry));*/
            String qry = "select * from AddressBook order by name asc";
            ResultSet rs = st.executeQuery(qry);
            //getting all the name in the arrayList
            while (rs.next()){
                String name = rs.getString(1);
               dlm.addElement(name);
            }
             
            namePanel.getJList().setModel(dlm);
           // conn.close();
        }
           catch (SQLException ex) {
                   System.out.println("NO Records/Cannot retrieve records");
                   }
        return dlm;
    }
    
    
    @Override
    public void getSelectedName(DetailViewPanel detailPanel,String selectedName)
    {
        try {
           //  conn = DBConnection.getInstance().getConnect();
            Statement st = conn.createStatement();
            String qry = "select * from AddressBook where name = '"+selectedName+"'";
            System.out.println(qry);
            ResultSet rs = st.executeQuery(qry);
            while(rs.next()){
            //geteMailField()
             detailPanel.setName(rs.getString("name"));
             detailPanel.seteMail(rs.getString("email"));
             detailPanel.setMobile(rs.getString("mob"));
             
           }
            //conn.close();
        }
         catch (SQLException ex) {
                   System.out.println("NO Records/Cannot retrieve records");
                   }
    }
           
    
}
