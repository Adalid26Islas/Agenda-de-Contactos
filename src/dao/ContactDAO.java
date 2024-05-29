package dao;
import entities.Contact;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

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
}
