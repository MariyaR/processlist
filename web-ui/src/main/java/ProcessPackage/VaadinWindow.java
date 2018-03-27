package ProcessPackage;

import com.vaadin.annotations.Theme;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.data.Binder;
import com.vaadin.ui.components.grid.HeaderCell;
import com.vaadin.ui.components.grid.HeaderRow;
import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.viritin.grid.MGrid;

import javax.persistence.PersistenceException;

import java.util.regex.Pattern;



@Theme("valo")
public class VaadinWindow extends UI {

    private ProcessService service = new ProcessService();
    private Logger log = LoggerFactory.getLogger(VaadinWindow.class);
    private Grid<GeneralProcess> grid = new MGrid<>(GeneralProcess.class);
    private final Binder<GeneralProcess> binder = new Binder<>();//todo why final???
    private Binder<GeneralLayer> layerBinder=new Binder<>();
    private GeneralProcess process= new GeneralProcess();
    private GeneralProcess process1=new GeneralProcess();
    private GeneralProcess process2=new GeneralProcess();
    private TextField ProcessNameTf = new TextField();
    private TextField ProjectTopicTf = new TextField();
    private TextField AnalogNameTf = new TextField();
    private TextField SubstrateTf = new TextField();
    private TextField OperatorCommentTf = new TextField();
    private TextField ErrorsTf = new TextField();
    private Button sv = new Button("Save",event -> save());
    private Button up = new Button("Update",event -> update());
    private Button ft = new Button("Filter");
    private Button rstf = new Button("Reset filters");
    private Button cmpr = new Button("Compare processes");
    private Button shw = new Button("Show process");
    private Button rs = new Button("Reset");
    private Button svcond=new Button("Save");
    private TextField FilterProcessName= new TextField();
    private TextField FilterProjectTopic= new TextField();
    private TextField FilterAnalog= new TextField();
    private TextField FilterSubstrate= new TextField();
   // private TextField FilterOperatorComment= new TextField();
   // private TextField FilterErrors= new TextField();
    private TextField Process1ToCompare = new TextField();
    private TextField Process2ToCompare = new TextField();
    private TextArea areaCompare = new TextArea();
    private TextField Temp= new TextField();
    private TextField Press= new TextField();
    private TextField H2= new TextField();
    private TextField N2= new TextField();
    private TextField NH3= new TextField();
    private TextField Mg= new TextField();
    private TextField Si= new TextField();
    private TextField TMG= new TextField();
    private TextField TMI= new TextField();
    private TextField TMA= new TextField();
    private TextField GrowthRate= new TextField();
    private TextField GrowthTime= new TextField();
    private TextField ProcessName= new TextField();
    private TextField LayerName= new TextField();
    private TextField Function= new TextField();
    private ConfigurableFilterDataProvider<GeneralProcess, Void, SerializablePredicate<GeneralProcess>> filterGPDataProvider;


    private void addToLayout(Layout layout, AbstractTextField textField,
                             String caption, String size) {
        textField.setCaption(caption);
//        Label statusMessage = new Label();
//        statusMessage.setVisible(false);
//        statusMessage.addStyleName("validation-message");
//        textField.setData(statusMessage);
        layout.addComponent(textField);
        textField.setWidth(size);
//        horizontalLayout.addComponent(statusMessage);
//        layout.addComponent(horizontalLayout);
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        gridCreate();

        //configure buttons for "compare" section
        rs.addClickListener(event -> {Process1ToCompare.setValue("");
                                  Process2ToCompare.setValue("");
                                  process1=null;
                                  process2=null;
                                  areaCompare.setValue("");});
        cmpr.addClickListener(event-> {

            if (process1!=null&&process2!=null) {

               process1.getStructure().forEach((n)->n.initConditions());
               process2.getStructure().forEach((n)->n.initConditions());
               ProcessComparer pcm=new ProcessComparer();
               areaCompare.setValue(pcm.compare(process1,process2));
               areaCompare.setReadOnly(true);
            }
        });
        shw.addClickListener(event-> {
            if (process1!=null)
            areaCompare.setValue(process1.toString());
        });

        sv.setErrorHandler(e->{});
        grid.setErrorHandler(e->{});

        //horizontal layouts
        HorizontalLayout hl = new HorizontalLayout();
        HorizontalLayout hl2 = new HorizontalLayout();
        HorizontalLayout hl3 = new HorizontalLayout();
        HorizontalLayout hl4 = new HorizontalLayout();
        HorizontalLayout hl5=new HorizontalLayout();

        VerticalLayout vl = new VerticalLayout();
        hl.setHeight("70");
        hl2.setHeight("70");
        hl3.setHeight("70");
        hl4.setHeight("600");

        addToLayout(hl, ProcessNameTf, "Process name", "140");
        binder.forField(ProcessNameTf).asRequired("Process name may not be empty")
                .withValidator((n) -> Pattern.matches("[a-zA-Z][0-9]{2}[0-1][0-9][0-3][0-9][a-zA-Z]",n), "wrong process name")  //todo correct validator
                .bind(GeneralProcess::getName, GeneralProcess::setName);

        addToLayout(hl, ProjectTopicTf, "Project Topic", "140");
        binder.forField(ProjectTopicTf).asRequired("Project Topic may not be empty")
                .withConverter(Topic::valueOf, (n) -> n.toString(), "wrong project topic")
                .bind(GeneralProcess::getProjectTopic, GeneralProcess::setProjectTopic);

        addToLayout(hl, AnalogNameTf, "Analog Name", "140");
        binder.forField(AnalogNameTf)
               .withValidator((n) -> (Pattern.matches("[a-zA-Z][0-9]{2}[0-1][0-9][0-3][0-9][a-zA-Z]",n)|n.equals(null) ), "wrong analog name")
                .bind(GeneralProcess::getAnalogName, GeneralProcess::setAnalogName);

        addToLayout(hl, SubstrateTf, "Substrate", "140");
        binder.forField(SubstrateTf).asRequired("Substrate may not be empty")
                .withConverter(Substrates::valueOf, (n) -> n.toString(), "wrong substrate")
                .bind(GeneralProcess::getSubstrate, GeneralProcess::setSubstrate);

        addToLayout(hl, OperatorCommentTf, "Operator Comment", "160");
        binder.forField(OperatorCommentTf)
                .bind(GeneralProcess::getOperatorComment, GeneralProcess::setOperatorComment);

        addToLayout(hl, ErrorsTf, "Errors", "160");
//        binder.forField(ErrorsTf) //todo решить что делать с ощибками
//                .bind(GeneralProcess::getErrors, GeneralProcess::setProjectTopic);

        hl2.addComponent(sv);
        hl2.addComponent(up);
        hl2.addComponent(ft);
        hl2.addComponent(rstf);
        hl3.setCaption("Comparision of processes");
        hl3.addComponent(Process1ToCompare);
        hl3.addComponent(Process2ToCompare);
        hl3.addComponent(cmpr);
        hl3.addComponent(shw);
        hl3.addComponent(rs);

        areaCompare.setRows(20);
        areaCompare.setWidth("600");
        Process1ToCompare.setPlaceholder("Process 1");
        Process1ToCompare.setDescription("right click on the process name " +
                "to choose the first process");
        Process2ToCompare.setPlaceholder("Process 2");
        Process2ToCompare.setDescription("right click on the process name " +
                "to choose the second process");
        ProcessNameTf.setDescription("Process name must be unique and corresponds to the format in the table");
        ProjectTopicTf.setDescription("List of available topics:\n" +
                "Project_2015\n" +
                "Project_2016\n" +
                "Project_2017\n");
        SubstrateTf.setDescription("List of available substrates:\n" +
                "Sapphire\n" +
                "Si\n" +
                "SiC\n SiC_on_Si\n DP_Sapphire\n Profiled_Sapphire");


        hl4.addComponent(areaCompare);
        grid.setCaption("List of processes");
        addToLayout(hl5,Temp,"Temperature","50");
        addToLayout(hl5,Press,"Pressure","50");
        addToLayout(hl5,H2,"H2","50");
        addToLayout(hl5,N2,"N2","50");
        addToLayout(hl5,NH3,"NH3","50");
        addToLayout(hl5,TMG,"TMG","50");
        addToLayout(hl5,TMI,"TMI","50");
        addToLayout(hl5,TMA,"TMA","50");
        addToLayout(hl5,Si,"Si","50");
        addToLayout(hl5,Mg,"Mg","50");
        addToLayout(hl5,GrowthTime,"Time","50");
        addToLayout(hl5,GrowthRate,"Growth Rate","50");

//        layerBinder.forField(ProcessName)
//                .asRequired("Process name may not be empty")
//                .withValidator((n) -> Pattern.matches("[a-zA-Z][0-9]{2}[0-1][0-9][0-3][0-9][a-zA-Z]",n), "wrong process name")  //todo correct validator
//                .bind(n->n.getGeneralProcess().getName(), n->n.getGeneralProcess()::setName());

        layerBinder.forField(Temp)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getTemperature, GeneralLayer::setTemperature);
        layerBinder.forField(Press)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getPressure, GeneralLayer::setPressure);
        layerBinder.forField(H2)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getH2, GeneralLayer::setH2);
        layerBinder.forField(N2)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getN2, GeneralLayer::setN2);
        layerBinder.forField(NH3)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getNh3, GeneralLayer::setNh3);
        layerBinder.forField(TMG)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getTmg, GeneralLayer::setTmg);
        layerBinder.forField(TMA)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getTma, GeneralLayer::setTma);
        layerBinder.forField(TMI)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getTmi, GeneralLayer::setTmi);
        layerBinder.forField(Si)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getSi, GeneralLayer::setSi);
        layerBinder.forField(Mg)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getMg, GeneralLayer::setMg);
        layerBinder.forField(GrowthRate)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getGrowthRate, GeneralLayer::setGrowthRate);
        layerBinder.forField(GrowthTime)
                .withConverter(Integer::parseInt, (n) -> n.toString(), "Value must be a number")
                .bind(GeneralLayer::getGrowthTime, GeneralLayer::setGrowthTime);





        vl.addComponent(grid);
        vl.addComponent(hl);
        vl.addComponent(hl2);
        vl.addComponent(hl3);
        vl.addComponent(hl4);


        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(vl);
        setContent(layout);

    }

    private void save() {

        try {
            binder.writeBean(process);
            DBHandler handler = new DBHandler();
            handler.send(process);
            service.addProcess(process);
            Notification.show("New process has been added successfully",
                    String.format("Process name '%s', substrate '%s'",
                            process.getName(), process.getSubstrate().toString()),
                    Notification.Type.HUMANIZED_MESSAGE);
            grid.getDataProvider().refreshAll();
            process=new GeneralProcess();
            binder.readBean(null);
        }
        catch (ValidationException e) {
            log.error(e.getStackTrace().toString());
            Notification.show(
                    "process could not be added, please check all fields",
                    Notification.Type.ERROR_MESSAGE);
        }
        catch(PersistenceException e) {
            log.error("exception is thrown: "+e.getClass().getCanonicalName());
            Notification.show(
                    "process could not be added, process name must be unique",
                    Notification.Type.ERROR_MESSAGE);
            process=new GeneralProcess();
            binder.readBean(null);
        }
    }


    private void update() {
        try {
            binder.writeBean(process);
            DBHandler handler = new DBHandler();
            handler.update(process);
            service.updateProcess(process);
            grid.getDataProvider().refreshAll();
            Notification.show("Process has been updated successfully:",
                    String.format("Process name '%s'",
                            process.getName()),Notification.Type.HUMANIZED_MESSAGE);
            process=new GeneralProcess();
            binder.readBean(null);
        }
        catch (ValidationException e) {
            log.error(e.getStackTrace().toString());
            Notification.show(
                    "process could not be updated, please check all fields",
                    Notification.Type.ERROR_MESSAGE);
        }

        catch(HibernateException e) {
            log.error(e.getStackTrace().toString());
            Notification.show(
                    "process could not be updated",
                    Notification.Type.ERROR_MESSAGE);
        }

        catch(PersistenceException e) {
            log.error(e.getStackTrace().toString());
            Notification.show(
                    "process could not be updated",
                    Notification.Type.ERROR_MESSAGE);
        }

    }


    public void gridCreate() {

        //configure grid and add filters
        grid.setSizeFull();
        grid.removeColumn("structure");
        grid.setColumnOrder("name", "projectTopic", "analogName", "substrate", "operatorComment");
        grid.getColumns().forEach(n -> configureColumn(n,140));
        configureColumn(grid.getColumn("operatorComment"), 190);
        configureColumn(grid.getColumn("errors"),190);
        setFilters();

        //set filtered dataprovider
        DataProvider<GeneralProcess,ProcessFilter> provider= DataProvider.fromFilteringCallbacks(query-> {
            ProcessFilter filter = query.getFilter().orElse(null);
            return service.findAll(
                            query.getOffset(),
                            query.getLimit(),
                            filter != null ? filter.namePrefix : null,
                            filter != null ? filter.topicPrefix : null,
                            filter != null ? filter.analogPrefix : null,
                            filter != null ? filter.substratePrefix : null).stream();
            },
                query ->{ ProcessFilter filter=query.getFilter().orElse(null);
                                return service.count(filter != null ? filter.namePrefix : null,
                                        filter != null ? filter.topicPrefix : null,
                                        filter != null ? filter.analogPrefix : null,
                                        filter != null ? filter.substratePrefix : null
                                );
                }
        );

        ConfigurableFilterDataProvider<GeneralProcess,Void,ProcessFilter> wrapper = provider.withConfigurableFilter();
        grid.setDataProvider(wrapper);

        //set filtering event when press the button "filter"
        ft.addClickListener(event -> {
            String name = FilterProcessName.getValue();
            String topic= FilterProjectTopic.getValue();
            String analog=FilterAnalog.getValue();
            String substrate=FilterSubstrate.getValue();
            if (name.trim().isEmpty()) {
                name = null;
            }
            if (topic.trim().isEmpty()) {
                topic = null;
            }
            if (analog.trim().isEmpty()) {
                analog = null;
            }
            if (substrate.trim().isEmpty()) {
                substrate = null;
            }

            ProcessFilter filter = new ProcessFilter(name, topic, analog, substrate);
            wrapper.setFilter(filter);
            grid.getDataProvider().refreshAll();
        });


        //set reset filters when button "reset" is pressed
        rstf.addClickListener(event -> {
            FilterProcessName.setValue("");
            FilterProjectTopic.setValue("");
            FilterAnalog.setValue("");
            FilterSubstrate.setValue("");
            wrapper.setFilter(null);
            grid.getDataProvider().refreshAll();
        });

        //add click event listeners
        grid.addItemClickListener(event -> {    String clickedColumnId= event.getColumn().getId();
            GeneralProcess clickedProcess= event.getItem();

            switch(clickedColumnId) {

                case "name":
                    ProcessNameTf.setValue(clickedProcess.getName());
                    break;
                case "projectTopic":
                    ProjectTopicTf.setValue(clickedProcess.getProjectTopic().toString());
                    break;
                case "analogName":
                    AnalogNameTf.setValue(clickedProcess.getAnalogName());
                    break;
                case "substrate":
                    SubstrateTf.setValue(clickedProcess.getSubstrate().toString());
                    break;
                case "operatorComment":
                    OperatorCommentTf.setValue(clickedProcess.getOperatorComment());
                    break;
                case "errors":
                    break;
                default:
                    break;

            }});

        grid.addContextClickListener(event -> {
            if (Process1ToCompare.isEmpty()) {
                process1= ((Grid.GridContextClickEvent<GeneralProcess>)event).getItem();
                Process1ToCompare.setValue(process1.getName());
            }
            else if ((!Process1ToCompare.isEmpty())&&Process2ToCompare.isEmpty()) {
                process2= ((Grid.GridContextClickEvent<GeneralProcess>)event).getItem();
                Process2ToCompare.setValue(process2.getName());
            }
        });
    }

    public void configureColumn (Grid.Column column, int i) {

        column.setMaximumWidth(i);
        column.setWidth(i);
        column.setMinimumWidth(i);
    }

    public void setFilters(){
        HeaderRow groupingHeader1 = grid.prependHeaderRow();
        setFilter("name",groupingHeader1,FilterProcessName);
        setFilter("projectTopic",groupingHeader1,FilterProjectTopic);
        setFilter("analogName",groupingHeader1,FilterAnalog);
        setFilter("substrate",groupingHeader1,FilterSubstrate);
       // setFilter("operatorComment",groupingHeader1,groupingHeader2,FilterOperatorComment);
       // setFilter("errors",groupingHeader1,groupingHeader2,FilterErrors);
    }

    public void setFilter(String columnId, HeaderRow row1, TextField field) {
        HeaderCell cell =row1.getCell(columnId);
        field.setHeight("35");
        field.setWidth("120");
        //field.setDescription("Filter");
        field.setPlaceholder("Filter");
        cell.setComponent(field);
    }
}