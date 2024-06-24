package dao;
import entities.Contact;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactDAO {
    private Hashtable<String,Contact>contacts;
    //Un constructor es un metodo publico que se llama igual que la clase
    public ContactDAO(){
        contacts=new Hashtable();
        
    }
    //Metodo para guardar el contacto
    public boolean saveContact(Contact contact){
        boolean result=false;
        contacts.put(contact.getId(), contact);
        result=true;
        return result;
    }
    public boolean existsContact(String id){
        return contacts.containsKey(id);
    } 
    
    //Metodo para buscar el contacto por Id
    public Contact getContactById(String id){
        return contacts.get(id);
    }
    
    //Metodo para obtener todos los contactos
    public ArrayList<Contact>getContacts(){
        ArrayList<Contact> contactsList=new ArrayList();
        //Extraemos las llaves con el metodo keys que crea un nuevo objeto con las llaves
        Enumeration keysList = contacts.keys();
        //Verificar si hay llaves
        while(keysList.hasMoreElements()){
            //Recorre las llaves si es que el ciclo devuelve true
            String id=(String)keysList.nextElement();
            //Obtiene el contacto y lo agrega al Array
            contactsList.add(contacts.get(id));
        }
        return contactsList;
    }
    
    public ArrayList<Contact>getContactsByName(String name){
        ArrayList<Contact> contactsListName=new ArrayList();
        Enumeration keysList = contacts.keys();
        while(keysList.hasMoreElements()){
            String id=(String)keysList.nextElement();
            Contact contact=contacts.get(id);
            if(contact.getName().equals(name)){
                contactsListName.add(contact);
            }
        }
        return contactsListName;
    }
    
    public boolean deleteContact(String id){
        boolean result = false;
        contacts.remove(id);
        result = true;
        return result;
    }
    
    //Metodos para guardad información en el disco duro
    //Verificar que exista el archivo
    public boolean fileExists(String filename){
        File f=new File(filename);
        return f.exists();
    }
    //Crear el archivo
    public boolean createFile(String filename){
        boolean result = false;
        File f=new File(filename);
        try {
            result = f.createNewFile();
        } catch (IOException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    //Guardar el archivo en el disco Duro
    public void saveChanges(String filename) {
       FileOutputStream fos=null;
        try {
            fos = new FileOutputStream(filename);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(contacts);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void readContacts(String filename){
        try {
            FileInputStream fis = new FileInputStream(filename);
            ObjectInputStream ois=new ObjectInputStream(fis);
            contacts=(Hashtable)ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ContactDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
