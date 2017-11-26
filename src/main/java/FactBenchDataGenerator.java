import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;
import org.hobbit.core.components.AbstractDataGenerator;
import org.hobbit.core.rabbit.RabbitMQUtils;

import java.io.FileReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Created by DANISH on 11/26/2017.
 */
public class FactBenchDataGenerator extends AbstractDataGenerator {

    /*taskGenerator byte array*/
    private byte[] task;

    private Semaphore generateTasks = new Semaphore(0);


    @Override
    public void init() throws Exception {
        super.init();

        long seedId = getGeneratorId();
        long totalGenerators = getNumberOfGenerators();
    }

    @Override
    protected void generateData() throws Exception {

        List<Statement> statementList = new ArrayList<>();

        Model model = ModelFactory.createDefaultModel();
        model.read(new FileReader("file_name.ttl"), null, "N-TRIPLES");

        // if file is large then you need to break it into chunks
        // sample at: https://github.com/hobbit-project/faceted-benchmark/blob/master/data-generator/src/main/java/org/hobbit/SampleDataGenerator.java
        sendDataToTaskGenerator(modelToBytes(model));
    }

    protected void createStatements(List<Statement> statementList) {

    }

    protected byte[] modelToBytes(Model tripleModel) {
        byte[] data;

        StringWriter stringWriter = new StringWriter();
        tripleModel.write(stringWriter, "TURTLE");

        String dataString = stringWriter.toString();

        return RabbitMQUtils.writeString(dataString);
    }
}
