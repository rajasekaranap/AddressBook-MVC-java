/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import mvc.models.Person;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import mvc.models.Person;
import mvc.views.ContactDialog;
//import mvc.views.EditContactDialog;
import mvc.views.AddressBookMainGUI;
import mvc.views.DetailViewPanel;
//import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import mvc.dao.AddressBookDAOImplementation;
import mvc.views.NameListPanel;
//import mvc.util.DBConnection;
/**
 *
 * @author Admin
 */
public class AddressBookController {
    
    //Connection con = 
    
   // private Person person;// = new Person();
    private AddressBookMainGUI view;
    private ActionListener actionListener;
    private ActionListener choiceListener;
    private ContactDialog dialog;
   // private EditContactDialog edit;
    private AddressBookDAOImplementation daoimplement;
    private NameListPanel nlp;
    private DetailViewPanel detailPanel;
    private DetailViewPanel contactDetailsPanel;
    
    public AddressBookController(){
         daoimplement = new AddressBookDAOImplementation();
       
System.out.println("going to call GUI");
        view =new AddressBookMainGUI("View");
    
System.out.println("after Main GUI");
        
      
    }
     
    
     
    
     
    public void control(){
        loadusers();
        actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== view.getAdd())
                {
                       
                    // view.getMainGUIFrame().setEnabled(false);
                    view.getMainGUIFrame().disable();
                        openAdd();
                        view.getMainGUIFrame().enable();
                       // view.getMainGUIFrame().setEnabled(true);
//view.getMainGUIFrame().requestFocus();
                        
                   // loadusers();
                }
                else if(e.getSource()== view.getEdit())
                    openEdit();
                else if(e.getSource() == view.getDelete())
                    openDelete();
            }
        };
        
        view.getAdd().addActionListener(actionListener);
        view.getDelete().addActionListener(actionListener);
        view.getEdit().addActionListener(actionListener);
      // loadusers();
    }
    
    
    private void openAdd(){        
         dialog =  new ContactDialog("New Entries");
         /*dialog.getSubmitButton().setEnabled(false);
         contactDetailsPanel = dialog.getPanel();*/
           //dialog.getSubmitButton().setEnabled(false);
            
                    Boolean flag = false;
             /*String name = contactDetailsPanel.getNameField().getText();
             String mobile = contactDetailsPanel.getMobileField().getText();
             String email = contactDetailsPanel.geteMailField().getText();  */
         /*if(validate())
                dialog.getSubmitButton().setEnabled(true);*/
        
        choiceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== dialog.getSubmitButton())
                {
        contactDetailsPanel=dialog.getPanel();
                    //callDAO("Add");
                    Boolean flag = false;
             String name = contactDetailsPanel.getNameField().getText();
             String mobile = contactDetailsPanel.getMobileField().getText();
             String email = contactDetailsPanel.geteMailField().getText();   
             flag = validate();
             if(flag)
             {    
             Person person = new Person();
             person.setData(name,mobile,email);
             daoimplement.addPerson(person);
             dialog.getFrame().dispose();
             loadusers();
              }
                   
                }
                else if(e.getSource()== dialog.getcancelButton())
                {
                    dialog.getFrame().dispose();
                }   
            }
            
        };
        
       
        
         dialog.getSubmitButton().addActionListener(choiceListener);
         dialog.getcancelButton().addActionListener(choiceListener);
    }  //method openAdd ends 
    
     public void callDAO(String operation)
        {
            /*contactDetailsPanel = dialog.getPanel();
           // dialog.getSubmitButton().setEnabled(false);
            
                    Boolean flag = false;*/
             contactDetailsPanel=dialog.getPanel();
             String name = contactDetailsPanel.getNameField().getText();
             String mobile = contactDetailsPanel.getMobileField().getText();
             String email = contactDetailsPanel.geteMailField().getText();  
             Boolean flag = validate();
             if(flag)
                 //System.out.println("valid");
                 //dialog.getSubmitButton().setEnabled(true);
               // dialog.getFrame().dispose();
             {  
             Person person = new Person();
             person.setData(name,mobile,email);
             if(operation.equals("Add"))
             {
              daoimplement.addPerson(person);
              dialog.getFrame().dispose();
              
             }
              else
             {
                 daoimplement.updatePerson(person,operation);
                    dialog.getFrame().dispose();
             }
             }
              loadusers();
        }
     
   
    public Boolean validate(){
        boolean valid = false;
        String name = contactDetailsPanel.getNameField().getText();
        String mobile = contactDetailsPanel.getMobileField().getText();
        String email = contactDetailsPanel.geteMailField().getText();
        if (name==null||name.equals("")||(mobile==null||mobile.equals("")))
            JOptionPane.showMessageDialog(new JFrame(), "Fields Marked as * are Mandatory","Inane error", JOptionPane.ERROR_MESSAGE);
        else 
            valid = true;
        System.out.println("valid is "+ valid);
        return valid;
                /*else if(mobile==null||mobile.equals(""))
                    JOptionPane.showMessageDialog(frame, "Mobile No.  Reqired");*/           
        //}
}
     
    private void openEdit(){
       // edit=new EditContactDialog("Edit");
        dialog =  new ContactDialog("Update Entries");
        final String name=detailPanel.getNameField().getText();
        System.out.println(detailPanel.getNameField().getText());
        dialog.getPanel().setName(detailPanel.getNameField().getText());
        dialog.getPanel().setMobile(detailPanel.getMobileField().getText());
        dialog.getPanel().seteMail(detailPanel.geteMailField().getText());
        choiceListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()== dialog.getSubmitButton())
                {
                   // System.out.println("Edit submit pressed");
                    callDAO(name);
                    //callEditDAO
                    //invokeAddPersonInDAO();
                    /*Boolean flag = false;
                    
                    //detailPanel.getNameField().getText();
                    
             String name = add.getNameField().getText();
             String mobile = add.getMobileField().getText();
             String email = add.geteMailField().getText();              
             Person person = new Person();
             person.setData(name,mobile,email);
              daoimplement.addPerson(person);*/
              //dialog.getFrame().dispose();
                   
                }
                else if(e.getSource()== dialog.getcancelButton())
                {
                    dialog.getFrame().dispose();
                }   
            }
        };
        
       
        
         dialog.getSubmitButton().addActionListener(choiceListener);
         dialog.getcancelButton().addActionListener(choiceListener);
         
    
      /*Person person = add.sendPerson();*/
        System.out.println("In controller  openEdit ");
    }
        
    private void openDelete(){
        
        final String name=detailPanel.getNameField().getText();
        int reply = JOptionPane.showConfirmDialog(
                null,
                name +" will be Deleted permanently", 
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
        
        if (reply == JOptionPane.YES_OPTION) {
          //System.out.println("selected yes");
          daoimplement.removePerson(name);
          loadusers();
        }
       
    }
    
    public void loadusers()
            
    {
       // System.out.println("Starting loadusers");
        nlp = view.getNameListPanel();
        daoimplement.getAllNames(nlp);
        nlp.getJList().setSelectedIndex(0);
        //System.out.println(nlp.getJList().getSelectedValue() != null);
        //System.out.println("Before if");
        if(nlp.getJList().getSelectedValue() != null)
        {  
        String selectedName = nlp.getJList().getSelectedValue().toString();
        detailPanel=view.getDetailViewPanel();
        daoimplement.getSelectedName(detailPanel,selectedName);
        nlp.getJList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nlp.getJList().addListSelectionListener(new ListSelectionListener()
                {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                    if (!event.getValueIsAdjusting()){
                    JList source = (JList)event.getSource();
                    
//                    System.out.println("in value changed listselectionEvent "+source.getSelectedValue().toString());
                  //  if(source.getSelectedValue()== null)
                  
                     if(source.getSelectedIndex() == -1)
                         source.setSelectedIndex(0);
                    String select = source.getSelectedValue().toString();
          //          System.out.println("in controller - loadUsers value is "+ select);
                    getSelectedUser(select);
                    
                    }
                    }
                    });  
         //detailPanel.getNameField().setEnabled(false);//
         
         detailPanel.getNameField().setEditable(false);
         detailPanel.getMobileField().setEditable(false);
         detailPanel.geteMailField().setEditable(false);
    }
    }
    public void getSelectedUser(String selectedName)
    {
        detailPanel=view.getDetailViewPanel();
        daoimplement.getSelectedName(detailPanel,selectedName);
       // detailPanel.getNameField().setEnabled(false);
    }
            
    private void linkBtnandLabel(){
       // view.setText(Integer.toString(model.getX()));        //model.incX();

    }
    
    private void linkBtnandLabel2(){
      //  model.decX();
       // view.setText(Integer.toString(model.getX()));
    }
}
