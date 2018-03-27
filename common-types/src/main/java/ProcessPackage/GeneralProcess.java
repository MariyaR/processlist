package ProcessPackage;

import org.hibernate.HibernateException;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.type.StringType;
import org.hibernate.type.descriptor.sql.BitTypeDescriptor;
import org.hibernate.usertype.UserType;
import org.jboss.logging.Logger;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;



@Entity
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "CacheForProcessList")
@Table(name = "process")
public class GeneralProcess implements Serializable {

    @Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "processname")
    private String Name;

    @Column(name = "analogname")
    private String AnalogName;
    @Enumerated(EnumType.STRING)
    @Column(name = "projecttopic")
    private Topic ProjectTopic;
    @Column(name = "operatorcomment")
    private String OperatorComment;
    @Enumerated(EnumType.STRING)
    @Column(name = "substrate")
    private Substrates Substrate;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "generalProcess")
    @OrderBy(value = "LayerId")
    private List <GeneralLayer> Structure = new ArrayList<GeneralLayer>();
    @Transient
    private List <ProcessError> Errors= new ArrayList<ProcessError>();
    @Transient
    private String EmptyString= "                                                    ";
    @Transient
    private StringBuffer st= new StringBuffer();

    public GeneralProcess() {}

    public String getName() {return Name;}
    public void setName (String s) {Name=s;}

    public void setAnalogName(String analog) {
        AnalogName = analog;
    }

    public void setProjectTopic (Topic s) {ProjectTopic=s;}
    public void setOperatorComment (String s) {OperatorComment=s;}
    public void setSubstrate (Substrates s) {Substrate=s;}
    public void setStructure (List<GeneralLayer> l) {Structure=l;}
    public void setErrors (List<ProcessError> l) {Errors=l;}



    public String getAnalogName() {
        return AnalogName;
    }

    public Topic getProjectTopic() { return ProjectTopic; }
    public String getOperatorComment() {
        return OperatorComment;
    }
    public Substrates getSubstrate() {
        return Substrate;
    }
    public List<GeneralLayer> getStructure() {
        return Structure;
    }
    public List<ProcessError> getErrors() {
        return Errors;
    }



//    public int lastIndexOf(Functions f) {
//        ListIterator<GeneralLayer> it = this.Structure.listIterator();
//        int i=-1;
//        while (it.hasNext()) {
//            if (it.next().getFunction().equals(f)) {
//                i=it.previousIndex();
//            }
//        }
//    return i;
//    }

    //public abstract void theOnlyAbstractMethod(); //the place for other methods

    @Override
    public String toString () {
        StringBuffer s=new StringBuffer();
        s.append("---------------------------------------------------------------"+"\n");
        s.append("Process name:    "+Name+"\n");
        s.append("ProjectTopic:    "+ProjectTopic+"\n"+"Substrate:       "+Substrate+"\n");
        s.append("Errors:          " +Errors.toString()+"\n");
        s.append("Operator Comment: "+OperatorComment+"\n");
        s.append("---------------------------------------------------------------"+"\n");
        s.append(this.StructureToString());
        return s.toString();
    }

    public void addLayer (GeneralLayer l) {
        this.Structure.add(l);
    }

    public String StructureToString () {

        ListIterator<GeneralLayer> It = this.Structure.listIterator();
        StringBuffer s = new StringBuffer();

        while (It.hasNext()) {
            s.append(It.next().toString());
        }
        return s.toString();
    }

//    abstract void firstAbstractMethod();

}


