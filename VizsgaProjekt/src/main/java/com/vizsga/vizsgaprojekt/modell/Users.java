/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.vizsga.vizsgaprojekt.modell;

import java.io.Serializable;
import javax.mail.PasswordAuthentication;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author asd
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByFirstName", query = "SELECT u FROM Users u WHERE u.firstName = :firstName"),
    @NamedQuery(name = "Users.findByLastName", query = "SELECT u FROM Users u WHERE u.lastName = :lastName"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByIsAdmin", query = "SELECT u FROM Users u WHERE u.isAdmin = :isAdmin"),
    @NamedQuery(name = "Users.findByIsDeleted", query = "SELECT u FROM Users u WHERE u.isDeleted = :isDeleted"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByDeletedAt", query = "SELECT u FROM Users u WHERE u.deletedAt = :deletedAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    public static Users getUserById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "is_deleted")
    private boolean isDeleted;
    @Basic(optional = false)
    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.vizsga_VizsgaProjekt_war_1.0-SNAPSHOTPU");
    
    public Users() {
    }

    public Users(Integer id) {
        EntityManager em = emf.createEntityManager();
        
        try {
            Users u = em.find(Users.class, id);
            
            this.id = u.getId();
            this.email = u.getEmail();
            this.firstName = u.getFirstName();
            this.lastName = u.getLastName();
            this.password = u.getPassword();
            this.isAdmin = u.getIsAdmin();
            this.isDeleted = u.getIsDeleted();
            this.createdAt = u.getCreatedAt();
            this.deletedAt = u.getDeletedAt();
            
        } catch (Exception e) {
            System.err.println("Hiba: " + e.getLocalizedMessage());
        }finally{
            em.clear();
            em.close();
        }
    }

    public Users(Integer id, String email, String firstName, String lastName, String password, boolean isAdmin, boolean isDeleted, Date createdAt, Date deletedAt) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isDeleted = isDeleted;
        this.createdAt = createdAt;
        this.deletedAt = deletedAt;
    }
    
    public Users(String email, String firsName, String lastName, String password){
        this.email = email;
        this.firstName = firsName;
        this.lastName = lastName;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.vizsga.vizsgaprojekt.modell.Users[ id=" + id + " ]";
    }
    
    public Users login(String email, String password){
        EntityManager em = emf.createEntityManager();
        
        try {
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("login");
            
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIN", email);
            spq.setParameter("passwordIN", password);
            
            spq.execute();
            
            List<Object[]> resultList = spq.getResultList();
            Users toReturn = new Users();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(Object[] o : resultList){
                Users u = new Users(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        o[2].toString(),
                        o[3].toString(),
                        o[4].toString(),
                        Boolean.parseBoolean(o[5].toString()),
                        Boolean.parseBoolean(o[6].toString()),
                        formatter.parse(o[7].toString()),
                        o[8] == null ? null : formatter.parse(o[8].toString())
                );
                return u;
            }
            return  toReturn;
            
        } catch (Exception e) {
            System.err.println("Hiba: "+ e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    public static Boolean isUserExists(String email){
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("isUserExists");

            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("resultOUT", Boolean.class, ParameterMode.OUT);

            spq.setParameter("emailIN", email);

            spq.execute();

            Boolean result = Boolean.valueOf(spq.getOutputParameterValue("resultOUT").toString());
            return result;

        }catch(Exception e){
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    public static Users getUserById(Integer id){
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getUserById");
            
            spq.registerStoredProcedureParameter("userIdIN", Integer.class, ParameterMode.IN);
            spq.setParameter("userIdIN", id);
            
            List<Object[]> resultList = spq.getResultList();
            
            if(resultList.isEmpty()){
                return null; //Nincs ilyen id felhasználó
            }
            
            Users toReturn = new Users();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for(Object[] o : resultList){
                Users u = new Users(
                        Integer.valueOf(o[0].toString()),
                        o[1].toString(),
                        o[2].toString(),
                        o[3].toString(),
                        o[4].toString(),
                        Boolean.parseBoolean(o[5].toString()),
                        Boolean.parseBoolean(o[6].toString()),
                        formatter.parse(o[7].toString()),
                        o[8] == null ? null : formatter.parse(o[8].toString())
                );
                return u;
            }
            return  toReturn;
            
        }  catch (Exception e) {
            System.err.println("Hiba: "+ e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
        
    }
    
    public Boolean registerAdmin(Users u){
        EntityManager em = emf.createEntityManager();
        
        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerAdmin");
            
            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("firstNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lastNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);
            
            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("firstNameIN", u.getFirstName());
            spq.setParameter("lastNameIN", u.getLastName());
            spq.setParameter("passwordIN", u.getPassword());
            
            spq.execute();
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba: "+ e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    
    public Boolean registerUser(Users u){
        EntityManager em = emf.createEntityManager();

        try {
            StoredProcedureQuery spq = em.createStoredProcedureQuery("registerUser");

            spq.registerStoredProcedureParameter("emailIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("firstNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("lastNameIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("passwordIN", String.class, ParameterMode.IN);

            spq.setParameter("emailIN", u.getEmail());
            spq.setParameter("firstNameIN", u.getFirstName());
            spq.setParameter("lastNameIN", u.getLastName());
            spq.setParameter("passwordIN", u.getPassword());

            spq.execute();

            return true;

        }catch(Exception e){
            System.err.println("Hiba: " + e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    public List<Users> getAllUser(){
        EntityManager em = emf.createEntityManager();
        
        try {
            
            StoredProcedureQuery spq = em.createStoredProcedureQuery("getAllUser");
            
            spq.execute();
            
            List<Users> toReturn = new ArrayList();
            List<Object[]> resultList = spq.getResultList();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            for(Object[] record : resultList){
                 Users u = new Users(
                        Integer.valueOf(record[0].toString()),
                        record[1].toString(),
                        record[2].toString(),
                        record[3].toString(),
                        record[4].toString(),
                        Boolean.parseBoolean(record[5].toString()),
                        Boolean.parseBoolean(record[6].toString()),
                        formatter.parse(record[7].toString()),
                        record[8] == null ? null : formatter.parse(record[8].toString())
                );
                toReturn.add(u);
            }
            return  toReturn;
            
        } catch (Exception e) {
            System.err.println("Hiba: "+ e.getLocalizedMessage());
            return null;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    public Boolean changePassword(Integer userId, String newPassword, Integer creator){
        EntityManager em = emf.createEntityManager();
        
        try{
            StoredProcedureQuery spq = em.createStoredProcedureQuery("changePassword");
            
            spq.registerStoredProcedureParameter("userIdIN ", Integer.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("newPasswordIN", String.class, ParameterMode.IN);
            spq.registerStoredProcedureParameter("creatorIN", Integer.class, ParameterMode.IN);
            
            spq.setParameter("userIdIN", userId);
            spq.setParameter("newPasswordIN", newPassword);
            spq.setParameter("creatorIN", creator);
        
            spq.execute();
            
            return true;
        }catch(Exception e){
            System.err.println("Hiba"+e.getLocalizedMessage());
            return false;
        }finally{
            em.clear();
            em.close();
        }
    }
    
    public static Boolean sendEmail(String to, boolean ccMe){
        try {
            //Emailt küldő email címe és alkalmazás jelszó
            final String from = "simonkincso2002@gmail.com";
            //Google alkalmazás jelszó netneans
            final String passwordApp = "bpip gaqn mfoq tako";
            
            //Tulajdonságok beállítása
            String host = "smtp.gmail.com";

            Properties properties = System.getProperties();

            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");
            
            //Application password beállítása az email címhez és session config
            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, passwordApp);
                }
            });
            session.setDebug(true);
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            
            //Több címzet
            if(ccMe){
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(from));
            }
            
            message.setSubject("Teszt email");
            
            String msg = "Email tartalma, lehet naggyon hosszu is, lehet html is.";
            message.setContent(msg, "text/html;charset=utf-8");
            
            Transport.send(message);
            
            return true;
        } catch (Exception e) {
            System.err.println("Hiba:" + e.getLocalizedMessage());
            return false;
        }
    }
    
}
