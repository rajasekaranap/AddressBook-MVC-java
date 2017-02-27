/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.views;
//import javax.swing.JOptionPane;
// vo object class
import mvc.models.Person;

// for GUI
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import mvc.controller.AddressBookController;

/**
 *
 * @author Admin
 */
public class ContactDialog {//implements ActionListener{
    private JFrame frame;
    private DetailViewPanel detailsPanel;
    final private JButton submitButton;
    private JButton cancelButton;
    private JPanel panel_btn;
   private Person  person;
   private AddressBookController controller;

    
    public ContactDialog(String name){
        frame = new JFrame(name);
        frame.getContentPane().setLayout(new BorderLayout());
        //frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
         frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(350, 250);
       frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        detailsPanel = new DetailViewPanel();
        frame.getContentPane().add(detailsPanel,BorderLayout.CENTER);
        
        panel_btn= new JPanel();
        panel_btn.setLayout(new GridLayout(1, 2));
        
        submitButton = new JButton("Submit");
        panel_btn.add(submitButton);
        
        cancelButton = new JButton("Cancel");
        panel_btn.add(cancelButton);
        
        frame.getContentPane().add(panel_btn,BorderLayout.SOUTH);
        /*
        submitButton.addActionListener(this);
        cancelButton .addActionListener(this);
        */
      //  controller = new subController();
        
    }
    
   
    
    public JButton getSubmitButton()
            {
                return submitButton;
            }
    
    
     public JButton getcancelButton()
            {
                return cancelButton;
            }
    public JFrame getFrame()
    {
        return frame;
    }
   
    /**
     *
     * @return
     */
    public DetailViewPanel getPanel()
    {
    //detailsPanel.getNameField();
        return detailsPanel;
    }
  /* public JComponent getComponent()
   {
       return detailsPanel;
   }*/
    /*
    public void actionPerformed(ActionEvent ae){
        person  = new Person();
        if(ae.getSource()==submitButton){
            
            getValues();
            //String t = detailsPanel.getComponent().getNameField().getText();
             Boolean flag = false;
             String name = detailsPanel.getNameField().getText();
             String mobile = detailsPanel.getMobileField().getText();
             String email = detailsPanel.geteMailField().getText();   
             System.out.println("name in add is "+name);
             System.out.println("name in add from panel  "+detailsPanel.getNameField().getText());
             flag = validate(); 
             System.out.print("flag is"+flag);
             //person = new Person();
             person.setData(name,mobile,email);
            if(flag)
            {
                frame.dispose();
            }
          // controller = new AddressBookController(person, "Add");
        }
        if (ae.getSource()==cancelButton)
        {
         //   System.out.println("pressed cancel)");
            frame.dispose();
        }
       
        
    };
    */
    public void getValues()
    {
        System.out.println("name from getValues"+detailsPanel.getNameField().getText());
    }
    public Person getPerson()
    {
        return person;
    }

    /**
     *validates that mobile field and name are not empty
     * @return
     */
    public Boolean validate(){
        boolean valid = false;
        String name = detailsPanel.getNameField().getText();
        String mobile = detailsPanel.getMobileField().getText();
        String email = detailsPanel.geteMailField().getText();
        if (name==null||name.equals("")||(mobile==null||mobile.equals("")))
            JOptionPane.showMessageDialog(frame, "Fields Marked as * are Mandatory");
        else 
            valid = true;
        return valid;
                /*else if(mobile==null||mobile.equals(""))
                    JOptionPane.showMessageDialog(frame, "Mobile No.  Reqired");*/           
        //}
}

}